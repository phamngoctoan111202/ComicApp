plugins {
    id("local.library2")
    id("kotlin-parcelize")
}

android {
    namespace = "com.noatnoat.comicapp.genres"
}

dependencies {
    implementation(projects.base)
    ksp(libs.roomCompiler)

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    val nav_version = "2.4.0-rc01"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.compose.ui:ui-tooling:1.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    ksp("com.google.devtools.ksp:symbol-processing-api:1.5.31-1.0.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.navigation:navigation-compose:2.5.0")

    implementation(libs.appCompat)


}