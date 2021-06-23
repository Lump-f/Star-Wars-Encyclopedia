package com.example.starwarsencyclopedia

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsencyclopedia.Network.Status
import com.example.starwarsencyclopedia.Network.ViewModel.ActivityViewModel
import com.example.starwarsencyclopedia.RecyclerView.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var mDrawerToggle: ActionBarDrawerToggle

    private lateinit var activityViewModel: ActivityViewModel

    private var recyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        setDrawer()
        configureRecyclerView()
        test()

        drawer_layout.bringToFront() // Без этой строки drawer когда открывается становиться не кликабельным
    }

    private fun configureRecyclerView() {
        LinearLayoutManager(context).orientation = LinearLayoutManager.VERTICAL

        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
    }

    private fun sendRequest(query: String, page: Int) {
        activityViewModel.universalGet(query, page)
    }

    private fun test() {
        activityViewModel.items.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.results?.let { array ->
                        recyclerViewAdapter.dataSource = array.toList()
                    }
                    recyclerViewAdapter.notifyDataSetChanged()
                }
//                Status.LOADING ->
//                Status.ERROR ->
            }
        })
    }

    private fun setDrawer() {
        setDrawerAdapter()

        setClickListenerInDrawerList()

        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
//        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)


        configureDrawerToggle()

        drawer_layout.addDrawerListener(mDrawerToggle)

        mDrawerToggle.syncState()
    }

    private fun setDrawerAdapter() {
        left_drawer.adapter = ArrayAdapter(
            requireContext(),
            R.layout.drawer_list_item, resources.getStringArray(R.array.drawer_strings)
        )

    }

    private fun setClickListenerInDrawerList() {
        left_drawer.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> sendRequest("films", 1)
//                0 -> (activity as MainActivity?)?.sendRequest("films", 1)

            }
        }
    }

    private fun configureDrawerToggle() {
        mDrawerToggle = object : ActionBarDrawerToggle(
            requireActivity(),
            drawer_layout,
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
        if (!drawer_layout.isOpen) {
            drawer_layout.openDrawer(GravityCompat.START)
        } else drawer_layout.closeDrawers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drawer_menu, menu)
    }

    fun showToast() {
        Toast.makeText(
            activity,
            "Test",
            Toast.LENGTH_SHORT
        ).show()
    }
}