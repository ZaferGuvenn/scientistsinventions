package com.lafimsize.bilimnsanlarvebulular.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navigationController: NavController
    private lateinit var navHostFragment:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        navHostFragment=binding.fragmentContainerView.getFragment()
        navigationController=navHostFragment.navController


        NavigationUI.setupActionBarWithNavController(this,navigationController,null)

        //https://raw.githubusercontent.com/ZaferGuvenn/KotlinApp5-Scientists/main/scientists.json?token=GHSAT0AAAAAAB35CLUF2SRVBEAEWPMOBCYMY6S53GA


        binding.bottomNavView2.setupWithNavController(navigationController)


        navigationController.addOnDestinationChangedListener{ _, destination, _ ->

            if (destination.id==R.id.scientistsFragment||destination.id==R.id.descriptionFragment){
                binding.bottomNavView2.visibility=View.GONE
            }else{
                binding.bottomNavView2.visibility=View.VISIBLE
            }

        }

    }


    override fun onSupportNavigateUp(): Boolean {

        //NavigationUI.navigateUp(navigationController,null)


        onBackPressedDispatcher.onBackPressed()

        return false
    }




}