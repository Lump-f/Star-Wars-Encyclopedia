package com.example.starwarsencyclopedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.starwarsencyclopedia.Network.Status
import com.example.starwarsencyclopedia.Network.ViewModel.ActivityViewModel

open class MainActivity : AppCompatActivity() {

    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navController = findNavController(R.id.nav_host_fragment)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)

        test()
    }

    protected fun sendRequest(query: String, page: Int) {
        activityViewModel.sendRequest(query, page)
    }

    private fun test() {
        activityViewModel.items.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    var res = it.data

                }
//                Status.LOADING ->
//                Status.ERROR ->
            }
        })
    }


//    Toast.makeText(applicationContext, "SSSS", Toast.LENGTH_SHORT).show()

}