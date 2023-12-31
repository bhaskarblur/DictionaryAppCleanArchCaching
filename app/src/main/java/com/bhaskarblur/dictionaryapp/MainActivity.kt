package com.bhaskarblur.dictionaryapp

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhaskarblur.dictionaryapp.dictionary_feature.presentation.WordInfoItem
import com.bhaskarblur.dictionaryapp.dictionary_feature.presentation.WordInfoViewModel
import com.bhaskarblur.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                val viewModel by viewModels<WordInfoViewModel>()
                val state = viewModel.state.value
                val scaffoldState = remember { SnackbarHostState() }
                
                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is WordInfoViewModel.UIEvents.ShowSnackbar -> {
                                scaffoldState.showSnackbar(message = event.message)
                            }
                        }
                    }
                }
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = scaffoldState) }) { it
                    Box(
                        Modifier.background(MaterialTheme.colorScheme.background)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {
                            TextField(value = viewModel.searchQuery.value
                                , onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text("Search...")
                                })

                            Spacer(Modifier.height(16.dp))

                            if(viewModel.state.value.loading) {
                                Column(Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally) {


                                    CircularProgressIndicator(
                                        modifier = Modifier.then(Modifier.size(42.dp))
                                    )
                                }
                            }
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(state.wordInfoItems.size) { i ->
                                    val wordInfo = state.wordInfoItems[i]
                                    if(i > 0) {
                                        Spacer(Modifier.height(8.dp))
                                    }
                                    WordInfoItem(wordInfo = wordInfo, modifier = Modifier)
                                    if(i < state.wordInfoItems.size - 1 ) {
                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}