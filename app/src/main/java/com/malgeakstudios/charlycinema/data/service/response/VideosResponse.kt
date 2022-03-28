package com.malgeakstudios.charlycinema.data.service.response

import com.malgeakstudios.charlycinema.data.model.Videos

data class VideosResponse(
    val id : Int,
    val results : List<Videos>
)
