import java.util.*

// 2. 김원빈 [1차 과제] 앱 스쿨 : Android2기 Kotlin 과제

fun main() {

    // 자동차 관리 객체 생성
    val carManger = CarManger()

    // 자동차 정보 입력
    println("★ 자동차 정보 입력 ★")
    carManger.inputCarsInfo()

    // 자동차 행동
    println("★ 각 자동차 기능 출력 ★")
    carManger.doCar()

    // 모든 자동차 정보 출력
    println("★ 각 자동차 정보 출력 ★")
    carManger.printCarsInfo()

    // 자동차 보고서 출력
    println("★ 최종 보고서 출력 ★")
    carManger.printCarsReport()

}

// 자동차 관리
class CarManger{
    lateinit var scanner: Scanner

    lateinit var s1:BasicCar
    lateinit var s2:BasicCar
    lateinit var s3:BasicCar
    lateinit var s4:Truck
    lateinit var s5:Truck
    lateinit var s6:Truck
    lateinit var s7:ElectricCar
    lateinit var s8:ElectricCar
    lateinit var s9:ElectricCar
    lateinit var s10:ElectricCar

    var allTireCount = 0
    var allSeatCount = 0
    var allCarSheetCount = 0
    var allLoadCount = 0
    var allBatteryCount = 0

    init {
        scanner = Scanner(System.`in`)

        s1 = BasicCar()
        s2 = BasicCar()
        s3 = BasicCar()
        s4 = Truck()
        s5 = Truck()
        s6 = Truck()
        s7 = ElectricCar()
        s8 = ElectricCar()
        s9 = ElectricCar()
        s10 = ElectricCar()
    }

    // 자동차 정보를 입력받는 기능
    fun inputCarsInfo(){
        s1.inputCarInfo(scanner)
        s2.inputCarInfo(scanner)
        s3.inputCarInfo(scanner)
        s4.inputCarInfo(scanner)
        s5.inputCarInfo(scanner)
        s6.inputCarInfo(scanner)
        s7.inputCarInfo(scanner)
        s8.inputCarInfo(scanner)
        s9.inputCarInfo(scanner)
        s10.inputCarInfo(scanner)
        println()
    }

    // 자동차 정보를 출력하는 기능
    fun printCarsInfo(){
        s1.printCarInfo()
        s2.printCarInfo()
        s3.printCarInfo()
        s4.printCarInfo()
        s5.printCarInfo()
        s6.printCarInfo()
        s7.printCarInfo()
        s8.printCarInfo()
        s9.printCarInfo()
        s10.printCarInfo()
    }

    // 자동차 행동 메서드를 호출하는 메서드
    fun doCar(){
        s1.go()
        s1.back()
        s1.sing()
        s2.go()
        s2.back()
        s2.sing()
        s3.go()
        s3.back()
        s3.sing()
        s4.go()
        s4.back()
        s4.load()
        s5.go()
        s5.back()
        s5.load()
        s6.go()
        s6.back()
        s6.load()
        s7.go()
        s7.back()
        s7.charge()
        s8.go()
        s8.back()
        s8.charge()
        s9.go()
        s9.back()
        s9.charge()
        s10.go()
        s10.back()
        s10.charge()
    }

    // 최종 보고서 출력하는 기능
    fun printCarsReport(){
        // 총 타이어의 개수
        allTireCount = s1.tireCount + s2.tireCount + s3.tireCount + s4.tireCount + s5.tireCount
        + s6.tireCount + s7.tireCount + s8.tireCount + s9.tireCount + s10.tireCount
        // 총 좌석의 개수
        allSeatCount = s1.seatCount + s2.seatCount + s3.seatCount + s4.seatCount + s5.seatCount
        + s6.seatCount + s7.seatCount + s8.seatCount + s9.seatCount + s10.seatCount
        // 총 카시트의 개수
        allCarSheetCount = s1.carSheetCount + s2.carSheetCount + s3.carSheetCount
        // 총 적재량
        allLoadCount = s4.loadCount + s5.loadCount + s6.loadCount
        // 총 배터리 용량
        allBatteryCount = s7.batteryCount + s8.batteryCount + s9.batteryCount + s10.batteryCount

        println("승용차 : 3대")
        println("트럭 : 3대")
        println("전기 자동차 : 4대")
        println("총 타이어의 개수 : ${allCarSheetCount}개")
        println("총 좌석의 개수 : ${allCarSheetCount}개")
        println("총 카시트의 개수 : ${allCarSheetCount}개")
        println("총 적재량 : ${allLoadCount}kg")
        println("총 배터리 용량 : ${allBatteryCount}mAh")
    }
}

open class Car(var carType:String){
    
    // 타이어 개수
    var tireCount:Int = 0
    var seatCount:Int = 0
    
    // 주행하는 기능
    fun go(){
        println("${carType}이(가) 주행한다")
    }

    // 후진하는 기능
    fun back(){
        println("${carType}이(가) 후진한다")
    }

    // 자동차의 정보를 입력받는 기능
    open fun inputCarInfo(scanner: Scanner){
        print("${carType}의 타이어의 개수 : ")
        tireCount = scanner.nextInt()

        print("${carType}의 좌석의 개수 : ")
        seatCount = scanner.nextInt()
    }

    // 자동차의 정보를 출력하는 기능
    open fun printCarInfo(){
        println("차 종류 : $carType")
        println("타이어의 개수 : ${tireCount}개")
        println("좌석의 개수 : ${seatCount}개")
    }

}

class BasicCar : Car("승용차"){
    
    // 카시트 개수
    var carSheetCount = 0

    // 자장가를 재생하는 기능
    fun sing(){
        println("${carType}이(가) 자장가를 재생한다")
        println()
    }

    // 자동차 정보를 입력받는 기능
    override fun inputCarInfo(scanner: Scanner) {
        super.inputCarInfo(scanner)
        print("카시트 개수 : ")
        carSheetCount = scanner.nextInt()
    }

    // 자동차 정보를 출력하는 기능
    override fun printCarInfo(){
        super.printCarInfo()
        println("카시트 개수 : ${carSheetCount}개")
        println()
    }
}

class Truck : Car("트럭"){

    // 적재량
    var loadCount = 0
    
    // 물건을 적재하는 기능
    fun load(){
        println("${carType}이(가) 물건을 싣는다")
        println()
    }

    // 자동차 정보를 입력받는 기능
    override fun inputCarInfo(scanner: Scanner) {
        super.inputCarInfo(scanner)
        print("적재량 : ")
        loadCount = scanner.nextInt()
    }

    // 자동차 정보를 출력하는 기능
    override fun printCarInfo(){
        super.printCarInfo()
        println("적재량 : ${loadCount}kg")
        println()
    }
}

class ElectricCar : Car("전기 자동차"){

    // 배터리 용량
    var batteryCount = 0

    // 충전하는 기능
    fun charge(){
        println("${carType}이(가) 충전한다")
        println()
    }

    // 자동차 정보를 입력받는 기능
    override fun inputCarInfo(scanner: Scanner) {
        super.inputCarInfo(scanner)
        print("배터리 용량 : ")
        batteryCount = scanner.nextInt()
    }

    // 자동차 정보를 출력하는 기능
    override fun printCarInfo(){
        super.printCarInfo()
        println("배터리 용량 : ${batteryCount}mAh")
        println()
    }
    
    
}