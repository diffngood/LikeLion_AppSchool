package kr.co.lion.jetpackexample

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.co.lion.jetpackexample.db.MemoDatabase
import kr.co.lion.jetpackexample.db.MemoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(navHostController: NavHostController) {

    // context
    val context = LocalContext.current

    // 제목 입력 요소와 연결되어 있는 데이터 관리 요소
    val subjectTextState = remember {
        mutableStateOf("")
    }

    // 내용 입력 요소와 연결되어 있는 데이터 관리 요소
    val contentTextState = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "메모 등록")
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
                // 우측 상단 메뉴
                actions = {
                    IconButton(onClick = {
                        // 데이터 베이스 객체를 가져온다.
                        var memoDatabase = MemoDatabase.getInstance(context)
                        // 데이터를 담는다.
                        val memoEntity = MemoEntity(memoSubject = subjectTextState.value, memoText = contentTextState.value)
                        // 저장
                        CoroutineScope(Dispatchers.Main).launch {
                            async (Dispatchers.IO){
                                memoDatabase?.memoDao()?.insertMemoData(memoEntity)
                            }
                            navHostController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "완료",
                            tint = Color.Black
                        )
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

            // 제목 입력 요소
            TextField(
                // TextField가 관리하는 값
                // 데이터 관리 요소를 지정해준다.
                value = subjectTextState.value,
                // 사용자가 입력한 값이 변경되었을 때
                onValueChange = {
                    subjectTextState.value = it
                },
                // hint
                placeholder = {
                    Text(text = "제목을 입력해주세요")
                },
                // 한 줄 입력 요소로 설정한다.
                singleLine = true,
                // 가로 길이
                modifier = Modifier.fillMaxWidth(),
                // 색상 설정
                colors = TextFieldDefaults.colors(
                    // 포커스가 주어졌을 때의 배경 색상(투명색으로 설정)
                    focusedContainerColor = Color.Transparent,
                    // 포커스가 사라졌을 때의 배경 색상(투명색으로 설정)
                    unfocusedContainerColor = Color.Transparent
                ),
                // 좌측의 아이콘
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "제목"
                    )
                },
                // 우측의 아이콘
                trailingIcon = {
                    IconButton( onClick = {
                        // 제목 TextField에 연결되어 있는 데이터 관리요소에
                        // 길이가 0 인 문자열을 넣어준다.
                        subjectTextState.value = ""
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "초기화"
                        )
                    }
                },
            )
            // 여백
            Spacer(modifier = Modifier.padding(top = 10.dp))

            // 제목 입력 요소
            TextField(
                // TextField가 관리하는 값
                // 데이터 관리 요소를 지정해준다.
                value = contentTextState.value,
                // 사용자가 입력한 값이 변경되었을 때
                onValueChange = {
                    contentTextState.value = it
                },
                // hint
                placeholder = {
                    Text(text = "내용을 입력해주세요")
                },
                // 가로 길이
                modifier = Modifier.fillMaxWidth(),
                // 색상 설정
                colors = TextFieldDefaults.colors(
                    // 포커스가 주어졌을 때의 배경 색상(투명색으로 설정)
                    focusedContainerColor = Color.Transparent,
                    // 포커스가 사라졌을 때의 배경 색상(투명색으로 설정)
                    unfocusedContainerColor = Color.Transparent
                ),
                // 좌측의 아이콘
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = "내용"
                    )
                },
                // 우측의 아이콘
                trailingIcon = {
                    IconButton( onClick = {
                        // 내용 TextField에 연결되어 있는 데이터 관리요소에
                        // 길이가 0 인 문자열을 넣어준다.
                        contentTextState.value = ""
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "초기화"
                        )
                    }
                },
                //
            )
        }
    }
}