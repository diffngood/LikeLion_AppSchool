package kr.co.lion.jetpackexample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {

    // 저장
    @Insert
    fun insertMemoData(memoEntity: MemoEntity)

    // 메모 내용 전체 가져오기
    @Query("""
        select memoIdx, memoSubject, memoText
        from memotable
        order by memoIdx desc
    """)
    fun selectMemoDataAll() : List<MemoEntity>

    // 메모 내용 하나만 가져오기
    @Query("""
        select memoIdx, memoSubject, memoText
        from memotable
        where memoIdx = :memoIdx
    """)
    fun selectMemoDataOne(memoIdx:Int) : MemoEntity
}
