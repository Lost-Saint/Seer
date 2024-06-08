plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
}

android {
	namespace = "gg.firmament.seer"
	compileSdk = 34

	defaultConfig {
		applicationId = "gg.firmament.seer"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables { useSupportLibrary = true }
		androidResources {
			generateLocaleConfig = true
		}
	}

	dependenciesInfo { includeInApk = false }

	buildTypes {
		debug {
			applicationIdSuffix = ".debug"
			versionNameSuffix = "-DEBUG"
			isMinifyEnabled = false
			isShrinkResources = false
		}
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
			signingConfig = signingConfigs.getByName("debug")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions { jvmTarget = "1.8" }
	buildFeatures { compose = true }
	lint { abortOnError = true }
	packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
	implementation(libs.androidx.core.ktx)

	implementation(libs.bundles.lifecycle)
	implementation(libs.androidx.navigation.compose)

	implementation(libs.androidx.activity.compose)

	implementation(libs.kotlinx.serialization.json)

	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.bundles.compose)
	debugImplementation(libs.bundles.compose.debug)
	// coil
	implementation(libs.bundles.coil)

	// crash handler
	implementation(libs.customActivityOnCrash)
	implementation(libs.leakCanary)
	// okio
	implementation(libs.bundles.okio)
	//jsoup
	implementation(libs.jsoup)
	// hilt
	implementation(libs.hilt.android)
	implementation(libs.androidx.hilt.navigation.compose)
	ksp(libs.hilt.compiler)
	// room
	implementation(libs.bundles.room)
	ksp(libs.androidx.room.compiler)
	// Room Test
	testImplementation(libs.androidx.room.testing)
	// Compose Tests
	testImplementation(libs.junit)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.compose.ui.test)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}
