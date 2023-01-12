package com.example.radios

import com.example.radios.base.DBHelper
import com.example.radios.base.model.MUser
import com.example.radios.fragments.SignUpFragment
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import kotlin.test.assertFails
import kotlin.test.assertFailsWith


class SignUpT {
    @Mock
    var dbHelper = mock<DBHelper>()
    var frag = mock<SignUpFragment>()

    @After
    fun tearDown() {
        dbHelper.close()
    }

/*    @Test
    fun existUsernameTest() {
        var testUser =
            MUser("iva", "ivana", "ignjatovic", "ignjatovicivanaa@gmail.com", "ivana123", "Srbija")
        `when`(dbHelper.getUserById("iva")).thenReturn(testUser)
        assertFailsWith<Exception> {
            frag.signUpUser(testUser, dbHelper)
        }

    }*/
}