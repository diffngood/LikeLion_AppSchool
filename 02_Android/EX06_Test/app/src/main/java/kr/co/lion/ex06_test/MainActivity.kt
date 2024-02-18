package kr.co.lion.ex06_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex06_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setEvent()
    }

    fun setEvent(){
        activityMainBinding.apply {

            // 입력완료 버튼
            button.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val kor = editTextKor.text.toString().toInt()
                val math = editTextMath.text.toString().toInt()
                val total = kor + math
                val avg = total / 2.0

                textViewResult.text = "이름 : $name\n나이 : $age\n국어 : $kor\n수학 : $math\n총점 : $total\n평균 : $avg"
            }
        }
    }
}