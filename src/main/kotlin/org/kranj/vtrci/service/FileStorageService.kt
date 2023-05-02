package org.kranj.vtrci.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@Service
class FileStorageService @Autowired constructor(env: Environment) {
    private val fileStorageLocation: Path

    init {
        fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files"))
            .toAbsolutePath().normalize()
        try {
            Files.createDirectories(fileStorageLocation)
        } catch (ex: Exception) {
            throw RuntimeException(
                "Could not create the directory where the uploaded files will be stored.", ex
            )
        }
    }

    private fun getFileExtension(fileName: String?): String? {
        if (fileName == null) {
            return null
        }
        val fileNameParts = fileName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return fileNameParts[fileNameParts.size - 1]
    }

    fun storeFile(file: MultipartFile): String {
        // Normalize file name
        val fileName: String = Date().time.toString() + "-file." + getFileExtension(file.originalFilename)
        return try {
            // Check if the filename contains invalid characters
            if (fileName.contains("..")) {
                throw RuntimeException(
                    "Sorry! Filename contains invalid path sequence $fileName"
                )
            }
            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        } catch (ex: IOException) {
            throw RuntimeException("Could not store file $fileName. Please try again!", ex)
        }
    }
}