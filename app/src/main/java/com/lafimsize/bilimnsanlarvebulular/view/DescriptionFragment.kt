package com.lafimsize.bilimnsanlarvebulular.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentDescriptionBinding
import com.lafimsize.bilimnsanlarvebulular.util.downloadWithGlide
import com.lafimsize.bilimnsanlarvebulular.viewmodel.DescriptionsViewModel

class DescriptionFragment : Fragment() {

    private lateinit var binding:FragmentDescriptionBinding

    private lateinit var viewModel:DescriptionsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_description,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val selectedInvention=DescriptionFragmentArgs.fromBundle(it).inventions
            binding.inventionn=selectedInvention

            val scientistsName=DescriptionFragmentArgs.fromBundle(it).scientistsName.replace("-"," ")

            (activity as AppCompatActivity).supportActionBar?.setTitle(scientistsName+" buluşları")

        }





    }


}