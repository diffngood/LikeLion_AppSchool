package kr.co.lion.androidproject3memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject3memoapp.databinding.FragmentShowAllBinding
import kr.co.lion.androidproject3memoapp.databinding.RowShowAllBinding


class ShowAllFragment : Fragment() {

    lateinit var fragmentShowAllBinding: FragmentShowAllBinding
    lateinit var mainActivity: MainActivity

    // 리사이클러뷰 구성을 위한 리스트
    var memoList = mutableListOf<MemoModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentShowAllBinding = FragmentShowAllBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerShowAll()

        return fragmentShowAllBinding.root
    }

    // RecyclerView 구성 메서드
    fun settingRecyclerShowAll(){
        fragmentShowAllBinding.apply {
            recyclerShowAll.apply {
                // 데이터 베이스에서 데이터를 가져온다.
                memoList = MemoDao.selectMemoDataAll(mainActivity)

                // 어뎁터
                adapter = RecyclerShowAllAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerShowAllAdapter : RecyclerView.Adapter<RecyclerShowAllAdapter.RecyclerShowAllViewHolder>(){

        inner class RecyclerShowAllViewHolder(rowShowAllBinding: RowShowAllBinding) : RecyclerView.ViewHolder(rowShowAllBinding.root){
            val rowShowAllBinding:RowShowAllBinding

            init {
                this.rowShowAllBinding = rowShowAllBinding
                this.rowShowAllBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerShowAllViewHolder {
            val rowShowAllBinding = RowShowAllBinding.inflate(layoutInflater)
            val recyclerShowAllViewHolder = RecyclerShowAllViewHolder(rowShowAllBinding)

            return recyclerShowAllViewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: RecyclerShowAllViewHolder, position: Int) {
            // position 번째 객체에서 데이터를 가져와 설정해준다.
            holder.rowShowAllBinding.textShowAllSubject.text = memoList[position].memoSubject
            holder.rowShowAllBinding.textShowAllWriteDate.text = memoList[position].memoDate

            // 항목을 누르면 동작하는 리스너
            holder.rowShowAllBinding.root.setOnClickListener {
                // 메모를 보는 화면이 나타나게 한다.
                val memoReadBundle = Bundle()
                memoReadBundle.putInt("memoIdx", memoList[position].memoIdx)
                mainActivity.replaceFragment(FragmentName.MEMO_READ_FRAGMENT, true, true, memoReadBundle)
            }
        }
    }
}










