package dev.csaba.assetlocationlogger.data.remote

import com.google.firebase.Timestamp


data class RemoteAsset(
    var id: String = "",
    var title: String = "",
    var created: Timestamp = Timestamp(0, 0),
    var updated: Timestamp = Timestamp(0, 0)
)