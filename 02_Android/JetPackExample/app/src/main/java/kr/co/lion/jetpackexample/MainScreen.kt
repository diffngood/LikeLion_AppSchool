package kr.co.lion.jetpackexample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.co.lion.jetpackexample.db.MemoDatabase
import kr.co.lion.jetpackexample.db.MemoEntity

// Material 3 가 아직 완성 버전이 아니기 때문에
// 실험적 요소(미완성된 UI 요소)를 사용할 때 반드시 붙혀줘야 하는 어노테이션
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController){

    val context = LocalContext.current
    // 데이터 베이스 객체를 가져온다.
    val memoDatabase = MemoDatabase.getInstance(context)

    // JetPack Compose에서 코루틴은 화면과 관련된 작업을 할 수도 있기 때문에
    // 화면이 모두 만들어진 다음에 코루틴을 가동하도록 제한되어 있다.
    // Scaffold 부분이 모두 수행되어야지만 화면이 구성되고 그 이후에 코루틴을 운영할 수 있다.
    //
    // 생성된 이후에 자동으로 원하는 작업을 수행할 수 있도록 만들 수 있다.

    // 라이프 사이클을 관리하는 객체를 생성하여 반환하는 Compose는 아 파일 제일 하단에 있습니다.

    // 라이프사이클을 관리하는 객체를 가져온다.
    val lifecycleEvent = rememberLifeCycleEvent()

    // 리스트 뷰 구성을 위해 사용할 리멤버 변수
    val memoList = remember {
        mutableStateOf(listOf<MemoEntity>())
    }

    // 라이프사이클이 변경될때 자동으로 동작하는 부분을 만들어준다.
    LaunchedEffect(lifecycleEvent) {
        // 라이프사이클이 ON_RESUME 상태 일때..
        if (lifecycleEvent == Lifecycle.Event.ON_START || lifecycleEvent == Lifecycle.Event.ON_RESUME){
            // 데이터를 가져온다.
            CoroutineScope(Dispatchers.Main).launch {
                val job1 = async (Dispatchers.IO) {
                    // 데이터를 가져온다.
                    memoDatabase?.memoDao()?.selectMemoDataAll()
                }
                memoList.value = job1.await() as List<MemoEntity>
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = "메모 목록")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    // 툴바 색성
                    containerColor = Color.White,
                    // 툴바의 타이틀 색상
                    titleContentColor = Color.Black
                ),
                // 툴바의 메뉴
                actions = {
                    IconButton(onClick = {
                            // InputScreen이 보이게
                        navHostController.navigate(ScreenName.InputScreen.name)
                    })
                    {
                        Icon(imageVector = Icons.Filled.Add,
                            contentDescription = "메모 추가",
                            tint = Color.Black)
                    }
                }
            )
        },
    ) {
        // 위에서 아래방향으로 배치하는 레이아웃
        Column(
            // fillMaxSize : 화면의 크기를 단말기 전체 화면으로 설정한다.
            // padding : 여백, Scaffold의 it 안에는 상단 툴바 만큼의 여백이 설정되어 있다.
            // background : 배경색상
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
        ) {
            // Column 은 위에서 아래방향으로 화면 요소들을 배치하기 위해 사용한다.
            // Row는 좌측에서 우측 방향으로 화면 요소들을 배치하기 위해 사용한다.
            // Box는 겹쳐지게 화면 요소들을 배치하기 위해 사용한다.
            // 위의 3개는 배치한 화면 요소들이 단말기 화면 밖으로 벗어난다고 해도 모두 생성된다.
            // Lazy로 시자하는 것들도 위와 유사하지만 화면상에 보이지 않는 화면 요소들은
            // 생성이 대기하고 있다가 보이게 되는 순간에 생성된다.
            // 보였다가 사라진 요소들은 사용 대기상태가 되고 재소용 된다.
            LazyColumn {
                // 리스트
                // 100개의 항목을 가진 리스트를 생성한다.
                // LazyColumn 안에 있기 때문에 보이지 않는 항목들은 생성이 대기된다
                // 사라진 항목들은 새롭게 나타난 항목들을 위해 재사용 된다.
                // it 에는 몇 번째 항목인지의 값이 들어온다.
                items(memoList.value.size) {
                    // 항목 하나의 모양을 구성한다.
                    Column (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                            // 항목을 눌렀을 때
                            .clickable {
                                // 결과 화면이 보이도록 한다.
                                navHostController.navigate("${ScreenName.OutputScreen.name}/${memoList.value[it].memoIdx}")
                            }
                    ) {
                        Text(text = memoList.value[it].memoSubject)
                    }
                    Divider()
                }
            }

        }
    }
}


// Compose 라이프 사이클을 관리하는 Composable
@Composable
fun rememberLifeCycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) : Lifecycle.Event{
    // 라이프 사이클 값을 담고 있을 리맴버 객체
    var state by remember{
        // 모든 라이프 사이클 상태 값을 담을 리맴버 객체로 생성한다.
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }
    // 라이프 사이클 관리 객체와 state 리맴버 변수를 연결해준다.
    DisposableEffect(lifecycleOwner) {
        // 라이프 사이클 감시자
        // 라이프 사이클이 변경되면 지정된 메서드가 호출된다.
        var observer = LifecycleEventObserver{ _, event ->
            // 새로운 라이프 사이클 상태를 리맴버 프로퍼티에 넣어준다.
            // 그 후 리맴버 프로퍼티를 사용한 모든 코드들이 다시 동작을 하게 된다.
            state = event
        }
        // 감시자를 라이프 사이클 관리 객체에 연결해준다.
        lifecycleOwner.lifecycle.addObserver(observer)
        // Composable이 종료되면 감시자도 자동 해지되게 해준다.
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}
