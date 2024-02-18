package kr.co.lion.ex09_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex09_view.databinding.ActivityMainBinding
import kr.co.lion.ex09_view.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var nameData = mutableListOf<String>()
    var gradeData = mutableListOf<Int>()
    var korScoreData = mutableListOf<Int>()
    var engScoreData = mutableListOf<Int>()
    var mathScoreData = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val contract = ActivityResultContracts.StartActivityForResult()
        val secondActivityLauncher = registerForActivityResult(contract) {
            if (it != null){
                when(it.resultCode) {
                    RESULT_OK -> {
                        if (it.data != null){
                            val name = it?.data!!.getStringExtra("name")
                            val grade = it?.data!!.getIntExtra("grade", 0)
                            val kor = it?.data!!.getIntExtra("kor", 0)
                            val eng = it?.data!!.getIntExtra("eng", 0)
                            val math = it?.data!!.getIntExtra("math", 0)

                            nameData.add(name.toString())
                            gradeData.add(grade)
                            korScoreData.add(kor)
                            engScoreData.add(eng)
                            mathScoreData.add(math)

                            activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        }

        activityMainBinding.apply {

            buttonInput.setOnClickListener {
                val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                secondActivityLauncher.launch(secondIntent)
            }

            buttonTotalAvg.setOnClickListener {


                if (nameData.isEmpty() || gradeData.isEmpty() || korScoreData.isEmpty() || engScoreData.isEmpty() || mathScoreData.isEmpty()) {
                    // 아무것도 안 입력하고 누르면 팅겨서 설정해봄
                    return@setOnClickListener
                }

                val thirdIntent = Intent(this@MainActivity, ThirdActivity::class.java)
                val korAvg = korTotal() / korScoreData.size
                val engAvg = engTotal() / engScoreData.size
                val mathAvg = mathTotal() / mathScoreData.size

                thirdIntent.putExtra("korTotal", korTotal())
                thirdIntent.putExtra("korAvg", korAvg)
                thirdIntent.putExtra("engTotal", engTotal())
                thirdIntent.putExtra("engAvg", engAvg)
                thirdIntent.putExtra("mathTotal", mathTotal())
                thirdIntent.putExtra("mathAvg", mathAvg)

                thirdIntent.putExtra("total", korTotal() + engTotal() + mathTotal())
                thirdIntent.putExtra("avg", (korAvg + engAvg + mathAvg) / 3)

                startActivity(thirdIntent)
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
    
    // 리사이클러 뷰
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var rowBinding:RowBinding

            init {
                this.rowBinding = rowBinding

                this.rowBinding.root.setOnClickListener{
                    val fourthIntent = Intent(this@MainActivity, FourthActivity::class.java)
                    val total = korScoreData[adapterPosition] + engScoreData[adapterPosition] + mathScoreData[adapterPosition]

                    fourthIntent.putExtra("name", nameData[adapterPosition])
                    fourthIntent.putExtra("grade", gradeData[adapterPosition])
                    fourthIntent.putExtra("kor", korScoreData[adapterPosition])
                    fourthIntent.putExtra("eng", engScoreData[adapterPosition])
                    fourthIntent.putExtra("math", mathScoreData[adapterPosition])
                    fourthIntent.putExtra("total", total)
                    fourthIntent.putExtra("avg", total / 3)

                    startActivity(fourthIntent)
                }

                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,    // 가로길이
                    ViewGroup.LayoutParams.WRAP_CONTENT     // 세로길이
                )
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
            holder.rowBinding.textViewName.text = nameData[position]
            holder.rowBinding.textViewGrade.text = "${gradeData[position]}학년"
        }
    }

    fun korTotal() : Int {
        var scoretotal = 0

        for (score in korScoreData){
            scoretotal += score
        }

        return scoretotal
    }

    fun engTotal() : Int {
        var scoretotal = 0

        for (score in engScoreData){
            scoretotal += score
        }

        return scoretotal
    }

    fun mathTotal() : Int {
        var scoretotal = 0

        for (score in mathScoreData){
            scoretotal += score
        }

        return scoretotal
    }
}