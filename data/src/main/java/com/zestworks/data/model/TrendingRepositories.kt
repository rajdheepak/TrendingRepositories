package com.zestworks.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories", primaryKeys = ["url"])
data class TrendingRepositories(

	@field:SerializedName("forks")
	val forks: Int = 0,

//	@field:SerializedName("builtBy")
//	val builtBy: List<BuiltByItem> = null,

	@field:SerializedName("author")
	val author: String = "",

	@field:SerializedName("name")
	val name: String = "",

	@field:SerializedName("description")
	val description: String = "",

	@field:SerializedName("language")
	val language: String = "",

	@field:SerializedName("avatar")
	val avatar: String = "",

	@field:SerializedName("languageColor")
	val languageColor: String = "",

	@field:SerializedName("stars")
	val stars: Int = 0,

	@field:SerializedName("url")
	val url: String = "",

	@field:SerializedName("currentPeriodStars")
	val currentPeriodStars: Int = 0
)

data class BuiltByItem(

	@field:SerializedName("href")
	val href: String = "",

	@field:SerializedName("avatar")
	val avatar: String = "",

	@field:SerializedName("username")
	val username: String = ""
)