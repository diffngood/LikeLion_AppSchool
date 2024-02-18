// 상속
// 하나의 클래스가 다른 클래스에 작성한 요소를 물려받는 개념
// 클래스를 통해 객체를 생성하면 클래스에 작성한 부분 뿐만 아니라 물려주는 쪽이 가지고 있는 부분도
// 가지고 있게 된다.
// 물려 주는 쪽을 부모클래스(Super Class)라고 부르고 물려 받는 쪽을 자식 클래스(Sub Class)라고 부른다.

// 코틀린에서 부모 클래스의 역할을 할 수 있도록 하겠다면 open 키워드를 붙혀 줘야 한다.

fun main() {
    // 자식 클래스를 통해 객체를 생성한다.
    val s1 = SubClass1()
    println(s1.superA1)
    println(s1.SubA2)
    s1.superMethod1()
    s1.subMethod2()

    // 부모클래스의 객체를 생성한다
    val s2 = SuperClass1()
    // 부모클래스는 자식클래스에 작성한 요소를 사용할 수 없다.
    // 부모클래스를 작성할 당시에는 자식 클래스가 어떤 것들이 있는지 알수도 없고
    // 부모클래스 작성 코드에는 자식클래스가 무엇인지 명시하는 곳이 없기 때문에
    // 객체를 생성할 때 자식클래스 부분을 생성할 수 없다.
    // 오류
    // println(s2.SubA2)
    // s2.subMethod2()

    println("------------ s3 -------------")
    val s3 = SubClass2()
    println("s3 : $s3")

    println("------------ s4 -------------")
    val s4 = SubClass2(100)
    println("s4 : $s4")

    println("------------ s5 -------------")
    val s5 = SubClass3()
    println("s5 : $s5")

    println("------------ s6 -------------")

}

// 부모클래스는 open 키워드를 붙혀줘야 한다.
open class SuperClass1{
    var superA1 = 100

    fun superMethod1(){
        println("SuperClass1의 superMethod1")
        // 부모쪽에서는 자식것에 접근할 수 없다.
        // println("SubA2 : $SubA2")
        // subMethod2( )
    }
}

// class 클래스이름 : 부모클래스이름( )
class SubClass1 : SuperClass1(){
    var SubA2 = 200

    fun subMethod2(){
        println("SubClass1의 subMethod2")
        // 자식클래스의 메서드에는 부모의 요소를 사용할 수 있다.
        println("superA1 : $superA1")
        superMethod1()
    }
}

// 상속과 생성자와의 관계
open class SuperClass2{

    constructor(){
        println("SuperClass2의 매개변수가 없는 생성자")
    }
    constructor(a1:Int){
        println("SuperClass2의 매개변수가 있는 생성자")
    }
}

// SuperClass2를 상속받은 클래스
// 자식클래스는 부모 클래스의 생성자를 반드시 호출해야 한다.
class SubClass2 : SuperClass2{
    constructor(){
        // 부모클래스의 생성자를 명시적으로 호출하지 않으면
        // 부모 클래스가 가지고 있는 생성자 중 매개변수가 없는 생성자를
        // 자동으로 호출한다
    }

    constructor(a1:Int):super(a1){
        // 만약 부모가 가지고 있는 생성자 중 매개변수가 있는 다른 생성자를 호출하겠다면
        // 명시적으로 호출해주면 된다.
        // super : 상속관계에서 부모를 지칭하는 키워드
    }
}

// 부모의 생성자 호출은 다음과 같이 작성해도 된다.
class SubClass3 : SuperClass2(100){

}