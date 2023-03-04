package com.lafimsize.bilimnsanlarvebulular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.SlidecpicturesBinding
import kotlinx.coroutines.*

class VPAdapter(val cImages:ArrayList<Int>):RecyclerView.Adapter<VPAdapter.VPViewHolder>() {

    private lateinit var binding: SlidecpicturesBinding
    private lateinit var annimm1: Animation
    private lateinit var annimm2: Animation
    private lateinit var annimm3: Animation

    private lateinit var annimationsList: List<Animation>
    class VPViewHolder(val binding: SlidecpicturesBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VPViewHolder {
        val inflater=LayoutInflater.from(parent.context)

        binding=DataBindingUtil.inflate(inflater, R.layout.slidecpictures,parent,false)

        annimm1=AnimationUtils.loadAnimation(parent.context,R.anim.fall_down)
        annimm2=AnimationUtils.loadAnimation(parent.context,R.anim.rotate_in)
        annimm3=AnimationUtils.loadAnimation(parent.context,R.anim.slide_in)

        annimationsList= listOf(annimm1,annimm2,annimm3)

        return VPViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VPViewHolder, position: Int) {

        setSlide(position)
    }

    override fun getItemCount(): Int {
        return cImages.size
    }


    private fun setSlide(position: Int){
        var p=position

        val jobS=CoroutineScope(Dispatchers.Default).launch {
            while (true){
                if (p==cImages.size){
                    p=0
                }
                binding.slideIV.setImageResource(cImages.get(p))
                val randomAnnimation=annimationsList.random()
                binding.slideIV.startAnimation(randomAnnimation)
                val randomTime=(2000L..4000L).random()
                delay(randomTime)

                p=p+1
            }
        }


    }
}