plugins {
    id("local.library2")
}

android {
    namespace = "com.noatnoat.comicapp.library"
}

dependencies {
    implementation(projects.base)
    ksp(libs.roomCompiler)
}