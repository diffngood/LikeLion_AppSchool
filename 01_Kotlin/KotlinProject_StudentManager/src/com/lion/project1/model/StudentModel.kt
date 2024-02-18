package com.lion.project1.model

import java.io.Serializable

// 주 생성자에서 정의한 프로퍼티
// 학생의 이름, 학생의 나이, 학생의 국어 점수, 학생의 영어 점수, 학생의 수학 점수
// 객체를 외부에 보내려면(파일에 저장하려면) Serializable 인터페이스를 구현해야 한다.
class StudentModel(var name:String, var age:Int, var kor:Int, var eng:Int, var math:Int) : Serializable {

    // 학생 한명의 정보를 출력하는 메서드
    fun printStudentInfo(){
        println("학생 이름 : $name")
        println("학생 나이 : $age")
        println("국어 점수 : $kor")
        println("영어 점수 : $eng")
        println("수어 점수 : $math\n")
    }
}