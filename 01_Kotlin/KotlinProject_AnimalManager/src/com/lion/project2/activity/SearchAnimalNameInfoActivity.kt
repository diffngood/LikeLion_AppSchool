package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.*

class SearchAnimalNameInfoActivity(var mainController: MainController):BaseActivity() {

    // 동물들의 정보를 담을 ArrayList
    var animalList: ArrayList<AnimalModel>? = null
    // 검색할 동물의 이름
    lateinit var searchName:String
    // 입력을 위한 스캐너
    lateinit var scanner: Scanner


    override fun initActivity() {
        // 동물정보를 가져온다.
        animalList = AnimalInfoDAO.getAnimalInfoList()
        // 스캐너 생성
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {

    }

    override fun showActivity() {
        println("\n[ 동물 정보 검색 ]")
        // 저장된 동물 정보가 없다면
        if(animalList == null){
            printNotExistStudentInfo()
        } else{
            // 동물을 검색한다.
            inputSearchName()
            // 동물 정보 출력
            printSearchResult()
        }
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 검색할 동물의 이름을 입력받는 기능
    fun inputSearchName(){
        print("검색할 동물의 이름을 입력해주세요 : ")
        searchName = scanner.next()
    }

    // 검색된 동물들의 정보를 출력하는 메서드
    fun printSearchResult(){
        // 검색된 동물의 수
        var findCnt = 0
        // ArrayList가 관리하는 객체의 수 만큼 반복한다.
        animalList?.forEach{
            // 현재 반복번째 동물의 이름이 검색어와 같다면 출력한다.
            if (it.name == searchName){
                it.printAnimalInfo()
                // 검색된 동물 수를 증가시킨다
                findCnt++
            }
        }
        // 검색된 동물이 없다면
        if (findCnt == 0){
            println("검색된 동물이 없습니다.")
        }
    }


    // 동물정보가 없을 경우 안내 문구를 출력하는 메서드
    fun printNotExistStudentInfo(){
        println("저장된 동물 정보가 없습니다.\n")
    }
}