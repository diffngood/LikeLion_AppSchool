package kr.co.lion.android_homework_1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.android_homework_1.databinding.ActivityInputBinding
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.concurrent.thread

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setToolBar()
        setView()
    }
    
    // 툴바
    fun setToolBar(){
        activityInputBinding.apply {
            toolbarInput.apply {

                title = "메모 작성"

                // 뒤로가기 아이콘 추가
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                inflateMenu(R.menu.menu_input)
                // 메뉴 버튼 클릭시
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_input_done -> {
                            processInputDone()
                        }
                    }
                    true
                }
            }
        }
    }

    // View 설정
    fun setView(){
        activityInputBinding.apply {
            // 뷰에 포커스를 준다 (제목)
            textFieldInputTitle.requestFocus()
            // 키보드를 올린다
            showSoftInput(textFieldInputTitle)

            // (내용 입력칸) 엔터키 누르면 입력 완료 처리
            textFieldInputContent.setOnEditorActionListener { v, actionId, event ->
                processInputDone()
                true
            }
        }
    }

    fun processInputDone(){

        activityInputBinding.apply {
            val title = textFieldInputTitle.text.toString()
            val content = textFieldInputContent.text.toString()

            val NowTime = System.currentTimeMillis()
            val DF = SimpleDateFormat("yyyy년 MM월 dd일 E요일", Locale.KOREAN)

            val date = DF.format(NowTime).toString()

            // 입력 검사 (제목, 내용)
            if (title.isEmpty()){
                showDialog("제목 입력 오류", "제목을 입력해주세요", textFieldInputTitle)
                return
            }
            if (content.isEmpty()){
                showDialog("내용 입력 오류", "내용을 입력해주세요", textFieldInputContent)
                return
            }

        
            // 입력받은 정보를 객체에 담아 주기
            val memoData = MemoData(title, date, content)

            var resultIntent = Intent()
            resultIntent.putExtra("memoData", memoData)

            // 완료 테스트
            Toast.makeText(this@InputActivity, "완료", Toast.LENGTH_SHORT).show()

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    // Dialog Show 기능
    fun showDialog(title:String, message:String, focusView: TextInputEditText){

        // 다이얼로그를 보여준다.
        val builder = MaterialAlertDialogBuilder(this@InputActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                focusView.setText("")
                focusView.requestFocus()
                showSoftInput(focusView)
            }
        }

        builder.show()
    }

    // 포커스 주고 키보드 올리기 기능
    fun showSoftInput(focusView:TextInputEditText){
        thread {
            SystemClock.sleep(200)
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(focusView, 0)
        }
    }
}