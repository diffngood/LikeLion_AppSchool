package kr.co.lion.naveropenapiretrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.naveropenapiretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 웹 통신 기초
// 요청방식(GET, POST, DELETE, ADD, 등등...)
// GET
// 사용자가 서버로 전달하는 파라미터 데이터가 주소에 붙여져서 전달된다.
// 서버로 전달되는 데이터의 총량이 적다.
// 요청 속도가 빠르다.
// 서버로 전달하는 파라미터가 공개된다.
// 주소를 포함한 파라미터 데이터의 총 길이가 255글자를 넘지 못한다.
// 영문, 숫자, 특수문자 외의 다른 글자를 허용하지 않는다(띄어쓰기도 허용하지 않는다)
// (최근의 웹 브라우저나 라이브러리들은 데이터에 띄어쓰기가 있으면 띄어쓰기에 해당하는 글자로
// 자동 변환해 서버로 전달해준다)

// POST
// 사용자가 서버로 전달하는 파라미터 데이터가 HTTP 요청 헤더에 담겨져서 전달된다.
// 서버로 전달되는 데이터의 총량이 많다.
// 요청 속도가 느리다.
// 파라미터 데이터의 총량은 제한이 없다.
// 모든 글자들을 허용한다.

// HTTP 요청 헤더
// 서버에 요청을 할때 데이터를 숨겨서 전달할 수 있는 수단
// HTTP 요청 헤더에 담아서 전달되는 데이터는 사용자가 알 수 없다.
// 서버에 요청을 하게 되면 기본적으로 몇 가지 데이터(단말기 정보, 브라우저의 정보 등등)가
// 담기고 그 밖에 개발자가 추가적으로 데이터를 담아 전달 수 있다.

// OPEN API 사용 절차
// 1. 반드시 관련 문서를 찾는다.
// - https://developers.naver.com/docs/serviceapi/search/news/news.md#%EB%89%B4%EC%8A%A4

// 2. API 키(혹은 접속시 필요한 개발자 구분값) 발급 방법을 확인해야 한다.
// - 애플리케이션을 등록하면 클라이언트 ID와 클라이언트 시크릿을 발급받을 수 있다.

// 3. 요청할 페이지의 주소를 확인해야 한다.
// - https://openapi.naver.com/v1/search/news.json

// 4. HTTP 요청 헤더로 전달할 데이터가 있는지 확인한다.
// - X-Naver-Client-Id : 발급 받은 클라이언트 ID
// - X-Naver-Client-Secret : 발급 받은 클라이언트 시크릿

// 5. 파라미터로 전달할 데이터가 있는지 확인한다.
// - query : 검색어(필수)
// - display : 한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100)
// - start : 검색 시작 위치(기본값: 1, 최댓값: 1000)
// - sort : 검색 결과 정렬 방법, sim: 정확도순으로 내림차순 정렬(기본값), date: 날짜순으로 내림차순 정렬

// 6. 요청 방식(GET or POST)을 확인한다.
// - GET

// 7. 서버가 전달해주는 데이터의 양식을 확인한다.
// 8. 오류 코드를 확인한다.
// 9. POSTMAN 을 이용해 요청 테스트를 한다.

// Retrofit 사용
// 1. AndroidManifest.xml 에 Internet 권한을 추가해준다.
//        <uses-permission android:name="android.permission.INTERNET"/>

// 2. build.gradle.kts 에 라이브러리를 설정한다.
// implementation("com.squareup.retrofit2:retrofit:2.11.0")
// implementation("com.squareup.retrofit2:converter-gson:2.11.0")
// implementation("com.squareup.retrofit2:converter-scalars:2.11.0")
// implementation("com.google.code.gson:gson:2.10.1")

// 3. 서버가 전달하는 데이터를 담을 클래스들을 작성해준다.
// ResultNews.kt

// 4. 요청 정보를 설정할 인터페이스를 작성해준다.
// NaverAPI.kt

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            buttonRequest.setOnClickListener {
                textViewResult.text = ""

                // 요청을 위한 Retrofit 객체를 생성한다.
                val retrofitBuilder = Retrofit.Builder()
                // 기본 주소
                retrofitBuilder.baseUrl("https://openapi.naver.com/v1/search/")
                // 가져온 데이터를 분석하여 객체에 담아줄 도구를 설정한다.
                // JSON 양식의 문서이므로 GsonConverter를 사용한다.
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create())

                val retrofit = retrofitBuilder.build()

                // 요청을 위해 사용할 객체
                // 앞서 만든 NaverAPI 인터페이스(요청에 필요한 정보를 가진 인터페이스)를 지정한다.
                val api = retrofit.create(NaverAPI::class.java)
                // 요청 객체를 추출한다.
                // 이 때, 요청을 위한 인터페이스의 메서드를 호출한다.
                val clientId = "X5REOXGceQzqqE7LZ5de"
                val clientSecret = "210jWrLh8G"
                val keyword = inputKeyword.text.toString()
                val display = 100

                val callNews = api.getSearchNews(clientId, clientSecret, keyword, display)

                // 요청한다.
                // 요청이 완료되면 동작할 콜백을 설정해준다
                callNews.enqueue(object : Callback<ResultNews> {
                    // 요청에 성공 했을 때
                    override fun onResponse(call: Call<ResultNews>, response: Response<ResultNews>) {
                        Log.d("test1234", "요청 성공")
                        // 서버로 부터 가져온 JSON 데이터는 이미 분석이 완료되어 객체에 담겨져서 전달된다.
                        // 객체에 담겨져 있는 데이터를 필
                        response.body()?.apply {
                            textViewResult.append("lastBuildDate : ${lastBuildDate}\n")
                            textViewResult.append("total : ${total}\n")
                            textViewResult.append("start : ${start}\n")
                            textViewResult.append("display : ${display}\n")

                            items.forEach {
                                textViewResult.append("title : ${it.title}\n")
                                textViewResult.append("originallink : ${it.originallink}\n")
                                textViewResult.append("link : ${it.link}\n")
                                textViewResult.append("description : ${it.description}\n")
                                textViewResult.append("pubDate : ${it.pubDate}\n")
                            }
                        }

                    }
                    // 요청에 실패 했을 때
                    override fun onFailure(call: Call<ResultNews>, error: Throwable) {
                        Log.e("test1234", "요청 실패", error)
                    }


                })
            }
        }
    }
}