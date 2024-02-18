
fun main() {
    val r1 = testFun1(100, 200)
    println("r1 : $r1")

    val r2 = testFun2(100, 200)
    println("r2 : $r2")

    val r3 = testFun3(100, 200)
    println("r3 : $r3")

    val r4 = lambda1(100, 200)
    println("r4 : $r4")

    val r5 = lambda2(100, 200)
    println("r5 : $r5")

    val r6 = lambda3(100, 200)
    println("r6 : $r6")

    val r7 = lambda4(100, 200)
    println("r7 : $r7")
}

// 매개변수로 들어오는 값을 계산하여 반환하는 함수
fun testFun1(a1:Int, a2:Int) : Int{
    return a1 + a2
}

// 위의 함수는 다음과 같이 작성할 수 있다.
// = 다음에 작성한 수식을 계산하여 반환해준다.
fun testFun2(a1:Int, a2:Int) : Int = a1 + a2

// 만약 매개변수로 들어오는 값을 계산하여 반환하는 함수 내부의 코드가 여러 줄이라면...
fun testFun3(a1:Int, a2:Int) : Int{
    val r1 = a1 + 100
    val r2 = a2 + 200
    val r3 = r1 + r2
    return r3
}

// 매개변수로 들어오는 값을 가지고 계산한다음 반환하는 함수의 코드가 여러줄일 경우
// 아래와 같이 작성하는 것은 불가능하다.
// 대신 람다라는 것을 사용할 수 있다.
//fun testFun4(a1:Int, a2:Int) : Int = {
//    val r1 = a1 + 100
//    val r2 = a2 + 200
//    val r3 = r1 + r2
//    r3
//}

// 람다 : 전달 받은 값을 가지고 계산을 한 다음 계산의 결과를 반환해주는 식
// 람다식이라고 부르기도 하고 람다 함수라고 부르기도 한다.
// 코틀린 람다식을 가지고 있는 함수를 람다 함수라고 부른다.
// (Int, Int) : 매개변수의 타입을 정의
// -> Int : 반환 타입을 정의
// {a1:Int, a2:Int : 매개변수의 이름을 정의. 앞서 정의한 매개변수의 타입과 일치해야 한다.
// -> a1 + a2} : 반환할 값을 작성한 부분
val lambda1 : (Int, Int) -> Int = {a1:Int, a2:Int -> a1 + a2}
// 코틀린은 람다함수 대신 람다식만 작성해도 된다.
val lambda2 = {a1:Int, a2:Int -> a1 + a2}
// 타입을 생략할 수 있다.
val lambda3 : (Int, Int) -> Int = {a1, a2 -> a1 + a2}

// 람다는 -> 다음에 나오는 코드 중에 제일 마지막에
// 작성한 수식의 결과를 반환한다.
// testFun3과 같이 코드가 여러 줄인 것을 람다로
// 대체할 수 있다.
val lambda4 = {a1:Int, a2:Int ->
    var r1 = a1 + 100
    var r2 = a2 + 200
    // 제일 마지막에 작성한 값, 변수의 값, 수식의 결과를 최정 결과로
    // 반환해준다.
    r1 + r2
}