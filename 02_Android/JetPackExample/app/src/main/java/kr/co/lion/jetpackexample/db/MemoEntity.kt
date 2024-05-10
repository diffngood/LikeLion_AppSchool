package kr.co.lion.jetpackexample.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MemoTable")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    var memoIdx:Int = 0,
    var memoSubject:String = "",
    var memoText:String = ""
)