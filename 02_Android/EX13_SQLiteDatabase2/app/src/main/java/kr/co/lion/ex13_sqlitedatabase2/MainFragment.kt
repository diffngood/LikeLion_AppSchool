package kr.co.lion.ex13_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex13_sqlitedatabase2.databinding.FragmentMainBinding
import kr.co.lion.ex13_sqlitedatabase2.databinding.MainRowBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 학생 객체를 담을 리스트
    lateinit var studentList:MutableList<StudentModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        settingToolbar()
        settingView()

        return fragmentMainBinding.root
    }

    // 데이터 설정
    fun settingData(){
        // 학생들의 정보를 가져온다
        studentList = StundentDao.selectAllStudent(mainActivity)
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "학생 관리"
                // menu
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener{
                    when(it.itemId){

                        R.id.menuItemMainAdd -> {
                            // InputFragment가 보이게 한다.
                            mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT, true, true, null)
                        }
                    }

                    true
                }
            }
        }
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
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            // position 번째 학생 객체를 추출한다.
            // val studentInfo = mainActivity.studentInfoList[position]

            // holder.mainRowBinding.textViewMainRowName.text = "${mainActivity.studentList[position].name}"
            // holder.mainRowBinding.textViewMainRowName.text = studentInfo.name
            holder.mainRowBinding.textViewMainRowName.text = studentList[position].name

            holder.mainRowBinding.root.setOnClickListener {
                // 항목을 누르면 ShowFragment로 이동되게 한다.
                val showBundle = Bundle()

                // 학생 번호를 번들에 담는다
                showBundle.putInt("idx", studentList[position].idx)

                mainActivity.replaceFragment(FragmentName.SHOW_INFO_FRAGMENT, true, true, showBundle)
            }
        }
    }
}