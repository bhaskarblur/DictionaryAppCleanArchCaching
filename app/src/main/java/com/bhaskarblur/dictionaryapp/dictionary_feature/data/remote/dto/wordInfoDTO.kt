package com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.dto

import com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.entity.WordInfoEntity
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo

data class wordInfoDTO(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
) {

    fun toWordInfo() : wordInfo {
        return wordInfo(meanings.map { it.toMeaning() }, origin, phonetic, word)
    }

    fun toWordInfoEntity() : WordInfoEntity {
        return WordInfoEntity(
            word = word,
            phonetic = phonetic,
            origin = origin,
            meanings = meanings.map { it.toMeaning() })
    }
}