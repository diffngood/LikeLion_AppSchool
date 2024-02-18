package com.lion.project2.model

import java.io.Serializable

class AnimalModel(var type:String, var name:String, var age:Int, var hair:Int, var stripe:Int, var nose:Int) : Serializable {


    // 학생 한명의 정보를 출력하는 메서드
    fun printAnimalInfo(){
        println("동물 종류 : $type")
        println("동물 이름 : $name")
        println("동물 나이 : $age")
        if (type == "사자") {
            println("털의 개수 : $hair\n")
        } else if (type == "호랑이") {
            println("줄무늬 개수 : $stripe\n")
        } else if (type == "코끼리") {
            println("코의 길이 : $nose\n")
        }
    }
}