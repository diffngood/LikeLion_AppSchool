package kr.co.lion.jetpackexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {

    // Dao
    abstract fun memoDao() : MemoDao

    companion object{
        // 데이터 베이스 객체를 담을 정적 변수
        var memoDatabase:MemoDatabase? = null

        // 데이터 베이스 객체를 생성하는 메서드
        @Synchronized
        fun getInstance(context: Context) : MemoDatabase?{
            // 데이터 베이스 객체가 생성된 적이 없을 경우
            if(memoDatabase == null){
                synchronized(MemoDatabase::class){
                    // 데이터 베이스 객체를 생성한다.
                    // 마지막에 데이터 베이스 파일명을 넣어준다.
                    memoDatabase = Room.databaseBuilder(
                        context.applicationContext, MemoDatabase::class.java, "memo.db"
                    ).build()
                }
            }
            return memoDatabase
        }
    }
}
