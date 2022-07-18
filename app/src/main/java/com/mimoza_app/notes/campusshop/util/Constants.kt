package com.mimoza_app.notes.campusshop.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.mimoza_app.notes.campusshop.database.firebase.DatabaseRepository
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
const val KEY_VALUE = "price"
const val KEY_BUILDING = "building"
const val KEY_CATEGORY = "category"
const val KEY_DESCRIPTION = "description"
const val KEY_DATABASE = "KEY"
const val KEY_COLLECTION_USERS = "users"
const val KEY_COLLECTION_ITEMS = "items"
const val KEY_FIREBASE_ID = "idFirebase"
const val KEY_EMAIL = "email"
const val KEY_PASSWORD = "password"
const val KEY_NAME = "name"
const val KEY_SURNAME = "surname"
const val KEY_PREFERENCE_NAME = "fragmentChatPreference"
const val KEY_IS_SIGNED_IN = "isSignedIn"
const val KEY_USER_ID = "userId"
const val KEY_FCM_TOKEN = "fcmToken"
const val KEY_IMAGE = "image"
const val KEY_UID = "uid"
const val KEY_USER ="user"
const val KEY_COLLECTION_CHAT = "chat"
const val SENDER_ID = "sender_id"
const val RECEIVER_ID = "receiver_id"
const val KEY_MESSAGE = "message"
const val KEY_TIMESTAMP = "timestamp"
const val KEY_IS_CHECKED = "is_checked"
const val KEY_ITEM = "item"
const val SENT = 1
const val RECEIVED = 0