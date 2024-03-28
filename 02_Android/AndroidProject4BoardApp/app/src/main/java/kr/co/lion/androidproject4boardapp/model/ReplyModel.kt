package kr.co.lion.androidproject4boardapp.model

data class ReplyModel(var replyIdx: Int, var replyWriterIdx: Int, var replyWriteDate: String, var replyContentIdx: Int,
    var replyText: String, var replyState: Int) {

    constructor():this(0,0,"",0,"",0)
}