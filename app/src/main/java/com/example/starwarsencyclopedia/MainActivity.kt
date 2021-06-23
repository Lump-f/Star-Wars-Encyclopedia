package com.example.starwarsencyclopedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.starwarsencyclopedia.Network.Status
import com.example.starwarsencyclopedia.Network.ViewModel.ActivityViewModel

open class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navController = findNavController(R.id.nav_host_fragment)

        setFragment()

    }

    private fun setFragment() {
        val fragment = ListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment).commit()
    }



//    Toast.makeText(applicationContext, "SSSS", Toast.LENGTH_SHORT).show()

}