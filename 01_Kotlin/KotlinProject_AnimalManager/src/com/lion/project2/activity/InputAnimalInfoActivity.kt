package com.lion.project2.activity

import com.lion.project2.controller.MainController
import com.lion.project2.dao.AnimalInfoDAO
import com.lion.project2.model.AnimalModel
import com.lion.project2.util.ProgramState
import java.util.Scanner

class InputAnimalInfoActivity(var mainController: MainController):BaseActivity() {

    // 입력된 동물의 정보를 담을 객체
    var animalList: ArrayList<AnimalModel>? = null
    // 동물 정보를 입력받을 객체
    lateinit var animalModel: AnimalModel
    // 정보를 입력받기 위한 스캐너
    lateinit var scanner: Scanner

    override fun initActivity() {
        scanner = Scanner(System.`in`)
        // 학생정보를 가져온다.
        animalList = AnimalInfoDAO.getAnimalInfoList()
    }

    override fun processActivity() {

    }

    override fun showActivity() {
        
        println("\n[ 동물 정보 입력 ]")
        // 현재 동물의 수를 출력한다.
        showAnimalCnt()

        // 현재 동물의 정보를 입력받는다.
        inputAnimalInfo()
        println()
    }

    override fun finishActivity() {
        // 작업 완료 후 상태를 메뉴를 보여주는 상태로 변경한다.
        mainController.setProgramState(ProgramState.PROGRAM_STATE_SHOW_MENU)
    }

    // 현재 입력된 학생의 수를 보여주는 기능
    fun showAnimalCnt(){
        if (animalList != null){
            println("현재 입력된 동물의 수 : ${animalList?.size} 마리")
        } else {
            println("현재 입력된 동물의 수 : 0 마리")
        }
    }

    // 학생의 정보를 입력받는 기능
    fun inputAnimalInfo(){
        var typenum = -1; var type = ""; var name = ""; var age = -1; var hair = -1; var stripe = -1; var nose = -1

        do {
            try {
                println("동물 종류(1. 사자, 2.호랑이, 3.코끼리) : ")
                typenum = scanner.nextInt()

                when (typenum) {
                    1 -> type = "사자"
                    2 -> type = "호랑이"
                    3 -> type ="코끼리"
                }

                if (typenum !in 1..3){
                    println("동물 종류는 1 ~ 3까지의 값으로 선택해주세요")
                }
            } catch (e:NumberFormatException){
                println("동물 종류는 숫자로 입력해주세요")
            }
        } while (typenum !in 1..3)

        print("동물의 이름 : ")
        name = scanner.next()

        do {
            try {
                print("동물의 나이 : ")
                age = scanner.nextInt()

                if (age !in 0..150){
                    println("나이는 0 ~ 150까지의 값을 넣어주세요")
                }
            } catch (e:NumberFormatException){
                println("나이는 숫자로 입력해주세요")
            }
        } while (age !in 0..150)

        when (type) {
            "사자" -> {
                print("털의 개수 : ")
                hair = scanner.nextInt()
//                animalModel = AnimalModel(type, name, age, hair, 0, 0)
            }
            "호랑이" -> {
                print("줄무늬 개수 : ")
                stripe = scanner.nextInt()
//                animalModel = AnimalModel(type, name, age, 0, stripe, 0)
            }
            "코끼리" -> {
                print("코의 길이 : ")
                nose = scanner.nextInt()
//                animalModel = AnimalModel(type, name, age, 0, 0, nose)
            }
        }

        animalModel = AnimalModel(type, name, age, hair, stripe, nose)

        // 학쟁 정보를 파일에 쓴다.
        saveAnimalInfo()
    }

    // 입력받은 동물의 정보를 저장하는 기능
    fun saveAnimalInfo(){
        // ArrayList가 null 이면 객체를 생성해준다.
        if (animalList == null){
            animalList = ArrayList<AnimalModel>()
        }

        // 객체를 ArrayList에 담아준다.
        animalList?.add(animalModel)
        // 파일에 저장한다.
        AnimalInfoDAO.saveAnimalInfoList(animalList!!)
    }
}