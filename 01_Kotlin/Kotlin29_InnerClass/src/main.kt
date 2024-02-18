// 중첩 클래스 : 클래스 안에 작성하는 클래스
// 클래스안에서만 사용하는 클래스를 정의할 때 많이 사용한다.
// 클래스 안에 있다고 해서 클래스를 가지고 있는 클래스에서만 객체를 생성할 수 있는 것은 아니다.
// 그러나 외부에서 객체를 생성하는 것이 불편하다.

fun main(){
    // 내부 클래스의 객체를 생성하기 위해서는 외부 클래스의 객체가 필요하다.
    val obj1 = Outer1()
    val obj2 = obj1.Inner1()

    // 다른 위치, 파일, 패키지, 모듈에 클래스를 작성했다면 저~~어기 가서 클래스 작성하고
    // 요~~기 와서 객체 생성하고 ..
    val obj3 = TestClass1()
    obj3.interMethod1()
    obj3.interMethod2()

    // 만약 익명 중첩클래스를 사용한다면
    // 클래스 작성과 메서드의 오버라이딩과 객체 생성을 같은 곳에서 모두 작업한다.
    // 단, 이름이 없는 1회용 클래스이기 때문에 이 클래스를 더 이상 사용할 수  없다.
    // object : 인터페이스나 추상클래스
    val obj4 = object : Inter1 {
        override fun interMethod1() {
            println("익명 중첩 클래스의 innerMethod1")
        }

        override fun interMethod2() {
            println("익명 중첩 클래스의 innerMethod2")
        }
    }
    obj4.interMethod1()
    obj4.interMethod2()
}

// 일반 중첩 클래스
// 외부 클래스
class Outer1{
    // 프로퍼티
    var outerValue1 = 100

    // 메서드
    fun outerMethod1(){
        println("Outer1의 outerMethod1")

        // 내부 클래스가 가지고 있는 요소를 사용 해본다.
        // 외부 클래스의 객체를 생성했다고 해서 내부 클래스의 객체가 생성되는 것이 아니므로
        // 외부 클래스에서 내부 클래스의 요소는 사용할 수 없다.
        // println("innerValue1 : $innerValue1")
        // innerMethod1()
    }

    // 내부 클래스
    inner class Inner1{
        
        var innerValue1 = 200
        
        fun innerMethod1(){
            println("Inner1의 innerMethod1 입니다")
            // 내부 클래스의 객체를 생성하려면 외부 클래스를 통해 만든 객체가 필요하다.
            // 따라서 내부 클래스를 가지고 만든 객체 입장에서는 외부 클래스의 객체가 생성되어
            // 있다는 것을 보장받을 수 있다. 이에 외부 클래스에 정의되어 있는 private를 제외한
            // 모든 멤버는 사용이 가능하다.
            println("outerValue1 : $outerValue1")
            outerMethod1()
        }
    }
}

// 인터페이스나 추상클래스를 통해 객체를 생성할 수 없다.
// 인터페이스를 구현하여 메서드를 구현한 클래스를 정의하거나 추상클래스를 상속받아 메서드를 구현한
// 클래스를 만들어야 객체를 생성할 수 있다.
// 만약 이러한 클래스를 통해 생성하는 객체가 하나뿐이라면 익명 중첩클래스를 사용하면 편하게 작업할 수 있다.
interface Inter1 {
    fun interMethod1()
    fun interMethod2()
}

class TestClass1 : Inter1{
    override fun interMethod1() {
        println("TestClass1의 innerMethod1")
    }

    override fun interMethod2() {
        println("TestClass2의 innerMethod2")
    }
}