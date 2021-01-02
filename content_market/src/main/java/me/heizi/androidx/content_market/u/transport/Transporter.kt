package me.heizi.androidx.content_market.u.transport

/**
 * Transporter 接入就行了
 *
 * 谁规定的一定要参数? 以下为用例:
 *
 * <code>
 *     interface UserTransporter:Transporter {
 *
 *         @GET("userById")
 *         fun getUserByName(name:String):List<User>
 *
 *         @PUT("user")
 *         fun addUser(user:UserTransporter)
 *     }
 *
 *     class Repository {
 *         val transporter by transporters(UserTransporter::class)
 *         val livedata = .......
 *     }
 * </code>
 */
interface Transporter {
}