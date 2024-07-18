package com.example.englishrussianflashcards

import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
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
    fun testNoInternetConnectionInDataTransferCase() {

        runBlocking {
            val errorImageService = ErrorImageService()
            imageRepository = ImageRepository(errorImageService)
            val uiState = imageRepository.getImageList()

            assertTrue(NO_INTERNET_CONNECTION_TEST_FAILURE_MESSAGE, uiState is UiState.Error)

            assertTrue(NO_INTERNET_CONNECTION_TEST_WRONG_RESPONSE_STATUS_MESSAGE,
                       uiState.hasResponseStatus(NO_INTERNET_CONNECTION_RESPONSE_STATUS))
        }
    }

    @Test
    fun testDataExtractionSuccess() {

        runBlocking {
            val successImageService = SuccessImageService()
            imageRepository = ImageRepository(successImageService)
            val uiState = imageRepository.getImageList()
            assertTrue("List<Image> extraction failure", uiState is UiState.Success)
        }
    }

    @Test
    fun testNotAvailableApiCase() {

        runBlocking {
            val unavailableApiImageService = UnavailableApiImageService()
            imageRepository = ImageRepository(unavailableApiImageService)
            val uiState = imageRepository.getImageList()
            assertTrue("Unavailable Api case wrong handling", uiState is UiState.Error)
        }
    }
}
