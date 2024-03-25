package com.noatnoat.genres.presentation.genresscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.noatnoat.base.presentation.activity.BaseFragment
import com.noatnoat.base.presentation.compose.composable.ImageCard
import com.noatnoat.comicapp.genres.R
import com.noatnoat.genres.domain.model.search_model.DataDomain
import com.noatnoat.genres.domain.model.search_model.MangaData
import org.koin.androidx.navigation.koinNavGraphViewModel


class GenresFragment : BaseFragment() {
    private val viewModel: GenresViewModel by koinNavGraphViewModel(R.id.feature_genres_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                MyApp(viewModel)
            }

        }
    }



}



@Composable
internal fun GenresScreen(viewModel: GenresViewModel) {
    val mangaData by viewModel.MangaData.observeAsState()
    val page = remember { mutableStateOf(1) }
    Column {
        mangaData?.let { data ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(data.data.size) { index ->
                    val item = data.data[index]
                    ImageCard(viewModel.getTitleInEnglish(item), viewModel.createCoverUrl(item), viewModel, item?.id)
                }

                item {
                    Button(
                        onClick = {
                            page.value--
                            viewModel.offset.value = page.value * 21
                            viewModel.loadMangaList()
                        },
                        modifier = Modifier.padding(vertical = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(255, 165, 0),
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp)
                    ) {
                        Text("Previous")
                    }
                }

                item {

                }

                item {
                    Button(
                        onClick = {
                            page.value++
                            viewModel.offset.value = page.value * 21
                            viewModel.loadMangaList()
                        },
                        modifier = Modifier.padding(vertical = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(255, 165, 0),
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp)
                    ) {
                        Text("Next Page")
                    }
                }

            }
        }


    }

}

@Composable
fun TagChip(tagName: String, isSelected: MutableState<Boolean>, onTagClicked: () -> Unit) {
    val shape = RoundedCornerShape(16.dp)
    val backgroundColor = if (isSelected.value) Color.Yellow else Color.Black
    val contentColor = if (isSelected.value) Color.Black else Color.White

    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shape = shape,
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                isSelected.value = !isSelected.value
                onTagClicked()
            },
        shadowElevation = 2.dp

    ) {
        Text(
            text = tagName,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MyApp(viewModel : GenresViewModel) {
    MaterialTheme {
        Scaffold {
            Column {
                Text("Genres", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
                val genres = listOf(
                    "Psychological", "Adventure", "Fantasy", "Isekai", "Drama", "Boys’ Love",
                    "Comedy", "Action", "Romance", "Tragedy", "Thriller", "Crime", "Girls’ Love",
                    "Historical", "Magical Girls", "Mecha", "Medical", "Mystery", "Philosophical",
                    "Sci-Fi", "Slice of Life", "Sports", "Superhero", "Wuxia"
                )
                TagList(tags = genres, viewModel = viewModel)

                GenresScreen(viewModel)


            }
        }
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun TagList(tags: List<String>, viewModel: GenresViewModel) {
    LazyColumn(modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp)) {
        items(tags.chunked(4)) { rowTags ->
            FlowRow {
                rowTags.forEach { tag ->
                    val isSelected = remember { mutableStateOf(viewModel.selectedTags.value?.contains(tag) ?: false) }
                    TagChip(
                        tagName = tag,
                        isSelected = isSelected,
                        onTagClicked = { viewModel.onTagSelected(tag) }
                    )
                }
            }
        }
    }
}




