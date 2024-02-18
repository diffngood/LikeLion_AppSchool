package kr.co.lion.ex13_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex13_sqlitedatabase2.databinding.FragmentShowInfoBinding

class ShowInfoFragment : Fragment() {

    lateinit var fragmentShowInfoBinding: FragmentShowInfoBinding
    lateinit var mainActivity: MainActivity

    // 학생 정보를 담을 객체
    lateinit var studentModel: StudentModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowInfoBinding = FragmentShowInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        setToolbar()
        setView()

        return fragmentShowInfoBinding.root
    }

    // 데이터를 설정한다.
    fun settingData(){
        // 학생 idx 값을 추출한다.
        val idx = arguments?.getInt("idx")!!
        // 학생 데이터를 가져온다.
        studentModel = StundentDao.selectOneStudent(mainActivity, idx)
    }

    // 툴바 설정
    fun setToolbar(){
        fragmentShowInfoBinding.apply {
            toolbarShowInfo.apply {
                title = "정보 보기"

                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // BackStack에서 Fragment를 제거해 이전 Fragment가 보이도록 한다.
                    mainActivity.removeFragment(FragmentName.SHOW_INFO_FRAGMENT)
                }

                inflateMenu(R.menu.show_info_menu)
            }
        }
    }

    fun setView(){
        fragmentShowInfoBinding.apply {

            textViewShowInfoName.text = "이름 : ${studentModel.name}"
            textViewShowInfoAge.text = "나이 : ${studentModel.age}살"
            textViewShowInfoKor.text = "국어 점수 : ${studentModel.kor}점"
            textViewShowInfoEng.text = "영어 점수 : ${studentModel.eng}점"
            textViewShowInfoMath.text = "수학 점수 : ${studentModel.math}점"

        }
    }

}