import java.lang.Exception

// 예외 : 대처가 가능한 오류들을 의미한다.
// 자바에서는 예외 상황에 대한 것들을 클래스로 만들어 제공하고 있다.
// 예외가 발생하면 발생된 예외와 관련된 클래스의 객체를 생성하여 개발자에게 전달해준다.
// 이 객체에는 오류와 관련된 정보가 담겨져 있다.

// try : 개발자가 구현하는 코드 부분
// catch : try 부분에서 예외가 발생했을 때 동작하는 코드
// try 부분에서 예외가 발생되면 try 부분의 수행은 중단되고 catch 부분으로 넘어간다.

// 예외가 발생되면 VM은 프로그램을 강제 종료시킨다.
// 예외 처리의 목적은 예외 발생시 프로그램이 강제 종료되지 않도록 하고 catch에 작성한 코드가
// 동작될 수 있도록 함에 있다.



fun main() {

    try {
         val a1 = 10 / 0

         val str:String? = null
         println(str!!.length)

        val str2 = "안녕하세요"
        val a2: Int = str2.toInt()
    } catch (e:ArithmeticException) {   // 10을 0으로 나눴을 때의 예외 처리
        // e.printStackTrace()
        println("수학오류가 발생하였습니다")
    } catch (e:NullPointerException){   // null을 가진 변수를 통해 객체에 접근하려고 했을 때의 예외 처리
        println("널 접근 오류가 발생하였습니다")
    } catch (e:NumberFormatException){  // 숫자로 변환할 수 없는 문자열을 숫자로 변환하고자 할 때의 예외 처리
        println("숫자 양식 오류")
    } catch (e:Exception){              // 그 밖의 모든 예외에 대한 처리
        println("그 밖의 오류가 발생하였습니다")
    }

    println("이 부분이 수행 될까요?")

}