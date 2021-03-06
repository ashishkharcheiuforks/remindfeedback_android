package com.example.remindfeedback.MyPage.ImagePick

import android.content.Context
import android.graphics.Bitmap
import android.widget.FrameLayout
import java.io.File

interface ContractImagePick {
    interface View{
        fun imageBrowse()
        fun cameraBrowse()

    }

    interface Presenter {
        var view: View
        var mContext: Context
        fun createImageFile(): File
        fun rotateImage(source: Bitmap, angle: Float): Bitmap
    }

}