package com.malgeakstudios.charlycinema.data.service.response

import com.malgeakstudios.charlycinema.data.model.VideosSerie

data class VideosSerieResponse(
    val id : Int,
    val results : List<VideosSerie>
)
