package com.nadillla.tabunganapp.Home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.nadillla.tabunganapp.Local.Tabungan
import com.nadillla.tabunganapp.R
import kotlinx.android.synthetic.main.item_tabungan.view.*

class TabunganAdapter(

    private val data: List<Tabungan>?,
    private val itemClick: OnClickListener)
    :RecyclerView.Adapter<TabunganAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabunganAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tabungan,parent,false)

        return ViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int = data?.size?:0

    override fun onBindViewHolder(holder: TabunganAdapter.ViewHolder, position: Int) {

        val item : Tabungan? = data?.get(position)
        holder.bind(item)
    }

    class ViewHolder(val view: View,val itemClick: OnClickListener): RecyclerView.ViewHolder(view){

        fun bind(item: Tabungan?){
            view.tvTgl.text = item?.tgl
            view.tvJml.text=item?.jumlah.toString()
            view.tvKeterangan.text=item?.keterangan

            view.btnEdit.setOnClickListener {
                itemClick.update(item)
            }
            view.btnDelete.setOnClickListener {
                itemClick.delete(item)
            }

        }
    }

    interface OnClickListener{
        fun update(item: Tabungan?)
        fun delete(item: Tabungan?)
    }


}




