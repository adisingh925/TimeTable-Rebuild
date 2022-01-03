package com.app.adreal.timetable.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.ActivityHomeactivityBinding

class homeactivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var actionBarToggle = ActionBarDrawerToggle(this,binding.drawerlayout, 0, 0)
        binding.drawerlayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarToggle.syncState()

        binding.navview.setNavigationItemSelectedListener {
            menuitem ->

            when(menuitem.itemId)
            {
                R.id.monday ->{
                    Toast.makeText(this, "Monday", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_homefragment_to_monday)
                }

                R.id.tuesday ->{
                    Toast.makeText(this, "Tuesday", Toast.LENGTH_SHORT).show()
                }

                R.id.wednesday ->{
                    Toast.makeText(this, "Wednesday", Toast.LENGTH_SHORT).show()
                }

                R.id.thursday ->{
                    Toast.makeText(this, "Thursday", Toast.LENGTH_SHORT).show()
                }

                R.id.friday ->{
                    Toast.makeText(this, "Friday", Toast.LENGTH_SHORT).show()
                }

                R.id.saturday ->{
                    Toast.makeText(this, "Saturday", Toast.LENGTH_SHORT).show()
                }

                R.id.home ->{
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if(binding.drawerlayout.isDrawerOpen(binding.navview))
        {
            binding.drawerlayout.closeDrawer(binding.navview)
        }
        else
        {
            binding.drawerlayout.openDrawer(binding.navview)
        }
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (this.binding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            this.binding.drawerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}