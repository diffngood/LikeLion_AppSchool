package kr.co.lion.android38_filestream

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android38_filestream.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 확인 받을 권한 목록
    var permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인
        requestPermissions(permissionList, 0)

        activityMainBinding.apply {
            // 내부 저장소
            // data/data/애플리케이션 패키지/files 폴더에 저장된다.
            // 저장한 애플리케이션 에서만 접근할 수 있다.
            // 저장한 애플리케이션 에서만 접근할 수 있기 때문에 코드상에서의 자유로운 접근이 가능하며
            // 필요한 권한도 없다. 다른 애플리케이션이 접근할 수 없으며 사용자가 파일에 접근하는 것도 불가능하다.
            button.setOnClickListener {
                // MODE_PRIVATE : 덮어 씌운다.
                // MODE_APPEND : 이어서 쓴다.
                // Stream을 가져온다.
                val fileOutputStream = openFileOutput("data1.dat", MODE_PRIVATE)
                val dataOutputStream = DataOutputStream(fileOutputStream)

                dataOutputStream.writeInt(100)
                dataOutputStream.writeDouble(11.11)
                dataOutputStream.writeBoolean(true)
                dataOutputStream.writeUTF("문자열1")

                dataOutputStream.flush()
                dataOutputStream.close()

                textView.text = "내부 저장소 쓰기 완료"
            }

            // 내부 저장소 읽기
            button2.setOnClickListener {
                // Stream을 가져온다.
                val fileInputStream = openFileInput("data1.dat")
                val dataInputStream = DataInputStream(fileInputStream)
                // 데이터를 읽어온다.
                val data1 = dataInputStream.readInt()
                val data2 = dataInputStream.readDouble()
                val data3 = dataInputStream.readBoolean()
                val data4 = dataInputStream.readUTF()

                dataInputStream.close()
                fileInputStream.close() // 이거만 닫아도 되긴함

                textView.apply {
                    text = "data1 : ${data1}\n"
                    append("data2 : ${data2}\n")
                    append("data3 : ${data3}\n")
                    append("data4 : ${data4}\n")
                }
            }

            // 외부 저장소 (sdcard/Android/data/패키지명/files/여기!)
            // 외부 저장소 영역의 Android/data/ 폴더에 저장된다.
            // Android/data/ 경로에 애플리케이션 패키지 명으로 폴더가 만들어지고 false 폴더도 만들어진다.
            // 여기에 파일이 저장된다.

            button3.setOnClickListener {

                // 외부 저장소 까지의 경로를 가져온다.
                // null을 넣어주면 files 까지의 경로를 가져온다.
                val filePath = getExternalFilesDir(null).toString()!!

                // 이를 통해 스트림을 생성한다. (내부 저장소와 이 부분만 다르다)
                val fileOutputStream = FileOutputStream("${filePath}/data2.dat")
                val dataOutputStream = DataOutputStream(fileOutputStream)

                dataOutputStream.writeInt(200)
                dataOutputStream.writeDouble(22.22)
                dataOutputStream.writeBoolean(false)
                dataOutputStream.writeUTF("문자열2")

                dataOutputStream.flush()
                dataOutputStream.close()
                
                textView.text = "외부 저장소 앱 데이터 폴더에 저장"
            }
        }
    }
}