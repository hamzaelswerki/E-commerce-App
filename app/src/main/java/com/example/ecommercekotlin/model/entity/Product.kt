package com.example.ecommercekotlin.model.entity

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Product (
    val colors: List<Color>,
    val created_at: String,
    val deleted_at: Any,
    val description: String,
    val id: Int,
    val name: String,
    val offer: Any,
    val price: Int,
    val product_images: List<ProductImage>,
    val quantity: Int,
    val rate: Double,
    val sale_price: Int,
    val sizes: List<Size>,
    val status: String,
    val updated_at: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("colors"),
        parcel.readString()!!,
        TODO("deleted_at"),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        TODO("offer"),
        parcel.readInt(),
        TODO("product_images"),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        TODO("sizes"),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(created_at)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(quantity)
        parcel.writeDouble(rate)
        parcel.writeInt(sale_price)
        parcel.writeString(status)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}