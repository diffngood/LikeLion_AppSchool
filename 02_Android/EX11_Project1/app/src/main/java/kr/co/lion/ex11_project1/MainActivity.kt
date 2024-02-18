package kr.co.lion.ex11_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex11_project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


    }


}