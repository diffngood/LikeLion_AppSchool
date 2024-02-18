// 지연 초기화
// 코틀린은 프로퍼티를 정의하면 무조건 값을 저장해야 한다.
// 이는 개발자가 값을 저장하지도 않았는데 프로퍼티를 사용하려고 하는 것을 예방하기 위함.
// 코드를 수행시켜서 얻어진 값을 저장해야 하는 경우 프로퍼티에 일단 아무 값이나 넣어두고
// 나중에 얻어진 값을 저장하도록 해야 한다.
// 지연 초기화를 이용하면 프로퍼티를 정의할 때 저장할 값을 지정하지 않고 나중에 지정해도 된다.

fun main() {
    val t1 = TestClass1()
    println("t1.a1 : ${t1.a1}")
    println("t1.a2 : ${t1.a2}")
    println("t1.a3 : ${t1.a3}")
    println("t1.a4 : ${t1.a4}")

    t1.testMethod1()
}

class TestClass1{
    // Kotlin 에서는 Property를 정의할 때 저장할 값을 무조건 지정해야 한다.
    var a1:Int = 100
    var a2 = 200
    // Property 정의시 저장할 값을 지정하지 않았지만
    // init블럭이나 생성자에서 값을 저장하는 코드를 작성해 두면 오류가 발생하지 않는다.
    // 이는 객체를 생성하면 무조건 init블럭과 생성자의 코드가 동작하기 때문에
    // Property에 값이 저장되는걸 보장받을 수 있기 때문이다.
    var a3:Int
    var a4:Int

    // 지연 초기화
    // init 블럭이나 생성자에서 값을 저장하지 않고 메서드에서 값을 저장하는 경우 사용한다.
    // var 변수만 가능하다.
    // Int, Float, Double과 같은 기본 자료형은 사용할 수 없다.
    lateinit var a5:String

    // val property도 init이나 생성자에서 초기화 해주는 코드를 넣어주면
    // property 정의시 저장할 값을 지정하지 않아도 된다.
    val a6:Int
    val a7:Int
    // lazy를 사용하면 property에 저장될 값을 어떠한 처리를 통해 구해 저장할 수 있다.
    val a8:Int by lazy{
        val temp = 1 + 2 + 3 + 4 + 5
        // 마지막에 작성한 값이나 변수의 값이 property에 저장된다.
        temp
    }

    init {
        a4 = 400
        a6 = 600
    }

    constructor(){
        a3 = 300
        a7 = 700
    }

    fun testMethod1(){
        //  lateinit property는 사용전에 반드시 초기화 되었는지를 검사하는 것이 좋다.
        if(::a5.isInitialized){
            println("step1 : $a5")
        }
        // lateinit property에 값을 저장한다.
        a5 = "안녕하세요"
        if(::a5.isInitialized){
            println("step2 : $a5")
        }
    }
}