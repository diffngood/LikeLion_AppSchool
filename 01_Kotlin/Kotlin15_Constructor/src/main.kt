// 생성자 : 클래스를 통해 객체를 생성하면 무조건 호출되는 요소
// 클래스를 통해 객체를 생성하면 무조건 동작해야 하는 코드가 있을 경우 생성자에 만들어준다.
// 생성자는 매개변수의 형태를 다르게 하여 여러개를 만들 수 있지만 자동으로 호출되는 생성자는 무조건 한개이다.

fun main(){
    // 객체를 생성한다.
    var t1 = TestClass1()
    println("t1 : $t1")

    println("-------------------------------")
    // 객체 생성시 호출될 생성자를 선택해줘야 한다.
    // 클래스이름(매개변수의 형태)

    // 객체 생성시 값을 전달하지 않았기 때문에 매개변수가 없는 생성자가 호출된다.
    val t2 = TestClass2()
    println("t2 : $t2")

    println("-------------------------------")
    // 객체 생성시 정수값 두개를 전달하였기 때문에 정수형 매개변수 두개가 있는 생성자가 호출된다.
    val t3 = TestClass2(100, 200)
    println("t3: $t3")

    println("-------------------------------")
    // 매개 변수가 있는 생성자 호출
    val t4 = TestClass3(100, 200)
    println("t4.v1: ${t4.v1}")
    println("t4.v2: ${t4.v2}")

    // TestClass3은 매개변수가 있는 생성자를 작성해 놨기 때문에
    // 매개변수가 없는 생성자가 자동으로 추가되지 않는다.
    // 오류
    // val t5 = TestClass3()
    println("-------------------------------")

    val t6 = TestClass4(100, 200)
    println("t6.memberA1: ${t6.memberA1}")
    println("t6.memberA2: ${t6.memberA2}")

    println("-------------------------------")

    val t7 = TestClass4()
    println("t7.memberA1: ${t7.memberA1}")
    println("t7.memberA2: ${t7.memberA2}")
    println("-------------------------------")

    val t8 = TestClass5(100,200,300)
    println("t8.a1: ${t8.a1}")
    println("t8.a2: ${t8.a2}")
    println("t8.a3: ${t8.a3}")
    println("-------------------------------")

    val t9 = TestClass6(100,200,300)
    println("t9.a1: ${t9.a1}")
    println("t9.a2: ${t9.a2}")
    println("t9.a3: ${t9.a3}")
    println("-------------------------------")

    val t10 = TestClass7(100,200, 300)
    println("t10.a1: ${t10.a1}")
    println("t10.a2: ${t10.a2}")
    println("t10.a3: ${t10.a3}")
}

// init 코드 블럭
// 객체를 생성할 때 자동으로 동작하는 부분이다.
// Java 코드로 변환될 때 모든 생성자의 가장 윗부분에 코드가 삽입된다.
// 생성자에 만든 코드가 동작하기 전에 동작한다.
// 생성자를 여러개 만들어 제공할 경우 중복되는 코드가 있을 경우 init 코드 블록에 작성해준다.

class TestClass1{
    // init 코드 블럭
    init {
        println("TestClass1의 init 코드 블럭")
        println("객체가 생성될 때 자동으로 동작하는 부분입니다")
        println("Java 코드로 변환될 때 모든 생성자의 상단에 삽입되는 코드입니다")
    }
}

// 생성자
class TestClass2{
    // init 블럭
    init {
        println("TestClass2의 init 코드 블럭입니다")
    }

    // 생성자
    constructor(){
        println("TestClass2의 매개 변수가 없는 생성자")
    }

    // 매개변수의 개수나 타입을 다르게 하면 여러개의 생성자를 제공할 수 있다.
    constructor(a1:Int, a2:Int){
        println("TestClass2의 매개 변수가 있는 생성자")
        println("a1 : $a1")
        println("a2 : $a2")
    }
}

// 생성자를 작성하지 않은 클래스는 매개변수가 없고 코드가 작성되어 있지 않은 생성자가
// 자동으로 추가된다.
// 생성자를 작성하게 되면 매개변수가 없고 코드가 작성되어 있지 않은 생성자가
// 자동으로 추가되지 않는다.
// 이를 통해 생성자를 통해서 값을 무조건 받을 수 있다.
class TestClass3{
    var v1 = 0
    var v2 = 0

    constructor(a1:Int, a2:Int){
        println("TestClass3의 생성자 호출")
        v1 = a1
        v2 = a2
    }
}

// this : 객체가 가지고 있는 맴버를 지칭하기 위해 사용한다.
// 메서드나 생성자의 매개변수 이름과 객체의 맴버 변수의 이름이 같을 때 사용한다.
// 같은 클래스 내의 다른 생성자를 호출하고자 할 때 사용한다.
class TestClass4{
    var memberA1 = 0
    var memberA2 = 0

    constructor(memberA1:Int, memberA2: Int){
        // 여기서 사용하는 memberA1과 memberA2는 매개변수를 지칭한다.
        println("memberA1 : $memberA1")
        println("memberA2 : $memberA2")
        // 생성자나 메서드의 매개변수 이름과 멤버 변수의 이름이 같은 경우
        // 맴버 변수를 사용하고자 한다면 this를 사용한다.
        // this는 객체 자기 자신을 가르키는 키워드이다.
        this.memberA1 = memberA1
        this.memberA2 = memberA2
    }

    // 생성자에서 다른 생성자를 호출한다
    // constructor(매개변수):호출할 다른 생성자(매개변수)
    // 생성자가 호출되면 지정한 다른 생성자를 먼저 호출하여 코드가 수행되고
    // 다른 생성자의 수행이 끝나면 생성자의 코드가 동작한다.
    constructor():this(1000, 2000){
        // this(1000, 2000)
        // 라고 생각해야함 같음
        // [애초에 자바에서도 이런식으로 불가 생성자에서 다른생성자 부를때는 항상 제일 위에 있어야함]
        println("매개변수가 없는 생성자 호출")
    }
}

// 주 생성자
// 하나의 클래스에서 생성자가 여러개 있을 경우 주 생성자를 지정할 수 있다.
// 주 생성자가 아닌 다른 생성자들은 모두 주 생성자를 무조건 호출해야 한다.

// 주 생성자의 매개변수는 모두 멤버 변수로 정의된다.
class TestClass5{
    var a1 = 0
    var a2 = 0
    var a3 = 0

    constructor(a1:Int, a2: Int, a3: Int){
        this.a1 = a1
        this.a2 = a2
        this.a3 = a3
    }
}

// 주 생성자 사용
// constructor에 명시한 변수들은 멤버 변수로 정의가 되고
// 매개 변수 3개를 가지고 있는 생성자가 자동으로 추가된다.
// 이 생성자는 매개변수로 들어오는 값을 멤버 변수에 저장해주는 코드가 삽입된다.
// TestClass5와 같은 형태로 변환된다.
// class TestClass6 constructor(var a1:Int, var a2: Int, var a3:Int){ }
// 주 생성자 정의시 constructor는 생략해도 된다
class TestClass6(var a1:Int, var a2: Int, var a3:Int)

// 만약 주 생성자에 정의하지 않은 멤버 변수가 있다면
class TestClass7(var a1:Int,
                 var a2: Int){
    // 주 생성자에 정의하지 않은 멤버 변수
    var a3:Int = 0

    // 다른 생성자를 하나 만들어서 주생성자에 정의하지 않은 변수도 값을 넣어줄 수 있어야 한다.
    // 이때, 추가 적으로 작성한 생성자는 반드시 주 생성자를 호출해야 한다.
    // 생성자():this() 형태로 하여 주 생성자를 반드시 호출해줘야 한다.
    constructor(a1:Int, a2:Int, a3:Int) : this(a1, a2) {
        this.a3 = a3
    }
}