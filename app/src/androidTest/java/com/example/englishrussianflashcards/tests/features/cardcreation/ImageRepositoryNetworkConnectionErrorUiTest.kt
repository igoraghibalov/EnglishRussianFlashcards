package com.example.englishrussianflashcards.tests.features.cardcreation

import androidx.test.espresso.core.internal.deps.dagger.Module
import androidx.test.espresso.core.internal.deps.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


private const val NETWORK_CONNECTION_ERROR_DIALOG_MESSAGE = "Check Internet connection"


/**
 * Created by Igor Aghibalov on 11.10.2026
 */
@HiltAndroidTest
@UninstallModules(WikiImageApiModule::class)
class ImageRepositoryNetworkConnectionErrorUiTest: BaseImageRepositoryErrorUiTest() {

    @Inject
    lateinit var mockWebServer: MockWebServer


    @Module
    @InstallIn(SingletonComponent::class)
    class NetworkConnectionErrorWikiImageApiModule {


        @Provides
        fun provideMockWebServer(): MockWebServer {
            return MockWebServer()
        }


        @Provides
        fun provideLocalUrl(mockWebServer: MockWebServer): String {
            return mockWebServer.url("/").toString()
        }


        @Provides
        fun provideRetrofit(localUrl: String): Retrofit {
            return Retrofit.Builder()
                           .baseUrl(localUrl)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build()
        }


        @Provides
        fun provideWikiImageApi(retrofit: Retrofit): WikiImageApi {
            return retrofit.create(WikiImageApi::class.java)
        }
    }


    @BindValue
    @JvmField
    val networkConnectionErrorImageRepository = RealWikiImageApiRepository()


    override fun setUp() {
        errorDialogMessage = NETWORK_CONNECTION_ERROR_DIALOG_MESSAGE
        super.setUp()
        mockWebServer.start()
    }

    @After
    fun close() {
        mockWebServer.shutdown()
    }
}