import jdk.incubator.vector.VectorOperators.Test

// 정적 맴버
// 변수인 경우에는 프로그램 전체에서 딱 하나만 만들어 사용하는 변수를 의미한다.
// 정적 맴버는 객체 생성 없이 바로 사용하는 요소이다.
// 하지만 딱 하나만 생성되기 때문에 객체의 맴버로 취급되지 않는다.

// Java 언어에서는 static라는 것을 사용한다.

fun main(){
    // 일반 멤버 변수는 객체를 생성할 때 마다 계속 속성된다.
    val t1 = TestClass1(100)
    val t2 = TestClass1(200)
    println("t1.a1 : ${t1.a1}")
    println("t2.a1 : ${t2.a1}")

    t1.a1 = 1000
    println("t1.a1 : ${t1.a1}")
    println("t2.a1 : ${t2.a1}")

    // 정적 맴버는 객체를 생성하지 않고 사용한다.
    // 클래스명.맴버이름
    println("TestClass1.a2 : ${TestClass1.a2}")
    // 정적 맴버는 객체를 통한 접근이 불가능하다.
    // println("t1.a2 : ${t1.a2}")
    // println("t2.a2 : ${t2.a2}")
    TestClass1.testMethod2()

    t1.testMethod1()

    // Java 파일에 정의된 static 맴버 사용
    // Java 파일에 정의된 정적 맴버 사용은 자유롭게 하시면 됩니다.
    println("JavaMain.javaValue1 : ${javaMain.javaValue1}")
    javaMain.javaMethod1()
}

class TestClass1{
    // 일반 멤버 변수
    var a1:Int

    // 정적 멤버 정의
    companion object{
        // 정적 멤버 변수
        var a2:Int = 100
        
        // 정적 멤버 메서드
        fun testMethod2(){
            // 정적 프로퍼티 사용
            println("testMethod2 - a2 : $a2")

            // 정적 메서드에서 일반 멤버 프로퍼티를 사용을 시도한다. -> println("testMethod2 - a1 : $a1")

            // 정적 멤버는 객체를 생성하지 않아도 사용 할 수 있다.
            // 이제, 정적 멤버 입장에서는 객체가 생성되었지 않을 가능성, 객체가 여러 개가 만들어져 있어서
            // 어떤 객체의 멤버인지 확인 할 수 없는 등의 여러가지 이유때문에 사용 불가능하다.
            // a1은 객체 생성이 안되있어서 사용불가
            // println("testMethod2 - a1 : $a1")
        }
    }

    constructor(a1:Int){
        this.a1 = a1
    }

    // 일반 멤버 메서드
    fun testMethod1(){
        println("a1 : $a1")
        // 일반 멤버 메서드에서는 정적 맴버를 사용할 수 있다.
        // 정적 맴버들은 프로그램이 시작할 때 이미 메모리에 올라가있다.
        // 그 후에 개발자가 만든 코드가 동작하기 때문에 객체 생성 시점보다 정적 멤버들이
        // 메모리에 올리가는 시점이 더 앞서 있다. 이에 사용이 가능하다.
        // 같은 클래스에 있는 정적 맴버 접근시에는 클래스의 이름을 생략할 수 있다.
        println("a2 : $a2")
        testMethod2()
    }
}

class TestClass2{

    companion object{
        var kotlinValue1 = 1000

        fun kotlinMethod1(){
            println("kotlinMethod1")
        }
    }
}