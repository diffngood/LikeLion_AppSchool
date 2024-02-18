// Sealed 클래스 : 특정 클래스를 상속받은 클래스들을 열거형처럼 모아 관리하는 개념
// Sealed 클래스가 부모클래스가 되고 Sealed 클래스 관리하는 모든 클래스는 Sealed 클래스를
// 상속받은 자식 클래스에 해당한다.

fun main() {
    // 객체를 생성한다
    val obj1 = NumberClass.OneClass(100, 200)
    val obj2 = NumberClass.TwoClass(300)
    val obj3 = NumberClass.ThreeClass(100, 11.11)

    obj1.numberMethod1()
    obj2.numberMethod1()
    obj3.numberMethod1()

}

// 열거형
enum class Number1(var num:Int, var str:String){
    ONE(1, "일"), TWO(2, "이"), THREE(3, "삼")
}

// Sealed 클래스는 자기 자신을 상속받은 클래스들을 모아 관리하는 개념
sealed class NumberClass{

    open fun numberMethod1(){
        println("NumberClass의 numberMethod1")
    }

    // 관리할 클래스.
    // 모든 클래스는 Sealed 클래스를 상속받는다.
    class OneClass(var a1:Int, var a2:Int) : NumberClass()
    class TwoClass(var a1:Int) : NumberClass(){
        override fun numberMethod1() {
            println("TwoClass의 numberMethod1")
        }
    }
    class ThreeClass(var a1:Int, var a2:Double):NumberClass()
}

// 매개변수로 들어오는 객체의 클래스 타입에 따라 분기해 처리한다.
// 함수의 매개변수에 Sealed 클래스 타입을 넣어준다.
fun checkNumber(t1:NumberClass){
    // when으로 분기할 때 is를 통해 어떤 클래스를 통해 만든 객체인지 검사한다.
    // 이때 스마트 캐스팅도 이루어진다.
    when(t1){
        is NumberClass.OneClass -> {
            println("OneClass입니다")
            println(t1.a1)
            println(t1.a2)
            t1.numberMethod1()
        }
        is NumberClass.TwoClass -> {
            println("TwoClass입니다")
            println(t1.a1)
            t1.numberMethod1()
        }
        is NumberClass.ThreeClass ->{
            println("ThreeClass입니다")
            println(t1.a1)
            println(t1.a2)
            t1.numberMethod1()
        }
    }
}