package kr.co.lion.androidproject1test

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.androidproject1test.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    // Activity 런처
    lateinit var modifyActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setLauncher()
        setToolbar()
        setView2()
    }

    // 런처 설정
    fun setLauncher(){
        // ModifyActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1){

        }
    }

    fun setToolbar(){
        activityShowBinding.apply {
            toolbarShow.apply {
                title = "동물 정보"

                // Back 버튼
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                inflateMenu(R.menu.menu_show)

                setOnMenuItemClickListener {
                    // 사용자가 선택한 메뉴 항목의 id로 분기한다.
                    when(it.itemId){
                        // 수정
                        R.id.menu_item_show_modify -> {
                            // ModifyActivity 실행
                            val modifyIntent = Intent(this@ShowActivity, ModifyActivity::class.java)

                            // 동물 순서값을 지정한다.
                            val position = intent.getIntExtra("position", 0)
                            modifyIntent.putExtra("position", position)

                            modifyActivityLauncher.launch(modifyIntent)
                        }
                        // 삭제
                        R.id.menu_item_show_delete -> {
                            // 항목 순서 값을 가져온다.
                            val position = intent.getIntExtra("position", 0)
                            // 항목 번째 객체를 리스트에서 제거한다.
                            Util.animalList.removeAt(position)
                            finish()
                        }
                    }
                    true
                }

            }
        }
    }
    
    // 뷰 설정
    fun setView(){
        activityShowBinding.apply {
            // TextView
            textViewShowInfo.apply {
                // 항목 순서값을 가져온다
                val position = intent.getIntExtra("position", 0)
                // 포지션 번째 객체를 추출한다.
                val animal = Util.animalList[position]

                // 공통
                text = "동물 종류 : ${animal.type.str}\n"
                append("이름 : ${animal.name}\n")
                append("나이 : ${animal.age}살\n")
                
                // 이것도 가능
//                if (animal.type == AnimalType.ANIMAL_TYPE_LION){
//
//                }
                when(animal.type){
                    // 사자
                    AnimalType.ANIMAL_TYPE_LION -> {
                        // Lion 타입으로 형변환한다.
                        val lion = animal as Lion
                        append("털의 개수 : ${lion.hairCount}개\n")
                        append("성별 : ${lion.gender.str}\n")

                    }
                    // 호랑이
                    AnimalType.ANIMAL_TYPE_TIGER -> {
                        val tiger = animal as Tiger
                        append("줄무늬 개수 : ${tiger.lineCount}개\n")
                        append("몸무게 : ${tiger.weight}kg\n")

                    }
                    // 기린
                    AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                        val giraffe = animal as Giraffe
                        append("목의 길이 : ${giraffe.neckLength}cm\n")
                        append("달리는 속도 : 시속 ${giraffe.runSpeed}km\n")
                    }
                }

            }
        }
    }

    // 뷰 설정
    fun setView2(){
        activityShowBinding.apply {
            // TextView
            textViewShowInfo.apply {
                // 항목 순서값을 가져온다
                val position = intent.getIntExtra("position", 0)
                // 포지션 번째 객체를 추출한다.
                // val animal = Util.animalList[position]
                val animal = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    // !! 붙이는 이유는 null을 허용하지 않는 타입으로 반환하는거임
                    intent.getParcelableExtra("obj", Animal::class.java)!!
                } else {
                    // !! 붙이는 이유는 null을 허용하지 않는 타입으로 반환하는거임
                    intent.getParcelableExtra<Animal>("obj")!!
                }

                // 공통
                text = "동물 종류 : ${animal.type.str}\n"
                append("이름 : ${animal.name}\n")
                append("나이 : ${animal.age}살\n")
                
                if (animal is Lion){ // 사자 
                    append("털의 개수 : ${animal.hairCount}개\n")
                    append("성별 : ${animal.gender.str}\n")

                } else if (animal is Tiger){ // 호랑이
                    append("줄무늬 개수 : ${animal.lineCount}개\n")
                    append("몸무게 : ${animal.weight}kg\n")

                } else if (animal is Giraffe){ // 기린
                    append("목의 길이 : ${animal.neckLength}cm\n")
                    append("달리는 속도 : 시속 ${animal.runSpeed}km\n")
                }
            } // textViewShowInfo.apply (end)
        } // activityShowBinding.apply (end)
    }// fun setView2 (end)

    override fun onResume() {
        super.onResume()
        // 다른곳 갔다 왔을 경우 출력 내용을 다시 구성해준다.
        setView2()
    }
}