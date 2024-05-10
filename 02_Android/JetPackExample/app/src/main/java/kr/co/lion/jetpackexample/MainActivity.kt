package kr.co.lion.jetpackexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            route = ScreenName.MainScreen.name,
            // 화면 전환 애니메이션 설정
            // A 화면에서 B 화면으로 이동된다고 가정한다.
            // 다음 화면으로 전환 될때 B 화면에 적용되는 애니메이션
            enterTransition = {
                slideInHorizontally(
                    // 화면 초기 위치
                    initialOffsetX = {it},
                    // 애니메이션 부가 설정
                    // durationMillis : 애니메이션 동작 시간
                    // delayMillis : 애니메이션 대기 시간
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면으로 전환 될때 A 화면에 적용되는 애니메이션
            exitTransition = {
                // slideOutHorizontally()
                fadeOut(
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면에서 돌아올 때 A 화면에 적용되는 애니메이션
            popEnterTransition = {
                // slideInHorizontally()
                fadeIn(
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면에서 돌아올 때 B 화면에 적용되는 애니메이션
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            }
        ) {
            // MainScreen이 구성되도록 호출한다.
            MainScreen(navHostController = navController)
        }

        // 입력화면
        composable(
            route = ScreenName.InputScreen.name,
            enterTransition = {
                slideInHorizontally(
                    // 화면 초기 위치
                    initialOffsetX = {it},
                    // 애니메이션 부가 설정
                    // durationMillis : 애니메이션 동작 시간
                    // delayMillis : 애니메이션 대기 시간
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면으로 전환 될때 A 화면에 적용되는 애니메이션
            exitTransition = {
                // slideOutHorizontally()
                fadeOut(
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면에서 돌아올 때 A 화면에 적용되는 애니메이션
            popEnterTransition = {
                // slideInHorizontally()
                fadeIn(
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면에서 돌아올 때 B 화면에 적용되는 애니메이션
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            }
        ) {
            // InputScreen이 구성되도록 호출한다.
            InputScreen(navHostController = navController)
        }

        // 결과화면
        composable(
            // 결과를 보여주는 화면은 메모 번호가 필요하기 때문에 메모 번호를 받도록 구성해준다.
            // 화면의 이름/값 : 값 부분으로 전달되는 값이 memoIdx라는 이름으로 추출할 수 있다.
            route = "${ScreenName.OutputScreen.name}/{memoIdx}",
            enterTransition = {
                slideInHorizontally(
                    // 화면 초기 위치
                    initialOffsetX = {it},
                    // 애니메이션 부가 설정
                    // durationMillis : 애니메이션 동작 시간
                    // delayMillis : 애니메이션 대기 시간
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면으로 전환 될때 A 화면에 적용되는 애니메이션
            exitTransition = {
                // slideOutHorizontally()
                fadeOut(
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면에서 돌아올 때 A 화면에 적용되는 애니메이션
            popEnterTransition = {
                // slideInHorizontally()
                fadeIn(
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 다음 화면에서 돌아올 때 B 화면에 적용되는 애니메이션
            popExitTransition = {//
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(durationMillis = 200, delayMillis = 200)
                )
            },
            // 보여줄 화면으로 값을 전달한다.
            // arguments 담은 객체는 다음에 보여줄 화면 Composable로 전달된다.
            arguments = listOf(
                // memoIdx 라는 이름으로 전달되는 값을 담아 준다.
                navArgument("memoIdx"){
                    // 전달할 값의 타입
                    type = NavType.IntType
                },
            )
        ) {
            // OutputScreen이 구성되도록 호출한다.
            // navHostController와 데이터를 추출할 수 있는 객체를 전달한다.
            ResultScreen(navHostController = navController, it)
        }
    }
}