package com.example.remindfeedback.MyPage

import android.content.Context
import android.widget.FrameLayout
import com.example.remindfeedback.FriendsList.ModelFriendsList
import java.util.ArrayList

interface ContractMyPage {
    interface View{
        fun setInfo(email:String, nickname:String, portrait:String?, introduction:String?)
        fun appReset()
    }

    interface Presenter {

        var view: View
        var mContext:Context
        var keyString:String
        fun getInfo()
        fun showDialog(showText:String, context: Context, params: FrameLayout.LayoutParams)
        fun patchPortrait(fileUri:String?)
        fun logout()
        fun inputPassword(type:String, mEmail:String)
        fun deleteAccount()
        fun checkPassword(password:String, type:String, mEmail:String)
        fun findPassword(email: String)
    }

}