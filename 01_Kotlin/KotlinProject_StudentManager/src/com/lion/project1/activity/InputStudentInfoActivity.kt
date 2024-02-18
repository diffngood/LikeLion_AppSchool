package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState
import java.util.Scanner

class InputStudentInfoActivity(var mainController: MainController) : BaseActivity() {

    // 입력된 학생의 정보를 담을 객체
    var studentList:ArrayList<StudentModel>? = null
    // 학생 정보를 입력받을 객체
    lateinit var studentModel:StudentModel
    // 정보를 입력받기 위한 스캐너
    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)
        // 학생정보를 가져온다.
        studentList = UserInfoDAO.getStudentInfoList()
    }

    override fun processActivity() {

    }

    override fun showActivity() {
        println()
        println("[ 학생정보 입력 ]")
        // 현재 학생 수를 출력한다.
        showStudentCnt()

        // 현재 학생의 정보를 입력받는다.
        inputStudentInfo()
        println()
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 현재 입력된 학생의 수를 보여주는 기능
    fun showStudentCnt(){
        if (studentList != null){
            println("현재 입력된 학생 수 : ${studentList?.size}명")
        } else {
            println("현재 입력된 학생 수 : 0명")
        }
    }

    // 학생의 정보를 입력받는 기능
    fun inputStudentInfo(){
        var name = ""; var age = -1; var kor = -1; var eng = -1; var math = -1

        print("학생 이름 : ")
        name = scanner.next()

        do {
            try {
                print("학생 나이 : ")
                age = scanner.nextInt()

                if (age !in 0..150){
                    println("나이는 0 ~ 150까지의 값을 넣어주세요")
                }
            } catch (e:NumberFormatException){
                println("나이는 숫자로 입력해주세요")
            }
        } while (age !in 0..150)

        print("국어 점수 : ")
        kor = scanner.nextInt()
        print("영어 점수 : ")
        eng = scanner.nextInt()
        print("수학 점수 : ")
        math = scanner.nextInt()

        studentModel = StudentModel(name, age, kor, eng, math)
        // 학쟁 정보를 파일에 쓴다.
        saveStudentInfo()
    }

    // 입력받은 학생의 정보를 저장하는 기능
    fun saveStudentInfo(){
        // ArrayList가 null 이면 객체를 생성해준다.
        if (studentList == null){
            studentList = ArrayList<StudentModel>()
        }

        // 객체를 ArrayList에 담아준다.
        studentList?.add(studentModel)
        // 파일에 저장한다.
        UserInfoDAO.saveStudentInfoList(studentList!!)
    }
}