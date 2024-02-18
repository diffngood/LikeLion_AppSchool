// 인터페이스

// 메서드 오버라이딩의 강제성을 주기위해 추상 클래스를 사용한다.
// 코틀린(자바 포함)은 당일 상속만 지원하기 때문에 사용해야 할 추상 클래스가 여러 개라면
// 추상 클래스 하나당 하나의 자식클래스를 만들어줘야 한다.
// 만약 추상메서드를 가지고 있는 것이 인터페이스라면 하나의 클래스에 안에서 여러 인터페이스가 가진
// 추상 메서드를 오버라이딩 할 수 있다.

// 인터페이스도 상속 관계에서 객체의 주소 값을 부모 타입의 변수에 담을 수 있는 것 처럼
// 인터페이스 타입의 변수에도 담을 수 있다. 이를 통해 오버라이딩한 메서드를 호출 할 수 있다.

// 클래스와 인터페이스의 차이는 인터페이스는 객체를 생성하기 위한 설계도가 아니다.
// 클래스와 인터페이스 관계에서 상속 관계가 성립할 수 없다.
// 또한 인터페이스를 가지고 객체를 생성하는 것이 불가능하기 때문에 객체와 같이 데이터를 관리하는 것도
// 불가능 하다.

fun main() {
    val t1 = SubClass1()
    val t2 = SubClass2()

    testFunction1(t1)
    testFunction2(t2)

    val t10 = TestClass10()
    testFunction3(t10)
    testFunction4(t10)
}

// 추상 클래스
abstract class AbstractClass1{
    abstract fun abstractMethod1()
}

abstract class AbstractClass2{
    abstract fun abstractMethod2()
}

// 객체의 주소값을 받아 메서드를 호출하는 함수
fun testFunction1(t100:AbstractClass1){
    t100.abstractMethod1()
}

fun testFunction2(t200:AbstractClass2){
    t200.abstractMethod2()
}

// 코틀린(자바 포함)에서는 단일 상속만 지원되기 때문에 여러 클래스를 상속 받을 수 없다.
// class SubClass1 : AbstractClass1(), AbstractClass2()
// 추상 클래스 하나당 하나의 자식 클래스를 만들어줘야 한다.
class SubClass1 : AbstractClass1(){
    override fun abstractMethod1() {
        println("SubClass1의 abstractMethod1")
    }
}

class SubClass2 : AbstractClass2(){
    override fun abstractMethod2() {
        println("SubClass2의 abstractMethod2")
    }
}

// 인터페이스
interface Inter1{
    fun interfaceMethod1()
}

interface Inter2{
    fun interfaceMethod2()
}

fun testFunction3(inter1:Inter1){
    inter1.interfaceMethod1()
}

fun testFunction4(inter2:Inter2){
    inter2.interfaceMethod2()
}

// 인터페이스를 구현한 클래스
class TestClass10 : Inter1, Inter2{
    override fun interfaceMethod1() {
        println("TestClass10의 interfaceMethod1")
    }

    override fun interfaceMethod2() {
        println("TestClass10의 interfaceMethod2")
    }
}