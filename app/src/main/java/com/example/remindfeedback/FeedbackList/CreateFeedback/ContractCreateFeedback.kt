package com.example.remindfeedback.CreateFeedback

import android.content.Context
import org.jetbrains.annotations.Contract
import org.json.JSONObject

interface ContractCreateFeedback {
    interface View {
        fun setData()
        fun setAdviser(portrait:String, introduction:String, nickname:String)
        fun setColor(categotyTitle:String, categoryColor:String)
    }

    interface Presenter {

        var view: View
        var mContext: Context
        fun getOneCategory(category_id:Int)
    }

}