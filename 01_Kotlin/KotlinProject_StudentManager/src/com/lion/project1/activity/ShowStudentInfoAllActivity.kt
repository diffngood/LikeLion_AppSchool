package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState
import java.util.ArrayList

class ShowStudentInfoAllActivity(var mainController: MainController) : BaseActivity() {

    // 학생들의 정보를 담을 ArrayList
    var studentList: ArrayList<StudentModel>? = null

    override fun initActivity() {
        // 학생 정보를 가져온다.
        studentList = UserInfoDAO.getStudentInfoList()
    }

    override fun processActivity() {

    }

    override fun showActivity() {

        println("\n[ 학생 전체 정보 ]")

        // 저장된 학생 정보가 없다면
        if(studentList == null){
            printNotExistStudentInfo()
        } else{
            // 학생 정보 출력
            printStudentInfoAll()
        }
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 모든 학생들의 정보를 출력하는 메서드
    fun printStudentInfoAll(){
        studentList?.forEach{
            it.printStudentInfo()
        }
    }

    // 학생정보가 없을 경우 안내 문구를 출력하는 메서드
    fun printNotExistStudentInfo(){
        println("저장된 학생 정보가 없습니다.\n")
    }

}