package com.app.adreal.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.adreal.timetable.databinding.ActivityMainBinding
import com.app.adreal.timetable.homeactivity.homeactivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var user = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        if(user.currentUser!=null)
        {
            //go to home activity
            val intent = Intent(this, homeactivity::class.java)
            startActivity(intent)
            finish()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navhostfragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.toolbar,navController)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navhostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}