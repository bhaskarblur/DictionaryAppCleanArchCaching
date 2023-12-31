package com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote

import com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.dto.wordInfoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("v2/entries/en/{word}")
    suspend fun getWordDetails(@Path("word") word: String) : List<wordInfoDTO>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/api/"
    }
}