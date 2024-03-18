package kr.co.lion.androidproject4boardapp.model

// 글번호, 글제목, 글타입, 글내용, 글 이미지 파일(null 허용), 작성자 번호, 작성 날짜, 상태
data class ContentModel(var contentIdx: Int, var contentSubject: String, var contentType: Int,
    var contentText: String, var contentImage: String?, var contentWriterIdx: Int, var contentWriteDate: String,
    var contentState: Int) {

    // 매개 변수가 없는 생성자 (FireStore 사용 위해)
    constructor(): this(0, "", 0, "", "", 0, "", 0)
}