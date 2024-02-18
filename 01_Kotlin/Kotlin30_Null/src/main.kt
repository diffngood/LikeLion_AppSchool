import jdk.incubator.vector.VectorOperators.Test

// null : 값은 값인데 의미가 없는 값을 의미
// Java에서 객체의 주소 값을 담을 변수에 아직 담을 객체의 주소값이 없을 경우 넣어주는 값
// 코틀린 지연 초기화가 있기 때문에 변수에 null을 넣어주지 않아도 된다.
// 하지만 호출하는 메서드가 null을 반환하는 경우도 있기 때문에 null 값에 대한 대비가 필요하다

// null 안전성 : 변수를 통해 객체를 사용할 때 null 값이 들어 있기 때문에 발생되는 오류를 예방하는 작업
// 변수에 null 값이 들어가지 못하도록 해준다.
// 변수에 null 값이 들어가있을 경우 객체의 접근을 막아준다.
// 변수에 null 값이 들어가 있을 경우 메서드의 호출이나 프로퍼티 사용을 취소시킨다.
// 변수에 null 값이 들어가 있을 경우 메서드를 호출하거나 프로퍼티 사용을 취소 시키고 지정된 기본값을 사용하게 한다.


fun main(){
    // null을 허용하지 않는 프로퍼티
    // var a1:TestClass1 = null
    // null을 허용하는 프로퍼티
    // null을 허용하는 프로퍼티를 선언할 때는 타입을 반드시 작성해줘야 한다.
    var a2:TestClass1? = null
    // 변수에 null 값이 들어있는 상태에서 메서드를 호출한다.
    // a2!!.testMethod1()

    // !! : null을 허용하는 변수에 저장되어 있는 값을 null을 허용하지 않는 형태로 변환한다.
    // null을 허용하는 변수에 저장되어 있는 값을 null을 허용하지 않는 변수에 담을 때 사용한다.
    // 이 때, null값이 들어 있다면 오류가 발생한다.
    val t1:TestClass1 = TestClass1()
    val t2:TestClass1? = TestClass1()
    val t3:TestClass1? = null

    // null을 허용하지 않는 타입 변수에 null을 허용하지 않는 타입의 변수의 값을 넣어준다.
    val t4:TestClass1 = t1
    // null을 허용하는 타입의 변수 뒤에 !!를 붙혀서 null을 허용하는 타입의 값으로 변경한다.
    // 이때 null 값이 아닌 객체의 주소값이 들어 있으므로 정상 변환된다.
    val t5:TestClass1 = t2!!
    println("t4 : $t4")
    println("t5 : $t5")
    // null을 허용하는 타입의 변수 뒤에 !!를 붙혀서 null을 허용하는 타입의 값으로 변경한다.
    // 이 때 null 값이 들어 있으므로 변환때 오류가 발생한다.
    // 변수에 null아닌 객체의 주소값이 들어 있다는 것을 100% 보장할 수 있을 경우에만 사용한다.
    // val t6:TestClass1 = t3!!
    // println("t6 : $t6")

    // 변수 ?: 기본값 : null 값을 허용하는 변수를 사용할 때 null이 들어가 있을 경우 객체 대신에
    // 기본값으로 설정되어 있는 것을 전달해 준다.
    // 변수에 null 값이 들어가 있을 경우 객체를 생성해서 반환해주는 작업을 할 때 사용한다.
    var t7:TestClass1? = null
    var t8:TestClass1? = TestClass1()

    var t9:TestClass1 = t7 ?: TestClass1()
    var t10:TestClass1 = t8 ?: TestClass1()

    println("t7 : $t7, t9 : $t9")
    println("t8 : $t8, t10 : $t10")

    // ? : 객체가 가지고 있는 프로퍼티나 메서드를 사용할 때 사용하는 연산자이다.
    // 변수에 null이 들어가 있으면 수행이 무시된다.
    // 변수에 객체의 주소값이 들어가 있다면 객체에 접근해 프로퍼티나 매서드를 사용한다.
    val t11:TestClass1? = null
    val t12:TestClass1? = TestClass1()

    // 프로퍼티
    // 객체를 가지고 있는 변수에 null이 들어가 있으면 프로퍼티 접근이 중단되고
    // null을 반환한다.
    println("t11.testValue1 : ${t11?.testValue1}")
    println("t12.testValue1 : ${t12?.testValue1}")

    // 메서드
    // null이 들어가 있으면 메서드 호출이 취소된다.
    t11?.testMethod1()
    t12?.testMethod1()
}

class TestClass1{

    val testValue1 = 100

    fun testMethod1(){
        println("TestClass1의 testMethod1")
    }

    fun testMethod2():Int{
        return 100
    }
}
