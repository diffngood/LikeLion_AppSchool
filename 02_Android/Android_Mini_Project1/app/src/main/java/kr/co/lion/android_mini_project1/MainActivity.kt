package kr.co.lion.android_mini_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android_mini_project1.databinding.ActivityMainBinding
import kr.co.lion.android_mini_project1.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setToolBar()
    }

    fun setToolBar(){
        activityMainBinding.apply {
            toolbarMain.apply {
                title = "동물원 관리"
                inflateMenu(R.menu.menu_main)
            }
        }
    }

    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){

        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            when(position % 3){
                0 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "사자 이름"
                    holder.rowMainBinding.textViewRowMainType.text = "사자 타입"
                }
                1 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "호랑이 이름"
                    holder.rowMainBinding.textViewRowMainType.text = "호랑이 타입"
                }
                2 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "기린 이름"
                    holder.rowMainBinding.textViewRowMainType.text = "기린 타입"
                }
            }
        }

    }
}