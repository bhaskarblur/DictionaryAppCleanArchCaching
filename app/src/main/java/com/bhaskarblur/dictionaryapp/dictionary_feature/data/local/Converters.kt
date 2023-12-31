package com.bhaskarblur.dictionaryapp.dictionary_feature.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.util.JsonParser
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningsJson(json: String) : List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(json,
            object : TypeToken<ArrayList<Meaning>>(){}.type)
    }

    @TypeConverter
    fun toMeaningsJson(meaning: List<Meaning>) : String {
        return jsonParser.toJson(meaning,
            object : TypeToken<ArrayList<Meaning>>(){}.type)
            ?: "[]"
    }
}