package kr.co.lion.androidproject4boardapp

class Tools {

    companion object {

    }

}

// MainActivity에서 보여줄 프레그먼트들의 이름
enum class MainFragmentName(var str: String) {
    LOGIN_FRAGMENT("LoginFragment"),
    JOIN_FRAGMENT("JoinFragment"),
}