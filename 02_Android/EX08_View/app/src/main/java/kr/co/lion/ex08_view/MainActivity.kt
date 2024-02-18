package kr.co.lion.ex08_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex08_view.databinding.ActivityMainBinding
import kr.co.lion.ex08_view.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var nameData = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            button.setOnClickListener{

                nameData.add(textFieldUserName.text!!.toString()) // 입력한 내용을 추가

                textFieldUserName.setText("") // 입력 요소를 비워준다.

                recyclerView.adapter?.notifyDataSetChanged() // 리사이클러 뷰를 갱신한다.
            }

            // RecyclerView 설정
            recyclerView.apply {
                adapter = RecyclerViewAdapter() // 어뎁터
                layoutManager = LinearLayoutManager(this@MainActivity) // 레이아웃 매니저
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL) // 데코레이션
                addItemDecoration(deco)
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var rowBinding:RowBinding

            init {
                this.rowBinding = rowBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)

            return ViewHolderClass(rowBinding)
        }

        override fun getItemCount(): Int {
            return nameData.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textView.text = nameData[position]
        }
    }
}