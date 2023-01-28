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
import com.lafimsize.bilimnsanlarvebulular.adapter.InventionsAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentInventionsBinding
import com.lafimsize.bilimnsanlarvebulular.viewmodel.InventionsViewModel


class InventionsFragment : Fragment() {


    private lateinit var viewModel:InventionsViewModel
    private lateinit var binding:FragmentInventionsBinding
    private var adapter=InventionsAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_inventions,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(InventionsViewModel::class.java)
        viewModel.getInventionsFromRetrofit()

        binding.recyclerView.layoutManager=LinearLayoutManager(context)
        binding.recyclerView.adapter=adapter


        observeAllDatas()

    }

    fun observeAllDatas(){

        viewModel.mutableInventionsList.observe(viewLifecycleOwner){

            it?.let {
                adapter.updateInventions(it)

                binding.recyclerView.visibility=View.VISIBLE
                binding.inventionsErrorMsg.visibility=View.GONE
                binding.inventionsProgressLoading.visibility=View.GONE
            }

        }

        viewModel.loadingInventions.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    binding.inventionsErrorMsg.visibility=View.GONE
                    binding.inventionsProgressLoading.visibility=View.VISIBLE

                }else{
                    binding.inventionsProgressLoading.visibility=View.GONE
                }
            }
        }

        viewModel.errorInventionsMsg.observe(viewLifecycleOwner){

            it?.let {
                if (it){
                    binding.recyclerView.visibility=View.GONE
                    binding.inventionsErrorMsg.visibility=View.VISIBLE
                    binding.inventionsProgressLoading.visibility=View.GONE
                }else{
                    binding.inventionsErrorMsg.visibility=View.GONE
                }
            }
        }

    }
}