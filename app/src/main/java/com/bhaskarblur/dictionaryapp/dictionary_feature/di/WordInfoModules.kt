package com.bhaskarblur.dictionaryapp.dictionary_feature.di

import android.app.Application
import android.content.Context
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.WordInfoDB
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.dao.WordInfoDao
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.DictionaryApi
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.remote.RetrofitApiClient
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.repository.WordInfoRepoImpl
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.repository.WordInfoRepo
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.usecase.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WordInfoModules {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repo: WordInfoRepo) : GetWordInfo {
        return GetWordInfo(repo)
    }
    @Provides
    @Singleton
    fun provideWordInfoRepo(wordInfoDb: WordInfoDB, dictionaryApi: DictionaryApi) : WordInfoRepo {
        return WordInfoRepoImpl(dictionaryApi,wordInfoDb.infoDao)
    }

    @Provides
    @Singleton
    fun provideWordDB(@ApplicationContext appContext: Context) : WordInfoDB {
        return WordInfoDB.getInstance(appContext)
    }

    @Provides
    @Singleton
    fun provideDictionaryApiClient() : Retrofit {
        return RetrofitApiClient.getInstance(DictionaryApi.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(apiClient : Retrofit) : DictionaryApi {
        return apiClient.create(DictionaryApi::class.java)
    }
}
