package com.example.radios

import android.telecom.Call
import com.example.radios.base.DBHelper
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.MUser
import com.example.radios.radioslist.view.RadioListFragment

import junit.framework.Assert.*
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.mock
import org.robolectric.RuntimeEnvironment

class LogInUnitTest {

    @Mock
    var dbHelper = mock<DBHelper>()
    @After
    fun tearDown() {
        dbHelper.close()
    }
    @Test
    @Throws(Exception::class)
    fun testDbInsertion() {
        val testUser1 = MUser("test1","Test1","Testic1","@test1.com","test123","Test1")
        val testUser2 = MUser("test2","Test2","Testic2","@test2.com","test123","Test2")
        dbHelper.insertUser(testUser1)
        dbHelper.insertUser(testUser2)
        var expectedResult = ArrayList<MUser>()
        expectedResult.add(testUser1)
        expectedResult.add(testUser2)
        `when`(dbHelper.getALLUsers()).thenReturn(expectedResult)
        assertEquals(dbHelper.getALLUsers(), expectedResult)

    }
    @Test
    @Throws(Exception::class)
    fun testSuccessLogin(){
        val test_username="test"
        val testUser1 = MUser("test1","Test1","Testic1","@test1.com","test123","Test1")
        dbHelper.insertUser(testUser1)
        `when`(dbHelper.getUserById(test_username)).thenReturn(testUser1)
        assertEquals(dbHelper.getUserById(test_username).username,testUser1.username)
        assertEquals(dbHelper.getUserById(test_username).password,testUser1.password)
    }
    @Test
    @Throws(Exception::class)
    fun testUnsuccessLogin(){
        val test_username="test2"
        val testUser1 = MUser("test1","Test1","Testic1","@test1.com","test123","Test1")
        dbHelper.insertUser(testUser1)
        Assert.assertNull(dbHelper.getUserById(test_username))
    }
    @Test
    @Throws(Exception::class)
    fun testUnsuccessLogin2(){
        val test_username="test"
        val test_password ="test321"
        val testUser1 = MUser("test1","Test1","Testic1","@test1.com","test123","Test1")
        dbHelper.insertUser(testUser1)
        `when`(dbHelper.getUserById(test_username)).thenReturn(testUser1)
        Assert.assertNotEquals(dbHelper.getUserById(test_username).password,test_password)
    }
}