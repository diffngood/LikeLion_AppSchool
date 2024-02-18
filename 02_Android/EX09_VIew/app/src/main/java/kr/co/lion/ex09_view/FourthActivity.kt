package kr.co.lion.ex09_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex09_view.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {

    lateinit var activityFourthBinding: ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFourthBinding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(activityFourthBinding.root)

        activityFourthBinding.apply {

            textViewStudentInfo.apply {

                val name = intent?.getStringExtra("name")
                val grade = intent?.getIntExtra("grade", 0)
                val kor = intent?.getIntExtra("kor", 0)
                val eng = intent?.getIntExtra("eng", 0)
                val math = intent?.getIntExtra("math", 0)
                val total = intent?.getIntExtra("total", 0)
                val avg = intent?.getIntExtra("avg", 0)
                
                text = "이름 : ${name}\n"
                append("학년 : ${grade}학년\n")
                append("국어 점수 : ${kor}점\n")
                append("영어 점수 : ${eng}점\n")
                append("수학 점수 : ${math}점\n")

                append("총점 : ${total}점\n")
                append("평균 : ${avg}점\n")
            }

            buttonEnd2.setOnClickListener {
                finish()
            }
        }
    }
}