import java.util.Scanner

// 학생 3명들의 이름, 국어, 영어, 수학점수를 관리하는 프로그램을 작성한다.
// 학생의 정보는 모두 입력을 받는다.
// 입력이 완료된 후에 각 학생별 정보를 출력하고
// 각 과목별 총점과 평균을 출력한다.

// step1) 출력 화면을 구현한다.
// 출력화면 구현시 필요한 데이터는 임의의 데이터로 설정해준다.

// step2) 프로그램에서 필요한 기능들을 정리한다.
// 1. 학생의 이름을 입력받는 기능
// 2. 점수를 입력받는 기능
// 3. 평균을 구하는 기능
// 4. 총점을 계산하는 기능
// 5. 출력하는 기능

// step3) step3에서 정리한 기능들을 그룹으로 묶어 준다.
// 1. 학생의 정보를 입력받는 기능 -> 학생
// 2. 평균을 구하는 기능 -> 학생들
// 3. 총점을 계산하는 기능 -> 학생들
// 4. 각 학생들 정보를 출력하는 기능 -> 학생
// 5. 학생들의 과목별 총점과 평균을 출력하는 기능 -> 학생들

// step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리한다.
// 필요할 데이터 : 화면을 구성하기 위해 필요한 데이터, 입력받는 데이터, 발생되는 데이터, 계산되는 데이터 등등
// 학생의 이름
// 학생의 국어 점수
// 학생의 영어 점수
// 학생의 수학 점수
// 국어 총점
// 영어 총점
// 수학 총점
// 국어 평균
// 영어 평균
// 수학 평균

// step5) step4에서 정리한 변수들을 그룹으로 묶어 준다.
// 학생의 이름 -> 학생
// 학생의 국어 점수 -> 학생
// 학생의 영어 점수 -> 학생
// 학생의 수학 점수 -> 학생
// 국어 총점 -> 학생들
// 영어 총점 -> 학생들
// 수학 총점 -> 학생들
// 국어 평균 -> 학생들
// 영어 평균 -> 학생들
// 수학 평균 -> 학생들

// step6) 클래스를 정의한다.
// 클래스 내부에 변수를 작성해주고 메서드를 작성해준다.
// 메서드의 코드는 아직 작성하지 않는다.

// step7) 메서드 내부의 코드를 작성하고 이를 테스트 한다.

// step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램을 완성한다.

// 주의!!! main에서는 절대로 객체가 관리하는 변수에 직접 접근하지 마세요

fun main(){
    // printResult()

    // Student 클래스의 inputStudentInfo 테스트
//    val scanner = Scanner(System.`in`)
//    val s1 = StudentClass()
//    s1.inputStudentInfo(scanner)
//
//    println(s1.name)
//    println(s1.kor)
//    println(s1.eng)
//    println(s1.math)

    // StudentClass의 printStudentInfo 메서드 테스트
//    val s1 = StudentClass()
//    s1.name = "홍길동"
//    s1.kor = 100
//    s1.eng = 90
//    s1.math = 80
//
//    s1.printStudentInfo()

    // StudentManagerClass의 getAvg 메서드 테스트
//    val manager1 = StudentManagerClass()
//    manager1.getAvg()
//    println(manager1.korAvg)
//    println(manager1.engAvg)
//    println(manager1.mathAvg)

    // StudentManagerClass의 getTotal 메서드 테스트
//    val manager1 = StudentManagerClass()
//    manager1.getTotal()
//    println(manager1.korTotal)
//    println(manager1.engTotal)
//    println(manager1.mathTotal)

    // StudentManagerClass의 printResult 메서드 테스트
//    val manager1 = StudentManagerClass()
//    manager1.printResult()

    // 학생 관리 요소의 객체를 생성한다.
    val studentManagerClass = StudentManagerClass()
    // 학생 정보를 입력받는다.
    studentManagerClass.inputStudentInfo()
    // 각 과목별 총점을 구한다.
    studentManagerClass.getTotal()
    // 각 과목별 평균을 구한다.
    studentManagerClass.getAvg()
    // 각 학생별 정보를 출력한다.
    studentManagerClass.printStudentInfo()
    // 각 과목별 총점과 평균을 출력한다.
    studentManagerClass.printResult()
}

// 출력 화면 - 테스트용으로 나중에 지워주세요
fun printResult(){
    println("이름 : 홍길동")
    println("국어 : 100점")
    println("영어 : 90점")
    println("수학 : 80점")
    println()
    println("이름 : 홍길동")
    println("국어 : 100점")
    println("영어 : 90점")
    println("수학 : 80점")
    println()
    println("이름 : 홍길동")
    println("국어 : 100점")
    println("영어 : 90점")
    println("수학 : 80점")
    println()
    println("국어 총점 : 300점")
    println("영어 총점 : 270점")
    println("수학 총점 : 240점")
    println("국어 평균 : 100점")
    println("영어 평균 : 90점")
    println("수학 평균 : 80점")
}

// 학생 클래스
class StudentClass{
    var name = ""
    var kor = 0
    var eng = 0
    var math = 0

    // 학생의 정보를 입력받는 메서드
    fun inputStudentInfo(scanner:Scanner){
        print("이름 : ")
        name = scanner.next()
        print("국어 : ")
        kor = scanner.nextInt()
        print("영어 : ")
        eng = scanner.nextInt()
        print("수학 : ")
        math = scanner.nextInt()
    }

    // 학생의 정보를 출력하는 메서드
    fun printStudentInfo(){
        println("이름 : $name")
        println("국어 점수 : $kor")
        println("영어 점수 : $eng")
        println("수학 점수 : $math")
        println()
    }
}

// 학생들을 관리하는 클래스
class StudentManagerClass{
    var korTotal = 0
    var engTotal = 0
    var mathTotal = 0
    var korAvg = 0
    var engAvg = 0
    var mathAvg = 0
    // 학생 객체의 주소값을 담을 변수
    val s1 = StudentClass()
    val s2 = StudentClass()
    val s3 = StudentClass()

    // 각 학생들의 정보를 입력받기 위한 메서드
    fun inputStudentInfo(){
        val scanner = Scanner(System.`in`)
        s1.inputStudentInfo(scanner)
        s2.inputStudentInfo(scanner)
        s3.inputStudentInfo(scanner)
    }

    // 각 학생들의 정보를 출력하는 메서드
    fun printStudentInfo(){
        s1.printStudentInfo()
        s2.printStudentInfo()
        s3.printStudentInfo()
    }

    // 과목별 평균을 구하는 메서드
    fun getAvg(){
        korAvg = (s1.kor + s2.kor + s3.kor) / 3
        engAvg = (s1.eng + s2.eng + s3.eng) / 3
        mathAvg = (s1.math + s2.math + s3.math) / 3
    }
    // 과목별 총점을 구하는 메서드
    fun getTotal(){
        korTotal = s1.kor + s2.kor + s3.kor
        engTotal = s1.eng + s2.eng + s3.eng
        mathTotal = s1.math + s2.math + s3.math
    }
    // 과목별 총점과 평균을 출력하는 메서드
    fun printResult() {
        println("국어 총점 : $korTotal")
        println("영어 총점 : $engTotal")
        println("수학 총점 : $mathTotal")
        println("국어 평균 : $korAvg")
        println("영어 평균 : $engAvg")
        println("수학 평균 : $mathAvg")
    }
}
