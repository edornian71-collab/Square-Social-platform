package com.example.squaresocialplatform

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseHelperTest {
    private lateinit var context: Context
    private lateinit var db: DatabaseHelper
    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase("squaresocial.db")
        db = DatabaseHelper(context)
    }
    @Test
    fun registerUser() {
        assertTrue(db.registerUser("mason","password123","mason@test.com"))

    }
    @Test
    fun registerUserWithExistingEmail() {
        db.registerUser("subject","subjectpass","subject@test.com")
        assertFalse(db.registerUser("testUser","userpass", "subject@test.com"))
    }
}