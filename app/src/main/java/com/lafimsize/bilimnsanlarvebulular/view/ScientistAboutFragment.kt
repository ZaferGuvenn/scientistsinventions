package com.lafimsize.bilimnsanlarvebulular.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentScientistAboutBinding
import com.lafimsize.bilimnsanlarvebulular.util.SelectedScientist
import com.lafimsize.bilimnsanlarvebulular.viewmodel.ScientistAboutViewModel

class ScientistAboutFragment: Fragment() {

    private lateinit var binding:FragmentScientistAboutBinding
    private lateinit var viewModel:ScientistAboutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil
            .inflate(inflater, R.layout.fragment_scientist_about,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val scientist=SelectedScientist.selectedScientist

        binding.scientist=scientist


    }





}