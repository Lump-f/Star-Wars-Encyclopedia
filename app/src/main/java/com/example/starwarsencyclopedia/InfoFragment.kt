package com.example.starwarsencyclopedia

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.starwarsencyclopedia.Network.Model.Response
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment() {
    private lateinit var myItem: Response.Item
    private lateinit var type: String
    private lateinit var sharedPref: SharedPreferences

    private var favoriteList: MutableList<Response.Item?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

        setHasOptionsMenu(true)

        arguments?.let {
            myItem =
                GsonBuilder().create().fromJson(it.getString("item"), Response.Item::class.java)
            type = it.getString("typeOfResult")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        setBackButton()

        updateList()
        setInfo()
    }

    override fun onStop() {
        super.onStop()

        saveList()
    }

    private fun updateList() {
        favoriteList = GsonBuilder().create().fromJson(
            sharedPref.getString("list", null),
            ArrayList<Response.Item?>()::class.java
        )
    }

    private fun saveList() {
        with(sharedPref.edit()) {
            putString("list", GsonBuilder().create().toJson(favoriteList))
            apply()
        }
    }


    private fun setBackButton() {
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher_back_button)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val favBtn = ToggleButton(context)
        favBtn.setTextColor(resources.getColor(R.color.transparent))
        favBtn.background = resources.getDrawable(R.color.transparent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            favBtn.foreground = resources.getDrawable(R.drawable.state_of_star)
        }

        favBtn.setOnCheckedChangeListener(stateListener)

        menu.add(Menu.NONE, Menu.NONE, 1, "Favorite")
            .setActionView(favBtn)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        inflater.inflate(R.menu.menu_info, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private val stateListener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            favoriteList.add(myItem)
        } else {
            favoriteList.remove(myItem)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> (activity as MainActivity).supportFragmentManager.popBackStack()
        }
        return true
    }

    private fun setInfo() {
        when (type) {
            "films" -> setFilmInfo()
            "people" -> setPersonInfo()
            "planets" -> setPlanetInfo()
            "species" -> setSpeciesInfo()
            "starships" -> setStarShipInfo()
            "vehicles" -> setVehicleInfo()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setFilmInfo() {
        textName.text = "Title: ${myItem.title}"
        textView1.text = "Episode number: ${myItem.episode_id}"
        textView2.text = "Director: ${myItem.director}"
        textView3.text = "Producer: ${myItem.producer}"
        textView4.text = "Release date: ${myItem.release_date}"
        textView5.text = "Opening crawl:\n   ${myItem.opening_crawl}"
        textView5.textSize = 18f
    }

    @SuppressLint("SetTextI18n")
    private fun setPersonInfo() {
        textName.text = "Name: ${myItem.name}"
        textView1.text = "Height: ${myItem.height}"
        textView2.text = "Mass: ${myItem.mass}"
        textView3.text = "Hair color: ${myItem.hair_color}"
        textView4.text = "Skin color: ${myItem.skin_color}"
        textView5.text = "Eye color: ${myItem.eye_color}"
        textView6.text = "Birth year: ${myItem.birth_year}"
        textView7.text = "Gender: ${myItem.gender}"
    }

    @SuppressLint("SetTextI18n")
    private fun setPlanetInfo() {
        textName.text = "Planet: ${myItem.name}"
        textView1.text = "Rotation period: ${myItem.rotation_period}"
        textView2.text = "Orbital period: ${myItem.orbital_period}"
        textView3.text = "Diameter: ${myItem.diameter}"
        textView4.text = "Climate: ${myItem.climate}"
        textView5.text = "Gravity: ${myItem.gravity}"
        textView6.text = "Terrain: ${myItem.terrain}"
        textView7.text = "Surface water: ${myItem.surface_water}"
        textView8.text = "Population: ${myItem.population}"
    }

    @SuppressLint("SetTextI18n")
    private fun setSpeciesInfo() {
        textName.text = "Species: ${myItem.name}"
        textView1.text = "Classification: ${myItem.classification}"
        textView2.text = "Designation: ${myItem.designation}"
        textView3.text = "Average height: ${myItem.average_height}"
        textView4.text = "Skin colors: ${myItem.skin_colors}"
        textView5.text = "Hair colors: ${myItem.hair_colors}"
        textView6.text = "Eye colors: ${myItem.eye_colors}"
        textView7.text = "Average lifespan: ${myItem.average_lifespan}"
        textView8.text = "Language: ${myItem.language}"
    }

    @SuppressLint("SetTextI18n")
    private fun setStarShipInfo() {
        textName.text = "Starship: ${myItem.name}"
        setStarShipAndVehicleInfo()
        textView10.text = "Hyperdrive rating: ${myItem.hyperdrive_rating}"
        textView11.text = "MGLT: ${myItem.MGLT}"
        textView12.text = "Starship class: ${myItem.starship_class}"
    }

    @SuppressLint("SetTextI18n")
    private fun setVehicleInfo() {
        textName.text = "Vehicle: ${myItem.name}"
        setStarShipAndVehicleInfo()
        textView10.text = "Vehicle class: ${myItem.vehicle_class}"
    }

    @SuppressLint("SetTextI18n")
    private fun setStarShipAndVehicleInfo() {
        textView1.text = "Model: ${myItem.model}"
        textView2.text = "Manufacturer: ${myItem.manufacturer}"
        textView3.text = "Cost in credits: ${myItem.cost_in_credits}"
        textView4.text = "Length: ${myItem.length}"
        textView5.text = "Maximum atmospheric speed: ${myItem.max_atmosphering_speed}"
        textView6.text = "Crew: ${myItem.crew}"
        textView7.text = "Passengers: ${myItem.passengers}"
        textView8.text = "Cargo capacity: ${myItem.cargo_capacity}"
        textView9.text = "Consumables: ${myItem.consumables}"
    }

    companion object {
        @JvmStatic
        fun newInstance(item: Response.Item, type: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString("item", GsonBuilder().create().toJson(item))
                    putString("typeOfResult", type)
                }
            }
    }
}