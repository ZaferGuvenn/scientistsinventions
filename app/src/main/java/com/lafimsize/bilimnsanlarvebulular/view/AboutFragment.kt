package com.lafimsize.bilimnsanlarvebulular.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentAboutBinding
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentMenuBinding

class AboutFragment: Fragment() {

    private lateinit var binding:FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_about,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onResume() {
        super.onResume()

        val animationForTVs=AnimationUtils.loadAnimation(context,R.anim.fall_down)
        binding.aboutTextTV.startAnimation(animationForTVs)
        binding.aboutTextTV2.startAnimation(animationForTVs)

    }


}