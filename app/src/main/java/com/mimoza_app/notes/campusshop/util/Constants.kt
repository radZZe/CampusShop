package com.mimoza_app.notes.campusshop.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.mimoza_app.notes.campusshop.database.DatabaseRepository
import com.mimoza_app.notes.campusshop.screens.MainActivity

lateinit var APP_ACTIVITY:MainActivity
lateinit var EMAIL:String
lateinit var PASSWORD:String
lateinit var REPOSITORY: DatabaseRepository
lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID:String
lateinit var REF_DATABASE: DatabaseReference
const val LOGIN = "LOGIN"
const val SIGNUP = "SIGNUP"