package kr.co.lion.ex10_project2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper (context:Context, dbName:String, version:Int) : SQLiteOpenHelper(context, dbName, null, version) {

    companion object {
        val databaseVersion = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table StudentDataTable
            | (idx integer primary key autoincrement,
            | name text not null,
            | grade integer not null,
            | kor integer not null,
            | eng integer not null,
            | math integer not null)
        """.trimMargin()

        // 쿼리문을 실행한다.
        db?.execSQL(sql)

        Log.d("test1234", "onCreate : DB - Create StudentData Table")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("test1234", "onUpgrade : $oldVersion -> $newVersion")
    }

}