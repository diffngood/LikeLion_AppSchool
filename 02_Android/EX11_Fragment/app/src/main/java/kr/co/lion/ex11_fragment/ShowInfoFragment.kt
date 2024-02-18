package kr.co.lion.ex11_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex11_fragment.databinding.FragmentShowInfoBinding

class ShowInfoFragment : Fragment() {

    lateinit var fragmentShowInfoBinding: FragmentShowInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowInfoBinding = FragmentShowInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setView()

        return fragmentShowInfoBinding.root
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

            val position = arguments?.getInt("position")

            textViewShowInfoName.text = "이름 : ${mainActivity.studentInfoList[position!!].name}"
            textViewShowInfoAge.text = "나이 : ${mainActivity.studentInfoList[position!!].age}살"
            textViewShowInfoKor.text = "국어 점수 : ${mainActivity.studentInfoList[position!!].kor}점"
            textViewShowInfoEng.text = "영어 점수 : ${mainActivity.studentInfoList[position!!].eng}점"
            textViewShowInfoMath.text = "수학 점수 : ${mainActivity.studentInfoList[position!!].math}점"

            /*
            textViewShowInfoName.text = "이름 : ${mainActivity.studentList[position!!].name}"
            textViewShowInfoAge.text = "나이 : ${mainActivity.studentList[position!!].age}살"
            textViewShowInfoKor.text = "국어 점수 : ${mainActivity.studentList[position!!].kor}점"
            textViewShowInfoEng.text = "영어 점수 : ${mainActivity.studentList[position!!].eng}점"
            textViewShowInfoMath.text = "수학 점수 : ${mainActivity.studentList[position!!].math}점"
            */
        }
    }

}