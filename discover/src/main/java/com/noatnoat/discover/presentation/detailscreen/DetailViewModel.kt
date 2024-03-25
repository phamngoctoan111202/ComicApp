package com.noatnoat.discover.presentation.detailscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noatnoat.base.common.NavigationSignal
import com.noatnoat.base.presentation.nav.NavManager
import com.noatnoat.discover.domain.model.chapter_model.ChapterImgDomain
import com.noatnoat.discover.domain.usecase.GetDetailComicUseCase
import com.noatnoat.discover.domain.usecase.GetImgComicUseCase
import kotlinx.coroutines.launch
import kotlin.math.log

class DetailViewModel(
    private val getDetailComicUseCase: GetDetailComicUseCase,
    private val getImgComicUseCase: GetImgComicUseCase,
    private val navManager: NavManager
) : ViewModel() {
    val chapterIds = MutableLiveData<List<String?>>()
    val chapterImg = MutableLiveData<ChapterImgDomain?>()
    val chapterlinks = MutableLiveData<List<String?>?>()
    val chapterCurrent = MutableLiveData<String?>()

    fun fetchChapterIds(args: DetailFragmentArgs) {

        viewModelScope.launch {
            val mangaData = getDetailComicUseCase(args.idComic)
            chapterIds.value = mangaData?.data?.map { it.id }
            chapterCurrent.value = chapterIds.value?.get(0)
        }
    }

    fun fetchChapterImg(chapterId: String) {
        viewModelScope.launch {
            val chapterData = getImgComicUseCase(chapterId)
            chapterImg.value = chapterData

            val imageLinks = chapterData?.data?.map { "https://uploads.mangadex.org/data/${chapterData.hash}/$it" }
            chapterlinks.value = imageLinks

            Log.d("abc", "Image links: $imageLinks")
        }
    }

}