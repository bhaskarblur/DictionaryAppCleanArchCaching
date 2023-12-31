package com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.dto

import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.Definition
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning() : Meaning {
        return Meaning(definitions.map { it.toDefinition() }, partOfSpeech)
    }
}