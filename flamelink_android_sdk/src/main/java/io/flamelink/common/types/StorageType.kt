package io.flamelink.common.types

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FolderStorageType(
    var id: Long = 0,
    var name: String = "",
    var order: Int? = 0,
    var parentId: Long = 0
)

@IgnoreExtraProperties
data class FileStorageType(
    var contentType: String = "",
    var file: String = "",
    var fileRef: FileRefType? = FileRefType(),
    var folderId: Long = 0,
    var id: Long = 0,
    var type: String = "",
    var sizes: List<FileSizesType> = emptyList()
)

@IgnoreExtraProperties
data class FileSizesType(
    var width: Long = 0,
    var height: Long = 0,
    var quality: Long = 0,
    var path: String = ""
)

@IgnoreExtraProperties
data class FileRefType(
    var preview: String = ""
)