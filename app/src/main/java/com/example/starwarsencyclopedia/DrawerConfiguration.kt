package com.example.starwarsencyclopedia

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment


class DrawerConfiguration : MainActivity() {

    private lateinit var mScreenTitles: Array<String>
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawerList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDrawer()

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

    }

    private fun setDrawer() {
        init()
        setDrawerAdapter()

        setClickListenerInDrawerList()

//        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configureDrawerToggle()

        mDrawerLayout.addDrawerListener(mDrawerToggle)

        mDrawerToggle.syncState()
    }

    private fun init() {
        mScreenTitles = resources.getStringArray(R.array.drawer_strings)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mDrawerList = findViewById(R.id.left_drawer)

    }

    private fun setDrawerAdapter() {
        mDrawerList.adapter = ArrayAdapter(
            this,
            R.layout.drawer_list_item, mScreenTitles
        )

    }

    private fun setClickListenerInDrawerList() {
        mDrawerList.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            when(position) {
                1 -> sendRequest("films", 1)
            }
        }
    }

    private fun configureDrawerToggle() {
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
//            override fun onDrawerClosed(view: View) {
//                supportActionBar!!.title = title
//                invalidateOptionsMenu()
//            }
//
//            override fun onDrawerOpened(drawerView: View) {
//                supportActionBar!!.title = "List"
//                invalidateOptionsMenu()
//            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> openOrCloseDrawer()
        }
        return true
    }

    private fun openOrCloseDrawer() {
        if (!mDrawerLayout.isOpen) {
            mDrawerLayout.openDrawer(GravityCompat.START)
        } else mDrawerLayout.closeDrawers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showToast() {
        Toast.makeText(applicationContext, "SOME", Toast.LENGTH_SHORT).show()
    }

}