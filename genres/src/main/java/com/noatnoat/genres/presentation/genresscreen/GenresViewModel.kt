package com.noatnoat.genres.presentation.genresscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noatnoat.base.presentation.activity.OnComicClickListener
import com.noatnoat.base.presentation.nav.NavManager
import com.noatnoat.genres.domain.model.search_model.DataDomain
import com.noatnoat.genres.domain.model.search_model.MangaData
import com.noatnoat.genres.domain.usecase.GetComicListByTagUseCase
import com.noatnoat.genres.presentation.infoscreen.CurrentComic
import com.noatnoat.genres.presentation.infoscreen.InfoFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


internal class GenresViewModel(
    private val navManager: NavManager,
    private val getComicListByTagUseCase: GetComicListByTagUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), OnComicClickListener {

    val MangaData = MutableLiveData<MangaData?>()
    val selectedTags = MutableLiveData<List<String>?>(listOf())
    val currentComic = MutableLiveData<DataDomain?>()
    val offset = MutableLiveData<Int>(0)

    init {
        loadMangaList()
        currentComic.value = CurrentComic.currentComic
    }

    internal fun loadMangaList() {
        viewModelScope.launch {
            val limit = 21
            val offset = offset.value
            MangaData.value = withContext(Dispatchers.IO) {
                selectedTags.value?.let { getComicListByTagUseCase(limit, offset, it, listOf()) }
            }
        }
    }

    private fun getFileName(dataDomain: DataDomain?): String? {
        return dataDomain?.relationshipDomains?.firstOrNull { it?.type == "cover_art" }?.attributes?.fileName
    }


    fun getTitleInEnglish(dataDomain: DataDomain?): String? {
        return dataDomain?.attributesDomain?.titleDomain?.en
    }

    fun createCoverUrl(dataDomain: DataDomain?): String {
        val mangaId: String? = dataDomain?.id
        val fileName: String? = getFileName(dataDomain)
        val size: Int? = null

        val baseUrl = "https://uploads.mangadex.org/covers"

        return if (size != null && mangaId != null && fileName != null) {
            "$baseUrl/$mangaId/$fileName.$size.jpg"
        } else if (mangaId != null && fileName != null) {
            "$baseUrl/$mangaId/$fileName"
        } else {
            ""
        }
    }

    override fun onComicClick(id: String) {
        val navDirections = GenresFragmentDirections.actionGenresFragmentToInfoFragment()
        loadCurrentComic(id)
        CurrentComic.currentComic  = currentComic.value
        navManager.navigate(navDirections)
    }

    fun loadCurrentComic(id :String?) {
        currentComic.value = MangaData.value?.data?.find { it?.id == id }

    }

    fun onComicClickToDetail(id: String) {
        val navDirections = InfoFragmentDirections.actionInfoFragmentToDetailFragment(id)
        navManager.navigate(navDirections)
    }

    val genreToIdMap = mapOf(
        "Psychological" to "3b60b75c-a2d7-4860-ab56-05f391bb889c",
        "Adventure" to "87cc87cd-a395-47af-b27a-93258283bbc6",
        "Fantasy" to "cdc58593-87dd-415e-bbc0-2ec27bf404cc",
        "Isekai" to "ace04997-f6bd-436e-b261-779182193d3d",
        "Drama" to "b9af3a63-f058-46de-a9a0-e0c13906197a",
        "Boys’ Love" to "5920b825-4181-4a17-beeb-9918b0ff7a30",
        "Comedy" to "4d32cc48-9f00-4cca-9b5a-a839f0764984",
        "Action" to "391b0423-d847-456f-aff0-8b0cfc03066b",
        "Romance" to "423e2eae-a7a2-4a8b-ac03-a8351462d71d",
        "Tragedy" to "f8f62932-27da-4fe4-8ee1-6779a8c5edba",
        "Thriller" to "07251805-a27e-4d59-b488-f0bfbec15168",
        "Crime" to "5ca48985-9a9d-4bd8-be29-80dc0303db72",
        "Girls’ Love" to "a3c67850-4684-404e-9b7f-c69850ee5da6",
        "Historical" to "33771934-028e-4cb3-8744-691e866a923e",
        "Magical Girls" to "81c836c9-914a-4eca-981a-560dad663e73",
        "Mecha" to "50880a9d-5440-4732-9afb-8f457127e836",
        "Medical" to "c8cbe35b-1b2b-4a3f-9c37-db84c4514856",
        "Mystery" to "ee968100-4191-4968-93d3-f82d72be7e46",
        "Philosophical" to "b1e97889-25b4-4258-b28b-cd7f4d28ea9b",
        "Sci-Fi" to "256c8bd9-4904-4360-bf4f-508a76d67183",
        "Slice of Life" to "e5301a23-ebd9-49dd-a0cb-2add944c7fe9",
        "Sports" to "69964a64-2f90-4d33-beeb-f3ed2875eb4c",
        "Superhero" to "7064a261-a137-4d3a-8848-2d385de3a99c",
        "Wuxia" to "acc803a4-c95a-4c22-86fc-eb6b582d82a2"
    )

    fun getGenreId(genre: String): String? {
        return genreToIdMap[genre]
    }


    fun onTagSelected(tag: String?) {

        val currentTags = selectedTags.value?.toMutableList()
        if (currentTags != null) {
            if (currentTags.contains(tag?.let { getGenreId(it) })) {
                currentTags.remove(tag?.let { getGenreId(it) })
            } else {
                if (tag != null) {
                    getGenreId(tag)?.let { currentTags.add(it) }
                }
            }
        }
        selectedTags.value = currentTags

        loadMangaList()
    }




}
