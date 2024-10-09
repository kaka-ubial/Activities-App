package com.example.intentpractice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.intentpractice.data.dao.UserDao
import com.example.intentpractice.data.model.User
import com.example.intentpractice.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class UserUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userDao = mock()
        userRepository = UserRepository(userDao)
    }

    @Test
    fun testInsertUser() = runBlockingTest {
        val user = User(name = "John Doe", email = "john@example.com", password = "password123")

        whenever(userDao.insert(user)).thenReturn(1L)

        val result = userRepository.insert(user)
        assertEquals(1L, result)

        verify(userDao).insert(user)
    }

}
