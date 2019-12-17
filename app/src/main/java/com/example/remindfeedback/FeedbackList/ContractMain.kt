package com.example.remindfeedback.FeedbackList

import android.content.Context
import java.util.*

interface ContractMain {
    interface View{
        fun refresh()
        fun modifyFeedbackActivity(id:Int, date: String?, title: String)
    }

    interface Presenter {
        var view: View
        var context:Context

        fun loadItems(list: ArrayList<ModelFeedback>, adapterMainFeedback: AdapterMainFeedback)

        fun addItems(date: String?, title:String, adapterMainFeedback: AdapterMainFeedback)

        fun removeItems(id:Int, context: Context)

        fun updateItems(id:Int, date: String?, title: String)

        fun modifyFeedbackActivity(id:Int, date: String?, title: String)
    }

}