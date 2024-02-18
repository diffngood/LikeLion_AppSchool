package kr.co.lion.ex05_linearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex05_linearlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            // 더하기 버튼
            button.apply {
                setOnClickListener{
                    val total = editTextText.text.toString().toInt() + editTextText2.text.toString().toInt()
                    textView3.text = "총합 : " + total
                }
            }// button 더하기 버튼(End)

            // 빼기 버튼
            button2.apply {
                setOnClickListener{
                    val total = editTextText.text.toString().toInt() - editTextText2.text.toString().toInt()
                    textView3.text = "총합 : " + total
                }
            }// button2 빼기 버튼 (End)

            // 곱하기 버튼
            button3.apply {
                setOnClickListener{
                    val total = editTextText.text.toString().toInt() * editTextText2.text.toString().toInt()
                    textView3.text = "총합 : " + total
                }
            }// button3 곱하기 버튼 (End)

            // 나누기 버튼
            button4.apply {
                setOnClickListener{
                    val total = editTextText.text.toString().toInt() / editTextText2.text.toString().toInt()
                    textView3.text = "총합 : " + total
                }
            }// button4 나누기 버튼 (End)

        }// binding (end)
    }
}