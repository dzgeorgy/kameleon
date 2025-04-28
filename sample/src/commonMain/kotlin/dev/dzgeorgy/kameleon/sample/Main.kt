package dev.dzgeorgy.kameleon.sample

import dev.dzgeorgy.kameleon.CustomMapper
import dev.dzgeorgy.kameleon.MapTo
import dev.dzgeorgy.kameleon.Mapper

fun main() {
    val track = Track(
        title = "Blank Space",
        length = 3.51f,
    )
    println(track.toTrackEntity().toTrackModel())
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

data class TrackModel(
    val title: String,
    val length: Float
)

@Mapper
class TrackEntityToTrackModelMapper : CustomMapper<TrackEntity, TrackModel>
