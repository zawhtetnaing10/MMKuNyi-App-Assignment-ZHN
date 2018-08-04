package com.zawhtetnaing.mmkunyi_app_assignment_zhn.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders.BaseViewHolder

abstract class BaseRecyclerAdapter<T : BaseViewHolder<W>, W>(context: Context) : RecyclerView.Adapter<T>() {

    protected var mData: MutableList<W> = arrayListOf()

    protected var mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setNewData(newData: List<W>) {
        mData = newData as MutableList<W>
        notifyDataSetChanged()
    }

    fun appendNewData(newData: List<W>) {
        mData.addAll(newData)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): W? {
        return if (position < mData.size - 1) mData[position] else null
    }

    fun getItems(): List<W> {
        return mData
    }

    fun removeData(data: W) {
        mData.remove(data)
        notifyDataSetChanged()
    }

    fun addNewData(data: W) {
        mData.add(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData = arrayListOf()
        notifyDataSetChanged()
    }

}