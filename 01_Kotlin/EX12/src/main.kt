// EX12) 동물원에는 동물들이 여러 가지가 있다.
//동물은 사자, 고양이, 새가 있다.
//사자는 다리가 네개이고 어흥이라는 소리는 낸다.
//고양이는 다리가 네개이고 야옹라는 소리는 낸다.
//새는 다리가 두개이고 짹짹이라는 소리는 낸다.
//
//프로그램이 실행되면 3가지 동물에 관련된 정보를 입력받는다.
//입력받는 정보는 동물의 종료와 다리의 개수, 내는 소리이다.
//입력이 완료되면 각 동물의 정보를 모두 출력하고
//동물의 다리 개수 총합을 구해 출력한다.

// step1) 출력 화면을 구현한다.
// 출력화면 구현시 필요한 데이터는 임의의 데이터로 설정해준다.

// step2) 프로그램에서 필요한 기능들을 정리한다.

// step3) step3에서 정리한 기능들을 그룹으로 묶어 준다.
// 1. 동물들의 정보를 입력받는다. -> 동물원
// 2. 각 동물들의 정보를 출력한다. -> 동물원
// 3. 동물의 다리개수를 구해 출력한다. -> 동물원
// 4. 동물 한 마리의 정보를 입력받는다 -> 동물
// 5. 동물 한 마리의 정보를 출력한다 -> 동물

// step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리한다.
// 필요할 데이터 : 화면을 구성하기 위해 필요한 데이터, 입력받는 데이터, 발생되는 데이터, 계산되는 데이터 등등
// 1. 동물 종류
// 2. 울음 소리
// 3. 다리 개수
// 4. 다리 갯수의 총합

// step5) step4에서 정리한 변수들을 그룹으로 묶어 준다.
// 1. 동물 종류 -> 동물
// 2. 울음 소리 -> 동물
// 3. 다리 개수 -> 동물
// 4. 다리 갯수의 총합 -> 동물원

// step6) 클래스를 정의한다.
// 클래스 내부에 변수를 작성해주고 메서드를 작성해준다.
// 메서드의 코드는 아직 작성하지 않는다.

// step7) 메서드 내부의 코드를 작성하고 이를 테스트 한다.

// step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램을 완성한다.

// 주의!!! main에서는 절대로 객체가 관리하는 변수에 직접 접근하지 마세요

import java.util.Scanner

fun main() {

    // 동물원 객체를 생성한다.
    val ZooClass = ZooClass()
    // 동물들의 정보를 입력받는다.
    ZooClass.inputAnimalsInfo()
    // 동물들의 정보를 출력한다.
    ZooClass.printAnimalsInfo()
    // 동물들의 다리 개수를 출력한다.
    ZooClass.printResult()
}

// 동물원 클래스
class ZooClass {
    
    // 다리 개수의 총합을 담을 변수
    var totalLegCount = 0

    val s1 = AnimalClass()
    val s2 = AnimalClass()
    val s3 = AnimalClass()

    fun inputAnimalsInfo(){
        val scanner = Scanner(System.`in`)
        s1.inputAnimalsInfo(scanner)
        s2.inputAnimalsInfo(scanner)
        s3.inputAnimalsInfo(scanner)
    }

    fun printAnimalsInfo(){
        s1.printAnimalsInfo()
        s2.printAnimalsInfo()
        s3.printAnimalsInfo()
    }

    fun printResult(){
        totalLegCount = s1.animalLegCount + s2.animalLegCount + s3.animalLegCount
        println("다리 개수의 총 합은 ${totalLegCount}개입니다")
    }
}

// 동물 클래스
class AnimalClass(){

    var animalType = ""
    var animalLegCount = 0
    var animalSound = ""

    fun inputAnimalsInfo(scanner: Scanner){
        print("종류 : ")
        animalType = scanner.next()
        print("다리 개수 : ")
        animalLegCount = scanner.nextInt()
        print("소리 : ")
        animalSound = scanner.next()
    }

    // 학생의 정보를 출력하는 메서드
    fun printAnimalsInfo(){
        println("이름 : $animalType")
        println("다리 개수 : $animalLegCount")
        println("소리 : $animalSound")
        println()
    }
}