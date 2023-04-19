package com.lafimsize.bilimnsanlarvebulular.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.InventionsAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentInventionsBinding
import com.lafimsize.bilimnsanlarvebulular.model.Inventions
import com.lafimsize.bilimnsanlarvebulular.viewmodel.InventionsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class InventionsFragment : Fragment() {


    private lateinit var viewModel:InventionsViewModel
    private lateinit var binding:FragmentInventionsBinding
    private lateinit var adapter:InventionsAdapter

    private var uuid=0L
    private var scientistsName=""

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


        argumentTransactions()
        bindingTransactions()
        observeAllData()

    }

    private fun observeAllData(){

        viewModel.mutableInventionsList.observe(viewLifecycleOwner){

            it?.let {
                adapter.updateInventions(it)
                binding.recyclerView.layoutAnimation=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation2)

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
                    binding.renewInventions.visibility=View.VISIBLE
                    binding.inventionsProgressLoading.visibility=View.GONE
                }else{
                    binding.inventionsErrorMsg.visibility=View.GONE
                    binding.renewInventions.visibility=View.GONE

                }
            }
        }

        viewModel.offlineBtnIsVisible.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    binding.offlineBtnInventions.visibility=View.VISIBLE
                }else{
                    binding.offlineBtnInventions.visibility=View.GONE
                }
            }
        }

        /*viewModel.alertDialog.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    showAlertDialogMsg()
                }
            }
        }*/



    }

    private fun argumentTransactions(){
        arguments?.let {
            uuid=InventionsFragmentArgs.fromBundle(it).scientistsUuid
            scientistsName=InventionsFragmentArgs.fromBundle(it).scientistsNmae
        }

        val fragmentLabel=scientistsName.replace("-"," ")+" buluşları"
        (activity as AppCompatActivity).supportActionBar?.title = fragmentLabel

        viewModel= ViewModelProvider(this)[InventionsViewModel::class.java]
        viewModel.getAllData(scientistsName)

        adapter= InventionsAdapter(arrayListOf(),scientistsName)
    }

    private fun bindingTransactions(){
        binding.recyclerView.layoutManager=GridLayoutManager(context,2)
        binding.recyclerView.adapter=adapter

        binding.swipeRefreshInventions.setOnRefreshListener {
            binding.swipeRefreshInventions.isRefreshing=false
            binding.offlineBtnInventions.visibility=View.GONE
            viewModel.getInventionsFromRetrofit(scientistsName,true)
        }

        binding.renewInventions.setOnClickListener {

            binding.renewInventions.visibility=View.GONE
            binding.offlineBtnInventions.visibility=View.GONE
            viewModel.getInventionsFromRetrofit(scientistsName,true)
        }

        binding.offlineBtnInventions.setOnClickListener {


            binding.renewInventions.visibility=View.GONE
            binding.offlineBtnInventions.visibility=View.GONE
            viewModel.getInventionsFromRoom(scientistsName)
        }

        binding.etSearchInvention.addTextChangedListener {searchText->

            var job: Job?=null
            job?.cancel()

            job=lifecycleScope.launch {

                val newList=viewModel.mutableInventionsList.value?.filter {
                    it.inventionName.contains(searchText?.toString()?:"",ignoreCase = true)
                }?: listOf()

                var filteredArrayList= ArrayList<Inventions>()
                filteredArrayList.addAll(newList)
                adapter.updateInventions(filteredArrayList)

            }

        }

    }



}