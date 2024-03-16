package com.irwan.testmostrans.api

data class CharResponse(
	val results: List<ResultsItem>,
	val info: Info
)

data class Location(
	val name: String,
	val url: String
)

data class ResultsItem(
	val image: String,
	val gender: String,
	val species: String,
	val created: String,
	val origin: Origin,
	val name: String,
	val location: Location,
	val episode: List<String>,
	val id: Int,
	val type: String,
	val url: String,
	val status: String
)

data class Info(
	val next: String,
	val pages: Int,
	val prev: Any,
	val count: Int
)

data class Origin(
	val name: String,
	val url: String
)

