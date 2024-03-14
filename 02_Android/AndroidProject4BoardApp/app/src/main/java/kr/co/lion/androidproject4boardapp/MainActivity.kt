package kr.co.lion.androidproject4boardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.Manifest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kr.co.lion.androidproject4boardapp.databinding.ActivityMainBinding
import kr.co.lion.androidproject4boardapp.fragment.AddUserInfoFragment
import kr.co.lion.androidproject4boardapp.fragment.JoinFragment
import kr.co.lion.androidproject4boardapp.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 프레그먼트의 주소 값을 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 스플래쉬 스크린이 나타나게 한다.
        // 반드시 setContentView 전에 해야 한다.
        installSplashScreen()

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인
        requestPermissions(permissionList, 0)

        /*
        CoroutineScope(Dispatchers.Main).launch {
            val result = fsTest()
            Log.d("test1234", "result : $result")
        } */

        replaceFragment(MainFragmentName.LOGIN_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name: MainFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?){

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(name){
            // 로그인 화면
            MainFragmentName.LOGIN_FRAGMENT -> {
                newFragment = LoginFragment()
            }
            // 회원가입 화면 (1)
            MainFragmentName.JOIN_FRAGMENT -> {
                newFragment = JoinFragment()
            }
            // 회원가입 화면 (2)
            MainFragmentName.ADD_USER_INFO_FRAGMENT -> {
                newFragment = AddUserInfoFragment()
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
    fun removeFragment(name: MainFragmentName){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    suspend fun fsTest(): Long{

        var result = 0L

        // 컬렉션에 접근한다.
        val collection = Firebase.firestore.collection("c1")

        // 코루틴을 가동시킨다.
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근하여 모든 문서를 가져온다.
            // 코루틴 내부에서 await을 사용하면 작업이 완료될 때 까지 대기했다가
            // 작업이 완료되면 값을 반환 받을 수 있다.
            val data = collection.get().await()

            // 가져온 문서의 수 만큼 반복한다.
            data.documents?.forEach {
                // 문서로 부터 필드값을 가지고와서 출력한다.
                val f1 = it?.getString("f1")
                val f2 = it?.getLong("f2")

                result += f2!!

                Log.d("test1234", "f1 : $f1")
                Log.d("test1234", "f2 : $f2")
            }
        }

        // 코루틴 수행이 모두 끝날 때 까지 대기하게 한다.
        // 코루틴 내부의 코드가 다 끝난 다음 return 이 수행될 수 있도록 한다.
        job1.join()

        return result
    }


}