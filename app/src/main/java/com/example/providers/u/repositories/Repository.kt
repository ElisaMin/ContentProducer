package com.example.providers.u.repositories

import com.example.providers.annotations.Field

val transporters = transport (
    resoler = ....
) {
    A::class,
    b::class,
}
data class User(
        val id:Int = -1,
        val name:String,
        @Field("img_path")
        val ImgPath:String
)
interface UserTransporter:Transporter {
    @GET("userById")
    fun getUserByName(name:String):List<User>

    @CHANGE("userHPic")
    fun setUserPic(@Param("") picPath:String)

    @PUT("user")
    fun addUser(user:UserTransporter)
}

class Repository {
    val transporter by transporters(UserTransporter::class)

    val livedata = .......
}
