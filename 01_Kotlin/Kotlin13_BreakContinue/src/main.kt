// break : 가장 가까운 반복문의 수행을 중단시킨다.
// continue : 다음 반복으로 넘어간다.
//class Person (
//    var name:String = "개발자", var age:Int = 20
//)



fun main() {
//    val p0 = Person() // name="개발자", age=20
//    val p1 = Person("김철수") // name="김철수", age=20
//    val p2 = Person("김영희", 25) // name="김영희", age=25
//    val p3 = Person(age=30, name="도라에몽") // name="도라에몽", age=30
//    println("${p0.name} ${p0.age}")
//    println("${p1.name} ${p1.age}")
//    println("${p2.name} ${p2.age}")
//    println("${p3.name} ${p3.age}")
    for (i in 1..10){
        if (i > 5){
            break
        }
        println("i : $i")
    }

    for (j in 1..10){
        if (j % 2 == 0){
            continue
        }
        println("j : $j")
    }

}