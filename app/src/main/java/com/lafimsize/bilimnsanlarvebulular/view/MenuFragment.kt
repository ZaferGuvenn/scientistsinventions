package com.lafimsize.bilimnsanlarvebulular.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.ContributionsAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentMenuBinding
import kotlinx.coroutines.Job

class MenuFragment: Fragment() {

    private lateinit var _binding:FragmentMenuBinding


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



    }

    override fun onResume() {
        super.onResume()
        _binding.contributionRV.adapter=ContributionsAdapter()
        _binding.contributionRV.layoutManager=GridLayoutManager(context,2)
        _binding.contributionRV.suppressLayout(true)
    }



}