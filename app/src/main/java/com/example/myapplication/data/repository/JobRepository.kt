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
import kotlinx.coroutines.flow.Flow
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

    suspend fun getAllWorkSiteFullDetails(): List<WorkSiteFullDetails> =
        db.workSiteDAO().getAllWorkSitesFullDetails()


    /* Job */
    val jobs = db.jobDAO().getAllJobs()

    fun getJobById(id: Int): Flow<Job?> = db.jobDAO().getJob(id)

    suspend fun upsertJob(job: Job) : Long = db.jobDAO().upsertJob(job)

    suspend fun deleteJob(job: Job) =
        db.withTransaction {
            val materialsRestock = getAllFutureMaterialsByJobId(job.id)

            materialsRestock.forEach{ item ->
               inventory.incMaterialAvailableQuantity(item.material, item.quantity)
            }

            db.jobDAO().deleteJob(job)
            println("DEBUG: repository - job eliminato")
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
    val futureJobMaterials = db.jobMaterialDAO().getAllFutureJobMaterials()

    fun getFutureJobMaterialById(material: Int, job: Int): Flow<FutureJobMaterial?> =
        db.jobMaterialDAO().getFutureJobMaterial(material, job)

    private fun getAllFutureMaterialsByJobId(job : Int) : List<FutureJobMaterial> = db.jobMaterialDAO().getAllFutureMaterialsByJob(job)

    suspend fun upsertFutureJobMaterial(futureJobMaterial: FutureJobMaterial) =
        db.jobMaterialDAO().upsertFutureJobMaterial(futureJobMaterial)

    suspend fun deleteFutureJobMaterial(futureJobMaterial: FutureJobMaterial) =
        db.jobMaterialDAO().deleteFutureJobMaterial(futureJobMaterial)

    /* MaterialUsage */
    val materialsUsage = db.materialUsageDAO().getAllMaterialsUsage()

    fun getMaterialUsageById(material: Int, job: Int): Flow<MaterialUsage?> =
        db.materialUsageDAO().getMaterialUsage(material, job)

    suspend fun upsertMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().upsertMaterialUsage(materialUsage)

    suspend fun deleteMaterialUsage(materialUsage: MaterialUsage) =
        db.materialUsageDAO().deleteMaterialUsage(materialUsage)
}