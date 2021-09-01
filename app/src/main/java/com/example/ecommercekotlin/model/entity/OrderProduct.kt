package com.example.ecommercekotlin.model.entity

import android.os.Parcel
import android.os.Parcelable

data class OrderProduct(
    val color: Color,
    val color_id: Int,
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val order_id: Int,
    val product: Product?,
    val product_id: Int,
    val quantity: Int,
    val sale_price: Int,
    val size: Size,
    val size_id: Int,
    val updated_at: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("color"),
        parcel.readInt(),
        parcel.readString()!!,
        TODO("deleted_at"),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Product::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        TODO("size"),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(color_id)
        parcel.writeString(created_at)
        parcel.writeInt(id)
        parcel.writeInt(order_id)
        parcel.writeParcelable(product, flags)
        parcel.writeInt(product_id)
        parcel.writeInt(quantity)
        parcel.writeInt(sale_price)
        parcel.writeInt(size_id)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderProduct> {
        override fun createFromParcel(parcel: Parcel): OrderProduct {
            return OrderProduct(parcel)
        }

        override fun newArray(size: Int): Array<OrderProduct?> {
            return arrayOfNulls(size)
        }
    }
}

