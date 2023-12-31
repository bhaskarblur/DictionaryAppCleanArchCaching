package com.bhaskarblur.dictionaryapp.dictionary_feature.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.model.wordInfo

@Composable
fun WordInfoItem(
    wordInfo: wordInfo,
    modifier: Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = wordInfo.word!!,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(wordInfo.phonetic?: "", fontWeight = FontWeight.Light)
        Spacer(Modifier.height(8.dp))
        Text(wordInfo.origin?: "")

        wordInfo.meanings.forEach{meaning ->
            Text(meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed {index, def ->
                Text(text = "${index + 1 } ${def.definition }")
                Spacer(modifier = Modifier.height(8.dp))
                def.example?.let {
                    Text(def.example)
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}