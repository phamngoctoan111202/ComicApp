package com.noatnoat.genres.presentation.infoscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.navArgs
import com.noatnoat.base.presentation.activity.BaseFragment
import com.noatnoat.base.presentation.compose.composable.ImageCard
import com.noatnoat.comicapp.genres.R
import com.noatnoat.genres.presentation.detailscreen.DetailFragmentArgs
import com.noatnoat.genres.presentation.detailscreen.DetailViewModel
import com.noatnoat.genres.presentation.genresscreen.GenresViewModel
import com.noatnoat.genres.presentation.genresscreen.MyApp
import org.koin.androidx.navigation.koinNavGraphViewModel

class InfoFragment: BaseFragment() {
    private val viewModel: GenresViewModel by koinNavGraphViewModel(R.id.feature_genres_nav)
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return ComposeView(requireContext()).apply {
            setContent {
                DiscoverScreen(viewModel)
            }
        }
    }

}


@Composable
internal fun DiscoverScreen(viewModel: GenresViewModel) {

    val comicInfo by viewModel.currentComic.observeAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        val title = viewModel.getTitleInEnglish(comicInfo)
        val imageUrl = viewModel.createCoverUrl(comicInfo)

        item {
            if (comicInfo != null) {
                Row {
                    ImageCard(
                        title = "",
                        imageUrl = imageUrl,
                        null,
                        id = comicInfo!!.id,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    )
                }
            }
        }

        item {
            if (title != null) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Black
                    )
                }
            }
        }

        item {
            Button(
                onClick = {
                    comicInfo?.id?.let { viewModel.onComicClickToDetail(it) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp)
            ) {
                Text(text = "Read Now", color = Color.Black)
            }
        }


        item {
            LazyRow {
                item {
                    comicInfo?.attributesDomain?.contentRating?.let { TagChip(it) }
                }

                item {
                    comicInfo?.attributesDomain?.tagDomains?.forEach { tagDomain ->
                        tagDomain?.attributes?.nameDomain?.en.let {
                            if (it != null) {
                                TagChip(it)
                            }
                        }
                    }
                }
            }
        }

        item {
            comicInfo?.attributesDomain?.descriptionDomain?.en?.let { Text(text = it, modifier = Modifier.padding(vertical = 16.dp)) }
        }

        item {
            fieldText("Year: ", "${comicInfo?.attributesDomain?.year}")
        }

        item {
            fieldText("Status: ", "${comicInfo?.attributesDomain?.status}")
        }

        item {
            fieldText("State: ", "${comicInfo?.attributesDomain?.state}")
        }


        item {
            fieldText("Last Chapter: ", "${comicInfo?.attributesDomain?.lastChapter}")
        }

        item {
            fieldText("lastVolume: ", "${comicInfo?.attributesDomain?.lastVolume}")
        }

        item {
            fieldText("publicationDemographic: ", "${comicInfo?.attributesDomain?.publicationDemographic}")
        }

        item {
            fieldText("originalLanguage: ", "${comicInfo?.attributesDomain?.originalLanguage}")
        }




    }
}

@Composable
fun fieldText(name : String, value : String) {
    Row {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = value,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}



@Composable
fun TagChip(tagName: String) {
    val shape = RoundedCornerShape(16.dp)
    val backgroundColor = Color(255, 165, 0)
    val contentColor = Color.Black

    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shape = shape,
        modifier = Modifier
            .padding(4.dp)
            .clickable {

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


@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun TagList(tags: List<String>, viewModel: GenresViewModel) {
    LazyColumn {
        items(tags.chunked(4)) { rowTags ->
            FlowRow {
                rowTags.forEach { tag ->
                    val isSelected = remember { mutableStateOf(viewModel.selectedTags.value?.contains(tag) ?: false) }
                    com.noatnoat.genres.presentation.genresscreen.TagChip(
                        tagName = tag,
                        isSelected = isSelected,
                        onTagClicked = { viewModel.onTagSelected(tag) }
                    )
                }
            }
        }
    }
}



