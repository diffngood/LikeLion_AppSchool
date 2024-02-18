package kr.co.lion.ex13_sqlitedatabase2

// 학생 인덱스 번호, 이름, 나이, 국어 점수, 영어 점수, 수학 점수
data class StudentModel(var idx:Int, var name:String, var age:Int, var kor:Int, var eng:Int, var math:Int)