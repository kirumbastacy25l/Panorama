package com.kirumbastacy.panoramahotel.navigation

const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_CONTACT = "contact"
const val ROUT_ROOMS = "rooms"
const val ROUT_STANDARD = "standard"
const val ROUT_DELUXE = "deluxe"
const val ROUT_SUITE = "suite"
const val ROUT_PRESIDENTIAL = "presidential"

const val ROUT_SPLASH= "splash"
const val ROUT_PAYMENT= "payment"
const val ROUT_CONFIRM= "confirm"
const val ROUT_DETAILS= "details"
const val ROUT_BOOK= "book"
const val ROUT_EDIT= "edit"
const val ROUT_PAY= "pay"



//Authentication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"

//Booking

const val ROUT_EDIT_BOOKING = "edit_booking/{bookingId}"
// ✅ Helper function for navigation
fun editBookingRoute(bookingId: Int) = "edit_booking/$bookingId"



