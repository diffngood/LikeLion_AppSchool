package kr.co.lion.android_homework_1

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.android_homework_1.databinding.ActivityEditBinding
import kotlin.concurrent.thread
import kr.co.lion.android_homework_1.MainActivity.MemoManager.memoList

class EditActivity : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)

        val adapterPosition = intent.getIntExtra("adapterPosition", 0)

        setToolBar(adapterPosition)
        setView(adapterPosition)
    }

    // 툴바
    fun setToolBar(adapterPosition:Int){
        activityEditBinding.apply {
            toolbarEdit.apply {
                title = "메모 수정"

                // 뒤로가기 아이콘 추가
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                inflateMenu(R.menu.menu_edit)
                // 메뉴 버튼 클릭시
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_edit_done -> {
                            processEditDone(adapterPosition)
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰
    fun setView(adapterPosition:Int){
        activityEditBinding.apply {
            textFieldEditTitle.setText("${memoList[adapterPosition].title}")
            textFieldEditContent.setText("${memoList[adapterPosition].content}")
        }
    }

    fun processEditDone(adapterPosition:Int){
        activityEditBinding.apply {
            // 제목, 내용
            val title = textFieldEditTitle.text.toString()
            val content = textFieldEditContent.text.toString()

            // 입력 검사 (제목, 내용)
            if (title.isEmpty()){
                showDialog("제목 입력 오류", "제목을 입력해주세요", textFieldEditTitle)
                return
            }
            if (content.isEmpty()){
                showDialog("내용 입력 오류", "내용을 입력해주세요", textFieldEditContent)
                return
            }

            memoList[adapterPosition].title = title
            memoList[adapterPosition].content = content

            setResult(RESULT_OK)
            finish()
        }
    }

    // Dialog Show 기능
    fun showDialog(title:String, message:String, focusView: TextInputEditText){

        // 다이얼로그를 보여준다.
        val builder = MaterialAlertDialogBuilder(this@EditActivity).apply {
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
    fun showSoftInput(focusView: TextInputEditText){
        thread {
            SystemClock.sleep(200)
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(focusView, 0)
        }
    }
}