package com.example.radios

import com.example.radios.base.DBHelper
import com.example.radios.registration.SignUpFragment
import org.junit.After
import org.mockito.Mock
import org.mockito.kotlin.mock


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