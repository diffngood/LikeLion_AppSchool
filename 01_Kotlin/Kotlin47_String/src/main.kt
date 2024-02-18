
fun main() {
    // 문자열 생성

    // String 클래스의 생성자에
    // StringBuffer, StringBuilder, ByteArray, CharArray
    // 를 넣어주면 가지고 있는 글자 데이터를 이용해
    // 문자열을 생성한다.
    val array1 = CharArray(5){
        'a'
    }
    val str1 = String(array1)
    println("str1 = $str1")
    
    // ""로 묶어준 것도 String 객체를 생성한 것이다.
    val str2 = "안녕하세요"

    // 원하는 번째의 글자를 가져올 수 있다.
    println("str2[0] : ${str2[0]}")
    println("str2[1] : ${str2[1]}")

    // 원하는 번째의 글자를 변경한다.
    // String 객체는 문자를 변경하는 것이 불가능하다.
    // str2[0] = 'A'

    // 원하는 부분의 글자들을 추출하여 새로운 문자열로 생성한다.
    // 두번째 ~ 네번째
    val str3 = str2.substring(1..3)
    println("str3 : $str3")

    // 문자열 비교
    val str4 = "Hello World"
    val str5 = "hello world"
    val str6 = "Hello World"

    // == : 자바에서는 문자열 객체의 주소값을 비교하지만
    // 코틀린에서는 문자열 값 자체를 비교한다.
    if(str4 == str5){
        println("str4와 str5는 같습니다")
    } else {
        println("str4와 str5는 다릅니다")
    }

    if(str4 == str6){
        println("str4와 str6은 같습니다")
    } else {
        println("str4와 str6은 다릅니다")
    }

    // compareTo : 두 문자열을 비교하는 메서드
    // 문자열을 구성하는 글자의 코드값을 합산하고 그걸 뺀 결과를 반환한다.
    println(str4.compareTo(str5))
    println(str4.compareTo(str6))

    // compareTo의 두 번째 매개변수에 true를 넣어주면
    // 모두 소문자로 변환하여 계산해준다.
    println(str4.compareTo(str5, true))

    // equals 메서드의 두 번째 매개변수에 true를 넣어주면 대소문자를 무시하고 같은지 비교해준다.
    if(str3.equals(str4, true)){
        println("두 문자열은 대소문자를 무시하면 같습니다")
    }

    // 구분자를 기준으로 문자열을 나눈다.
    var str7 = "ab cd ef gh"

    // 띄어쓰기를 기준으로 나눈다.
    var r6 = str7.split(" ")
    println("r6 : $r6")

    for (temp6 in r6){
        println(temp6)
    }

    // upperCase : 소문자를 대문자로 하는 문자열을 생성한다.
    // lowerCase : 대문자를 소문자로 하는 문자열을 생성한다.
    val str8 = str4.uppercase()
    val str9 = str4.lowercase()

    println("str8 : $str8")
    println("str9 : $str9")

    // startsWith : ~ 로 시작하는지..
    // endsWith : ~ 로 끝나는지..
    val r10 = str4.startsWith("H")
    val r11 = str4.startsWith("A")
    val r12 = str4.endsWith("d")
    val r13 = str4.endsWith("A")

    println("r10 : $r10")
    println("r11 : $r11")
    println("r12 : $r12")
    println("r13 : $r13")

    // 글자의 개수를 반환한다.
    println("str4의 글자수 : ${str4.length}")

    // 문자열 좌우 공백을 제거한다.
    val str20 = "   aaa    "
    println("[${str20}]")
    println("[${str20.trim()}]")


}