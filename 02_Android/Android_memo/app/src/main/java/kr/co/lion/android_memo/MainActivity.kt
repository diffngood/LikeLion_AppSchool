package kr.co.lion.android_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.android_memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        settingToolbar()
        replaceFragment(FragmentName.MAIN_FRAGMENT, false, false, null)
    }

    // 툴바 설정
    fun settingToolbar(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "메모"
                // 메뉴
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemAdd -> {
                            // Add 메뉴는 보이지 않게 한다.
                            it.isVisible = false
                            // Done 메뉴를 보이게 한다.
                            menu?.findItem(R.id.menuItemDone)?.isVisible = true
                            // 타이틀 변경
                            title = "메모 입력"

                            // Back
                            setNavigationIcon(R.drawable.arrow_back_24px)
                            setNavigationOnClickListener {
                                backProcess()
                            }

                            replaceFragment(FragmentName.INPUT_FRAGMENT, true, false, null)
                        }
                        R.id.menuItemDone -> {
                            if (newFragment is InputFragment){
                                (newFragment as InputFragment).saveMemoData()
                            }
                            backProcess()
                        }
                    } // when (end)
                    true
                } // setOnMenuItemClickListner(end)

            }
        }
    }

    // 뒤로가기 처리
    fun backProcess(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // Add 메뉴를 보이게 한다.
                menu?.findItem(R.id.menuItemAdd)?.isVisible = true
                // Done 메뉴를 사라지게 한다.
                menu?.findItem(R.id.menuItemDone)?.isVisible = false
                // 타이틀 변경
                title = "메모"
                // Back을 사라지게 한다.
                navigationIcon = null
                // Main으로 돌아간다.
                removeFragment(FragmentName.INPUT_FRAGMENT)
            }
        }
    }

    fun replaceFragment(name:FragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

        SystemClock.sleep(150)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(name){
            FragmentName.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            // 입력 화면
            FragmentName.INPUT_FRAGMENT -> {
                newFragment = InputFragment()
            }
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){

            // 애니메이션 설정
            if(isAnimate == true){
                // oldFragment -> newFragment
                // oldFragment : exitTransition
                // newFragment : enterTransition

                // newFragment -> oldFragment
                // oldFragment : reenterTransition
                // newFragment : returnTransition

                // MaterialSharedAxis : 좌우, 위아래, 공중 바닥 사이로 이동하는 애니메이션 효과
                // X - 좌우
                // Y - 위아래
                // Z - 공중 바닥
                // 두 번째 매개변수 : 새로운 화면이 나타나는 것인지 여부를 설정해준다.
                // true : 새로운 화면이 나타나는 애니메이션이 동작한다.
                // false : 이전으로 되돌아가는 애니메이션이 동작한다.

                if(oldFragment != null){
                    // old에서 new가 새롭게 보여질 때 old의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    // new에서 old로 되돌아갈때 old의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈때 new의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }

            // Fragment를 교체한다.(이전 Fragment가 없으면 새롭게 추가하는 역할을 수행한다)
            // 첫 번째 매개 변수 : Fragment를 배치할 FragmentContainerView의 ID
            // 두 번째 매개 변수 : 보여주고하는 Fragment 객체를
            fragmentTransaction.replace(R.id.containerMain, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if(addToBackStack == true){
                // BackStack 포함 시킬때 이름을 지정해주면 원하는 Fragment를 BackStack에서 제거할 수 있다.
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name:FragmentName){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    // RecyclerView 갱신 메서드
    fun reloadRecyclerView(){
        if (newFragment is MainFragment){
            (newFragment as MainFragment).reloadRecyclerView()
        }
    }
}

enum class FragmentName(var str:String){
    MAIN_FRAGMENT("MainFragment"),
    INPUT_FRAGMENT("InputFragment"),
}