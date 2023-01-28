package com.lafimsize.bilimnsanlarvebulular.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.ScientistsAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentScientistsBinding
import com.lafimsize.bilimnsanlarvebulular.viewmodel.ScientistsViewModel


class ScientistsFragment : Fragment() {

    private var adapter=ScientistsAdapter(arrayListOf())
    private lateinit var viewModel:ScientistsViewModel

    private lateinit var binding:FragmentScientistsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_scientists,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(ScientistsViewModel::class.java)
        viewModel.refreshDataRetrofit()

        binding.recyclerViewScientists.adapter=adapter
        binding.recyclerViewScientists.layoutManager=LinearLayoutManager(context)



        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.mutableScientistsList.observe(viewLifecycleOwner){


            it?.let {
                adapter.updateScientists(it)

                binding.recyclerViewScientists.visibility=View.VISIBLE
                binding.scientistsErrorMsg.visibility=View.GONE
                binding.scientistsProgressing.visibility=View.GONE
            }

        }

        viewModel.loadingProgress.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    binding.scientistsErrorMsg.visibility=View.GONE
                    binding.scientistsProgressing.visibility=View.VISIBLE
                }else{
                    binding.scientistsProgressing.visibility=View.GONE
                }
            }
        }

        viewModel.errorDialog.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    binding.recyclerViewScientists.visibility=View.GONE
                    binding.scientistsErrorMsg.visibility=View.VISIBLE
                    binding.scientistsProgressing.visibility=View.GONE
                }else{
                    binding.scientistsErrorMsg.visibility=View.GONE
                }
            }
        }

    }

}