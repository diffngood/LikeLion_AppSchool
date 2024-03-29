package kr.co.lion.android09_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import kr.co.lion.android09_edittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // EditText
            editTextText4.apply{
                // 문자열을 설정해준다.
                setText("코드로 설정한 문자열")

                // 실시간으로 키보드 입력을 감시한다.
                // it : EditText에 입력된 문자열을 가지고 온다.
                addTextChangedListener {
                    textView.text = "실시간 감시 : $it"
                }

                // 리턴키를 눌렀을 때 동작한다.
                setOnEditorActionListener { v, actionId, event ->  

                    textView.text = "엔터키를 눌렀습니다\n"
                    textView.append("입력된 문자열 : $text")

                    // true : 리턴키를 누른 후 포커스가 현재 EditText로 유지된다.
                    // false : 리턴키를 누른 후 포커스가 다음 EditText로 이동한다.
                    false
                }
            }// editTextText4(End)

            // button
            button.apply {
                setOnClickListener{
                    // EditText에서 문자열을 가져온다.
                    // Editable 타입으로 반환되므로 String 형태로 사용하고자 한다면 변환해줘야 한다.
                    val str1 = editTextText4.text.toString()
                    textView.text = str1
                }
            }// button(End)
        }
    }
}