package io.flamelink.firebase.storage

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.flamelink.common.interfaces.*
import io.flamelink.common.types.FileStorageType
import io.flamelink.common.types.FolderStorageType
import io.flamelink.common.types.Image
import java.io.File

class StorageSettings : CommonStorage {
    override fun upload(file: File, metaData: StorageMetaData?, storageConfig: StorageConfig?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun upload(bytes: ByteArray, metaData: StorageMetaData?, storageConfig: StorageConfig?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun upload(string: String, metaData: StorageMetaData?, storageConfig: StorageConfig?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFolders(callBack: FlamelinkCallBack) {
        val folderRefVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncListResponse(dataSnapshot.children.map {
                    it.getValue(FolderStorageType::class.java)
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonStorage.Companion.folderStorageRef.addListenerForSingleValueEvent(folderRefVEL)
    }

    override fun getFiles(callBack: FlamelinkCallBack) {
        val fileRefVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncListResponse(dataSnapshot.children.map {
                    it.getValue(FileStorageType::class.java)
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonStorage.Companion.fileStorageRef.addListenerForSingleValueEvent(fileRefVEL)
    }

    override fun getFile(fileId: String, callBack: FlamelinkCallBack) {
        val fileRefVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.map {
                    callBack.onAsyncResponse(it.getValue(FileStorageType::class.java))
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonStorage.Companion.fileStorageRef.orderByKey().equalTo(fileId).addListenerForSingleValueEvent(fileRefVEL)
    }

    override fun getURL(fileId: String, callBack: FlamelinkCallBack) {
        getFile(fileId, object : FlamelinkCallBack {
            override fun <T, E> onAsyncMapResponse(response: Map<E, T>) {
                println(response)
            }

            override fun <T> onAsyncListResponse(response: List<T>) {
                println(response)
            }

            override fun <T> onAsyncResponse(response: T) {
                val file = response as FileStorageType
                CommonStorage.Companion.urlStorageRef.child("flamelink/media/${file.file}").downloadUrl.addOnSuccessListener {
                    callBack.onAsyncResponse(it)
                }.addOnFailureListener {
                    println(it)
                }
            }
        })
    }

    override fun deleteFile(fileId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMetadata(fileId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateMetadata(fileId: String, storageMetaData: StorageMetaData?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ref(fileName: String, storageMetaData: StorageMetaData?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ref(filePath: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}