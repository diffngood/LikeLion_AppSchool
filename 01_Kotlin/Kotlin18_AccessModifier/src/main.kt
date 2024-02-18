// 클래스
// private : 파일이 같을 경우에만 사용이 가능하다.
// public : 다른 파일, 다른 패키지, 다른 모듈에서도 사용이 가능하다.(기본)
// internal : 패키지나 모듈이 다르면 사용이 불가능하다

// 변수, 메서드
// private : 모든 경우에 사용이 불가능하다
// public : 모든 경우에 사용이 가능하다(기본)
// protected : 상속 관계에서만 사용이 가능하다. 패키지, 모듈이 달라도 사용 가능하다.
// internal : 모듈이 다르면 사용이 불가능하다

// 생략하면 public으로 설정되고 internal의 경우 모듈이 같으면 public과 동일하다

import kr.co.lion.pkg1.*
import kr.co.lion.pkg2.*

fun main() {
    // 같은 파일에 있는 클래스 사용
    // 접근 제한자에 관계없이 모두 사용이 가능하다.
    println("같은 모듈, 같은 패키지, 같은 파일")
    val obj10 = PrivateClass1()
    val obj11 = PublicClass1()
    val obj12 = InternalClass1()
    println("obj10 : $obj10")
    println("obj11 : $obj11")
    println("obj12 : $obj12")

    // 같은 모듈, 같은 패키지, 다른 파일
    // private는 파일이 다르면 사용이 불가
    // val obj20 = PrivateClass2()
    // public은 모주건 사용 가능
    println("같은 모듈, 같은 패키지, 다른 파일")
    val obj21 = PublicClass2()
    // 모듈이 같다면 public과 동일하다
    val obj22 = InternalClass2()
    // println("obj20 : $obj20")
    println("obj21 : $obj21")
    println("obj22 : $obj22")
    
    // 같은 모듈, 다른 패키지
    // 파일이 다르므로 사용 불가
    // val obj30 = PrivateClass3()
    println("같은 모듈, 다른 패키지")
    val obj31 = PublicClass3()
    val obj32 = InternalClass3()
    // println("obj30 : $obj30")
    println("obj31 : $obj31")
    println("obj32 : $obj32")

    // 다른 모듈
    println("다른 모듈")
    // val obj40 = PrivateClass4()
    val obj41 = PublicClass4()
    // Internal은 모듈이 다르면 사용이 불가능하다
    // val obj42 = InternalClass4()
    // println("obj40 : $obj40")
    println("obj41 : $obj41")
    // println("obj42 : $obj42")

    // 같은 파일에 있는 클래스의 객체를 생성하여 변수나 메서드에 접근한다
    println("같은 모듈, 같은 패키지, 같은 파일")
    val obj50 = SuperClass1()
    // private는 절대 사용 불가
    // println("obj50.a10 : ${obj50.a10}")
    println("obj50.a11 : ${obj50.a11}")
    // protected 객체 생성을 통한 사용에는 접근이 불가
    // println("obj50.a12 : ${obj50.a12}")
    println("obj50.a13 : ${obj50.a13}")

    // 같은 모듈, 같은 패키지, 다른 파일
    println("같은 모듈, 같은 패키지, 다른 파일")
    val obj60 = SuperClass2()
    // println("obj60.a20 : ${obj60.a20}")
    println("obj60.a21 : ${obj60.a21}")
    // 상속관계가 아니기 때문에 오류 / 상속관계에만 접근 가능함
    // println("obj60.a22 : ${obj60.a22}")
    println("obj60.a23 : ${obj60.a23}")

    // 같은 모듈, 다른 패키지
    println("같은 모듈, 다른 패키지")
    val obj70 = SuperClass3()
    // println("obj60.a30 : ${obj70.a30}")
    println("obj70.a31 : ${obj70.a31}")
    // println("obj60.a32 : ${obj70.a32}")
    println("obj70.a33 : ${obj70.a33}")

    // 다른 모듈
    println("다른 모듈")
    val obj80 = SuperClass4()
    // println("obj60.a40 : ${obj60.a20}")
    println("obj60.a41 : ${obj80.a41}")
    // println("obj60.a42 : ${obj60.a22}")
    // println("obj60.a43 : ${obj80.a43}")
}

// 같은 파일에 있는 클래스
open private class PrivateClass1
open public class PublicClass1
// 클래스에는 protected를 붙힐 수 없다.
// protected class ProtectedClass1
open internal class InternalClass1

// 같은 파일에 있는 클래스 상속
// 상속시 자식 클래스는 부모클래스의 접근 제한자를 따라야한다.
// 같은 파일에 있는 모든 클래스들은 상속이 자유롭다
private class TestClass1 : PrivateClass1()
public class TestClass2 : PublicClass1()
internal class TestClass3 : InternalClass1()

// 같은 모듈, 같은 패키지, 다른 파일
// private class TestClass4 : PrivateClass2()
public class TestClass5 : PublicClass2()
internal class TestClass6 : InternalClass2()

// 같은 모듈, 다른 패키지
// private class TestClass7 : PrivateClass3()
public class TestClass8 : PublicClass3()
internal class TestClass9 : InternalClass3()

// 다른 모듈
// private class TestClass10 : PrivateClass4()
public class TestClass11 : PublicClass4()
// internal class TestClass12 : InternalClass4()

// 같은 파일의 클래스
open class SuperClass1{
    private var a10 = 100
    public var a11 = 101
    protected var a12 = 102
    internal var a13 = 103
}

// 같은 파일의 클래스 상속
class SubClass1 : SuperClass1(){
    fun subMethod1(){
        // println("a10 : $a10")
        println("a10 : $a11")
        println("a10 : $a12")
        println("a10 : $a13")
    }
}

// 같은 모듈, 같은 패키지, 다른 파일
class SubClass2 : SuperClass2(){

    fun subMethod2(){
        // println("a20 : $a20")
        println("a21 : $a21")
        println("a22 : $a22")
        println("a23 : $a23")
    }
}

// 같은 모듈, 다른 패키지
class SubClass3 : SuperClass3(){

    fun subMethod3(){
        // println("a30 : $a30")
        println("a31 : $a31")
        println("a32 : $a32")
        println("a33 : $a33")
    }
}

// 같은 모듈, 다른 패키지
class SubClass4 : SuperClass4(){

    fun subMethod3(){
        // println("a40 : $a40")
        println("a41 : $a41")
        println("a42 : $a42")
        // println("a43 : $a43")
    }
}