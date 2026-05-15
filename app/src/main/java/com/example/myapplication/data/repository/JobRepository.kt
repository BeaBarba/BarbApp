package com.example.myapplication.data.repository

import androidx.room.withTransaction
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.FutureJobMaterial
import com.example.myapplication.data.database.Image
import com.example.myapplication.data.database.Job
import com.example.myapplication.data.database.JobAssignmentDetails
import com.example.myapplication.data.database.JobFullDetails
import com.example.myapplication.data.database.JobMaterialFullDetails
import com.example.myapplication.data.database.MaterialUsage
import com.example.myapplication.data.database.PropertyOwnership
import com.example.myapplication.data.database.Reference
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.database.WorkSiteAssignmentDetails
import com.example.myapplication.data.database.WorkSiteFullDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDate

class JobRepository( private val db : AppDatabase ){

    /* Image */
    val images = db.imageDAO().getAllImages()

    fun getImageById(id: Int): Flow<Image?> = db.imageDAO().getImage(id)

    suspend fun upsertImage(image: Image) = db.imageDAO().upsertImage(image)

    suspend fun deleteImage(image: Image) = db.imageDAO().deleteImage(image)

    /* WorkSite */
    val workSites = db.workSiteDAO().getAllWorkSites()

    fun getWorkSiteById(id: Int): Flow<WorkSite?> = db.workSiteDAO().getWorkSite(id)

    fun getFlowWorkSiteFullDetailsById(id: Int): Flow<WorkSiteFullDetails?> =
        db.workSiteDAO().getFlowWorkSiteFullDetails(id)

    fun getFlowWorkSiteAssignmentDetailsById(id: Int): Flow<WorkSiteAssignmentDetails?> =
        db.workSiteDAO().getFlowWorkSiteAssignmentDetails(id)

    suspend fun getWorkSiteFullDetailsById(id: Int): WorkSiteFullDetails? =
        db.workSiteDAO().getWorkSiteFullDetails(id)

    fun getFlowAllWorkSiteFullDetails(): Flow<List<WorkSiteFullDetails>> = db.workSiteDAO().getFlowAllWorkSitesFullDetails()

    suspend fun getAllWorkSiteFullDetails(): List<WorkSiteFullDetails> = withContext(Dispatchers.IO) {
        db.workSiteDAO().getAllWorkSitesFullDetails()
    }

    fun getFlowAllWorksitesAssignmentDetails() : Flow<List<WorkSiteAssignmentDetails>> = db.workSiteDAO().getAllWorkSitesAssignmentDetails()

    suspend fun upsertWorkSite(workSite: WorkSite) : Long = db.workSiteDAO().upsertWorkSite(workSite)

    private suspend fun deleteWorkSite(workSite: WorkSite) = db.workSiteDAO().deleteWorkSite(workSite)

    suspend fun deleteWorkSiteComplete(worksiteId : Int) = withContext(Dispatchers.IO){
        db.withTransaction {
            val worksite = getWorkSiteFullDetailsById(worksiteId)

            worksite?.let { details ->
                details.jobs.forEach { job ->
                    upsertJob(job.copy(workSite = null))
                }

                details.revenues?.forEach { revenue ->
                    db.revenuesDAO().deleteRevenue(revenue)
                }

                deleteWorkSite(details.workSiteAssignment.workSite)
            }
        }
    }

    suspend fun saveWorkSiteComplete(
        worksite : WorkSite,
        reference: Reference?
    ) : Int = withContext(Dispatchers.IO){
        db.withTransaction {
            val referenceId =
                if(reference != null){
                    db.referenceDAO().upsertReference(reference).toInt()
                }else{
                    worksite.manager
                }

            val workSiteId = upsertWorkSite(worksite.copy(manager = referenceId)).toInt()
            val finaleWorkSiteId = if(workSiteId != -1) workSiteId else worksite.id

            return@withTransaction finaleWorkSiteId
        }
    }

    /* Job */
    val jobs = db.jobDAO().getFlowAllJobs()

    fun getFlowJobById(id: Int): Flow<Job?> = db.jobDAO().getFlowJob(id)

    suspend fun upsertJob(job: Job) : Long = db.jobDAO().upsertJob(job)

    suspend fun deleteJob(job: Job) =
        db.withTransaction {
            val materialsRestock = getAllFutureMaterialsByJobId(job.id)

            materialsRestock.forEach{ item ->
               db.materialDAO().offsetMaterialAvailableQuantity(item.material, item.quantity)
            }

            db.jobDAO().deleteJob(job)
        }

    suspend fun getJobMaterialFullDetailsById(id : Int) : JobMaterialFullDetails? = withContext(Dispatchers.IO){
        db.jobDAO().getFlowJobMaterialFullDetails(id).first()
    }

    suspend fun saveJobComplete( job : Job, materials : List<Pair<Int, Float>> ) : Int =
        db.withTransaction {
            val newJobId = db.jobDAO().upsertJob(job).toInt()
            val jobId = if (newJobId > 0) newJobId else job.id

            job.customer?.let { customerId ->
                val customer = db.customerDAO().getCustomer(customerId)
                val jobAddress = job.address

                if (customer != null && jobAddress != 0) {
                    val residence = customer.residence

                    if (residence != jobAddress) {
                        val existsPropertyOwnership = db.propertyOwnershipDAO().existsPropertyOwnership(
                            cf =
                            customerId, address = jobAddress
                        )

                        if (existsPropertyOwnership == 0) {
                            db.propertyOwnershipDAO().upsertPropertyOwnership(
                                PropertyOwnership(
                                    customer = customerId,
                                    address = jobAddress
                                )
                            )
                        }
                    }
                }
            }

            val isFuture = job.date.isAfter(LocalDate.now())

            deleteFutureJobMaterialByJobId(jobId)
            deleteMaterialUsageByJobId(jobId)

            if (isFuture) {
                val futureItems = materials.map { (matId, quantity) ->
                    FutureJobMaterial(
                        material = matId,
                        job = jobId,
                        quantity = quantity
                    )
                }

                insertFutureMaterialsList(futureItems)

            } else {
                val usageItems = materials.map { (matId, quantity) ->
                    MaterialUsage(
                        material = matId,
                        job = jobId,
                        quantity = quantity
                    )
                }

                insertMaterialUsageList(usageItems)
            }
            jobId
        }

    fun getFlowJobDoneSummaryById(id: Int): Flow<JobMaterialFullDetails?> =
        db.jobDAO().getFlowJobMaterialFullDetails(id)

    fun getFlowJobAssignmentDetails(id: Int): Flow<JobAssignmentDetails?> =
        db.jobDAO().getFlowJobAssignmentDetails(id)

    fun getFlowAllJobsAssignmentDetails(): Flow<List<JobAssignmentDetails>> =
        db.jobDAO().getFlowAllJobsAssignmentDetails()

    fun getFlowJobFullDetails(id: Int): Flow<JobFullDetails?> = db.jobDAO().getFlowJobFullDetails(id)

    fun getFlowAllJobsFullDetails(): Flow<List<JobFullDetails>> = db.jobDAO().getFlowAllJobsFullDetails()

    fun getFlowAllToScheduleJobsAssignmentDetailsByDate(date : LocalDate) : Flow<List<JobAssignmentDetails>> =
        db.jobDAO().getFlowAllToScheduleJobsAssignmentDetails(date)

    fun getFlowAllTodayJobsFullDetailsByDate(date : LocalDate) : Flow<List<JobFullDetails>> =
        db.jobDAO().getFlowAllTodayJobsFullDetails(date)

    /* FutureJobMaterial */
    val futureJobMaterials = db.futureJobMaterialDAO().getAllFutureJobMaterials()

    fun getFlowFutureJobMaterialById(material: Int, job: Int): Flow<FutureJobMaterial?> =
        db.futureJobMaterialDAO().getFutureJobMaterial(material, job)

    private suspend fun getAllFutureMaterialsByJobId(job : Int) : List<FutureJobMaterial> =
        db.futureJobMaterialDAO().getAllFutureMaterialsByJob(job)

    private suspend fun insertFutureMaterialsList (materials : List<FutureJobMaterial>) =
        db.futureJobMaterialDAO().insertFutureMaterials(materials)

    suspend fun upsertFutureJobMaterial(futureJobMaterial: FutureJobMaterial) =
        db.futureJobMaterialDAO().upsertFutureJobMaterial(futureJobMaterial)

    suspend fun deleteFutureJobMaterial(futureJobMaterial: FutureJobMaterial) =
        db.futureJobMaterialDAO().deleteFutureJobMaterial(futureJobMaterial)

    private suspend fun deleteFutureJobMaterialByJobId(jobId : Int) =
        db.futureJobMaterialDAO().deleteFutureJobMaterialByJob(jobId)

    /* MaterialUsage */
    val materialsUsage = db.materialUsageDAO().getFlowAllMaterialsUsage()

    fun getFlowMaterialUsageById(material: Int, job: Int): Flow<MaterialUsage?> =
        db.materialUsageDAO().getFlowMaterialUsage(material, job)

    private suspend fun insertMaterialUsageList (materials : List<MaterialUsage>) =
        db.materialUsageDAO().insertMaterialUsage(materials)

    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().upsertMaterialUsage(materialUsage)

    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().deleteMaterialUsage(materialUsage)

    private suspend fun deleteMaterialUsageByJobId(jobId : Int) =
        db.materialUsageDAO().deleteMaterialUsageByJob(jobId)
}