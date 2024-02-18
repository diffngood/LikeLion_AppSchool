package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState
import java.util.ArrayList

class ShowPointTotalAvgActivity(var mainController: MainController) : BaseActivity() {

    // 총점들을 담을 프로퍼티
    var korTotal = 0
    var engTotal = 0
    var mathTotal = 0
    // 평균들을 담을 프로퍼티
    var korAvg = 0.0f
    var engAvg = 0.0f
    var mathAvg = 0.0f

    // 학생들의 정보를 담을 ArrayList
    var studentList: ArrayList<StudentModel>? = null

    override fun initActivity() {
        // 학생정보를 가져온다.
        studentList = UserInfoDAO.getStudentInfoList()
    }

    override fun processActivity() {
    }

    override fun showActivity() {

        println("\n[ 점수 통계 ]")
        // 저장된 학생 정보가 없다면
        if(studentList == null){
            printNotExistStudentInfo()
        } else{
            // 총점을 구한다.
            getPointTotal()
            // 평균을 구한다.
            getPointAvg()

            println("국어 총점 : $korTotal")
            println("영어 총점 : $engTotal")
            println("수학 총점 : $mathTotal")
            println("국어 평균 : $korAvg")
            println("영어 평균 : $engAvg")
            println("수학 평균 : $mathAvg\n")
        }
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 과목별 총점을 구하는 메서드
    fun getPointTotal(){
        // ArrayList가 관리하는 객체의 수 만큼 반복한다.
        studentList?.forEach{
            korTotal += it.kor
            engTotal += it.eng
            mathTotal += it.math
        }
    }

    // 과목별 평균을 구하는 메서드
    fun getPointAvg(){
        korAvg = korTotal.toFloat() / (studentList!!.size)
        engAvg = engTotal.toFloat() / (studentList!!.size)
        mathAvg = mathTotal.toFloat() / (studentList!!.size)
    }

    // 학생정보가 없을 경우 안내 문구를 출력하는 메서드
    fun printNotExistStudentInfo(){
        println("저장된 학생 정보가 없습니다.\n")
    }
}