// this : 객체 자기 자신을 가르킨다.
// 변수 : property(멤버 변수)를 지칭하는 용도로 사용한다.
// 생성자 : 다른 생성자를 호출하기 위해 사용한다.
// 메서드 : 멤버 메서드와 지역 메서드를 구분하기 위해 사용한다.

// super : 부모를 가르킨다.
// 변수 : 객체가 가지고 있는 property(변수)와 부모가 가지고 있는 property(변수)를 구분하기 위해 사용한다.
// 생성자 : 부모의 생성자를 호출하기 위해 사용한다.
// 메서드 : 자식에서 overriding한 메서드와 부모의 메서드를 구분하기 위해 사용한다.

fun main() {
    val t1 = TestClass1()
    t1.testMethod1(200)

    val t2 = TestClass2()
    println("t2 : $t2")

    t1.testMethod2()

    val sub1 = SubClass1()
    sub1.subMethod1(300)

    sub1.subMethod2()
}

class TestClass1 {
    // 프로퍼티
    var value1 = 100

    // 매개변수
    fun testMethod1(value:Int){
        // 프로퍼티의 이름과 매개변수의 이름이 같을 경우
        // 매개변수를 사용한다.
        println("value1 : $value1")
        // 프로퍼티의 이름과 매개변수의 이름이 같은 경우
        // 매개변수가 아닌 프로퍼티를 사용하겠다면
        // this를 통해 객체에 접근해 사용하면 된다.
        println("this.value1 : ${this.value1}")
    }

    // 코틀린은 메서드(외부) 안에 메서드(내부)를 만들 수 있다.
    // 메서드(외부)내의 메서드(내부)는 메서드(외부)에서만 사용이 가능하다.
    fun testMethod2(){

        fun innerMethod1(){
            println("innerMethod1 호출")
        }
        innerMethod1()

        // 클래스의 멤버 메서드와 동일한 메서드를 내부에 만들어준다.
        fun testMethod3(){
            println("testMethod2 메서드의 내부 메서드 testMethod3")
        }

        // 메서드 내부에 만든 메서드가 호출된다.
        testMethod3()
        // 만약 멤버 메서드를 호출하고 싶다면 this를 이용하여 호출한다.
        this.testMethod3()
    }

    fun testMethod3(){
        println("TestClass1의 testMethod3")
    }
}

class TestClass2{
    // this( ) : 다른 생성자를 호출한다.
    // 만약 클래스에 주 생성자가 정의되어 있다면 무조건 주 생성자를 반드시 호출하도록 해야 한다.
    constructor() : this(100){
        println("매개 변수가 없는 생성자 호출")
    }
    constructor(a1:Int){
        println("매개 변수가 있는 생성자 호출")
        println("a1 : $a1")
    }
}

open class SuperClass1{
    // 자식 클래스에서 동일한 이름의 프로퍼티를 만드는 것을 허용하겠다면 open을 붙혀준다.
    open var superValue1 = 100
    
    // 생성자
    constructor(){
        println("SuperClass1의 생성자")
    }

    constructor(a1:Int){
        println("매개 변수를 가지고 있는 SuperClass1의 생성자")
        println("a1 : $a1")
    }

    // 자식 클래스에서 메서드를 재 구현(overriding)을 허용하겠다면 open을 붙혀준다.
    open fun superMethod2(){
        println("SuperClass1의 superMethod2")
    }
}

class SubClass1 : SuperClass1{
    // 부모 클래스에 정의된 프로퍼티와 동일한 이름의 프로퍼티를 만들고자 한다면
    // 부모가 가지고 있는 프로퍼티 중에 open 프로퍼티만 가능하며
    // 앞에 override 키워드를 붙혀줘야 한다.
    override var superValue1 = 200
    
    // 코틀린의 모든 클래스는 생성자에서 부모 클래스의 생성자 호출을 명시하지 않으면
    // 자동으로 부모의 생성자 중 매개변수가 없는 생성자가 호출된다.
    // 만약 호출되는 부모의 생성자를 명시적으로 지칭하겠다면 super를 사용한다.
    constructor() : super(1000){
        println("SubClass1의 생성자 호출")
    }

    fun subMethod1(superValue1:Int){
        // 매개변수 사용
        println("superValue1 : $superValue1")
        // 매개변수의 이름과 프로퍼티의 이름이 같기 때문에 프로퍼티에 접근하려면
        // this를 사용해야 한다.
        // 만약 동일명의 매개변수가 없다면 this는 생략 가능하다.
        println("this.superValue1 : ${this.superValue1}")
        // 매개변수의 이름이나 멤버 프로퍼티의 이름이 부모의 프로퍼티 이름과 동일할 경우
        // super를 통해 부모의 프로퍼티에 접근한다.
        println("super.superValue1 : ${super.superValue1}")
    }

    // 부모 클래스의 메서드를 overriding 한다.
    override fun superMethod2(){
        println("SubClass1의 superMethod2")
    }

    fun subMethod2(){
        superMethod2()
        // 부모의 메서드를 자식에서 재 구현한 경우 부모의 메서드를 호출하고자 한다면
        // super를 사용한다
        super.superMethod2()
    }
}