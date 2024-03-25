package com.noatnoat.library.presentation.screen.libraryscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noatnoat.base.common.SharePreferenceCustom.SharePreferenceCustom
import com.noatnoat.base.presentation.activity.BaseFragment
import com.noatnoat.base.presentation.compose.composable.ImageCard

class LibraryFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LibraryComic()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun LibraryComic() {
    val tabs = listOf("Subscription", "History", "Download")
    val selectedTabIndex = remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Text(text = "Comics |", fontSize = 24.sp)
            Text(text = " Novels", fontSize = 24.sp)
        }
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(vertical = 24.dp),
            containerColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color(255, 165, 0),
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                )
            },
            tabs = {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        onClick = { selectedTabIndex.value = index },
                        text = { Text(text = title, fontSize = 12.sp, color = Color.Black) }
                    )
                }
            }

        )
        when (selectedTabIndex.value) {
            0 -> SubscriptionContent()
            1 -> HistoryContent()
            2 -> DownloadContent()
        }
    }

}

@Composable
fun SubscriptionContent() {
    Text(text = "Subscription Content")
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(255, 165, 0),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp)
    ) {
        Text(text = "Search", fontSize = 16.sp, color = Color.Black)
    }
}

@Composable
fun HistoryContent() {
    Text(text = "History Content")
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(255, 165, 0),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp)
    ) {
        Text(text = "Search", fontSize = 16.sp, color = Color.Black)
    }
}

@Composable
fun DownloadContent() {
    Text(text = "Download Content")
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(255, 165, 0),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp)
    ) {
        Text(text = "Search", fontSize = 16.sp, color = Color.Black)
    }
}

