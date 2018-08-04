package com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_pod_empty.view.*

class EmptyViewPod : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setEmptyData(imageUrl: String, emptyMessage: String) {
        Glide.with(ivEmpty.context)
                .load(imageUrl)
                .into(ivEmpty)
        tvEmpty.text = emptyMessage
    }

    fun setEmptyData(imageResource: Int, emptyMessage: String) {
        ivEmpty.setImageResource(imageResource)
        tvEmpty.text = emptyMessage
    }
}