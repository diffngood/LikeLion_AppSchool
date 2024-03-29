package kr.co.lion.ex05_cal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex05_cal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setEvent()
    }


    // 이벤트를 설정하는 메서드
    fun setEvent(){

        activityMainBinding.apply {
            // 더하기 버튼
            buttonPlus.setOnClickListener {
                // 입력한 값들을 가져온다.
                var number1 = editTextNumber1.text.toString().toInt()
                var number2 = editTextNumber2.text.toString().toInt()
                
                // 계산한다
                var result = number1 + number2

                // 출력한다
                textViewResult.text = "결과 : $result"
            }

            // 빼기 버튼
            buttonMinus.setOnClickListener {
                // 입력한 값들을 가져온다.
                var number1 = editTextNumber1.text.toString().toInt()
                var number2 = editTextNumber2.text.toString().toInt()

                // 계산한다
                var result = number1 - number2

                // 출력한다
                textViewResult.text = "결과 : $result"
            }

            // 곱하기 버튼
            buttonMultiply.setOnClickListener {
                // 입력한 값들을 가져온다.
                var number1 = editTextNumber1.text.toString().toInt()
                var number2 = editTextNumber2.text.toString().toInt()

                // 계산한다
                var result = number1 * number2

                // 출력한다
                textViewResult.text = "결과 : $result"
            }

            // 나누기 버튼
            buttonDivide.setOnClickListener {
                // 입력한 값들을 가져온다.
                var number1 = editTextNumber1.text.toString().toInt()
                var number2 = editTextNumber2.text.toString().toInt()

                // 계산한다
                var result = number1 / number2

                // 출력한다
                textViewResult.text = "결과 : $result"
            }

        }
    }
}