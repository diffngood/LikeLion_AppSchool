// 객체 지향 프로그래밍(OOP, Object Oriented Programming)
// 프로그램이 가져야 하는 다양한 기능들이나 변수들을 객체라는 단위로 나눠서 관리하는 기법
// 객체지향 프로그래밍 기법에서는 객체를 만들기 위한 클래스를 설계하고 클래스를 통해
// 필요한 만큼의 객체를 생성한다
// 각 객체들은 서로 독립적인 존재이며 하나의 객체에는 그 객체가 가져야할 모든 변수(맴버 변수)와 모든
// 함수(메서드)를 가지고 있어야 한다.
fun main(){
    // 객체를 생성한다.
    // 객체가 생성되면 생성된 객체는 메모리에 자리를 잡는다.
    // 생성된 객체를 출력하면
    // 클래스이름@객체의일련번호 형태로 출력된다.
    println(TestClass1())
    // 객체가 생성되면 객체를 구분하기 위한 일련번호(흔히 객체의 주소값이라고 부른다)가
    // 생성된다. 이 객체의 주소값은 모든 객체에게 다르게 부여된다.
    // 객체는 객체의 주소값을 가지고 객체를 구분하고 지칭할 수 있다.
    // 객체를 생성하게 되면 이 객체의 주소값이 반환되고 이를 변수에 담아 두었다가
    // 객체를 사용하고자 할 때 객체의 주소값을 가지고 있는 변수를 통해 객체에 접근할 수 있다.
    val obj1:TestClass1 = TestClass1()
    println("obj1 : $obj1")

    val obj2 = TestClass2()
    println("obj2 : $obj2")

    val obj3 = TestClass3()
    // 객체가 가지고 있는 맴버 변수나 맴버 메서드를 사용하려면
    // . 연산자를 사용한다.
    obj3.a1 = 100
    obj3.a2 = 200
    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")

    obj3.testMethod1()
    obj3.testMethod2()
}

// 클래스를 정의한다.
// class 클래스이름
class TestClass1{

}

// 코틀린에서는 클래스 내부에 코드가 없다면 { } 생략이 가능하다.
class TestClass2

// 객체가 가지는 변수를 맴버 변수라고 부른다.
// 객체가 가지는 함수를 맴버 메서드라고 부른다.
class TestClass3{

    // 맴버 변수
    var a1 = 0
    var a2 = 0

    // 맴버 메서드
    fun testMethod1(){
        println("testMethod1")
    }

    fun testMethod2(){
        println("testMethod2")
    }
}






