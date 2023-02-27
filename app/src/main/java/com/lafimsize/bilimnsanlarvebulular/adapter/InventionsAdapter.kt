package com.lafimsize.bilimnsanlarvebulular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.InventionsRowBinding
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.util.downloadWithGlide
import com.lafimsize.bilimnsanlarvebulular.view.InventionsFragmentDirections

class InventionsAdapter(val inventionsList:ArrayList<Inventions>,val inventionScientistsName:String): RecyclerView.Adapter<InventionsAdapter.InventionsViewHolder>() {

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
        holder.binding.InventionsImage.downloadWithGlide(inventionsList.get(position).inventionImage)

        holder.binding.inventionsRowConstraint.setOnClickListener {
            val selectedInventions=inventionsList[position]

            val action=InventionsFragmentDirections.actionInventionsFragmentToDescriptionFragment(selectedInventions,inventionScientistsName)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return inventionsList.size
    }


    fun updateInventions(newInventionsList:List<Inventions>){
        inventionsList.clear()
        inventionsList.addAll(newInventionsList)
        notifyDataSetChanged()
    }



}