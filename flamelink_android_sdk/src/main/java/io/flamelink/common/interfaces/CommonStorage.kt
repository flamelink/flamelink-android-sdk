package io.flamelink.common.interfaces

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.flamelink.common.types.Image
import java.io.File

interface CommonStorage {

    companion object {
        private val databaseRef = FirebaseDatabase.getInstance().reference
        private val storageRef = FirebaseStorage.getInstance().reference
        val folderStorageRef = databaseRef.child("flamelink").child("media").child("folders")
        val fileStorageRef = databaseRef.child("flamelink").child("media").child("files")
        val urlStorageRef = storageRef
    }

    fun upload(
        file: File,
        metaData: StorageMetaData? = null,
        storageConfig: StorageConfig? = null
    )

    fun upload(
        bytes: ByteArray,
        metaData: StorageMetaData? = null,
        storageConfig: StorageConfig? = null
    )

    fun upload(
        string: String,
        metaData: StorageMetaData? = null,
        storageConfig: StorageConfig? = null
    )

    fun getFolders(callBack: FlamelinkCallBack)

    fun getFiles(callBack: FlamelinkCallBack)

    fun getFile(
        fileId: String,
        callBack: FlamelinkCallBack
    )

    fun getURL(
        fileId: String,
        callBack: FlamelinkCallBack
    )

    fun deleteFile(fileId: String)

    fun getMetadata(fileId: String)

    fun updateMetadata(
        fileId: String,
        storageMetaData: StorageMetaData? = null
    )

    fun ref(
        fileName: String,
        storageMetaData: StorageMetaData? = null
    )

    fun ref(filePath: String)
}

enum class MediaType(key: String) { FILE("files"), IMAGE("images") }

enum class Structure { NESTED, TREE }

enum class Encoding(key: String) {
    BASE64("base64"),
    BASE64_URL("base64url"),
    DATA_URL("data_url")
}

data class StorageMetaData(
    var bucket: String,
    var generation: String,
    var metageneration: String,
    var fullPath: String,
    var name: String,
    var size: Int,
    var timeCreated: String,
    var updated: String,
    var md5Hash: String,
    var cacheControl: String,
    var contentDisposition: String,
    var contentEncoding: String,
    var contentLanguage: String,
    var contentType: String,
    var customMetadata: String
)

data class StorageConfig(
    var folderId: String?,
    var folderName: String?,
    var mediaType: MediaType?,
    var sizes: List<Image>?,
    var encoding: Encoding?,
    var overwriteSizes: Boolean = false
)