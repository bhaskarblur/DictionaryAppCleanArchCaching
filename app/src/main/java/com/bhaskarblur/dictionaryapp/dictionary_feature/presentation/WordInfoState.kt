package com.bhaskarblur.dictionaryapp.dictionary_feature.presentation

import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo

data class WordInfoState(
    val wordInfoItems : List<wordInfo> = emptyList(),
    val loading : Boolean = false)