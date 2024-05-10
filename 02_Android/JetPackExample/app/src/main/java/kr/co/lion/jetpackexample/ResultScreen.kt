package kr.co.lion.jetpackexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.co.lion.jetpackexample.db.MemoDatabase
import kr.co.lion.jetpackexample.db.MemoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// 전달 받은 데이터를 추출하기 위한 객체를 받을 매개 변수를 정의한다. (navBackStackEntry)
fun ResultScreen(navHostController: NavHostController, navBackStackEntry: NavBackStackEntry){

    val context = LocalContext.current

    // 데이터 베이스 객체를 가져온다.
    val memoDatabase = MemoDatabase.getInstance(context)

    // 전달 받은 데이터를 추출한다.
    var memoIdx = navBackStackEntry.arguments?.getInt("memoIdx")

    // 출력할 데이터를 가지고 있는 리맴버 프로퍼티
    var memoData = remember {
        mutableStateOf(MemoEntity())
    }

    // 라이프사이클을 관리하는 객체를 가져온다.
    val lifecycleEvent = rememberLifeCycleEvent()

    // 라이프사이클이 변경될때 자동으로 동작하는 부분을 만들어준다.
    LaunchedEffect(lifecycleEvent) {
        // 라이프사이클이 ON_RESUME 상태 일때..
        if (lifecycleEvent == Lifecycle.Event.ON_START || lifecycleEvent == Lifecycle.Event.ON_RESUME){
            // 데이터를 가져온다.
            CoroutineScope(Dispatchers.Main).launch {
                val job1 = async (Dispatchers.IO) {
                    // 데이터를 가져온다.
                    memoDatabase?.memoDao()?.selectMemoDataOne(memoIdx!!)
                }
                memoData.value = job1.await() as MemoEntity
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "메모 보기")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    // 툴바 색성
                    containerColor = Color.White,
                    // 툴바의 타이틀 색상
                    titleContentColor = Color.Black
                ),
                // 네비게이션 아이콘
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // 이전 화면이 보이도록 한다.
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            // 표시할 아이콘
                            imageVector = Icons.Filled.ArrowBack,
                            // 설명 문자열(화면에 나타나지는 않는다)
                            contentDescription = "뒤로가기",
                            // 아이콘 색상
                            tint = Color.Black
                        )
                    }
                },
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
            Text(text = "${memoData.value.memoSubject}")
            
            // 여백
            Spacer(modifier = Modifier.padding(top = 10.dp))
            
            Text(text = "${memoData.value.memoText}")
            
        }
    }
}