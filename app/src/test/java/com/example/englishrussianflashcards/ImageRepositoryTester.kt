package com.example.englishrussianflashcards

import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * Created by Igor Aghibalov on 15.07.2024
 */
/*TODO: test cases
 * - no internet connection before/during data transfer
 * - data extraction success
 * - api is not available before data transfer
 */
class ImageRepositoryTester: UnitTester() {
    private lateinit var imageRepository: ImageRepository

    override fun setupTestEnvironment() {}


    @Test
    fun testNoInternetConnectionBeforeDataTransferCase() {

        runTest {
            val errorImageService = ErrorImageService()
            imageRepository = ImageRepository(errorImageService)
            val uiState = imageRepository.getImageList()
            assertTrue("", uiState is UiState.Error)
        }
    }
}
