package com.example.theboredapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.theboredapplication.model.QuoteEntity
import com.example.theboredapplication.view.QuoteFragment

class MainActivity : AppCompatActivity() {

    lateinit var left_button: Button
    lateinit var mid_button: Button
    lateinit var right_button: Button

    lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.hide()
        supportActionBar?.hide();

        mSharedPref = getSharedPreferences("sharedP", Context.MODE_PRIVATE)

        navigateToRandomQuotePage()

        left_button = findViewById<Button>(R.id.left_button)
        mid_button = findViewById<Button>(R.id.mid_button)
        right_button = findViewById<Button>(R.id.right_button)

        mid_button.setOnClickListener{

        }

        left_button.setOnClickListener{
            navigateToRandomQuotePage()
            println("from shared pref"+mSharedPref.getString("tmp", "").toString())
        }

        right_button.setOnClickListener {
            addQuoteToFavorite(mSharedPref.getString("tmp", "").toString())
            navigateToRandomQuotePage()
        }


    }

    private fun navigateToRandomQuotePage(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,QuoteFragment()).commit()
    }

    private fun addQuoteToFavorite(str : String){
        try{
            AppDataBase.getDatabase(this).quoteDao().insert(QuoteEntity(0,str))
        }catch (ex: Exception){
                println(ex)
        }

    }
}