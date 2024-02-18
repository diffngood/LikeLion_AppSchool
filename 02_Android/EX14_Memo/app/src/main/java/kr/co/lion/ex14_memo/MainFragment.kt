package kr.co.lion.ex14_memo

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex14_memo.databinding.CustomDialogBinding
import kr.co.lion.ex14_memo.databinding.FragmentMainBinding
import kr.co.lion.ex14_memo.databinding.MainRowBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 메모 객체 담을 리스트
    lateinit var memoList:MutableList<MemoModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        settingView()

        return fragmentMainBinding.root
    }

    // 데이터 설정
    fun settingData(){
        // 학생들의 정보를 가져온다
        memoList = MemoDao.selectAllStudent(mainActivity)
    }

    // View 설정
    fun settingView(){
        fragmentMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // 어뎁터
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터 클래스
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHolderClass
        inner class ViewHolderMain(mainRowBinding: MainRowBinding) : RecyclerView.ViewHolder(mainRowBinding.root){
            val mainRowBinding:MainRowBinding

            init {
                this.mainRowBinding = mainRowBinding

                this.mainRowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val mainRowBinding = MainRowBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(mainRowBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {

            holder.mainRowBinding.textViewMainRowName.text = memoList[position].title

            // 해당 항목을 누르면
            holder.mainRowBinding.root.setOnClickListener {
                // mainActivity.bottomSheetDialog.setContentView(mainActivity.bottomSheetView)

                // val idx = memoList[position].idx

                val builder = MaterialAlertDialogBuilder(mainActivity).apply {
                    setTitle("${memoList[position].title}")

                    // 뷰를 설정한다.
                    val customDialogBinding = CustomDialogBinding.inflate(layoutInflater)
                    setView(customDialogBinding.root)

                    setNegativeButton("확인", null)
                    customDialogBinding.textViewDialogContent.text = memoList[position].content
                }

                builder.show()



            }
        }
    }
}