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
import kr.co.lion.androidproject3memoapp.databinding.RowCalendarBinding
import kr.co.lion.androidproject3memoapp.databinding.RowShowAllBinding

class ShowAllFragment : Fragment() {

    lateinit var fragmentShowAllBinding: FragmentShowAllBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentShowAllBinding = FragmentShowAllBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerShowAll()

        return fragmentShowAllBinding.root
    }

    // RecyclerView 설정
    fun settingRecyclerShowAll(){
        fragmentShowAllBinding.apply {
            recyclerShowAll.apply {
                // 어뎁터 및 레이아웃 매니저 설정
                adapter = RecyclerShowAllAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerShowAllAdapter: RecyclerView.Adapter<RecyclerShowAllAdapter.RecyclerShowAllViewHolder>(){

        inner class RecyclerShowAllViewHolder(rowShowAllBinding: RowShowAllBinding): RecyclerView.ViewHolder(rowShowAllBinding.root){
            val rowShowAllBinding: RowShowAllBinding

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
            return 100
        }

        override fun onBindViewHolder(holder: RecyclerShowAllViewHolder, position: Int) {
            holder.rowShowAllBinding.textShowAllSubject.text = "Subject: $position"
            holder.rowShowAllBinding.textShowAllWriteDate.text = "2024-02-28"
        }
    }

}