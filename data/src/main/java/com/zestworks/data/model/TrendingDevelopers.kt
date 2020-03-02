package com.zestworks.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "developers", primaryKeys = ["url"])
data class TrendingDevelopers(

	@field:SerializedName("name")
	val name: String = "",

	@field:SerializedName("avatar")
	val avatar: String = "",

	@field:SerializedName("url")
	val url: String = "",

	@field:SerializedName("username")
	val username: String = ""
)