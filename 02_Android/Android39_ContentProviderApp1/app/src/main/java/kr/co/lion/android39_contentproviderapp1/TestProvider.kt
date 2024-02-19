package kr.co.lion.android39_contentproviderapp1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class TestProvider : ContentProvider() {


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    // ContentProvider의 authorities 를 반환해준다.
    // ContentProvider 사용하는 쪽에서 다수의 Provider를 사용하고 있다면
    // 이를 구분하기 위한 용도로 사용한다.
    override fun getType(uri: Uri): String? {
        return uri.authority
    }

    // 첫 번째 : authorities 가 담긴 Uri 객체
    // 두 번째 : 저장할 데이터가 담긴 객체
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // 저장한다.
        var dbHelper = DBHelper(context!!)
        dbHelper.writableDatabase.insert("TestTable", null, values)
        dbHelper.close()

        return uri
    }

    // ContentProvider 생성될 때 자동으로 호출된다.
    // 별로 할 작업이 없다
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}