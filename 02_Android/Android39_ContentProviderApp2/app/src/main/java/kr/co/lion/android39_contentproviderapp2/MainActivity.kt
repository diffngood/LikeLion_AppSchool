package kr.co.lion.android39_contentproviderapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android39_contentproviderapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}