package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.ArrayList

class ShowAnimalInfoAllActivity(var mainController: MainController):BaseActivity() {

    // 프로퍼티
    var animalTotal = 0
    var animalTiger = 0
    var animalLion = 0
    var animalElephant = 0

    // 학생들의 정보를 담을 ArrayList
    var animalList: ArrayList<AnimalModel>? = null

    override fun initActivity() {
        // 학생 정보를 가져온다.
        animalList = AnimalInfoDAO.getAnimalInfoList()
    }

    override fun processActivity() {

    }

    override fun showActivity() {

        getAnimalTotal()
        println("\n[ 모든 동물의 정보 출력 ]")
        println("전체 동물의 수 : $animalTotal 마리")
        println("호랑이 : $animalTiger 마리")
        println("사자 : $animalLion 마리")
        println("코끼리 : $animalElephant 마리\n")
        // 저장된 학생 정보가 없다면
        if(animalList == null){
            printNotExistStudentInfo()
        } else{
            // 학생 정보 출력
            printAnimalInfoAll()
        }
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 과목별 총점을 구하는 메서드
    fun getAnimalTotal(){
        // ArrayList가 관리하는 객체의 수 만큼 반복한다.
        animalList?.forEach{
            animalTotal = animalList!!.size
            when (it.type) {
                "호랑이" -> animalTiger += 1
                "사자" -> animalLion += 1
                "코끼리" -> animalElephant += 1
            }
        }
    }

    // 모든 학생들의 정보를 출력하는 메서드
    fun printAnimalInfoAll(){
        animalList?.forEach{
            it.printAnimalInfo()
        }
    }

    // 학생정보가 없을 경우 안내 문구를 출력하는 메서드
    fun printNotExistStudentInfo(){
        println("저장된 학생 정보가 없습니다.\n")
    }
}