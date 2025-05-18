package buildsrc.convention

import org.gradle.api.tasks.testing.logging.TestLogEvent


plugins {
    kotlin("jvm")
    `maven-publish`
}


kotlin {
    jvmToolchain(8)
    explicitApi()
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            
            groupId = "com.qnxy"
            artifactId = project.name
            version = "1.0.0-SNAPSHOT"
        }
    }

    repositories {
        maven {
            url = uri("file://${project.layout.buildDirectory}/repo")
        }
    }
}




tasks.withType<Test>().configureEach {
    useJUnitPlatform()

// Log information about all test results, not only the failed ones.
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED
        )
    }
}
