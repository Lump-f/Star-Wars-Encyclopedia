package com.example.starwarsencyclopedia.Network.Model

data class Response(
    var count: Int?,
    var items: List<Item?>
) {
    data class Item(
        var name: String?
    )
}