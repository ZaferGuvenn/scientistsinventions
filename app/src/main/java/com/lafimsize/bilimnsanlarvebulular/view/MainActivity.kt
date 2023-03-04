package com.lafimsize.bilimnsanlarvebulular.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.adapter.myJobIsWork
import com.lafimsize.bilimnsanlarvebulular.databinding.ActivityMainBinding
import com.lafimsize.bilimnsanlarvebulular.databinding.FragmentMenuBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navigationController: NavController
    private lateinit var navHostFragment:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        /*
        * navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navigationController= navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navigationController,null)
        * */

        navHostFragment=binding.fragmentContainerView.getFragment()
        navigationController=navHostFragment.navController


        NavigationUI.setupActionBarWithNavController(this,navigationController,null)


        //https://raw.githubusercontent.com/ZaferGuvenn/KotlinApp5-Scientists/main/scientists.json?token=GHSAT0AAAAAAB35CLUF2SRVBEAEWPMOBCYMY6S53GA



    }



    override fun onSupportNavigateUp(): Boolean {

        //NavigationUI.navigateUp(navigationController,null)



        onBackPressedDispatcher.onBackPressed()

        return false
    }

}