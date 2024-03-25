package com.noatnoat.discover.presentation.discoverscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noatnoat.base.presentation.activity.BaseFragment
import com.noatnoat.base.presentation.compose.composable.ImageCard
import com.noatnoat.comicapp.discover.R
import com.noatnoat.discover.domain.model.search_model.MangaData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.navigation.koinNavGraphViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DiscoverFragment : BaseFragment() {
    private val viewModel: DiscoverViewModel by koinNavGraphViewModel(R.id.feature_discover_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                viewModel.loadMangaList()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return ComposeView(requireContext()).apply {
            setContent {
                DiscoverScreen(viewModel)
            }
        }
    }



}



@Composable
internal fun DiscoverScreen(viewModel: DiscoverViewModel) {
    val mangaData by viewModel.MangaData.observeAsState()
    var searchText by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }
    val page = remember { mutableStateOf(1) }

    Column {
        if (isSearchVisible) {
            TextField(
                value = searchText,
                onValueChange = { newText -> searchText = newText },
                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.loadMangaList(searchText)
                })
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Discover", fontSize = 24.sp)
                IconButton(onClick = { isSearchVisible = true }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            }
        }

        ThemeTabLayout(viewModel)


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
                            defaultElevation = 4.dp
                        )
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
                            defaultElevation = 4.dp
                        )
                    ) {
                        Text("Next Page")
                    }
                }

            }


        }
    }
}


@Composable
internal fun ThemeTabLayout(viewmodel : DiscoverViewModel) {
    val themes = listOf("All",
        "Survival", "Harem", "Demons", "Supernatural", "Horror", "Samurai",
        "Office Workers", "Police", "Aliens", "Animals", "Cooking",
        "Crossdressing", "Delinquents", "Genderswap", "Ghosts", "Gyaru",
        "Incest", "Loli", "Mafia", "Magic", "Martial Arts", "Military",
        "Monster Girls", "Monsters", "Music", "Ninja", "Post-Apocalyptic",
        "Reincarnation", "Reverse Harem", "School Life", "Shota", "Time Travel",
        "Traditional Games", "Vampires", "Video Games", "Villainess",
        "Virtual Reality", "Zombies"
    )
    var selectedTabIndex by remember { mutableStateOf(0) }



    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 0.dp),
        contentColor = Color(255, 165, 0),
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = Color(255, 165, 0)
            )
        }
    ) {
        themes.forEachIndexed { index, genre ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    if (genre == "All") {
                        viewmodel.loadMangaList()
                    } else {
                        viewmodel.onTagSelected(genre)
                    }
                }
            ) {
                Text(text = genre, fontSize = 16.sp, modifier = Modifier.padding(vertical = 12.dp), color = Color.Black)
            }
        }
    }
}
