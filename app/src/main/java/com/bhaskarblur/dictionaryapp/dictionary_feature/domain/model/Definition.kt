package com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model

data class Definition(
    val antonyms: List<Any>,
    val definition: String?,
    val example: String?,
    val synonyms: List<Any>
) {

}
