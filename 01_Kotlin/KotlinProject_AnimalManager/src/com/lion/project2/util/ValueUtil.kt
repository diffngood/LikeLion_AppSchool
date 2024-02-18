package com.lion.project2.util

// 프로그램 전체 상태를 정의한다.
enum class ProgramState{
    // 메뉴를 보여주는 상태
    PROGRAM_STATE_SHOW_MENU,

    // 동물 정보를 입력하는 상태
    PROGRAM_STATE_INPUT_ANIMAL_INFO,

    // 동물 정보를 검색하는 상태(이름)
    PROGRAM_STATE_SEARCH_ANIMAL_NAME_INFO,

    // 동물 정보를 검색하는 상태(타입)
    PROGRAM_STATE_SEARCH_ANIMAL_TYPE_INFO,

    // 동물 정보 전체를 출력하는 상태
    PROGRAM_STATE_SHOW_ANIMAL_INFO_ALL,

    // 동물 정보를 삭제하는 상태
    PROGRAM_STATE_DELETE_ANIMAL,

    // 종료 상태
    PROGRAM_STATE_FINISH
}

// 각 메뉴
enum class MenuNumber(var num:Int){
    // 동물 정보 입력
    MAIN_MENU_INPUT_ANIMAL_INFO(1),

    // 동물 정보 검색 (이름)
    MAIN_MENU_SEARCH_ANIMAL_NAME_INFO(2),

    // 동물 정보 검색 (타입)
    MAIN_MENU_SEARCH_ANIMAL_TYPE_INFO(3),

    // 동물 정보 전체 출력
    MAIN_MENU_SHOW_ANIMAL_INFO_ALL(4),

    // 종료
    MAIN_MENU_DELETE_ANIMAL(5),

    // 종료
    MAIN_MENU_FINISH(6)
}