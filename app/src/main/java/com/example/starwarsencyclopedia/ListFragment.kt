package com.example.starwa

import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsencyclopedia.MainActivity
import com.example.starwarsencyclopedia.Network.Model.Response
import com.example.starwarsencyclopedia.Network.Status.*
import com.example.starwarsencyclopedia.Network.ViewModel.ActivityViewModel
import com.example.starwarsencyclopedia.R
import com.example.starwarsencyclopedia.RecyclerView.ItemClickSupport
import com.example.starwarsencyclopedia.RecyclerView.RecyclerViewAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private lateinit var typeOfResult: String

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

        configureActionBar()

        configureRecyclerView()
        setItemClickListenerInRV()

        observeViewModel()

    }

    private fun setItemClickListenerInRV() {
        ItemClickSupport.addTo(list).setOnItemClickListener { recyclerView, position, v ->
            recyclerViewAdapter.results[position]?.let {
                (activity as MainActivity).setInfoFragment(it, typeOfResult)
            }
        }
    }

    private fun configureRecyclerView() {

        recyclerViewAdapter = RecyclerViewAdapter()

        //
        LinearLayoutManager(context).orientation = LinearLayoutManager.VERTICAL

        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
    }

    private fun sendRequest(query: String, page: Int) {
        activityViewModel.universalGet(query, page)
    }

    private fun observeViewModel() {
        activityViewModel.items.observe(this, {
            when (it.status) {
                SUCCESS -> {
                    it.data?.results?.let { array ->
                        recyclerViewAdapter.results = array.toList()
                    }
                    recyclerViewAdapter.notifyDataSetChanged()

                    recyclerViewAdapter.copyOfResults = recyclerViewAdapter.results
                }
//                Status.LOADING -> добавить progressBar
                ERROR -> showToast("Request error")
            }
        })
    }

    private fun configureActionBar() {
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setDrawer() {
        setDrawerAdapter()

        setClickListenerInDrawerList()

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
                0 -> {
                    sendRequest("films", 1)
                    typeOfResult = "films"
                }
                1 -> {
                    sendRequest("people", 1)
                    typeOfResult = "people"
                }
                2 -> {
                    sendRequest("planets", 1)
                    typeOfResult = "planets"
                }
                3 -> {
                    sendRequest("species", 1)
                    typeOfResult = "species"
                }
                4 -> {
                    sendRequest("starships", 1)
                    typeOfResult = "starships"
                }
                5 -> {
                    sendRequest("vehicles", 1)
                    typeOfResult = "vehicles"
                }
//                6 -> loadFavorites()
            }
        }
    }

    private fun loadFavorites() {
        val sharedPref = requireActivity().getSharedPreferences("shared_pref", Context.MODE_PRIVATE)


        val type = object : TypeToken<ArrayList<Response.Item>>() {}.type

        val list = GsonBuilder().create().fromJson(
            sharedPref.getString("list", null),
            ArrayList<Response.Item>()::class.java
        )


        recyclerViewAdapter.results = list
        recyclerViewAdapter.notifyDataSetChanged()
        recyclerViewAdapter.copyOfResults = recyclerViewAdapter.results
    }


    private fun configureDrawerToggle() {
        mDrawerToggle = object : ActionBarDrawerToggle(
            requireActivity(),
            drawer_layout,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerClosed(view: View) {
                list.bringToFront()
            }
        }

    }

    private fun openOrCloseDrawer() {
        if (!drawer_layout.isOpen) {
            drawer_layout.bringToFront()
            drawer_layout.openDrawer(GravityCompat.START)
        } else {
            drawer_layout.closeDrawers()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> openOrCloseDrawer()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list, menu)

        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(textWatcher)

    }

    private val textWatcher = object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String?): Boolean {
            recyclerViewAdapter.updateList(newText)
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

    }

    private fun showToast(s: CharSequence?) {
        Toast.makeText(
            activity,
            s,
            Toast.LENGTH_SHORT
        ).show()
    }
}