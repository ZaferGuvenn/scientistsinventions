package com.lafimsize.bilimnsanlarvebulular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.InventionsRowBinding
import com.lafimsize.bilimnsanlarvebulular.model.Inventions

class InventionsAdapter(val inventionsList:ArrayList<Inventions>): RecyclerView.Adapter<InventionsAdapter.InventionsViewHolder>(),IOnClickListener {

    private lateinit var binding:InventionsRowBinding

    class InventionsViewHolder(var binding: InventionsRowBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventionsViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        binding=DataBindingUtil.inflate<InventionsRowBinding>(inflater, R.layout.inventions_row,parent,false)
        return InventionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventionsViewHolder, position: Int) {
        holder.binding.inventions=inventionsList[position]
        holder.binding.listener=this
    }

    override fun getItemCount(): Int {
        return inventionsList.size
    }


    fun updateInventions(newInventionsList:List<Inventions>){
        inventionsList.clear()
        inventionsList.addAll(newInventionsList)
        notifyDataSetChanged()
    }

    override fun onItemClicked(view: View) {

    }

}