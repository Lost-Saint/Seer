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
	compileSdk = 35

	defaultConfig {
		applicationId = "gg.firmament.seer"
		minSdk = 24
		targetSdk = 35
		versionCode = 1
		versionName = "1.0.0"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables { useSupportLibrary = true }
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
			signingConfig = signingConfigs.getByName("debug")
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	
	compileOptions {
		isCoreLibraryDesugaringEnabled = true
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions { jvmTarget = "17" }
	buildFeatures { compose = true }
	lint { abortOnError = true }
	packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {

	coreLibraryDesugaring(libs.desugar)

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
	// jsoup
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
