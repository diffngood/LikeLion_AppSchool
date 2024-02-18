// 사용자에게 정수를 입력받고 1 부터 입력 받은 숫자까지의 총합을 구한다

// step1) 기능 정리
// 1. 정수를 입력받는 기능
// 2. 1부터 입력 받은 숫자까지의 총합을 구한다.
// 3. 출력한다.

// step2) 각 기능별 함수를 구현
// 아직 함수 내부의 코드는 작성하지 않는다.

// step3) 각 함수 내부의 코드를 구현해준다.
// 이 때, 함수 하나의 구현이 끝나면 의도한대로 동작하는지 반드시 테스트를 해준다.

// step4) 프로그램이 동작하도록 main 함수에서 각 함수들을 순서에 맞게 호출해준다.

import java.util.Scanner

fun main() {

    // 숫자를 입력받는다.
    val num = inputNumber()

    // 1부터 입력받은 숫자까지의 총합을 구한다.
    val total = getTotal(num)

    // 출력한다.
    printResult(total=total, number=num)
}

// 1. 정수를 입력받는 기능
fun inputNumber():Int{
    val scanner = Scanner(System.`in`)
    print("숫자를 입력해주세요 : ")
    val number = scanner.nextInt()
    return number
}
// 2. 1부터 입력 받은 숫자까지의 총합을 구한다.
fun getTotal(number:Int) : Int{

    // 누적할 값을 담을 변수
    var total = 0

    // 1 부터 입력받은 숫자까지 반복한다.
    for(i in 1..number){
        // 누적한다.
        total += i
    }
    // 누적한 값을 반환한다.
    return total
}
// 3. 출력한다.
fun printResult(number:Int, total:Int){
    println("1부터 ${number}까지의 총합은 ${total}입니다")
}