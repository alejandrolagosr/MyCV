package com.lagos.mycv.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.lagos.mycv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_with_image.view.*

class CardWithImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.card_with_image, this, true)
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun setSubtitle(subtitle: String) {
        tv_subtitle.text = subtitle
    }

    fun setImage(url: String) {
        Picasso.get()
            .load(url)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(iv_icon)
    }

    fun setDescription(description: String) {
        tv_description.visibility = View.VISIBLE
        tv_description.text = description
    }
}

