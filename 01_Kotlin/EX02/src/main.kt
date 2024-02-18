// 키보드로 부터 입력을 받아 짝수라면 "짝수입니다"를 출력하고
// 홀수라면 "홀수입니다"를 출력한다.

// step1) 기능 정리

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않는다.

// step3) 각 함수 내부의 코드를 구현해준다.
// 이 때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트를 해준다.

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출해준다.

import java.util.Scanner

fun main() {
    // inputNumber 함수 테스트
    val num = inputNumber()
    printResult(num)
}

// 키보드로 부터 입력 받는 기능
fun inputNumber():Int{
    val scanner = Scanner(System.`in`)

    // 입력을 받는다
    print("숫자를 입력하세요 : ")
    val num = scanner.nextInt()

    // 입력받은 값을 반환한다.
    return num
}

fun printResult(num:Int) {

    if (num % 2 == 0){
        println("짝수입니다")
    } else{
        println("홀수입니다")
    }
}