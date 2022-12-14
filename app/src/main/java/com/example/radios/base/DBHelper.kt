package com.example.radios.base
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.radios.base.model.MUser
import com.example.radios.base.model.RadiosUsers


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE  $TABLE_NAME ( " +
                "$USERNAME_COl TEXT PRIMARY KEY, " +
                "$NAME_COl TEXT NOT NULL, " +
                "$SURNAME_COl TEXT NOT NULL," +
                "$EMAIL_COl TEXT NOT NULL, " +
                "$PASSWORD_COl TEXT NOT NULL, " +
                "$COUNTRY_COL TEXT )")
        db.execSQL(query)
        Log.d("SQL 1 ",query)
        val query2= ("CREATE TABLE $TABLE_NAME2 ($USERNAME_COL2 TEXT, $RADIOS_COL2 TEXT, PRIMARY KEY($USERNAME_COL2, $RADIOS_COL2))");
            /*("CREATE TABLE " + TABLE_NAME2 + " ("
                + USERNAME_COL2 + " TEXT" +
                RADIOS_COL2 + "TEXT" +
                "PRIMARY KEY ("+ USERNAME_COL2 + RADIOS_COL2 + ")"+
                ")")*/
        Log.d("SQL 2"  ,query2)
        db.execSQL(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME2")
        onCreate(db)
    }

    companion object {

        private val DATABASE_NAME = "USERDB"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "User"
        val USERNAME_COl = "username"
        val NAME_COl = "name"
        val SURNAME_COl = "surname"
        val EMAIL_COl = "email"
        val PASSWORD_COl = "password"
        val COUNTRY_COL = "country"

        val TABLE_NAME2 = "Favorites"
        val USERNAME_COL2 = "username"
        val RADIOS_COL2 = "radios"
    }


    fun insertUser(usr: MUser): Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(USERNAME_COl, usr.username)
        values.put(NAME_COl, usr.name)
        values.put(SURNAME_COl, usr.surname)
        values.put(EMAIL_COl, usr.email)
        values.put(PASSWORD_COl, usr.password)
        values.put(COUNTRY_COL, usr.country)
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        return success
    }
    fun insertFavorite(rusr: RadiosUsers): Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(USERNAME_COL2, rusr.username)
        values.put(RADIOS_COL2, rusr.radios)
        val success = db.insert(TABLE_NAME2, null, values)
        db.close()
        return success
    }
    fun deleteALLUsers() {
        val db = this.writableDatabase
        db.execSQL("DELETE  FROM $TABLE_NAME")
    }
    fun getALLFavorites(): ArrayList<RadiosUsers> {
        val usrList: ArrayList<RadiosUsers> = ArrayList()
        val selectQuery = "SELECT * FROM " + TABLE_NAME2
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var username: String
        var radios :String

        if (cursor.moveToFirst()) {
            do {
                val index0: Int = cursor.getColumnIndex("username")
                username = cursor.getString(index0)
                val index1: Int = cursor.getColumnIndex("radios")
                radios = cursor.getString(index1)
                val rusr = RadiosUsers(username,radios)
                usrList.add(rusr)
            } while (cursor.moveToNext())
        }
        return usrList

    }
    fun getALLUsers(): ArrayList<MUser> {
        val usrList: ArrayList<MUser> = ArrayList()
        val selectQuery = "SELECT * FROM " + TABLE_NAME
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var username: String
        var name: String
        var surname: String
        var email: String
        var password: String
        var country: String
        if (cursor.moveToFirst()) {
            do {
                val index0: Int = cursor.getColumnIndex("username")
                username = cursor.getString(index0)
                val index1: Int = cursor.getColumnIndex("name")
                name = cursor.getString(index1)
                val index2: Int = cursor.getColumnIndex("surname")
                surname = cursor.getString(index2)
                val index3: Int = cursor.getColumnIndex("email")
                email = cursor.getString(index3)
                val index4: Int = cursor.getColumnIndex("password")
                password = cursor.getString(index4)
                val index5: Int = cursor.getColumnIndex("country")
                country = cursor.getString(index5)
               // val index6 : Int = cursor.getColumnIndex("radios")
               // radioId = cursor.getString(index6)

                val usr = MUser(username, name, surname, email, password, country)
                usrList.add(usr)
            } while (cursor.moveToNext())
        }
        return usrList

    }

    fun getUserById(usern: String): MUser {
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE username='$usern'"
        val db = this.readableDatabase
        var username: String = ""
        var name: String = ""
        var surname: String = ""
        var email: String = ""
        var password: String = ""
        var country: String = ""
        var radioId: String = ""
        val cursor: Cursor?
        cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            val index0: Int = cursor.getColumnIndex("username")
            username = cursor.getString(index0)
            val index1: Int = cursor.getColumnIndex("name")
            name = cursor.getString(index1)
            val index2: Int = cursor.getColumnIndex("surname")
            surname = cursor.getString(index2)
            val index3: Int = cursor.getColumnIndex("email")
            email = cursor.getString(index3)
            val index4: Int = cursor.getColumnIndex("password")
            password = cursor.getString(index4)
            val index5: Int = cursor.getColumnIndex("country")
            country = cursor.getString(index5)
  //          val index6: Int = cursor.getColumnIndex("radioId")
//            radioId = cursor.getString(index6)

        }
        Log.d("Errod", "Doslo je do greske" )
        val usr = MUser(username, name, surname, email, password, country)
        return usr

    }
}


