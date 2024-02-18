package kr.co.lion.ex11_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.ex11_fragment.databinding.FragmentInputBinding
import kr.co.lion.ex11_fragment.databinding.FragmentMainBinding

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentInputBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "정보 입력"
                // menu
                inflateMenu(R.menu.input_menu)
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // BackStack에서 Fragment를 제거해 이전 Fragment가 보이도록 한다.
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemInputDone -> {
                            // 강사
                            // 입력한 학생 정보를 객체에 담아준다.
                            val name = textFieldInputName.text.toString()
                            val age = textFieldInputAge.text.toString().toInt()
                            val kor = textFieldInputKor.text.toString().toInt()
                            val eng = textFieldInputEng.text.toString().toInt()
                            val math = textFieldInputMath.text.toString().toInt()

                            val studentInfo = StudentInfo(name, age, kor, eng, math)

                            // 리스트에 담아준다.
                            mainActivity.studentInfoList.add(studentInfo)
                            // 이전 화면으로 돌아간다.
                            mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)

                            // processInputDone()
                        }
                    }
                    true
                }

            }
        }
    }

    // 입력 완료 처리 (내가 한 것)
    fun processInputDone() {
        fragmentInputBinding.apply {

            val name = textFieldInputName.text.toString()
            val age = textFieldInputAge.text.toString().toInt()
            val kor = textFieldInputKor.text.toString().toInt()
            val eng = textFieldInputEng.text.toString().toInt()
            val math = textFieldInputMath.text.toString().toInt()

            // 사용자가 입력한 내용을 학생리스트에 추가
            val studentData = StudentData(name, age, kor, eng, math)
            mainActivity.studentList.add(studentData)

            mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
        }
    }
}