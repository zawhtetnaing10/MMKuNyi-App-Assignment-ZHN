package com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<W>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    protected var mData: W? = null
    open fun bindData(data: W) {
        mData = data
    }

}