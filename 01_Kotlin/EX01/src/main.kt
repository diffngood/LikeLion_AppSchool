import java.util.Scanner

// 키보드로 부터 입력을 받아 출력하는 프로그램
// 1. 키보드로부터 입력받는 기능
// 2. 출력하는 기능
fun main() {
    // 키보드로 부터 입력을 받는다.
    var n1 = inputNumber()
    // 출력한다.
    printNumber(n1)
}

// 키보드로 부터 입력받는 기능
fun inputNumber():Int {
    val scanner = Scanner(System.`in`)
    // 키보드로 부터 정수값을 입력받는다.
    print("숫자를 입력해주세요 : ")
    val number = scanner.nextInt()
    // 입력받은 값을 반환한다.
    return number
}
// 출력하는 기능
fun printNumber(number:Int){
    println("입력받은 숫자 : $number")
}

fun p(number:Int) = when(number){
    1 -> "1"
    2 -> "2"
    else -> "1이랑 2 아님"
}