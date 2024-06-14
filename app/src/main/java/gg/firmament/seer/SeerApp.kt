package gg.firmament.seer

import android.app.Application
import android.content.Context
import cat.ereza.customactivityoncrash.config.CaocConfig
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class SeerApp :
	Application(),
	ImageLoaderFactory {
	// TODO Delete when https://github.com/google/dagger/issues/3601 is resolved.
	@Inject
	@ApplicationContext
	lateinit var context: Context

	override fun onCreate() {
		super.onCreate()
		CaocConfig.Builder
			.create()
			.restartActivity(MainActivity::class.java)
			.apply()
	}

	override fun newImageLoader(): ImageLoader {
		val coilOkhttpClient =
			OkHttpClient
				.Builder()
				.connectTimeout(60, TimeUnit.SECONDS)
				.writeTimeout(60, TimeUnit.SECONDS)
				.readTimeout(100, TimeUnit.SECONDS)
				.build()

		return ImageLoader(this)
			.newBuilder()
			.memoryCachePolicy(CachePolicy.ENABLED)
			.memoryCache {
				MemoryCache
					.Builder(this)
					.maxSizePercent(0.25)
					.strongReferencesEnabled(true)
					.build()
			}.diskCachePolicy(CachePolicy.ENABLED)
			.diskCache {
				DiskCache
					.Builder()
					.maxSizePercent(0.03)
					.directory(cacheDir)
					.build()
			}.okHttpClient(coilOkhttpClient)
			.logger(DebugLogger())
			.build()
	}
}
