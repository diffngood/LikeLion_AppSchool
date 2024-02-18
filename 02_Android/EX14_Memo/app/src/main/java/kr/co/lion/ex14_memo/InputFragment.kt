package kr.co.lion.ex14_memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex14_memo.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setEvent()

        return fragmentInputBinding.root
    }

    // Event 처리
    fun setEvent(){
        fragmentInputBinding.apply {
            buttonDone.setOnClickListener {
                // 저장
                saveStudentInfo()
                // 이전(Main)으로
                mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
            }
        }
    }

    // 데이터 저장
    fun saveStudentInfo() {
        fragmentInputBinding.apply {

            val title = textFieldInputTitle.text.toString()
            val content = textFieldInputContent.text.toString()

            // 객체에 담는다.
            val memoModel = MemoModel(0, title, content)

            // 사용자가 입력한 내용을 학생리스트에 추가
            MemoDao.insertStudent(mainActivity, memoModel)
        }
    }
}