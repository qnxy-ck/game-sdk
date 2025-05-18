plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.kotlinPluginSerialization)
}


dependencies {
    implementation(libs.kotlinxSerialization)
    implementation(libs.bundles.ktorClient)
    implementation("io.ktor:ktor-client-okhttp-jvm:3.1.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

}

