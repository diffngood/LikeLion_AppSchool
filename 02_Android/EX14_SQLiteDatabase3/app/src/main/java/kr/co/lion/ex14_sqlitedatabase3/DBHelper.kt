package kr.co.lion.ex14_sqlitedatabase3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Memo", null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table MemoTable
            | (memoIdx integer primary key autoincrement,
            | memoSubject text not null,
            | memoText text not null)
        """.trimMargin()

        // 쿼리 실행
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("test1234", "onUpgrade : $oldVersion -> $newVersion")
    }
}