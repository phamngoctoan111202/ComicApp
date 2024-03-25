plugins {
    id("local.library2")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.noatnoat.comicapp.account"
}

dependencies {
    implementation(projects.base)
    implementation("com.google.firebase:firebase-auth:22.3.1")
    ksp(libs.roomCompiler)
}