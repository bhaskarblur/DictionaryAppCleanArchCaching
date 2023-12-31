package com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.dto.Phonetic
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.Meaning
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo
import java.util.UUID
import javax.annotation.Nullable

@Entity
data class WordInfoEntity(
    @PrimaryKey val id : Int? = null,
    val word : String,
    val phonetic: String? = "",
    val origin: String? = "",
    val meanings: List<Meaning>
) {
    fun toWordInfo() : wordInfo {
        return wordInfo(
            meanings = meanings,
            word = word,
            phonetic = phonetic?: "",
            origin = origin?: ""
        )
    }
}
