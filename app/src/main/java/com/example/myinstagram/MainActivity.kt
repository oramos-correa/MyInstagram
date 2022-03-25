package com.example.myinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queryPosts()
    }

    private fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.LeUser)
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e == null) {
                    Log.e(TAG, "Error fetching posts")
                    // Hooray! Let them use the app now.
                } else {
                    if (posts != null){
                        for (post in posts){
                            Log.i(TAG, "Post: " + post.getDescription() + " , username: " + post.getUser()?.username)
                        }
                    }
                    //e.printStackTrace()
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }

                TODO("Not yet implemented")
            }


        })
    }
    companion object{
        const val TAG = " MainActivity"
    }
}