package kr.co.lion.android_homework_1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android_homework_1.databinding.ActivityMainBinding
import kr.co.lion.android_homework_1.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 런처 모음
    // InputActivity의 런처
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    // ShowMemoActivity의 런처
    lateinit var showMemoActivityLauncher: ActivityResultLauncher<Intent>

    // 학생들의 정보를 담을 리스트
    object MemoManager {
        val memoList = mutableListOf<MemoData>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolBar()
        setView()
    }

    // 데이터 관리
    fun initData(){
        // InputActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                        val memoData = it.data?.getParcelableExtra("memoData", MemoData::class.java)
                        MemoManager.memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    } else {
                        val memoData = it.data?.getParcelableExtra<MemoData>("memoData")
                        MemoManager.memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
        // showMemoActivity 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showMemoActivityLauncher = registerForActivityResult(contract2){
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }
    }
    
    // 툴바
    fun setToolBar(){
        activityMainBinding.apply {
            toolbarMain.apply {
                title = "메모 관리"

                inflateMenu(R.menu.menu_main)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_main_add -> {
                            val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(inputIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰
    fun setView(){
        activityMainBinding.apply {
            recyclerViewMain.apply {
                // 어댑터, 레이아웃 매니저 설정
                adapter = RecyclerViewMainAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                
                // 구분선
                addItemDecoration(MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL))
            }
        }
    }

    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderClass>(){

        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // 각 항목 클릭시
                this.rowMainBinding.root.setOnClickListener {

                    // Intent 생성
                    val showMemoIntent = Intent(this@MainActivity, ShowMemoActivity::class.java)

                    // 선택 항목의 memo 객체의 adapterPosition 을 Intent 에 담아준다
                    showMemoIntent.putExtra("adapterPosition", adapterPosition)

                    showMemoActivityLauncher.launch(showMemoIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMainBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return MemoManager.memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMainBinding.textViewRowMainTitle.text = "${MemoManager.memoList[position].title}"
            holder.rowMainBinding.textViewRowMainDate.text = "${MemoManager.memoList[position].date}"
        }
    }
}