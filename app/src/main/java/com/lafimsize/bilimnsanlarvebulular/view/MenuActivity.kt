package com.lafimsize.bilimnsanlarvebulular.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.ScientistsAdapter
import com.lafimsize.bilimnsanlarvebulular.adapter.VPAdapter
import com.lafimsize.bilimnsanlarvebulular.databinding.ActivityMenuBinding
import kotlinx.coroutines.*

class MenuActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private var timeBack=false

    private lateinit var navController: NavController
    private lateinit var navHostFragment:NavHostFragment

    private lateinit var backPresJob:Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_menu)

        eventTransactions()


        navHostFragment=binding.fragmentContainerView4.getFragment()
        navController=navHostFragment.navController

        binding.bottomNavView.setupWithNavController(navController)
        observeLiveData()

    }


    private fun observeLiveData(){

    }



    private fun eventTransactions() {

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (timeBack){
                    backPresJob.cancel()
                    finish()

                }else{
                    timeBack=true
                    println(timeBack)
                    Toast.makeText(applicationContext,"Çıkmak için bir daha tıklayın!",Toast.LENGTH_SHORT).show()

                    backPresJob=lifecycleScope.launchWhenResumed {

                        delay(3000)
                        timeBack=false
                        println(timeBack)
                    }

                }
            }
        })
    }





}