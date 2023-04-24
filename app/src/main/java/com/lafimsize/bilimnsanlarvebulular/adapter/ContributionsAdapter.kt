package com.lafimsize.bilimnsanlarvebulular.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.ContributionsBinding
import com.lafimsize.bilimnsanlarvebulular.view.MainActivity

class ContributionsAdapter: RecyclerView.Adapter<ContributionsAdapter.ContributionsHolder>() {

    private var vPSImages= arrayListOf(R.mipmap.alan_turing,R.mipmap.ada_lovelace,R.mipmap.albert_einstein,R.mipmap.marie_curie,R.mipmap.nikola_tesla)
    private var vPAImages= arrayListOf(R.mipmap.orhanpmk,R.mipmap.yasark,R.mipmap.oguzatay)
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
            0-> {
                binding.cSlideInclude.slideVP.adapter = VPAdapter(vPSImages)
                binding.cSlideInclude.slideVP.setBackgroundColor(Color.parseColor("#75CFE5"))
                binding.cNameTV.text="Bilim İnsanı"
                binding.root.setOnClickListener {
                    goIntent("bilim")
                }

            }
            1-> {
                binding.cSlideInclude.slideVP.adapter = VPAdapter(vPAImages)
                binding.cSlideInclude.slideVP.setBackgroundColor(Color.parseColor("#C9B1BD"))
                binding.cNameTV.text="Yazar"
                binding.root.setOnClickListener {
                    goIntent("yazar")
                }

            }
            2-> {

                binding.cSlideInclude.slideVP.adapter = VPAdapter(vPHImages)
                binding.cSlideInclude.slideVP.setBackgroundColor(Color.parseColor("#A5CCD1"))

                binding.cNameTV.text="Tarihçi"
                binding.root.setOnClickListener {
                    goIntent("tarih")
                }
            }
            3-> {

                binding.cSlideInclude.slideVP.adapter = VPAdapter(vPPImages)
                binding.cSlideInclude.slideVP.setBackgroundColor(Color.parseColor("#7F6DA7"))

                binding.cNameTV.text="Şair"
                binding.root.setOnClickListener {
                    goIntent("sair")
                }
            }

        }



    }

    private fun goIntent(whichIntent:String){
        println(whichIntent)
        val intent= Intent(binding.root.context,MainActivity::class.java)
        intent.putExtra("whichContribution",whichIntent)
        binding.root.context.startActivity(intent)
        myJobIsWork.isWork=false

    }

    override fun getItemCount(): Int {

        return 4
    }


}