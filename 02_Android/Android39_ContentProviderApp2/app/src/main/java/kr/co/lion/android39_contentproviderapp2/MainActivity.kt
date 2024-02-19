package kr.co.lion.android39_contentproviderapp2

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android39_contentproviderapp2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            buttonMainInsert.setOnClickListener{
                // 저장할 데이터를 준비한다.
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                // 저장할 데이터를 ContentValues에 담아준다.
                val cv1 = ContentValues()
                cv1.put("data1", 100)
                cv1.put("data2", 11.11)
                cv1.put("data3", "문자열1")
                cv1.put("data4", now)

                // ContentProvider 접근을 위한 이름을 가진 객체를 생성한다.
                val uri = Uri.parse("content://kr.co.lion.testprovider")

                // 저장요청을 수행한다.
                // 저장 요청은 OS에 하게 되고 이 요청을 받은 OS는 해당 Content Provider의 insert 메서드를 호출해준다
                // Content Provider의 insert 메서드 내에 코드가 구현되어 있는대로 동작한다.
                contentResolver.insert(uri, cv1)
                textView.text = "저장완료"
            } // Insert Button Click Listener (end)



        } // activityMainBinding.apply (end)
    }
}