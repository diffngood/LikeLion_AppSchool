package kr.co.lion.ex14_memo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Memo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        Log.d("test1234", "onCreate 실행되었음 - MemoTable 생성")

        val sql = """create table MemoTable
            | (idx integer primary key autoincrement,
            | title text not null,
            | content text not null)
        """.trimMargin()

        // 쿼리문을 실행한다.
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("test1234", "onUpgrade : $oldVersion -> $newVersion")
    }
}