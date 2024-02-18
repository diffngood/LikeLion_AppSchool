import java.util.Scanner

// EX14) 동물원..
//동물을 관리하는 프로그램을 작성한다.
//동물은 총 6마리이다.
//호랑이는 이름, 동물종류, 다리 개수로 구성된다.
//사자는 이름, 동물종류, 털 개수로 구성된다.
//여우는 이름, 동물종류, 꼬리 개수로 구성된다.
//
//호랑이는 먹는다, 달린다는 행동을 할 수 있다.
//사자는 먹는다, 염색한다라는 행동을 할 수 있다.
//여우는 먹는다, 유혹한다라는 행동을 할 수 있다.
//
//프로그램이 시작되면 호랑이 두마리, 사자 두마리, 여우 두마리 순서대로 입력을 받는다.
//
//입력시 동물종류는 입력받지 않는다.
//
//모든 동의 정보가 입력이 완료되면
//호랑이는 먹는다와 달린다는 행동을 하게 하고
//사자는 먹는다와 염색한다라는 행동을 하게 하고
//여우는 먹는다와 유혹한다라는 행동을 할하게 한다
//
//그 이후, 모든 동물들의 정보를 출력한다.

// step1) 출력 화면을 구현한다.
// 출력화면 구현시 필요한 데이터는 임의의 데이터로 설정해준다.

// step2) 프로그램에서 필요한 기능들을 정리한다.
// 먹는다
// 달린다
// 염색한다
// 유혹한다
// 동물들의 정보를 입력받는다.
// 동물들의 정보를 출력한다.

// step3) step2에서 정리한 기능들을 그룹으로 묶어 준다.
// 먹는다 -> 호랑이, 사자, 여우
// 달린다 -> 호랑이
// 염색한다 -> 사자
// 유혹한다 -> 여우
// 동물들의 정보를 입력받는다. -> 동물원
// 동물들의 정보를 출력한다. -> 동물원
// 동물의 정보를 입력받는다 -> 호랑이, 사자, 여우
// 동물의 정보를 출력한다 -> 호랑이, 사자, 여우

// step4) 출력 화면과 step2, step3에서 정의한 기능을 보고 필요한 데이터를 정리한다.
// 필요할 데이터 : 화면을 구성하기 위해 필요한 데이터, 입력받는 데이터, 발생되는 데이터, 계산되는 데이터 등등
// 동물 종류 -> 호랑이, 사자, 여우
// 동물 이름 -> 호랑이, 사자, 여우
// 다리 개수 -> 호랑이
// 털 개수 -> 사자
// 꼬리 개수 -> 여우

// step5) step4에서 정리한 변수들을 그룹으로 묶어 준다.

// step6) 클래스를 정의한다.
// 클래스 내부에 변수를 작성해주고 메서드를 작성해준다.
// 메서드의 코드는 아직 작성하지 않는다.

// step7) 메서드 내부의 코드를 작성하고 이를 테스트 한다.

// step8) main에서 필요한 만큼 객체를 생성하고 메서드를 호출하여 프로그램을 완성한다.

// 주의!!! main에서는 절대로 객체가 관리하는 변수에 직접 접근하지 마세요

fun main(){
    // testPrint()
    // 동물원 객체를 생성한다.
    val zoo = Zoo()
    // 입력을 받는다.
    zoo.inputAnimalsInfo()
    // 각 동물이 행동을 한다
    zoo.doAnimal()
    // 각 동물들의 정보를 출력한다.
    zoo.printAnimalsInfo()
}

fun testPrint(){
    println("호랑이 동물1이 먹는다")
    println("호랑이 동물1이 달린다")
    println()
    println("호랑이 동물2가 먹는다")
    println("호랑이 동물2가 달린다")
    println()
    println("사자 동물3이 먹는다")
    println("사자 동물3이 염색한다")
    println()
    println("사자 동물4가 먹는다")
    println("사자 동물4가 염색한다")
    println()
    println("여우 동물5가 먹는다")
    println("여우 동물5가 유혹한다")
    println()
    println("여우 동물6이 먹는다")
    println("여우 동물6이 유혹한다")
    println()
    println("이름 : 동물1")
    println("종류 : 호랑이")
    println("다리 개수 : 10개")
    println()
    println("이름 : 동물2")
    println("종류 : 호랑이")
    println("다리 개수 : 20개")
    println()
    println("이름 : 동물3")
    println("종류 : 사자")
    println("털 개수 : 100개")
    println()
    println("이름 : 동물4")
    println("종류 : 사자")
    println("털 개수 : 200개")
    println()
    println("이름 : 동물5")
    println("종류 : 여우")
    println("꼬리 개수 : 2개")
    println()
    println("이름 : 동물6")
    println("종류 : 여우")
    println("꼬리 개수 : 3개")
    println()
}

// 동물원
class Zoo{

    val scanner = Scanner(System.`in`)

    // 동물들
    val animal1 = Tiger()
    val animal2 = Tiger()
    val animal3 = Lion()
    val animal4 = Lion()
    val animal5 = Fox()
    val animal6 = Fox()

    // 동물들의 정보를 입력받는다
    fun inputAnimalsInfo(){
        animal1.inputTigerInfo(scanner)
        animal2.inputTigerInfo(scanner)
        animal3.inputLionInfo(scanner)
        animal4.inputLionInfo(scanner)
        animal5.inputFoxInfo(scanner)
        animal6.inputFoxInfo(scanner)
    }

    // 동물들의 정보를 출력한다.
    fun printAnimalsInfo(){
        animal1.printTigerInfo()
        println()
        animal2.printTigerInfo()
        println()
        animal3.printLionInfo()
        println()
        animal4.printLionInfo()
        println()
        animal5.printFoxInfo()
        println()
        animal6.printFoxInfo()
        println()
    }

    // 동물들이 행동한다.
    fun doAnimal(){
        animal1.doEat()
        animal1.doRun()
        println()
        animal2.doEat()
        animal2.doRun()
        println()

        animal3.doEat()
        animal3.doDyed()
        println()
        animal4.doEat()
        animal4.doDyed()
        println()

        animal5.doEat()
        animal5.doTempted()
        println()
        animal6.doEat()
        animal6.doTempted()
    }
}

// 동물들이 상속받을 부모 클래스
// animalType: 동물 종류
open class Animal(var animalType:String){
    // 동물 이름
    var animalName = ""

    // 먹는다
    fun doEat(){
        println("${animalType} ${animalName}이(가) 먹는다")
    }

    // 동물의 정보를 입력 받는다.
    fun inputAnimalInfo(scanner:Scanner){
        print("동물 이름 : ")
        animalName = scanner.next()
    }

    // 동물의 정보를 출력한다
    fun printAnimalInfo(){
        println("동물 이름 : $animalName")
        println("동물 종류 : $animalType")
    }
}

// 호랑이
class Tiger : Animal("호랑이"){
    // 다리 개수
    var legCount = 0

    // 달린다
    fun doRun(){
        println("$animalType ${animalName}이(가) 달린다")
    }

    // 입력받는다
    fun inputTigerInfo(scanner: Scanner){
        inputAnimalInfo(scanner)
        println("다리 개수 : ")
        legCount = scanner.nextInt()
    }

    //출력한다
    fun printTigerInfo(){
        printAnimalInfo()
        println("다리 개수 : ${legCount}개")
        println()
    }
}

// 사자
class Lion : Animal("사자"){
    // 털 개수
    var hairCount = 0

    // 염색한다
    fun doDyed(){
        println("$animalType ${animalName}이(가) 염색한다")
    }

    // 입력받는다
    fun inputLionInfo(scanner: Scanner){
        inputAnimalInfo(scanner)
        println("털 개수 : ")
        hairCount = scanner.nextInt()
    }

    //출력한다
    fun printLionInfo(){
        printAnimalInfo()
        println("털 개수 : ${hairCount}개")
        println()
    }
}

// 여우
class Fox : Animal("여우"){
    // 꼬리 개수
    var tailCount = 0

    // 유혹한다
    fun doTempted(){
        println("$animalType ${animalName}이(가) 유혹한다")
    }

    // 입력받는다
    fun inputFoxInfo(scanner: Scanner){
        inputAnimalInfo(scanner)
        println("털 개수 : ")
        tailCount = scanner.nextInt()
    }

    //출력한다
    fun printFoxInfo(){
        printAnimalInfo()
        println("털 개수 : ${tailCount}개")
        println()
    }
}