// 추상 메서드 : 구현되지 않은 메서드
// 추상 클래스 : 추상 메서드를 가지고 있는 클래스

// 추상 클래스는 추상 메서드를 가지고 있기 때문에(구현되지 않은 부분이 있기 때문에...) 완벽한 설계도가 아니다
// 이 때문에 추상 클래스를 통한 객체 생성은 불가능하다.

// 따라서 추상 클래스를 상속받은 클래스를 만든 다음 추상 클래스가 가지고 있는 추상 메서드를 구현하여 완벽한
// 설계도로 만들어줘야지만 객체를 생성해서 사용할 수 있다.

// 이는 메서드 오버라이딩의 강제성을 주기위해 사용한다.

fun main(){
    // 추상 클래스의 객체 생성
    // 추상 클래스는 완벽한 클래스가 아니기 때문에 객체를 생성하는 것이 불가능 하다.
    // val t1 = TestClass1()

    // 추상 클래스를 상속 받은 클래스의 객체를 생성한다.
    val sub1 = SubClass1()
    sub1.testMethod1()
    sub1.testMethod2()
    sub1.testMethod3()
}

// 추상 클래스는 abstract 키워드를 붙혀줘야 한다.
// 추상 클래스는 상속이 가능해야 하기 때문에 open 키워드를 붙혀줘야 한다.
// 추상 클래스는 상속 밖에 방법이 없기 때문에 open은 생략해도 되겠금 제공된다(구 버전 코틀린에서는 open을
// 반드시 붙혀줘야 하는 경우도 있다.)
open abstract class TestClass1{
    fun testMethod1(){
        println("TestClass1의 testMethod1")
    }

    fun testMethod2(){
        println("TestClass1의 testMethod2")
    }

    // 추상 메서드
    // 추상 메서드는 추상 메서드임을 알리기 위해 abstract 라는 키워드를 붙혀줘야 한다.
    // 추상 메서드는 overriding이 가능해야 하기 때문에 반드시 open을 붙혀줘야 한다.
    // 추상 메서드는 자식 클래스에서 오버라이딩을 해야지만 되기 때문에 open 키워드를 생략해도
    // 되겠금 제공된다(구 버전 코틀린에서는 open을 반드시 붙혀줘야 하는 경우도 있다)
    open abstract fun testMethod3()
}

// 추상클래스를 상속받은 클래스
class SubClass1 : TestClass1(){
    override fun testMethod3(){
        println("SubClass1에서 구현한 testMethod3")
    }
}