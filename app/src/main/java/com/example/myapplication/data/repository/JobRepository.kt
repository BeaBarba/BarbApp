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
import com.example.myapplication.data.database.WorkSite
import com.example.myapplication.data.database.WorkSiteFullDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDate

class JobRepository(
    private val db : AppDatabase,
    private val inventory: InventoryRepository
){

    /* Image */
    val images = db.imageDAO().getAllImages()

    fun getImageById(id: Int): Flow<Image?> = db.imageDAO().getImage(id)

    suspend fun upsertImage(image: Image) = db.imageDAO().upsertImage(image)

    suspend fun deleteImage(image: Image) = db.imageDAO().deleteImage(image)

    /* WorkSite */
    val workSites = db.workSiteDAO().getAllWorkSites()

    fun getWorkSiteById(id: Int): Flow<WorkSite?> = db.workSiteDAO().getWorkSite(id)

    suspend fun upsertWorkSite(workSite: WorkSite) = db.workSiteDAO().upsertWorkSite(workSite)

    suspend fun deleteWorkSite(workSite: WorkSite) = db.workSiteDAO().deleteWorkSite(workSite)

    fun getWorkSiteFullDetailsById(id: Int): Flow<WorkSiteFullDetails?> =
        db.workSiteDAO().getWorkSiteFullDetails(id)

    suspend fun getFlowAllWorkSiteFullDetails(): Flow<List<WorkSiteFullDetails>> = withContext(Dispatchers.IO) {
        db.workSiteDAO().getFlowAllWorkSitesFullDetails()
    }

    suspend fun getAllWorkSiteFullDetails(): List<WorkSiteFullDetails> = withContext(Dispatchers.IO) {
        db.workSiteDAO().getAllWorkSitesFullDetails()
    }


    /* Job */
    val jobs = db.jobDAO().getAllJobs()

    fun getJobById(id: Int): Flow<Job?> = db.jobDAO().getJob(id)

    suspend fun upsertJob(job: Job) : Long = db.jobDAO().upsertJob(job)

    suspend fun deleteJob(job: Job) =
        db.withTransaction {
            val materialsRestock = getAllFutureMaterialsByJobId(job.id)

            materialsRestock.forEach{ item ->
               inventory.offsetMaterialAvailableQuantity(item.material, item.quantity)
            }

            db.jobDAO().deleteJob(job)
        }

    suspend fun getJobMaterialFullDetailsById(id : Int) : JobMaterialFullDetails? = withContext(Dispatchers.IO){
        db.jobDAO().getJobMaterialFullDetails(id).first()
    }

    suspend fun saveJobComplete(
        job : Job,
        materials : List<Pair<Int, Float>>
    ) : Int =
        db.withTransaction {
            val newJobId = db.jobDAO().upsertJob(job).toInt()
            val jobId = if(newJobId > 0) newJobId else job.id

            val isFuture = job.date.isAfter(LocalDate.now())

            deleteFutureJobMaterialByJobId(jobId)
            deleteMaterialUsageByJobId(jobId)

            if(isFuture){
                val futureItems = materials.map{ (matId, quantity) ->
                    FutureJobMaterial(
                        material = matId,
                        job = jobId,
                        quantity = quantity
                    )
                }

                insertFutureMaterialsList(futureItems)

            }else{
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


    fun getJobDoneSummaryById(id: Int): Flow<JobMaterialFullDetails?> =
        db.jobDAO().getJobMaterialFullDetails(id)

    fun getJobAssignmentDetails(id: Int): Flow<JobAssignmentDetails?> =
        db.jobDAO().getJobAssignmentDetails(id)

    fun getAllJobsAssignmentDetails(): Flow<List<JobAssignmentDetails>> =
        db.jobDAO().getAllJobsAssignmentDetails()

    fun getJobFullDetails(id: Int): Flow<JobFullDetails?> = db.jobDAO().getJobFullDetails(id)

    fun getAllJobsFullDetails(): Flow<List<JobFullDetails>> = db.jobDAO().getAllJobsFullDetails()

    fun getAllToScheduleJobsAssignmentDetailsByDate(date : LocalDate) : Flow<List<JobAssignmentDetails>> =
        db.jobDAO().getAllToScheduleJobsAssignmentDetails(date)

    fun getAllTodayJobsFullDetailsByDate(date : LocalDate) : Flow<List<JobFullDetails>> =
        db.jobDAO().getAllTodayJobsFullDetails(date)

    /* FutureJobMaterial */
    val futureJobMaterials = db.futureJobMaterialDAO().getAllFutureJobMaterials()

    fun getFutureJobMaterialById(material: Int, job: Int): Flow<FutureJobMaterial?> =
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
        db.futureJobMaterialDAO().deleteFutureJobMaterialByJob(jobId)/**/

    /* MaterialUsage */
    val materialsUsage = db.materialUsageDAO().getAllMaterialsUsage()

    fun getMaterialUsageById(material: Int, job: Int): Flow<MaterialUsage?> =
        db.materialUsageDAO().getMaterialUsage(material, job)

    private suspend fun insertMaterialUsageList (materials : List<MaterialUsage>) =
        db.materialUsageDAO().insertMaterialUsage(materials)

    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().upsertMaterialUsage(materialUsage)

    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().deleteMaterialUsage(materialUsage)

    private suspend fun deleteMaterialUsageByJobId(jobId : Int) =
        db.materialUsageDAO().deleteMaterialUsageByJob(jobId)
}