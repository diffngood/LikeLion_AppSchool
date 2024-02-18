package kr.co.lion.ex14_memo

import android.content.Context

class MemoDao {
    companion object {

        // select one
        fun selectOneStudent(context: Context, idx:Int) : MemoModel{
            // 쿼리문
            val sql = """select idx, title, content 
                | from MemoTable
                | where idx = ?
            """.trimMargin()

            // ? 에 들어갈 값
            val args = arrayOf("$idx")

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 데이터를 가져온다.
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("title")
            val idx3 = cursor.getColumnIndex("content")

            val idx = cursor.getInt(idx1)
            val title = cursor.getString(idx2)
            val content = cursor.getString(idx3)

            // 객체에 데이터를 담는다.
            val memoModel = MemoModel(idx, title, content)

            // 데이터 베이스를 닫아준다.
            dbHelper.close()

            return memoModel
        }

        // select all
        fun selectAllStudent(context: Context) : MutableList<MemoModel>{
            // 쿼리문
            // order by : 정렬
            // order by 기준컬럼 정렬방식
            // 정렬방식 : asc 나 생략 - 오름 차순, desc : 내림차순
            val sql = """select idx, title, content 
                | from MemoTable
                | order by idx desc
            """.trimMargin()

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            // 데이터를 담을 리스트
            val memoList = mutableListOf<MemoModel>()

            // 데이터를 가져온다.
            while (cursor.moveToNext()){

                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("title")
                val idx3 = cursor.getColumnIndex("content")

                val idx = cursor.getInt(idx1)
                val title = cursor.getString(idx2)
                val content = cursor.getString(idx3)

                // 객체에 데이터를 담는다.
                val memoModel = MemoModel(idx, title, content)

                // 객체를 리스트에 담는다.
                memoList.add(memoModel)
            }
            dbHelper.close()

            return memoList
        }

        // insert
        fun insertStudent(context: Context, memoModel: MemoModel){
            // 쿼리문
            val sql = """insert into MemoTable
                | (title, content)
                | values (?, ?)
            """.trimMargin()

            // ? 에 바인딩 될 값
            val args = arrayOf(memoModel.title, memoModel.content)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // update
        fun updateStudent(context: Context, memoModel: MemoModel){
            // 쿼리문
            val sql  = """update MemoTable
                | set title = ?, content = ?
                | where idx = ?
            """.trimMargin()

            // ? 에 바인딩 될 값
            val args = arrayOf(memoModel.title, memoModel.content, memoModel.idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // delete
        fun deleteStudent(context: Context, idx:Int){
            // 쿼리문
            val sql = """delete from MemoTable
                | where idx = ?
            """.trimMargin()

            // ? 에 바인딩 될 값
            val args = arrayOf(idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }

}