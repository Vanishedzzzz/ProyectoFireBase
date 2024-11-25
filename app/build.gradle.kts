plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
    id("com.google.gms.google-services")
}

=======
<<<<<<< HEAD
    id("com.google.gms.google-services")
}


=======
}

>>>>>>> fdaa6367d3c952bea4e1a35788f5d29b3910b4c2
>>>>>>> 703ec891ec0b35739f1a4029c6f2e7d248ed6e55
android {
    namespace = "com.example.proyectofirebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyectofirebase"
<<<<<<< HEAD
        minSdk = 33
=======
<<<<<<< HEAD
        minSdk = 33 // Considera bajar a 21 o 24 si necesitas más compatibilidad
=======
        minSdk = 33
>>>>>>> fdaa6367d3c952bea4e1a35788f5d29b3910b4c2
>>>>>>> 703ec891ec0b35739f1a4029c6f2e7d248ed6e55
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
}

<<<<<<< HEAD
dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-analytics")
    implementation("androidx.multidex:multidex:2.0.1")
=======
<<<<<<< HEAD

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-analytics")
    implementation("androidx.multidex:multidex:2.0.1")
=======
dependencies {
>>>>>>> fdaa6367d3c952bea4e1a35788f5d29b3910b4c2
>>>>>>> 703ec891ec0b35739f1a4029c6f2e7d248ed6e55

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
<<<<<<< HEAD
}
=======
<<<<<<< HEAD
}

=======
}
>>>>>>> fdaa6367d3c952bea4e1a35788f5d29b3910b4c2
>>>>>>> 703ec891ec0b35739f1a4029c6f2e7d248ed6e55
