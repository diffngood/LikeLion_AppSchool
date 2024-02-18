// 패키지 (Package) : 작업하는 파일들을 폴더별로 나눠서 관리하는 개념
// 물리적인 폴더를 만들어 사용한다.

// import를 이용해 사용하고자 하는 것을 명시해 주면
// 패키지명을 생략하고 사용할 수 있다.
// 아래의 코드는 이 파일에서 TestClass2를 사용하면
// kr.co.lion.pkg1에 있는 것임을 알려주는 것이다.
import kr.co.lion.pkg1.TestClass2

// 패키지가 다르다고 하더라도 동일명의 클래스를 import 해주는 것은 불가능하다.
// VM 입장에서 클래스가 어떤 패키지 있는 것인지 명확하지 않기 때문이다.
// import kr.co.lion.pkg2.TestClass2

// 특정 패키지에 있는 모든 클래스를 import 하고 싶을 때 *를 사용한다.
// kr.co.lion.pkg3에 있는 모든 클래스들은 패키지명을 생략하고 사용할 수 있다.
// 주의 : pkg3에 있는 클래스와 이름은 같지만 패키지가 다른 클래스를 import 하게되면
// 오류가 발생한다.
import kr.co.lion.pkg3.*
import java.awt.List
import java.util.ArrayList

fun main() {
    // 다른 패키지의 클래스를 사용한다.
    // 사용하고자 하는 클래스와 똑같은 이름의 클래스가 여러 패키지에 있을 수도 있기 때문에
    // 내가 사용하고자 하는 클래스가 어떤 패키지에 있는 것인지를 명시해줘야 한다.
    val obj1 = kr.co.lion.pkg1.TestClass1()
    println("obj1.t1 : ${obj1.t1}")
    obj1.t1Method()
    println()

    // TestClass2는 import로 명시하였기 때문에 패키지명을 생략할 수 있다.
    val obj2 = TestClass2()
    println("obj2.t2 : ${obj2.t2}")
    obj2.t2Method()
    println()

    // 이미 import 되어 있는 클래스 이름과 동일한 다른 패키지의 클래스를 사용할 때는
    // 패키지명을 생략할 수 없다.
    val obj22 = kr.co.lion.pkg2.TestClass2()
    println("obj22.t22 : ${obj22.t22}")
    obj22.t22Method()
    println()

    // pkg3 에 있는 모든 클래스들을 import 하였기 때문에 패키지명을 생략하고 사용할 수 있다.
    val obj3 = TestClass3()
    val obj4 = TestClass4()
    obj3.t3Method()
    obj4.t4Method()

    // 개발도구가 제공하는 자동완성 기능을 이용해주세요~~~
    // var obj5 = List()
    // val obj6 = ArrayList()