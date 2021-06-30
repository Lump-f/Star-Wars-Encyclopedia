package com.example.starwarsencyclopedia.Network.Model

data class Response(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: ArrayList<Item?>
) {
    data class Item(
        var name: String?,

        // Films
        var title: String?,
        var episode_id: Int?,
        var opening_crawl: String?,
        var director: String?,
        var producer: String?,
        var release_date: String?,

        // People
        var height: String?,
        var mass: String?,
        var hair_color: String?,
        var skin_color: String?,
        var eye_color: String?,
        var birth_year: String?,
        var gender: String?,

        // Planets
        var rotation_period: String?,
        var orbital_period: String?,
        var diameter: String?,
        var climate: String?,
        var gravity: String?,
        var terrain: String?,
        var surface_water: String?,
        var population: String?,

        // Species
        var classification: String?,
        var designation: String?,
        var average_height: String?,
        var skin_colors: String?,
        var hair_colors: String?,
        var eye_colors: String?,
        var average_lifespan: String?,
        var language: String?,

        // General Starships & Vehicles
        var model: String?,
        var manufacturer: String?,
        var cost_in_credits: String?,
        var length: String?,
        var max_atmosphering_speed: String?,
        var crew: String?,
        var passengers: String?,
        var cargo_capacity: String?,
        var consumables: String?,

        // Starships
        var hyperdrive_rating: String?,
        var MGLT: String?,
        var starship_class: String?,

        // Vehicles
        var vehicle_class: String?,
        )
}