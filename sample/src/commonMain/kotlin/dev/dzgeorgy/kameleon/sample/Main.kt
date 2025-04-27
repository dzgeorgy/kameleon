package dev.dzgeorgy.kameleon.sample

import dev.dzgeorgy.kameleon.MapTo

fun main() {
    val track = Track(
        title = "Blank Space",
        length = 3.51f,
    )
    println(track.toTrackEntity())
}

@MapTo(TrackEntity::class)
internal data class Track(
    val title: String,
    val length: Float
)

data class TrackEntity(
    val title: String,
    val length: Float
)
