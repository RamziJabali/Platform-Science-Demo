plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize") // Add this line
}

android {
    namespace = "ramzi.eljabali.lifecyclescienceplatformsciencemockapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "ramzi.eljabali.lifecyclescienceplatformsciencemockapp"
        minSdk = 23
        targetSdk = 34
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlin.parcelize.runtime)
    implementation(libs.kotlin.kx.protobuf)

    implementation(libs.timber)
    implementation(files("libs/connectionsdk-1.2.9.aar"))
    implementation(files("libs/core-1.0.10.aar"))
    implementation(files("libs/deeplinklib-1.5.1.aar"))
    implementation(files("libs/doccapsdk-2.3.5.aar"))
    implementation(files("libs/hossdk-2.5.9.aar"))
    implementation(files("libs/ipclib-2.6.12.aar"))
    implementation(files("libs/knoxsvclib-2.2.4.aar"))
    implementation(files("libs/orionextservicelib-3.4.0.aar"))
    implementation(files("libs/pscoreromsdk-2.7.9.aar"))
    implementation(files("libs/pscoresdk-2.9.7.aar"))
    implementation(files("libs/pushnotificationsdk-1.3.1.aar"))
    implementation(files("libs/vehicledatasdk-1.0.4.aar"))
    implementation(files("libs/wfsdk-2.5.10.aar"))

// when using a flat directory repository define dependencies like this:
//
//    implementation(libs.pltsci.deeplinklib){
//        artifact {
//            name = "deeplinklib"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.connectionsdk){
//        artifact {
//            name = "connectionsdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.doccapsdk){
//        artifact {
//            name = "doccapsdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.hossdk){
//        artifact {
//            name = "hossdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.ipclib) {
//        artifact {
//            name = "ipclib"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.knoxsvclib) {
//        artifact {
//            name = "knoxsvclib"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.orionextservicelib) {
//        artifact {
//            name = "orionextservicelib"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.pushnotificationsdk) {
//        artifact {
//            name = "pushnotificationsdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.pscoreromsdk) {
//        artifact {
//            name = "pscoreromsdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.pscoresdk) {
//        artifact {
//            name = "pscoresdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.uikit) {
//        artifact {
//            name = "core"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.vehicledatasdk) {
//        artifact {
//            name = "vehicledatasdk"
//            type = "aar"
//        }
//    }
//    implementation(libs.pltsci.wfsdk) {
//        artifact {
//            name = "wfsdk"
//            type = "aar"
//        }
//    }
}