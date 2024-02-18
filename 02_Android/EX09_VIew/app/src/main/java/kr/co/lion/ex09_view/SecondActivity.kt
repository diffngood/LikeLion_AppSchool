package kr.co.lion.ex09_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex09_view.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.apply {
            buttonSubmit.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("name", textFieldName.text.toString())
                resultIntent.putExtra("grade", textFieldGrade.text.toString().toInt())
                resultIntent.putExtra("kor", textFieldKor.text.toString().toInt())
                resultIntent.putExtra("eng", textFieldEng.text.toString().toInt())
                resultIntent.putExtra("math", textFieldMath.text.toString().toInt())

                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}