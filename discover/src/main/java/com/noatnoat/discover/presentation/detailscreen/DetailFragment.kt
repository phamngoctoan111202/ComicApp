package com.noatnoat.discover.presentation.detailscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.noatnoat.base.presentation.activity.BaseFragment
import com.noatnoat.base.presentation.compose.composable.ImageCard
import com.noatnoat.base.presentation.compose.composable.ImageFromUrl

import com.noatnoat.comicapp.discover.R
import com.noatnoat.discover.data.datasource.api.service.ComicRetrofitService
import com.noatnoat.discover.data.repository.ComicRepositoryImpl
import com.noatnoat.discover.domain.repository.ComicRepository
import com.noatnoat.discover.domain.usecase.GetDetailComicUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.navigation.koinNavGraphViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailFragment : BaseFragment() {
    private val viewModel: DetailViewModel by koinNavGraphViewModel(R.id.feature_discover_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args: DetailFragmentArgs by navArgs()
        viewModel.fetchChapterIds(args)

        return ComposeView(requireContext()).apply {
            setContent {
                ChapterScreen(viewModel)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterScreen(viewModel: DetailViewModel) {
    val chapterImg by viewModel.chapterlinks.observeAsState()
    val chapterIds by viewModel.chapterIds.observeAsState()
    val chapterCurrent by viewModel.chapterCurrent.observeAsState()
    var selectedChapter by remember { mutableStateOf(0) }
    chapterCurrent?.let { viewModel.fetchChapterImg(it) }

    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    val chapters = chapterIds?.mapIndexed { index, _ -> (index + 1).toString() } ?: listOf()
    chapterImg?.let { imgLinks ->
        Box {
            LazyColumn {
                stickyHeader {
                    Row(modifier = Modifier.fillMaxWidth()
                        .background(color = Color.Black.copy(alpha = 0.5f)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = {
                            if (selectedChapter > 0) {
                                selectedChapter--
                                viewModel.chapterCurrent.value = chapterIds?.get(selectedChapter)
                            } else {
                                Toast.makeText(context, "This is the first chapter", Toast.LENGTH_SHORT).show()
                            }
                        },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
                        }
                        IconButton(onClick = { expanded = true },  modifier = Modifier.weight(1f)) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color.White),

                            ) {
                            chapters.forEachIndexed { index, chapter ->
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.chapterCurrent.value = chapterIds?.get(index)
                                        expanded = false
                                        selectedChapter = index
                                    },
                                    text = {
                                        if (selectedChapter == index) {
                                            Text(text = "Chap $chapter", color = Color(255, 165, 0))
                                        } else {
                                            Text(text = "Chap $chapter")
                                        }
                                    }
                                )
                            }
                        }
                        IconButton(onClick = {
                            if (selectedChapter < (chapterIds?.size ?: 0) - 1) {
                                selectedChapter++
                                viewModel.chapterCurrent.value = chapterIds?.get(selectedChapter)
                            } else {
                                Toast.makeText(context, "This is the last chapter", Toast.LENGTH_SHORT).show()
                            }
                        },  modifier = Modifier.weight(1f)) {
                            Icon(Icons.Filled.ArrowForward, contentDescription = "", tint = Color.White)
                        }
                    }
                }

                items(imgLinks) { imgLink ->
                    ImageFromUrl(imageUrl = imgLink)
                }
            }
            if (expanded) {
                Box(modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.5f)))
            }
        }
    }
}
