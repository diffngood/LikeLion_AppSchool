package kr.co.lion.ex07_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex07_recycler.databinding.ActivityMainBinding
import kr.co.lion.ex07_recycler.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // RecyclerView를 구성하기 위한 데이터를 담을 리스트
    var textData1 = mutableListOf<String>()
    var textData2 = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setViewEvent()

        activityMainBinding.apply {
            // RecyclerView 설정
            recyclerView.apply {
                // 어뎁터
                adapter = RecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }

        }
    }
    
    // 이벤트를 설정하는 메서드
    fun setViewEvent(){
        activityMainBinding.apply {
            // 버튼 이벤트
            button.setOnClickListener{
                // 사용자가 입력한 내용을 리스트에 담는다.
                textData1.add(textFieldUserId.text!!.toString())
                textData2.add(textFieldUserName.text!!.toString())

                // 입력 요소를 비워준다.
                textFieldUserId.setText("")
                textFieldUserName.setText("")

                // 리사이클러 뷰를 갱신한다.
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
    
    // 리사이클러 뷰의 어뎁터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){ // 제네릭 ViewHolderClass로지정

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var rowBinding:RowBinding

            init{
                this.rowBinding = rowBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return textData1.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textView.text = textData1[position]
            holder.rowBinding.textView2.text = textData2[position]
        }
    }
}