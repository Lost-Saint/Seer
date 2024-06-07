package gg.firmament.seer.epub.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gg.firmament.seer.epub.domain.EpubParser
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

  @Provides fun provideAppContext(@ApplicationContext context: Context) = context

  @Singleton @Provides fun provideEpubParser() = EpubParser()
}
