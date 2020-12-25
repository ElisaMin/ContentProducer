package com.example.providers.u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.providers.R
import com.example.providers.u.repositories.Repository

class MainActivity : AppCompatActivity() {

    val repository by  lazy { Repository(contentResolver) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository.getTypeByID("")
    }
}