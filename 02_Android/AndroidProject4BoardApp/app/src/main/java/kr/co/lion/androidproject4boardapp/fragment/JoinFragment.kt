package kr.co.lion.androidproject4boardapp.fragment

import android.graphics.Paint.Join
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentJoinBinding
import kr.co.lion.androidproject4boardapp.viewmodel.JoinViewModel

class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity
    lateinit var  joinViewModel: JoinViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)
        // 바인딩 객체를 생성한다. ViewBinding의 기능을 포함한다.
        // 첫 번째 : LayoutInflater
        // 두 번째 : 화면을 만들 때 사용할 layout 폴더의 xml 파일
        // 세 번째 : xml 을 통해서 만들어진 화면을 누가 관리하게 할 것인가. 여기서는 Fragment를 의미한다.
        // 네 번째 : Fragment 상태에 영향을 받을 것인가...
        fragmentJoinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false)
        // ViewModel 객체를 생성한다.
        joinViewModel = JoinViewModel()
        // 생성한 ViewModel 객체를 layout 파일에 설정해준다,
        fragmentJoinBinding.joinViewModel = joinViewModel
        // ViewModel의 생명 주기를 Fragment와 일치시킨다. Fragment가 살아 있을 때 ViewModel 객체도 살아 있겠금 해준다.
        fragmentJoinBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonJoinNext()
        settingTextField()

        return fragmentJoinBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){

        // 타이틀 설정
        joinViewModel.toolbarJoinTitle.value = "회원가입"
        // NavigationIcon Back 설정
        joinViewModel.toolbarJoinNavigationIcon.value = R.drawable.arrow_back_24px

        fragmentJoinBinding.apply {
            toolbarJoin.apply {
                setNavigationOnClickListener {
                    // 이전 화면으로 간다.
                    mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }

    fun settingButtonJoinNext(){
        fragmentJoinBinding.apply {
            buttonJoinNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // AddUserInfoFragment를 보여준다.
                    mainActivity.replaceFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)
                }
            }
        }
    }

    // 입력요소 초기설정
    fun settingTextField(){
        // 입력 요소들을 초기화 한다.
        joinViewModel.textFieldJoinUserId.value = ""
        joinViewModel.textFieldJoinUserPw.value = ""
        joinViewModel.textFieldJoinUserPw2.value = ""
        // 첫 번째 입력 요소에 포커스를 준다.
        Tools.showSoftInput(mainActivity, fragmentJoinBinding.textFieldJoinUserId)
    }

}