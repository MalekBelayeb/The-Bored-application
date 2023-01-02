package com.example.theboredapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.example.theboredapplication.model.QuoteEntity
import com.example.theboredapplication.view.FavoriteFragment
import com.example.theboredapplication.view.QuoteFragment
import kotlin.math.abs

class MainActivity : AppCompatActivity() , GestureDetector.OnGestureListener  {

    lateinit var left_button: Button
    lateinit var mid_button: Button
    lateinit var right_button: Button

    lateinit var mSharedPref: SharedPreferences

    lateinit var gestureDetector: GestureDetector
    private val swipeThreshold = 100
    private val swipeVelocityThreshold = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.hide()
        supportActionBar?.hide();

        gestureDetector = GestureDetector(this)

        mSharedPref = getSharedPreferences("sharedP", Context.MODE_PRIVATE)

        navigateToRandomQuotePage()

        left_button = findViewById<Button>(R.id.left_button)
        mid_button = findViewById<Button>(R.id.mid_button)
        right_button = findViewById<Button>(R.id.right_button)



        left_button.setOnClickListener{
            navigateToRandomQuotePage()
            println("from shared pref"+mSharedPref.getString("tmp", "").toString())
        }
        mid_button.setOnClickListener{
            displaySavedQuotes()
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

    private fun displaySavedQuotes(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,FavoriteFragment()).commit()

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        }
        else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }
    override fun onShowPress(p0: MotionEvent?) {
        return
    }
    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }
    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }
    override fun onLongPress(p0: MotionEvent?) {
        return
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        try {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {
                    if (diffX > 0) {
                        navigateToRandomQuotePage()
                    }
                    else {
                        addQuoteToFavorite(mSharedPref.getString("tmp", "").toString())
                        navigateToRandomQuotePage()
                    }
                }
            }
        }
        catch (exception: Exception) {
            exception.printStackTrace()
        }
        return true
    }
}