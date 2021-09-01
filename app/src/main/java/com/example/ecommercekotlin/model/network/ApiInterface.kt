package com.example.ecommercekotlin.model.network

import com.example.ecommercekotlin.model.entity.*
import com.example.ecommercekotlin.utils.BranchStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface  ApiInterface {

    companion object{
        var BASE_URL2 = "https://varvalue.com/api/public_html/api/"
        fun getClient(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL2)
                .client(createHttpLoggingInterceptor2())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
        fun createHttpLoggingInterceptor(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }
        fun createHttpLoggingInterceptor2(): OkHttpClient? {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNDhmNzg2NTc0ZmFmMmU1NjBiY2RhMmEzZWY3NjJkNmVmZjI3YjZkZmYzY2RiNzM4OGMzMjFkNDE0YTYxY2ZjN2ZiNzg0NDhlZDNjNTZmY2MiLCJpYXQiOjE2MjkyNzY5OTUuMjQ1ODYyMDA3MTQxMTEzMjgxMjUsIm5iZiI6MTYyOTI3Njk5NS4yNDU4NzM5MjgwNzAwNjgzNTkzNzUsImV4cCI6MTY2MDgxMjk5NS4yMzExNzU4OTk1MDU2MTUyMzQzNzUsInN1YiI6IjEwOSIsInNjb3BlcyI6W119.nnaqsVR9t7fsQQIvAI2uq1aBXQZHfTRFZCYBMlHtqZnd0TSX91eIl5rLTeDhGnBTLOd8wQCcxlLdcv96eJgHUxgaOwabN9wq-BMNFMO87qc7_-q5iahXkGP1pPjYsKn-PN1_z_9BRLX2mGsXlS7NfqqGZbSNMscRLXZJF93-vUCNKRM9FlvVvfDdiMVAI_Sle1bS1dtwtZtoJTQrRVeMgbEcgP1G8-Cps7zTR0EFvxag503NcWSR-t3vzSCwywfBiAWPMrchoun6qiqZdnOMNGddoVvmdYTkjSrMUrR7MxFX-y_-sfN9IIlGv9wi8CT5EeppHZg3XBlQXylef5MILgLQDmVwYSCeFqvKrEOjqUJWtL4N_dtcxX5G9GY4dW9DtkUkHtuzhD6vkCD1RK5Z1NjB0gPxFVSUWtA6MdYw4s8SveriTPv-bqwqDpV8TPdmBHZiBiVf5vim6lgwC7pXsH4oGVu1MJfigNnWaCBWwNYr-3RslCMNX_wYAKJvq6sVuvTotiDME1P4BB86AXUvY0d05mlM5YIFbqG_q5QOV3qmsZMv6iQOUW01Kydao9P5alTYWB_nVwaoZOh1cx4qEFwWQjTfSkEcX-MeYJZjkveubzeVI9yXTrM8tF9Yzs3WUnYXpaz_Evci2NyEsD6twvJHKPPJZfghrjvivHBKdf0"
                    )
                    .build()
                chain.proceed(newRequest)
            }.build()
        }

    }


    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("user/register")
   suspend fun register(
        @Field("username") user: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("password_confirmation") password_confirmation: String?,
        @Field("phone_number") phone_number: String?,
        @Field("address") address: String?,
    ) : UserStatus

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?,
    ) : UserStatus

    @GET("home")
    suspend fun getHomeData() : HomeStatus

    @GET("category/{id}/product")
   suspend fun getProductsForSingleCat(@Path("id") id: Int):CategoryStatus


    @FormUrlEncoded
    @POST("user/add/cart")
  suspend  fun addProductToCart(
        @Field("product_id") product_id: Int?,
        @Field("quantity") quantity: Int?,
        @Field("color_id") color_id: Int?,
        @Field("size_id") size_id: Int?,
    ) : CartStatus

    @GET("user/cart")
 suspend   fun showCartsForUser() : CartResponse

    @GET("user/cart/{id}")
   suspend fun deleteCart(@Path("id")id: Int) : CartResponse


    @FormUrlEncoded
    @POST("user/cart/{id}")
   suspend fun updateCart(@Path("id")id: Int,@Field("quantity")quantity: Int?) : CartResponse

    @FormUrlEncoded
    @POST("user/add/order")
   suspend fun confirmCarts(@Field("status")status: String?):CartResponse

    @FormUrlEncoded
    @POST("user/add/bookmark")
    fun addProductToBookMark(
        @Field("product_id") product_id: Int?) : Call<CartStatus>








    @GET("user/bookmark")
   suspend fun getBookMark():BookMarkStatus









   @GET("user/bookmark/{id}")
  suspend  fun deleteBookMark(@Path("id")id: Int):CartStatus


    @GET("user/show/order")
   suspend fun getOrders():OrderStatus

    @GET("user/show")
    suspend fun getUserInfo():UserStatus


    @FormUrlEncoded
    @POST("user/update")
    suspend fun updateUser(
        @Field("username") user: String?,
        @Field("phone_number") phonenumber: String?,
        @Field("address") address: String?,
    ) : UserStatus

    @FormUrlEncoded
    @POST("user/rate/product")
  suspend  fun rateProduct(@Field("product_id") id: Int?,
                    @Field("rate") rate: Int?,):RateResponse

    @GET("search")
    suspend fun search(@Header("word")word:String,
               @Header("sort_type")sort_type:String,
               @Header("sort_value")sort_value:String,
                ):SearchResponse

    @GET("branches")
    suspend fun getBranches():BranchStatus

    @FormUrlEncoded
    @POST("nearest/branche")
   suspend fun getNearestBranches(@Field("lat") id: Double?,
                           @Field("long") rate: Double?,
                           @Field("radius") radius: Int?,):BranchStatus

}