package kr.co.lion.ex10_view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex10_view.databinding.ActivityMainBinding
import kr.co.lion.ex10_view.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity 실행을 위한 런처
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    // ShowInfoActivity 실행을 위한 런처
    lateinit var showInfoActivityLauncher:ActivityResultLauncher<Intent>

    // 학생정보를 담고 있을 리스트를 생성한다.
    val studentList = mutableListOf<InfoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        initToolbar()
        initView()
    }

    fun initData(){
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){
            if(it.resultCode == RESULT_OK){
                if(it.data != null){
                    val info1 = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it.data!!.getParcelableExtra("obj", InfoClass::class.java)
                    } else {
                        it.data!!.getParcelableExtra<InfoClass>("obj")
                    }

                    // 리스트에 객체 담기
                    studentList.add(info1!!)

                    // 리사이클러 뷰를 갱신한다.
                    activityMainBinding.recyclerViewItem.adapter?.notifyDataSetChanged()
                }
            }
        }

        // ShowInfoActivity 등록
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contract2){

        }
    }

    fun initToolbar(){
        activityMainBinding.apply {
            toolbar.apply {
                title = "학생 정보 관리"

                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 학생 정보 추가
                        R.id.main_menu_item1 -> {
                            // InputActivity를 실행한다.
                            val newIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(newIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    fun initView(){
        activityMainBinding.apply {

            recyclerViewItem.apply {
                // RecyclerView에 어뎁터를 설정한다.
                adapter = RecyclerViewAdapter()
                // layoutManager 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val dec = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(dec)
            }
        }
    }

    inner class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            val rowBinding: RowBinding

            init {
                this.rowBinding = rowBinding

                // 항목 뷰의 가로 세로 길이 셋팅
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목을 터치했을 때의 이벤트
                this.rowBinding.root.setOnClickListener {
                    SystemClock.sleep(100)
                    // ShowInfoActivity를 실행한다.
                    val newIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)

                    // 선택한 항목 번째의 학생 객체를 Intent에 담아준다.
                    newIntent.putExtra("obj", studentList[adapterPosition])

                    showInfoActivityLauncher.launch(newIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textViewRowMainName.text = studentList[position].name
            holder.rowBinding.textViewRowMainGrade.text = "${studentList[position].grade} 학년"
        }
    }

}