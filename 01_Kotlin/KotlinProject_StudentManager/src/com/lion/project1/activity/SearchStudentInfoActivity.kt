package com.lion.project1.activity

import com.lion.project1.controller.MainController
import com.lion.project1.dao.UserInfoDAO
import com.lion.project1.model.StudentModel
import com.lion.project1.util.ProgramState
import java.util.*

class SearchStudentInfoActivity(var mainController: MainController) : BaseActivity() {

    // 학생들의 정보를 담을 ArrayList
    var studentList:ArrayList<StudentModel>? = null
    // 검색할 학생의 이름
    lateinit var searchName:String
    // 입력을 위한 스캐너
    lateinit var scanner: Scanner

    override fun initActivity() {
        // 학생정보를 가져온다.
        studentList = UserInfoDAO.getStudentInfoList()
        // 스캐너 생성
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {

    }

    override fun showActivity() {
        println("\n[ 학생 정보 검색 ]")
        // 저장된 학생 정보가 없다면
        if(studentList == null){
            printNotExistStudentInfo()
        } else{
            // 학생을 검색한다.
            inputSearchName()
            // 학생 정보 출력
            printSearchResult()
        }
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 검색할 학생의 이름을 입력받는 기능
    fun inputSearchName(){
        print("검색할 학생 이름 : ")
        searchName = scanner.next()
    }

    // 검색된 학생들의 정보를 출력하는 메서드
    fun printSearchResult(){
        // 검색된 학생의 수
        var findCnt = 0
        // ArrayList가 관리하는 객체의 수 만큼 반복한다.
        studentList?.forEach{
            // 현재 반복번째 학생의 이름이 검색어와 같다면 출력한다.
            if (it.name == searchName){
                it.printStudentInfo()
                // 검색된 학생 수를 증가시킨다
                findCnt++
            }
        }
        // 검색된 학생이 없다면
        if (findCnt == 0){
            println("검색된 학생이 없습니다.")
        }
    }

    // 학생정보가 없을 경우 안내 문구를 출력하는 메서드
    fun printNotExistStudentInfo(){
        println("저장된 학생 정보가 없습니다.\n")
    }
}