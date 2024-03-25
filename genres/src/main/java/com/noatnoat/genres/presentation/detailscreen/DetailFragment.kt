package com.noatnoat.genres.presentation.detailscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.noatnoat.base.common.NavigationSignal
import com.noatnoat.base.presentation.activity.BaseFragment
import com.noatnoat.base.presentation.compose.composable.ImageCard
import com.noatnoat.base.presentation.compose.composable.ImageFromUrl

import com.noatnoat.comicapp.genres.R
import com.noatnoat.genres.data.datasource.api.service.ComicRetrofitService
import com.noatnoat.genres.data.repository.ComicRepositoryImpl
import com.noatnoat.genres.domain.repository.ComicRepository
import com.noatnoat.genres.domain.usecase.GetDetailComicUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import org.koin.androidx.navigation.koinNavGraphViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DetailFragment : BaseFragment() {
    private val viewModel: DetailViewModel by koinNavGraphViewModel(R.id.feature_genres_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val client = OkHttpClient().newBuilder()
            .build()
        val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "grant_type=password&username=20021452&password=IOB300621&client_id=personal-client-2584a6c8-62b8-4bb7-bf3d-b94b31079f37-9d6a1eb5&client_secret=ccU7RLs7ze9kmSayQAicBvVE9MHgoatq")
        val request = Request.Builder()
            .url("https://auth.mangadex.org/realms/mangadex/protocol/openid-connect/token")
            .method("POST", body)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()

        var accessToken: String? = null

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val json = JSONObject(responseBody)
                accessToken = json.getString("access_token")
                val client2 = OkHttpClient()
                val mangaId = "6cc85df7-e44b-4fa1-97f2-be04430cc570"
                val status = "reading" // Trạng thái bạn muốn đặt

                val requestBody = FormBody.Builder()
                    .add("status", status)
                    .build()

                val request2 = Request.Builder()
                    .url("https://api.mangadex.org/manga/$mangaId/status")
                    .post(requestBody)
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()

                client2.newCall(request2).execute().use { response2 ->
                    if (!response2.isSuccessful) {
                        Log.d("bcd", "onResponse: $accessToken")
                        throw IOException("Unexpected code $response2")
                    } else {
                        val responseBody2 = response2.body?.string()
                        println(responseBody2)
                        Log.d("bcd", "onResponse: $responseBody2")
                    }
                }
            }
        })



// Sau khi có access token, bạn có thể sử dụng nó trong lần gọi request thứ hai
//




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
                    Row(modifier = Modifier
                        .fillMaxWidth()
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
