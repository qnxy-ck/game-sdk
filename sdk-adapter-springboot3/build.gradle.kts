plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.kotlinPluginSerialization)

    kotlin("kapt")
}


kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":sdk-core"))
    implementation("org.springframework:spring-web:6.2.6")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.4.5")
    compileOnly("org.springframework.boot:spring-boot-starter-logging:3.4.5")
    kapt("org.springframework.boot:spring-boot-configuration-processor:3.4.5")

}