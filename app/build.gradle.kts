@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.sisecevirmece"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.sisecevirmece"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
        implementation("org.jetbrains.kotlinx:kotlin")
        val room_version = "2.5.2"
        val lifecycle_version="2.2.0"
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
        implementation("androidx.room:room-runtime:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version")

        // To use Kotlin annotation processing tool (kapt)
        kapt("androidx.room:room-compiler:$room_version")

        // optional - Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$room_version")

        // optional - RxJava2 support for Room
        implementation("androidx.room:room-rxjava2:$room_version")

        // optional - RxJava3 support for Room
        implementation("androidx.room:room-rxjava3:$room_version")

        // optional - Guava support for Room, including Optional and ListenableFuture
        implementation("androidx.room:room-guava:$room_version")

        // optional - Test helpers
        testImplementation("androidx.room:room-testing:$room_version")

        // optional - Paging 3 Integration
        implementation("androidx.room:room-paging:$room_version")

    //work
    val work_version="2.8.1"
    //rxjava2 support
    implementation("androidx.work:work-rxjava3:$work_version")
    //rxjava3 support
    implementation("androidx.work:work-rxjava2:$work_version")
    //kotlin+coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")
    //test helpers
    testImplementation("androidx.work:work-testing:$work_version")
    //optional - multiprocess support
    implementation("androidx.work:work-multiprocess:$work_version")

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(files("libs/mysql-connector-java-8.0.21"))
    implementation(files("libs/postgresql-42.6.0.jar"))
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}