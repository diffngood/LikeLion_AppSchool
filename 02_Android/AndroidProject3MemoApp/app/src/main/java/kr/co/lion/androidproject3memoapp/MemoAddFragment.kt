package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return fragmentMemoAddBinding.root
    }

    // 툴바 구성
    fun settingToolbar(){
        fragmentMemoAddBinding.apply {
            toolbarMemoAdd.apply {
                // 타이틀 및 메뉴 설정
                title = "메모 추가"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // BackStack에서 Fragment를 제거해 이전 Fragment가 보이도록 한다.
                    mainActivity.removeFragment(FragmentName.MEMO_ADD_FRAGMENT)
                }
            }
        }
    }

}