package com.bhaskarblur.dictionaryapp.dictionary_feature.domain.usecase

import com.bhaskarblur.dictionaryapp.core.utils.Resources
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.repository.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository : WordInfoRepo
) {

    operator fun invoke(word: String) : Flow<Resources<List<wordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }

}