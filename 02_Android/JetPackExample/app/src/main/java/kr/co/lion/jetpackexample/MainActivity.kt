package kr.co.lion.jetpackexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.lion.jetpackexample.ui.theme.JetPackExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetPackExampleTheme {
                // 애플리케이션 메인 코드 함수 호출
                MemoApp()
            }
        }
    }
}

// 각 화면을 지칭할 이름
enum class ScreenName(){
    MainScreen,
    InputScreen,
    OutputScreen
}

@Composable
fun MemoApp(modifier: Modifier = Modifier) {
    // 화면 네비게이션을 관리하는 객체
    val navController = rememberNavController()
    // 보여줄 화면을 등록한다.
    NavHost(
        // 화면 네비게이션을 관리하는 객체
        navController = navController,
        // 첫 화면의 이름
        startDestination = ScreenName.MainScreen.name) {
        
        // 사용할 화면들을 등록해준다.
        // route : 화면의 이름을 지정한다.
        // 새로운 화면을 보여주고 싶을 때 지정하는 이름이 route에 등록되어 있는
        // 화면을 보여주게 된다.
        // 메인 화면
        composable(
            route = ScreenName.MainScreen.name
        ) {
            
        }

        // 입력화면
        composable(
            route = ScreenName.InputScreen.name
        ) {

        }

        // 결과화면
        composable(
            route = ScreenName.OutputScreen.name
        ) {

        }
    }
}