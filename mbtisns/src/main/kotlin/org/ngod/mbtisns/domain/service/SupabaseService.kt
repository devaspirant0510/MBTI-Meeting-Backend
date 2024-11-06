package org.ngod.mbtisns.domain.service

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service

class SupabaseService(
    @Autowired
    private val supabaseClient: SupabaseClient
) {
    suspend fun uploadFileWithSupabaseStorage(fileName:String,bytes:ByteArray):String {


        return withContext(Dispatchers.IO){
            val bucket = supabaseClient.storage.from("image")
            val result = bucket.upload("${Date().time}$fileName",bytes)
            return@withContext "https://diczysxinppyaxbwcxnk.supabase.co/storage/v1/object/public/image/${result.path}"
        }
    }
}