plugins {
    id("local.library2")
}

android {
    namespace = "com.noatnoat.comicapp.premium"
}

dependencies {
    implementation(projects.base)
    ksp(libs.roomCompiler)
}