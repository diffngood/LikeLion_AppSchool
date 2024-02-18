package kr.co.lion.android32_service

import android.app.ActivityManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android32_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 서비스 가동을 위해 사용할 Intent
    lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            button.setOnClickListener {
                // 현재 서비스가 실행중인지 파악한다.
                val chk = isServiceRunning("kr.co.lion.android32_service.TestService")
                // 서비스를 실행하기 위한 Intent 생성
                serviceIntent = Intent(this@MainActivity, TestService::class.java)

                // 서비스가 가동중이 아니라면
                if(chk == false){
                    // 서비스를 가동한다.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        startForegroundService(serviceIntent)
                    } else {
                        startService(serviceIntent)
                    }
                }
            }

            button2.setOnClickListener {
                // 실행중인 서비스를 중단시킨다. 서비스 객체가 생성되서 당겨져 있다면
                if (::serviceIntent.isInitialized){
                    stopService(serviceIntent)
                }
            }
        }
    }

    // 서비스가 가동중인지 확인하는 메서드
    fun isServiceRunning(name:String) : Boolean{
        // 서비스 관리자를 추출한다.
        val activitiyManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        // 현재 실행 중인 서비스들을 가져온다.
        val serviceList = activitiyManager.getRunningServices(Int.MAX_VALUE)
        // 가져온 서비스의 수 만큼 반복한다.
        serviceList.forEach {
            // 현재 서비스의 이름이 동일한지 확인한다.
            if (it.service.className == name){
                return true
            }
        }

        return false
    }
}