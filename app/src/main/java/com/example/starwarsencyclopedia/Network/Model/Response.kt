package com.example.starwarsencyclopedia.Network.Model

data class Response(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: ArrayList<Item?>
) {
    data class Item(
        var name: String?,
        var title: String?
    )
}