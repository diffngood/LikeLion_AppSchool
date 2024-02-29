package kr.co.lion.androidproject3memoapp

import android.content.Context
import android.database.Cursor

class MemoDao {

    companion object {

        // insert
        fun insertMemoData(context: Context, memoModel: MemoModel){
            // 쿼리문
            val sql = """
                insert into MemoTable
                (memoSubject, memoDate, memoText)
                values (?, ?, ?)
            """.trimIndent()

            // ?에 바인딩 될 값
            val args = arrayOf(memoModel.memoSubject, memoModel.memoDate, memoModel.memoText)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // select all
        fun selectMemoDataAll(context: Context): MutableList<MemoModel>{
            // 쿼리문
            val sql = """
                select memoIdx, memoSubject, memoDate, memoText
                from MemoTable
                order by memoIdx desc
            """.trimIndent()

            // Cursor 객체를 가져온다.
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            // 데이터를 담을 리스트
            val memoList = mutableListOf<MemoModel>()


            // 마지막 행까지 반복한다.
            while (cursor.moveToNext()){
                // 행 하나의 데이터를 가져온다.
                val memoModel = getRowData(cursor)
                memoList.add(memoModel)
            }
            dbHelper.close()

            return memoList
        }

        // select one
        fun selectMemoDataOne(context: Context, memoIdx: Int): MemoModel{
            // 쿼리문
            val sql = """
                select memoIdx, memoSubject, memoDate, memoText
                from MemoTable
                where memoIdx = ?
            """.trimIndent()

            // ? 에 바인딩 될 값
            val args = arrayOf("$memoIdx")

            // 데이터 및 Cursor 객체를 가져온다.
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)
            cursor.moveToNext()

            val memoModel = getRowData(cursor)
            dbHelper.close()

            return memoModel
        }

        // 행 하나의 데이터를 담아 반환하는 메서드
        fun getRowData(cursor: Cursor): MemoModel{
            // 값의 순서를 가져온다.
            val idx1 = cursor.getColumnIndex("memoIdx")
            val idx2 = cursor.getColumnIndex("memoSubject")
            val idx3 = cursor.getColumnIndex("memoDate")
            val idx4 = cursor.getColumnIndex("memoText")
            // 값을 가져온다.
            val memoIdx = cursor.getInt(idx1)
            val memoSubject = cursor.getString(idx2)
            val memoDate = cursor.getString(idx3)
            val memoText = cursor.getString(idx4)

            // 객체에 담는다.
            val memoModel = MemoModel(memoIdx, memoSubject, memoDate, memoText)

            // 객체를 반환한다.
            return memoModel
        }

        // update
        fun updateMemoData(context: Context, memoModel: MemoModel) {
            // 쿼리문
            val sql = """
                update MemoTable
                set memoSubject = ?, memoText = ?
                where memoIdx = ?
            """.trimIndent()

            val args = arrayOf(memoModel.memoSubject, memoModel.memoText, memoModel.memoIdx)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // delete
        fun deleteMemoData(context:Context, memoIdx:Int){
            // 쿼리문
            val sql = """
                delete from MemoTable
                where memoIdx = ?
            """.trimIndent()

            val args = arrayOf(memoIdx)

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }
}