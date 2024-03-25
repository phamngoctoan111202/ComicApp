plugins {
    id("local.library2")
}

android {
    namespace = "com.noatnoat.comicapp.base"
}

dependencies {
    api(libs.kotlin)
    api(libs.playCore)
    api(libs.coreKtx)
    api(libs.fragmentKtx)
    api(libs.viewBindingPropertyDelegate)
    api(libs.timber)
    api(libs.constraintLayout)
    api(libs.appCompat)
    api(libs.recyclerView)
    api(libs.coroutines)
    api(libs.material)
    api(libs.composeMaterial)
    api(libs.accompanistFlowLayout)
    api(libs.bundles.koin)
    api(libs.bundles.retrofit)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.bundles.room)
    api(libs.bundles.compose)

//    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    testRuntimeOnly(libs.junitJupiterEngine)
}