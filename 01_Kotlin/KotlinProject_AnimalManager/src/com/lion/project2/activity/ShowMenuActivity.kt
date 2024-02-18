package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.util.MenuNumber
import com.lion.project2.util.ProgramState
import java.util.Scanner

class ShowMenuActivity(var mainController: MainController):BaseActivity() {
    // 메뉴 번호를 담을 프로퍼티
    var menuNumber = 0
    // 입력을 받기 위한 스캐너
    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)
    }

    override fun processActivity() {

    }

    override fun showActivity() {
        println("[ 메인 메뉴 ]")
        println("1. 동물 정보 입력")
        println("2. 동물 이름 검색")
        println("3. 동물 타입 검색")
        println("4. 모든 동물의 정보 출력")
        println("5. 동물 삭제")
        println("6. 프로그램 종료")

        // 메뉴 번호를 입력받는다.
        inputMenuNumber()
    }

    override fun finishActivity() {
        // 사용자가 입력한 메뉴 번호에 따라 프로그램의 상태를 변경한다.
        when (menuNumber) {
            // 동물 정보를 입력
            MenuNumber.MAIN_MENU_INPUT_ANIMAL_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_INPUT_ANIMAL_INFO)
            // 동물 정보를 이름으로 검색
            MenuNumber.MAIN_MENU_SEARCH_ANIMAL_NAME_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SEARCH_ANIMAL_NAME_INFO)
            // 동물 정보를 타입으로 검색
            MenuNumber.MAIN_MENU_SEARCH_ANIMAL_TYPE_INFO.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SEARCH_ANIMAL_TYPE_INFO)
            // 모든 동물의 정보 출력
            MenuNumber.MAIN_MENU_SHOW_ANIMAL_INFO_ALL.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_ANIMAL_INFO_ALL)
            // 동물 삭제
            MenuNumber.MAIN_MENU_DELETE_ANIMAL.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_DELETE_ANIMAL)
            // 종료
            MenuNumber.MAIN_MENU_FINISH.num ->
                mainController.setProgramState(ProgramState.PROGRAM_STATE_FINISH)
        }
    }

    // 메뉴의 번호를 입력받는 메서드
    fun inputMenuNumber(){
        do {
            print("번호를 입력해주세요 : ")
            menuNumber = scanner.nextInt()

            // 입력받은 메뉴 번호가 1 ~ 5에 포함되지 않는다면...
            if (menuNumber !in 1..6) {
                println("메뉴 번호는 1부터 6까지의 숫자 중 하나를 입력해주세요")
            }
        } while (menuNumber !in 1..6)
    }
}