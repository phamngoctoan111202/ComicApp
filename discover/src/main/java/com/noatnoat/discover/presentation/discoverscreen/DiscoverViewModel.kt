package com.noatnoat.discover.presentation.discoverscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.noatnoat.base.presentation.activity.OnComicClickListener
import com.noatnoat.base.presentation.nav.NavManager
import com.noatnoat.discover.domain.model.search_model.DataDomain
import com.noatnoat.discover.domain.model.search_model.MangaData
import com.noatnoat.discover.domain.usecase.GetSearchComicListUseCase
import com.noatnoat.discover.presentation.infoscreen.CurrentComic
import com.noatnoat.discover.presentation.infoscreen.InfoFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


internal class DiscoverViewModel(
    private val navManager: NavManager,
    private val getSearchComicListUseCase: GetSearchComicListUseCase,
) : ViewModel(), OnComicClickListener {

    val MangaData = MutableLiveData<MangaData?>()
    val currentComic = MutableLiveData<DataDomain?>()
    val selectedTags = MutableLiveData<List<String>?>(listOf())
    val offset = MutableLiveData<Int?>(0)

    init {
        loadMangaList()
        currentComic.value = CurrentComic.currentComic
    }

    internal fun loadMangaList(searchString: String = "") {
        val limit = 21
        val newOffset = if (searchString.isNotBlank()) 0 else offset.value ?: 0
        viewModelScope.launch {
            MangaData.value = withContext(Dispatchers.IO) {
                if (searchString.isNotBlank()) {
                    getSearchComicListUseCase(50, 0, listOf(), listOf(), searchString)
                } else {
                    selectedTags.value?.let { getSearchComicListUseCase(limit, newOffset, it, listOf(), searchString) }
                }
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
        val navDirections = DiscoverFragmentDirections.actionDiscoverFragmentToInfoFragment()
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


    val themeToIdMap = mapOf(
        "Survival" to "5fff9cde-849c-4d78-aab0-0d52b2ee1d25",
        "Harem" to "aafb99c1-7f60-43fa-b75f-fc9502ce29c7",
        "Demons" to "39730448-9a5f-48a2-85b0-a70db87b1233",
        "Supernatural" to "eabc5b4c-6aff-42f3-b657-3e90cbd00b75",
        "Horror" to "cdad7e68-1419-41dd-bdce-27753074a640",
        "Samurai" to "81183756-1453-4c81-aa9e-f6e1b63be016",
        "Office Workers" to "92d6d951-ca5e-429c-ac78-451071cbf064",
        "Police" to "df33b754-73a3-4c54-80e6-1a74a8058539",
        "Aliens" to "e64f6742-c834-471d-8d72-dd51fc02b835",
        "Animals" to "3de8c75d-8ee3-48ff-98ee-e20a65c86451",
        "Cooking" to "ea2bc92d-1c26-4930-9b7c-d5c0dc1b6869",
        "Crossdressing" to "9ab53f92-3eed-4e9b-903a-917c86035ee3",
        "Delinquents" to "da2d50ca-3018-4cc0-ac7a-6b7d472a29ea",
        "Genderswap" to "2bd2e8d0-f146-434a-9b51-fc9ff2c5fe6a",
        "Ghosts" to "3bb26d85-09d5-4d2e-880c-c34b974339e9",
        "Gyaru" to "fad12b5e-68ba-460e-b933-9ae8318f5b65",
        "Incest" to "5bd0e105-4481-44ca-b6e7-7544da56b1a3",
        "Loli" to "2d1f5d56-a1e5-4d0d-a961-2193588b08ec",
        "Mafia" to "85daba54-a71c-4554-8a28-9901a8b0afad",
        "Magic" to "a1f53773-c69a-4ce5-8cab-fffcd90b1565",
        "Martial Arts" to "799c202e-7daa-44eb-9cf7-8a3c0441531e",
        "Military" to "ac72833b-c4e9-4878-b9db-6c8a4a99444a",
        "Monster Girls" to "dd1f77c5-dea9-4e2b-97ae-224af09caf99",
        "Monsters" to "36fd93ea-e8b8-445e-b836-358f02b3d33d",
        "Music" to "f42fbf9e-188a-447b-9fdc-f19dc1e4d685",
        "Ninja" to "489dd859-9b61-4c37-af75-5b18e88daafc",
        "Post-Apocalyptic" to "9467335a-1b83-4497-9231-765337a00b96",
        "Reincarnation" to "0bc90acb-ccc1-44ca-a34a-b9f3a73259d0",
        "Reverse Harem" to "65761a2a-415e-47f3-bef2-a9dababba7a6",
        "School Life" to "caaa44eb-cd40-4177-b930-79d3ef2afe87",
        "Shota" to "ddefd648-5140-4e5f-ba18-4eca4071d19b",
        "Time Travel" to "292e862b-2d17-4062-90a2-0356caa4ae27",
        "Traditional Games" to "31932a7e-5b8e-49a6-9f12-2afa39dc544c",
        "Vampires" to "d7d1730f-6eb0-4ba6-9437-602cac38664c",
        "Video Games" to "9438db5a-7e2a-4ac0-b39e-e0d95a34b8a8",
        "Villainess" to "d14322ac-4d6f-4e9b-afd9-629d5f4d8a41",
        "Virtual Reality" to "8c86611e-fab7-4986-9dec-d1a2f44acdd5",
        "Zombies" to "631ef465-9aba-4afb-b0fc-ea10efe274a8"
    )

    fun getThemeId(theme: String): String? {
        return themeToIdMap[theme]
    }


    fun onTagSelected(tag: String?) {
        selectedTags.value = emptyList()

        val currentTags = selectedTags.value?.toMutableList()
        if (currentTags != null) {
            if (currentTags.contains(tag?.let { getThemeId(it) })) {
                currentTags.remove(tag?.let { getThemeId(it) })
            } else {
                if (tag != null) {
                    getThemeId(tag)?.let { currentTags.add(it) }
                }
            }
        }
        selectedTags.value = currentTags

        loadMangaList()
    }
}
