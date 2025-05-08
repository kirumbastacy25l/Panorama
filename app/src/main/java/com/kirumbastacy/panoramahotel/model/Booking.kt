package com.kirumbastacy.panoramahotel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val contact: String,
    val roomType: String,
    val checkInDate: String,
    val checkOutDate: String,
    val imagePath: String

)