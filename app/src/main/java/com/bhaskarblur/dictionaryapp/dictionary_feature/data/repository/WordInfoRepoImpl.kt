package com.bhaskarblur.dictionaryapp.dictionary_feature.data.repository

import android.net.http.HttpException
import com.bhaskarblur.dictionaryapp.core.utils.Resources
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.dao.WordInfoDao
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.DictionaryApi
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.repository.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordInfoRepoImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepo{

    override fun getWordInfo(word: String) : Flow<Resources<List<wordInfo>>> = flow {
        emit(Resources.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resources.Loading(data = wordInfos))

        try {
            val remoteWordInfo = api.getWordDetails(word = word)
            dao.deleteWordInfos(remoteWordInfo.map { it.word })
            dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })
        }
        catch (e: retrofit2.HttpException) {
            emit(Resources.Error(data = wordInfos, message = "Something went wrong!"))
            e.printStackTrace()
        }
        catch (e : IOException) {
            emit(Resources.Error(data = wordInfos, message = "Couldn't reach server. Check your internet."))
            e.printStackTrace()
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resources.Success(newWordInfos))
    }
}