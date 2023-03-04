package com.lafimsize.bilimnsanlarvebulular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.ContributionsBinding

class ContributionsAdapter: RecyclerView.Adapter<ContributionsAdapter.ContributionsHolder>() {

    private var vPSImages= arrayListOf<Int>(R.mipmap.alan_turing,R.mipmap.ada_lovelace,R.mipmap.albert_einstein,R.mipmap.marie_curie,R.mipmap.nikola_tesla)
    private var vPAImages= arrayListOf<Int>(R.mipmap.orhanpmk,R.mipmap.yasark,R.mipmap.oguzatay)
    private var vPHImages= arrayListOf(R.mipmap.ilberci,R.mipmap.afetinan,R.mipmap.feroz)
    private var vPPImages= arrayListOf(R.mipmap.cemalsureya,R.mipmap.nazim,R.mipmap.turgutuyar)


    private lateinit var binding:ContributionsBinding
    class ContributionsHolder(val binding:ContributionsBinding):RecyclerView.ViewHolder(binding.root){


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributionsHolder {
        val inflater=LayoutInflater.from(parent.context)
        binding=DataBindingUtil.inflate(inflater, R.layout.contributions,parent,false)
        return ContributionsHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributionsHolder, position: Int) {
        checkHolders(position)
    }

    private fun checkHolders(position: Int){


        when(position){
            0-> binding.cSlideInclude.slideVP.adapter=VPAdapter(vPSImages)
            1-> binding.cSlideInclude.slideVP.adapter=VPAdapter(vPAImages)
            2-> binding.cSlideInclude.slideVP.adapter=VPAdapter(vPHImages)
            3-> binding.cSlideInclude.slideVP.adapter=VPAdapter(vPPImages)

        }



    }

    override fun getItemCount(): Int {

        return 4
    }
}