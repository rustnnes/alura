import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val assertJVersion: String by project
val jupiterVersion: String by project
val mockkVersion: String by project

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
//    id("org.jetbrains.kotlin.jvm") version "1.3.61"
     kotlin("jvm") version "1.3.61"

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components; enforce versions, like
    //    maven's dependencyManagement do
    // implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library & Kotlin JUnit4 integration.
    // testImplementation("org.jetbrains.kotlin:kotlin-test")
    // testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Hamcrest & Mockito
    testImplementation ("org.hamcrest:hamcrest:2.2")
    implementation ("org.mockito:mockito-core:3.3.0")

    // Use JUnit Jupiter API and JUnit Jupiter Engine for testing.
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.5.2")

    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
     testImplementation (group = "org.seleniumhq.selenium", name = "selenium-java", version = "3.141.59")

    // implementation ("com.google.guava:guava:28.1-jre")

    /*listOf(
            "org.junit.jupiter:junit-jupiter-api:$jupiterVersion",
            "org.assertj:assertj-core:$assertJVersion",
            "io.mockk:mockk:$mockkVersion"
    )
            .forEach { testImplementation(it) }*/
}

application {
    // Define the main class for the application.
    mainClassName = "selenium.AppKt"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.clean{
    listOf(
            "${rootDir}/app/db",
            "${rootDir}/app/target",
            "${rootDir}/app/lib",
            "${rootDir}/app/src/main/webapp/WEB-INF/classes",
            "${rootDir}/app/src/main/webapp/WEB-INF/lib"
    ).forEach{
        println("Deleting folder " + it + "...")
        delete(it)
    }
}