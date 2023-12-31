package com.bhaskarblur.dictionaryapp.dictionary_feature.domain.repository

import com.bhaskarblur.dictionaryapp.core.utils.Resources
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepo {

    fun getWordInfo(word : String) : Flow<Resources<List<wordInfo>>>
}