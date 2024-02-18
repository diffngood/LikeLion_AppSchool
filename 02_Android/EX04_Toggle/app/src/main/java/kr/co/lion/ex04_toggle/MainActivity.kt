package kr.co.lion.ex04_toggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex04_toggle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            // 토글그룹에서 + 버튼 선택으로 초기 값 설정
            toggleGroup.check(R.id.button1)
            
            buttonResult.setOnClickListener {

                var result = 0
                val num1 = editText1.text.toString().toInt()
                val num2 = editText2.text.toString().toInt()

                when (toggleGroup.checkedButtonId){

                    R.id.button1 -> {
                        result = num1 + num2
                        textViewResult.text = "결과는 $result 입니다" }

                    R.id.button2 -> {
                        result = num1 - num2
                        textViewResult.text = "결과는 $result 입니다" }

                    R.id.button3 -> {
                        result = num1 * num2
                        textViewResult.text = "결과는 $result 입니다" }

                    R.id.button4 -> {
                        result = num1 / num2
                        textViewResult.text = "결과는 $result 입니다" }
                }

            }
        }
    }
}