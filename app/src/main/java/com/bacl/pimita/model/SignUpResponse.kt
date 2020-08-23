package com.bacl.pimita.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("message")
	val message: String? = "",

	@field:SerializedName("status")
	val status: Int? = 0
)
