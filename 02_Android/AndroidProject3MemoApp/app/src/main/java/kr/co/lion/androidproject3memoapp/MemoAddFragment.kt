package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.co.lion.androidproject3memoapp.databinding.FragmentMemoAddBinding
import kr.co.lion.androidproject3memoapp.databinding.RowShowAllBinding

class MemoAddFragment : Fragment() {

    lateinit var fragmentMemoAddBinding: FragmentMemoAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMemoAddBinding = FragmentMemoAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTextField()

        return fragmentMemoAddBinding.root
    }

    // 툴바 구성
    fun settingToolbar(){
        fragmentMemoAddBinding.apply {
            toolbarMemoAdd.apply {
                // 타이틀 및 메뉴 설정
                title = "메모 추가"
                inflateMenu(R.menu.memo_add_menu)

                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // BackStack에서 Fragment를 제거해 이전 Fragment가 보이도록 한다.
                    mainActivity.removeFragment(FragmentName.MEMO_ADD_FRAGMENT)
                }

                // 아이템 클릭 리스너
                setOnMenuItemClickListener {
                    // 메뉴의 항목 id로 분기한다.
                    when (it.itemId) {
                        // 일차별 항목 보기 메뉴
                        R.id.menuItemMemoAddDone -> {
                            // 유효성 검사 메서드를 호출한다.
                            val chk = checkTextFieldInput()
                            Log.d("test1234", "$chk")
                            if (chk == true) {
                                // 키보를 내린다.
                                mainActivity.hideSoftInput()
                                // 모두 제대로 입력을 했다면 MemoReadFragment로 가게 한다.
                                mainActivity.replaceFragment(FragmentName.MEMO_READ_FRAGMENT, true, false, null)
                                // MemoReadFragment 에서 뒤로 가기 할 때 MainFragment로 갈 수 있도록 하기 위해
                            }
                        }
                        // 초기화
                        R.id.menuItemMemoAddReset -> {
                            // 입력 요소들을 모두 초기화한다.
                            textFieldMemoAddSubject.setText("")
                            textFieldMemoAddText.setText("")
                            textInputLayoutMemoAddSubject.error = null
                            textInputLayoutMemoAddText.error = null
                            // 첫 번째 입력 요소에 포커스를 준다.
                            mainActivity.showSoftInput(textFieldMemoAddSubject)
                        }
                    }
                    true
                }


            }
        }
    }

    // 입력 요소들 설정
    fun settingTextField(){
        fragmentMemoAddBinding.apply {

            // 첫 번째 입력 요소에 포커스를 준다.
            mainActivity.showSoftInput(textFieldMemoAddSubject)

//            textInputlayoutMemoAddText.error = " "
//            textInputlayoutMemoAddText.error = " "

            // 에러메시지가 보여지는 상황일때를 대비하여 무언가 입력하면 에러메시지를 없애준다.
            textFieldMemoAddSubject.addTextChangedListener {
                textInputLayoutMemoAddSubject.error = null
            }

            // 에러메시지가 보여지는 상황일때를 대비하여 무언가 입력하면 에러메시지를 없애준다.
            textFieldMemoAddText.addTextChangedListener {
                textInputLayoutMemoAddText.error = null
            }
        }
    }

    // 유효성 검사 메서드
    // 반환값 : true - 모두 정상적으로 잘 입력한 것, false - 입력에 문제가 있는 것
    fun checkTextFieldInput() : Boolean{
        fragmentMemoAddBinding.apply {
            // 입력하지 않은 입력 요소 중 가장 위에 있는 View를 담을 변수
            var errorView:View? = null

            // 제목
            if(textFieldMemoAddSubject.text.toString().trim().isEmpty()){
                textInputLayoutMemoAddSubject.error = "메모 제목을 입력해주세요"
                if(errorView == null) {
                    errorView = textFieldMemoAddSubject
                }
            } else {
                textInputLayoutMemoAddSubject.error = null
            }

            // 내용
            if(textFieldMemoAddText.text.toString().trim().isEmpty()){
                textInputLayoutMemoAddText.error = "메모 내용을 입력해주세요"
                if(errorView == null){
                    errorView = textFieldMemoAddText
                }
            } else {
                textInputLayoutMemoAddText.error = null
            }

            // 비어있는 입력 요소가 있다면...
            if(errorView != null){
                // 비어 있는 입력 요소에 포커스를 준다
                mainActivity.showSoftInput(errorView)
                // 메서드의 수행을 중지시킨다.
                return false
            }
            return true
        }
    }
}