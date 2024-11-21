package gg.firmament.seer.epub.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gg.firmament.seer.epub.data.SeerDatabase
import gg.firmament.seer.epub.domain.EpubParser
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

	@Provides
	fun provideAppContext(@ApplicationContext context: Context) = context

	@Singleton
	@Provides
	fun provideSeerDatabase(@ApplicationContext context: Context) =
		SeerDatabase.getInstance(context)

	@Provides
	fun provideLibraryDao(seerDatabase: SeerDatabase) = seerDatabase.getLibraryDao()

	@Provides
	fun provideReaderDao(seerDatabase: SeerDatabase) = seerDatabase.getReaderDao()


	@Singleton
	@Provides
	fun provideEpubParser(@ApplicationContext context: Context) = EpubParser(context)

}
