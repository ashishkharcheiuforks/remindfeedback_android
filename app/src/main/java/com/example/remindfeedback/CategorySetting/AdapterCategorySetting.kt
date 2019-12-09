package com.example.remindfeedback.CategorySetting

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remindfeedback.FeedbackList.FeedbackDetail.AdapterFeedbackDetail
import com.example.remindfeedback.FeedbackList.FeedbackDetail.ModelFeedbackDetail
import com.example.remindfeedback.FeedbackList.FeedbackDetail.Post.PostActivity
import com.example.remindfeedback.R
import java.util.ArrayList

class AdapterCategorySetting(val context: Context, val arrayList: ArrayList<ModelCategorySetting>) :   RecyclerView.Adapter<AdapterCategorySetting.Holder>()  {


    fun addItem(item: ModelCategorySetting) {//아이템 추가
        if (arrayList != null) {//널체크 해줘야함
            arrayList.add(item)

        }
    }

    fun removeAt(position: Int) {
        arrayList.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category_setting, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(arrayList[position], context)

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_Color = itemView.findViewById<TextView>(R.id.category_Color)
        val category_Title = itemView.findViewById<TextView>(R.id.category_Title)
        val category_More = itemView.findViewById<ImageView>(R.id.category_More)


        fun bind (categorySetting: ModelCategorySetting, context: Context) {
            //헥사코드인식해서 배경으로 만듦
            category_Color.setBackgroundColor(Color.parseColor(categorySetting.color))
            Log.e("category_Color", categorySetting.color)
            category_Title.text = categorySetting.title
            category_More.setOnClickListener {

            }

        }
    }


}