package com.example.englishrussianflashcards

import android.graphics.drawable.Drawable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.CoroutineContext

/**
 * Created by Igor Aghibalov on 24.05.2024
 */
class ImageRepositoryTester(): RepositoryTester, ListExtractionTester {
    private lateinit var additionalCoroutineContext: CoroutineContext
    private lateinit var imageRepository: ImageRepository


    @Before
    fun setup() {
        imageRepository = FakeImageRepository()
        additionalCoroutineContext = Dispatchers.Default
    }


    @Test
    override fun testDataExtraction() {
        testListExtraction()
    }


    override fun testListExtraction() {

        runBlocking {
            val word = "apple"

            val deferredImageList: Deferred<List<Drawable>> = async(additionalCoroutineContext) {
                imageRepository.getImageList(word)
            }

            assertEquals("Image list has not loaded", true, deferredImageList.await())
        }
    }
}
