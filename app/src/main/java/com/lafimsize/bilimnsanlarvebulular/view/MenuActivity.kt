package com.lafimsize.bilimnsanlarvebulular.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.lafimsize.bilimnsanlarvebulular.R
import com.lafimsize.bilimnsanlarvebulular.databinding.ActivityMenuBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MenuActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private var timeBack=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_menu)

        eventTransactions()



    }

    private fun eventTransactions() {
        binding.button.setOnClickListener {
            val intent= Intent(this@MenuActivity,MainActivity::class.java)
            startActivity(intent)

        }


        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                var myJob: Job?=null

                if (timeBack){
                    myJob?.cancel()
                    finish()

                }else{
                    timeBack=true
                    println(timeBack)
                    Toast.makeText(applicationContext,"Çıkmak için bir daha tıklayın!",Toast.LENGTH_SHORT).show()

                    myJob=lifecycleScope.launch {

                        delay(3000)
                        timeBack=false
                        println(timeBack)
                    }

                }
            }
        })
    }


}