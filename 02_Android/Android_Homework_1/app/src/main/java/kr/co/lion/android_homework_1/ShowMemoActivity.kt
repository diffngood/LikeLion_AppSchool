package kr.co.lion.android_homework_1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android_homework_1.databinding.ActivityShowMemoBinding
import kr.co.lion.android_homework_1.MainActivity.MemoManager.memoList

class ShowMemoActivity : AppCompatActivity() {

    lateinit var activityShowMemoBinding: ActivityShowMemoBinding

    lateinit var editActivityLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowMemoBinding = ActivityShowMemoBinding.inflate(layoutInflater)
        setContentView(activityShowMemoBinding.root)

        val adapterPosition = intent.getIntExtra("adapterPosition", 0)

        initData(adapterPosition)
        setToolBar(adapterPosition)
        setView(adapterPosition)
    }
    
    // 기본 세팅
    fun initData(adapterPosition:Int){
        // EditActivity 런처
        val contracts1 = ActivityResultContracts.StartActivityForResult()
        editActivityLauncher = registerForActivityResult(contracts1){
            if (it.resultCode == RESULT_OK){

                activityShowMemoBinding.apply {
                    textFieldShowMemoTitle.setText("${memoList[adapterPosition].title}")
                    textFieldShowMemoDate.setText("${memoList[adapterPosition].date}")
                    textFieldShowMemoContent.setText("${memoList[adapterPosition].content}")
                }

            }
        }
    }

    // 툴바
    fun setToolBar(adapterPosition:Int){
        activityShowMemoBinding.apply {
            toolbarShowMemo.apply {
                title = "메모 보기"

                // 뒤로가기 아이콘 추가
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                inflateMenu(R.menu.menu_show_memo)
                // 메뉴 버튼 클릭시
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_show_memo_edit -> {
                            processShowMemoEdit(adapterPosition)
                        }
                        R.id.menu_show_memo_delete -> {
                            processShowMemoDelete(adapterPosition)
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰
    fun setView(adapterPosition:Int){
        activityShowMemoBinding.apply {
            // Intent로 부터 학생 정보 객체를 추출한다.

            textFieldShowMemoTitle.setText("${memoList[adapterPosition].title}")
            textFieldShowMemoDate.setText("${memoList[adapterPosition].date}")
            textFieldShowMemoContent.setText("${memoList[adapterPosition].content}")
        }
    }

    // 수정 기능
    fun processShowMemoEdit(adapterPosition:Int){
        // 수정 테스트
        // Toast.makeText(this@ShowMemoActivity, "수정", Toast.LENGTH_SHORT).show()

        val editIntent = Intent(this@ShowMemoActivity, EditActivity::class.java)
        editIntent.putExtra("adapterPosition", adapterPosition)
        editActivityLauncher.launch(editIntent)
    }

    // 삭제 기능
    fun processShowMemoDelete(adapterPosition:Int){
        // 삭제 테스트
        // Toast.makeText(this@ShowMemoActivity, "삭제", Toast.LENGTH_SHORT).show()

        memoList.removeAt(adapterPosition)

        // 삭제 후에는 결과를 설정하고 액티비티를 종료
        setResult(RESULT_OK)
        finish()
    }
}