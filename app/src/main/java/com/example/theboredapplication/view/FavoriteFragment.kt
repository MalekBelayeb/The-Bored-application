package com.example.theboredapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theboredapplication.AppDataBase
import com.example.theboredapplication.R
import com.example.theboredapplication.model.QuoteEntity

lateinit var recylcerFavoriteAdapter: FavoriteAdapter
lateinit var quoteRecyclerView: RecyclerView
lateinit var dataBase: AppDataBase

class FavoriteFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.favorite_fragment, container, false)

        var quoteList : MutableList<QuoteEntity> = ArrayList()
        dataBase = AppDataBase.getDatabase(requireActivity())

        quoteRecyclerView = rootView.findViewById(R.id.recylerview)

        quoteList =  dataBase.quoteDao().getAllSavedQuotes()



        recylcerFavoriteAdapter = FavoriteAdapter(quoteList)
        quoteRecyclerView.adapter = recylcerFavoriteAdapter

        quoteRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,
            false)


        return rootView
    }

}