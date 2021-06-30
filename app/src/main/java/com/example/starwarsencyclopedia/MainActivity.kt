package com.example.starwarsencyclopedia

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.starwa.ListFragment
import com.example.starwarsencyclopedia.Network.Model.Response
import com.example.starwarsencyclopedia.Network.Status
import com.example.starwarsencyclopedia.Network.ViewModel.ActivityViewModel

open class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navController = findNavController(R.id.nav_host_fragment)

        setListFragment()

    }


    private fun setListFragment() {
        val fragment = ListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment).commit()
    }

    fun setInfoFragment(item: Response.Item, type: String) {
        val fragment = InfoFragment.newInstance(item, type)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.container, fragment).commit()

    }



//    Toast.makeText(applicationContext, "SSSS", Toast.LENGTH_SHORT).show()

}