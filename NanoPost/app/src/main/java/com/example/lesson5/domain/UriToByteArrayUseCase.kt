package com.example.lesson5.domain

import android.content.ContentResolver
import android.net.Uri
import java.lang.IllegalStateException
import javax.inject.Inject

class UriToByteArrayUseCase @Inject constructor(

    private val resolver: ContentResolver
) {

    operator fun invoke(listOfUri: List<Uri>?): List<ByteArray> {
        return listOfUri.orEmpty().map {
            resolver.openInputStream(it)?.readBytes() ?: throw IllegalStateException("ByteArray is null")
        }
    }
}