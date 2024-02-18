package kr.co.lion.ex09_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex09_view.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var activityThirdBinding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(activityThirdBinding.root)

        activityThirdBinding.apply {

            textView.apply {
                val korTotal = intent?.getIntExtra("korTotal", 0)
                val korAvg = intent?.getIntExtra("korAvg", 0)
                val engTotal = intent?.getIntExtra("engTotal", 0)
                val engAvg = intent?.getIntExtra("engAvg", 0)
                val mathTotal = intent?.getIntExtra("mathTotal", 0)
                val mathAvg = intent?.getIntExtra("mathAvg", 0)
                val total = intent?.getIntExtra("total", 0)
                val avg = intent?.getIntExtra("avg", 0)

                text = "국어 총점 : ${korTotal}점\n"
                append("국어 평균 : ${korAvg}점\n")
                append("영어 총점 : ${engTotal}점\n")
                append("영어 평균 : ${engAvg}점\n")
                append("수학 총점 : ${mathTotal}점\n")
                append("수학 평균 : ${mathAvg}점\n\n")

                append("전체 총점 : ${total}점\n")
                append("전체 평균 : ${avg}점\n")
            }

            buttonEnd.setOnClickListener {
                finish()
            }
        }
    }
}