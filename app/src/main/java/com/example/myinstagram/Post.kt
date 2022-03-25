package com.example.myinstagram

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Post")

class Post : ParseObject() {

    fun getDescription(): String? {
        return getString(LeDescription)
    }
    fun setDescription(description: String) {
        put(LeDescription, description)
    }

    fun getimage(): ParseFile? {
        return getParseFile(LeImage)
    }
    fun setImage(parsefile: ParseFile){
        put(LeImage, parsefile)
    }

    fun getUser(): ParseUser? {
        return getParseUser(LeUser)
    }
    fun setUser(user: ParseUser) {
        put(LeUser, user)
    }

    companion object{
        const val LeDescription = "description"
        const val LeImage = "image"
        const val LeUser = "user"
    }
}