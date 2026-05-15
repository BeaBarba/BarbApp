package com.example.myapplication.ui.utilities

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

object ImageStorageManager {

    fun getImagesDirectory(ctx: Context): File {
        return File(ctx.filesDir, "images").apply {
            if (!exists()) mkdirs()
        }
    }

    fun saveImageToInternalStorage(ctx: Context, uri: Uri): String? {
        return try {
            val mediaDir = getImagesDirectory(ctx)
            val fileName = "Img_${UUID.randomUUID()}.jpg"
            val targetFile = File(mediaDir, fileName)

            ctx.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(targetFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            fileName
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(ctx, "Impossibile salvare la foto", Toast.LENGTH_SHORT).show()
            null
        }
    }

    fun createTempImageUri(ctx : Context): Uri {
        val tempDir = ImageStorageManager.getImagesDirectory(ctx)
        val tempFile = File.createTempFile("temp_camera_", ".jpg", tempDir)
        return FileProvider.getUriForFile(
            ctx,
            "${ctx.packageName}.fileprovider",
            tempFile
        )
    }

    fun deleteImage(ctx: Context, fileName: String) {
        try {
            val file = File(getImagesDirectory(ctx), fileName)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(ctx, "Impossibile eliminare la foto $fileName", Toast.LENGTH_SHORT).show()
        }
    }
}