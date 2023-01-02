package com.example.theboredapplication.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.theboredapplication.R
import com.example.theboredapplication.domain.RetrofitApi
import com.example.theboredapplication.model.Quote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteFragment: Fragment(R.layout.quote_fragment) {
    lateinit var mSharedPref: SharedPreferences
    lateinit var quote_label: TextView
    var mContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.quote_fragment, parent, false)
        if (parent != null) {
            this.mContext = parent.getContext()
        };
        mSharedPref = requireActivity().getSharedPreferences("sharedP", Context.MODE_PRIVATE)

        quote_label = view.findViewById(R.id.quote_label)
        var random_quote = Quote()

        val api = RetrofitApi.create().GetRandomQuote()
        api.enqueue(object : Callback<Quote>{
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                println(response.body()!!.activity)
                    quote_label.text = response.body()!!.activity.toString().trim()
                mSharedPref.edit().apply {
                    putString("tmp",response.body()!!.activity.toString().trim())
                }.apply()
            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                println("network failure")
            }

        }
        )
        return view
    }
}
