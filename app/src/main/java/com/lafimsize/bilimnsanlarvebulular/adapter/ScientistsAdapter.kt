package com.lafimsize.bilimnsanlarvebulular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.ScientistsRowBinding
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import com.lafimsize.bilimnsanlarvebulular.util.downloadWithGlide
import com.lafimsize.bilimnsanlarvebulular.view.ScientistsFragment
import com.lafimsize.bilimnsanlarvebulular.view.ScientistsFragmentDirections

class ScientistsAdapter(val scientistsList:ArrayList<Scientists>):RecyclerView.Adapter<ScientistsAdapter.ScientistsViewHolder>(),
    IOnClickListener {

    private lateinit var binding:ScientistsRowBinding

    class ScientistsViewHolder(var binding:ScientistsRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScientistsViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        binding=DataBindingUtil.inflate(inflater, R.layout.scientists_row,parent,false)

        return ScientistsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ScientistsViewHolder, position: Int) {
        holder.binding.scientists=scientistsList[position]
        holder.binding.listener=this

        holder.binding.ScientistsImage.downloadWithGlide(scientistsList.get(position).scientistsImage)
    }

    override fun getItemCount(): Int {
        return scientistsList.size
    }

    fun updateScientists(newScientistsList:ArrayList<Scientists>){
        scientistsList.clear()
        scientistsList.addAll(newScientistsList)
        notifyDataSetChanged()
    }

    override fun onItemClicked(view: View) {
        val uUid=0L
        val action=ScientistsFragmentDirections.actionScientistsFragmentToInventionsFragment(uUid)
        Navigation.findNavController(view).navigate(action)
    }


}