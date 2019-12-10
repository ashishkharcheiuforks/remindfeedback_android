package com.example.remindfeedback.CreateCategory

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.remindfeedback.CategorySetting.CategorySettingActivity
import com.example.remindfeedback.CategorySetting.ContractCategorySetting
import com.example.remindfeedback.CategorySetting.CreateCategory.ColorList.ColorListActivity
import com.example.remindfeedback.CategorySetting.CreateCategory.ContractCreateCategory
import com.example.remindfeedback.CategorySetting.CreateCategory.PresenterCreateCategory
import com.example.remindfeedback.CategorySetting.ModelCategorySetting
import com.example.remindfeedback.CategorySetting.PresenterCategorySetting
import com.example.remindfeedback.FriendsList.PresenterFriendsList
import com.example.remindfeedback.R
import com.example.remindfeedback.Register.RegisterActivity
import kotlinx.android.synthetic.main.activity_create_category.*
import kotlinx.android.synthetic.main.activity_find_friends.*

class CreateCategoryActivity : AppCompatActivity(), ContractCreateCategory.View {

    internal lateinit var presenterCreateCategory: PresenterCreateCategory
    var chooseColor :String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        //액션바 설정
        var ab: ActionBar = this!!.supportActionBar!!
        ab.setTitle("새로운 주제")
        //뒤로가기 버튼 만들어주는부분 -> 메니페스트에 부모액티비티 지정해줘서 누르면 그쪽으로 가게끔함
        ab.setDisplayHomeAsUpEnabled(true)


        presenterCreateCategory = PresenterCreateCategory().apply {
            view = this@CreateCategoryActivity
            context = this@CreateCategoryActivity
        }
        //색상 리스트
        another_Color_Select.setOnClickListener {
            val intent = Intent(this, ColorListActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            100 -> {
                when (resultCode) {
                    Activity.RESULT_OK -> if (data != null) {
                        selected_Color.setBackgroundColor(Color.parseColor(data.getStringExtra("color")))
                        chooseColor = data.getStringExtra("color")
                    }
                    Activity.RESULT_CANCELED -> Toast.makeText(this@CreateCategoryActivity, "취소됨.", Toast.LENGTH_SHORT).show()

                }

            }
        }

    }


    //타이틀바에 어떤 menu를 적용할지 정하는부분
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_category_menu, menu)
        return true
    }

    // 타이틀바 메뉴를 클릭했을시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when(item.itemId){
            R.id.add_Category_Button -> { return add_Category_Button() }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }

    // 주제 추가 완료 버튼 눌렀을 때
    fun add_Category_Button(): Boolean {
        Toast.makeText(this@CreateCategoryActivity, "완료 누름.", Toast.LENGTH_SHORT).show()
        val intent = Intent()

        if(chooseColor.equals("")){
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }else{
            intent.putExtra("title", create_Category_Title.text.toString())
            intent.putExtra("color", chooseColor)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        return true
    }




}
// category_Color1.text = "✔"