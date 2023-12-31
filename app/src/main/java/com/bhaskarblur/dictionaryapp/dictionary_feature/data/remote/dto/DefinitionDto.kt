package com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.dto

import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.Definition

data class DefinitionDto (
    val antonyms: List<Any>,
    val definition: String?,
    val example: String?,
    val synonyms: List<Any>
) {
    fun toDefinition() : Definition {
        return Definition(
            antonyms,
            definition,
            example,
            synonyms)
    }
}