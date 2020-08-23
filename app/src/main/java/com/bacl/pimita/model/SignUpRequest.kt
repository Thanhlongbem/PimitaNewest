package com.bacl.pimita.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpRequest(@SerializedName("name") @Expose var name: String? = "",
                         @SerializedName("email") @Expose var email: String? = "",
                         @SerializedName("id_of_introductor") @Expose var idOfIntroductor: Int? = 0,
                         @SerializedName("avatar") @Expose var avatar: Any? = null,
                         @SerializedName("phone") @Expose var phone: String?,
                         @SerializedName("account_type") @Expose var accountType: Int?,
                         @SerializedName("gender") @Expose var gender: Int?,
                         @SerializedName("password") @Expose var password: String?
)