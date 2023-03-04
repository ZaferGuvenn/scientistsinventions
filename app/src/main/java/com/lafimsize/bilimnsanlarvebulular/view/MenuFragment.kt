package com.lafimsize.bilimnsanlarvebulular.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.ContributionsAdapter
import com.lafimsize.bilimnsanlarvebulular.adapter.VPAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentMenuBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MenuFragment: Fragment() {

    private lateinit var _binding:FragmentMenuBinding

    private lateinit var jobS: Job
    private lateinit var jobA: Job
    private lateinit var jobH: Job
    private lateinit var jobP: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=DataBindingUtil.inflate(inflater, R.layout.fragment_menu,container,false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        makeAdapterForContribution()
//        setViews()

//        eventObserve()
//

        _binding.contributionRV.adapter=ContributionsAdapter()
        _binding.contributionRV.layoutManager=GridLayoutManager(context,2)
        _binding.contributionRV.suppressLayout(true)





    }

    private fun eventObserve(){

//        _binding.cPoet.root.setOnClickListener {
//            goContent("sair")
//        }
//        _binding.cAuthor.root.setOnClickListener {
//            goContent("yazar")
//        }
//        _binding.cScientist.root.setOnClickListener {
//            goContent("bilim")
//        }
//        _binding.cHistorian.root.setOnClickListener {
//            goContent("tarihci")
//        }

    }


    private fun goContent(wContent:String){
        jobA.cancel()
        jobH.cancel()
        jobP.cancel()
        jobS.cancel()

        val intent=Intent(this.activity,MainActivity::class.java)
        intent.putExtra("wContent",wContent)
        startActivity(intent)
    }


//
//
//    private fun makeAdapterForContribution() {
//
//        var vPSImages= arrayListOf<Int>()
//        var vPAImages= arrayListOf<Int>()
//        var vPHImages= arrayListOf<Int>()
//        var vPPImages= arrayListOf<Int>()
//
//        vPSImages= arrayListOf(R.mipmap.alan_turing,R.mipmap.ada_lovelace,R.mipmap.albert_einstein,R.mipmap.marie_curie,R.mipmap.nikola_tesla)
//        vPAImages= arrayListOf(R.mipmap.yasark,R.mipmap.oguzatay,R.mipmap.orhanpmk)
//        vPHImages= arrayListOf(R.mipmap.ilberci,R.mipmap.afetinan,R.mipmap.feroz)
//        vPPImages= arrayListOf(R.mipmap.cemalsureya,R.mipmap.nazim,R.mipmap.turgutuyar)
//
//
//        val vPS=_binding.cScientist.cSlideInclude.slideVP
//        vPS.adapter= VPAdapter(vPSImages)
//        val annimm= AnimationUtils.loadAnimation(context,R.anim.rotate_in)
//
//        val vPA=_binding.cAuthor.cSlideInclude.slideVP
//        vPA.adapter=VPAdapter(vPAImages)
//        val annimm2= AnimationUtils.loadAnimation(context,R.anim.fall_down)
//
//        val vPH=_binding.cHistorian.cSlideInclude.slideVP
//        vPH.adapter=VPAdapter(vPHImages)
//        val annimm3= AnimationUtils.loadAnimation(context,R.anim.scale_up)
//
//        val vPP=_binding.cPoet.cSlideInclude.slideVP
//        vPP.adapter=VPAdapter(vPPImages)
//        val annimm4= AnimationUtils.loadAnimation(context,R.anim.animation1)
//
//
//        jobS=lifecycleScope.launchWhenResumed{
//            while (true){
//                delay(2500)
//                vPS.currentItem=(vPS.currentItem+1)%(vPSImages.size)
//                vPS.startAnimation(annimm)
//
//            }
//
//        }
//
//        jobA=lifecycleScope.launchWhenResumed{
//            while (true){
//                delay(4000)
//                vPA.currentItem=(vPA.currentItem+1)%(vPAImages.size)
//                vPA.startAnimation(annimm2)
//
//            }
//
//        }
//        jobH=lifecycleScope.launchWhenResumed{
//            while (true){
//                delay(4500)
//                vPH.currentItem=(vPH.currentItem+1)%(vPHImages.size)
//                vPH.startAnimation(annimm3)
//
//            }
//
//        }
//        jobP=lifecycleScope.launchWhenResumed{
//            while (true){
//                delay(5000)
//                vPP.currentItem=(vPP.currentItem+1)%(vPPImages.size)
//                vPP.startAnimation(annimm4)
//
//            }
//
//        }
//
//    }
//
//
//    private fun setViews(){
//
//        println("setbiewss")
//
//        _binding.cAuthor.backColorCont.setBackgroundColor(Color.parseColor("#E91E63"))
//        _binding.cAuthor.cNameTV.text="Yazarlar"
//
//        _binding.cHistorian.backColorCont.setBackgroundColor(Color.parseColor("#FFEB3B"))
//        _binding.cHistorian.cNameTV.text="Tarihçiler"
//
//
//        _binding.cPoet.backColorCont.setBackgroundColor(Color.parseColor("#9C27B0"))
//        _binding.cPoet.cNameTV.text="Şairler"
//
//
//        _binding.cScientist.backColorCont.setBackgroundColor(Color.parseColor("#03A9F4"))
//        _binding.cScientist.cNameTV.text="Bilim İnsanları"
//
//
//        makeAdapterForContribution()
//
//
//    }


}