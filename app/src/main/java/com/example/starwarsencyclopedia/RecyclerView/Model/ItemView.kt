package com.example.starwarsencyclopedia.RecyclerView.Model

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.example.starwarsencyclopedia.R
import kotlinx.android.synthetic.main.item_view.view.*

class ItemView(context: Context) : LinearLayout(context) {

    private var stringContent: TextView? = null

    init {
        inflate(context, R.layout.item_view, this)
        stringContent = itemName
    }
}