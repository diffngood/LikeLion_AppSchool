package kr.co.lion.ex13_sqlitedatabase2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Student.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        Log.d("test1234", "onCreate 실행되었음")

        val sql = """create table StudentTable
            | (idx integer primary key autoincrement,
            | name text not null,
            | age integer not null,
            | kor integer not null,
            | eng integer not null,
            | math integer not null)
        """.trimMargin()

        // 쿼리문을 실행한다.
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("test1234", "onUpgrade : $oldVersion -> $newVersion")
    }
}