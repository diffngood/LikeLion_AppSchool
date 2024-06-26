package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RowReadContentReplyViewModel: ViewModel() {
    val textViewRowReplyText = MutableLiveData<String>()
    val textViewRowReplyNickName = MutableLiveData<String>()
    val textViewRowReplyDate = MutableLiveData<String>()
}