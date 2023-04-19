package com.lafimsize.bilimnsanlarvebulular.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.ScientistsAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentScientistsBinding
import com.lafimsize.bilimnsanlarvebulular.model.Scientists
import com.lafimsize.bilimnsanlarvebulular.viewmodel.ScientistsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ScientistsFragment : Fragment() {

    private var adapter=ScientistsAdapter(arrayListOf())
    private lateinit var viewModel:ScientistsViewModel

    private lateinit var binding:FragmentScientistsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(ScientistsViewModel::class.java)
        viewModel.getAllData()


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



        bindingTransactions()
        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.mutableScientistsList.observe(viewLifecycleOwner){


            it?.let {
                adapter.updateScientists(it)
                binding.recyclerViewScientists.layoutAnimation=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation)

                binding.recyclerViewScientists.visibility=View.VISIBLE
                binding.scientistsErrorMsg.visibility=View.GONE
                binding.scientistsProgressing.visibility=View.GONE

            }

        }

        viewModel.loadingProgress.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    binding.scientistsErrorMsg.visibility=View.GONE
                    binding.offlineBtn.visibility=View.GONE
                    binding.renewScientists.visibility=View.GONE
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
                    binding.renewScientists.visibility=View.VISIBLE
                    binding.offlineBtn.visibility=View.VISIBLE
                }else{
                    binding.renewScientists.visibility=View.GONE
                    binding.offlineBtn.visibility=View.GONE
                    binding.scientistsErrorMsg.visibility=View.GONE
                }
            }
        }

        viewModel.alertDialog.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    showAlertDialogMsg()
                }
            }
        }

    }

    private fun bindingTransactions(){

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerViewScientists.adapter=adapter
        binding.recyclerViewScientists.layoutManager=LinearLayoutManager(context)

        binding.swipeRefresh.setOnRefreshListener {

            viewModel.refreshDataRetrofit()
            binding.swipeRefresh.isRefreshing=false
        }

        binding.renewScientists.setOnClickListener {
            binding.renewScientists.visibility=View.GONE
            binding.offlineBtn.visibility=View.GONE
            viewModel.refreshDataRetrofit()

        }

        binding.offlineBtn.setOnClickListener {
            binding.renewScientists.visibility=View.GONE
            binding.offlineBtn.visibility=View.GONE
            viewModel.getDataFromRoom()
        }


        binding.edSearchScientist.addTextChangedListener {searchText->

            var job: Job?=null

            job?.cancel()

            job=lifecycleScope.launch {
                delay(100)

                val listScientistFiltered=viewModel.mutableScientistsList.value?.let { scientistsArrayList ->

                    scientistsArrayList.filter {
                        it.scientistsName.contains(searchText.toString()?:"",ignoreCase = true)
                    }

                }?: listOf()

                val listScientistFilteredArrayList= arrayListOf<Scientists>()
                listScientistFilteredArrayList.addAll(listScientistFiltered)

                adapter.updateScientists(listScientistFilteredArrayList)
            }

        }
    }


    private fun showAlertDialogMsg(){
        val alertDialog= AlertDialog.Builder(context)

        alertDialog.setTitle("Çevrimdışı Mod Başlatılamadı!")
        alertDialog.setMessage("Önceden yüklenmiş hiçbir veri bulunamadı. Lütfen internet bağlantınızı aktifleştirip tekrar deneyin!")
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("Yeniden Dene"){ _ , _ ->
            viewModel.getAllData()
        }
        alertDialog.show()
    }

}