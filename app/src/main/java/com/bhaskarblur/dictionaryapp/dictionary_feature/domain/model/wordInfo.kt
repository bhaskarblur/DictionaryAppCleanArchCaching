package com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model

import com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.dto.Phonetic

data class wordInfo(
    val meanings: List<Meaning>,
    val origin: String?,
    val phonetic: String?,
    val word: String?
)