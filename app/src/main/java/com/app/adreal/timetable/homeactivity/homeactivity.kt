package com.app.adreal.timetable.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.ActivityHomeactivityBinding
import com.app.adreal.timetable.daysfragments.*
import com.app.adreal.timetable.daysfragments.home.homefragment

class homeactivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeactivityBinding

    lateinit var homeViewModel: homeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.homeactivity.homeViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var actionBarToggle = ActionBarDrawerToggle(this,binding.drawerlayout, 0, 0)
        binding.drawerlayout.addDrawerListener(actionBarToggle)

        actionBarToggle.syncState()

        replaceFragment(homefragment(),"Home")
        homeViewModel.homestate = "home"

        binding.navview.setNavigationItemSelectedListener {
            menuitem ->

            when(menuitem.itemId)
            {
                R.id.monday ->{
                    replaceFragment(monday(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "none"
                }

                R.id.tuesday ->{
                    replaceFragment(tuesday(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "none"
                }

                R.id.wednesday ->{
                    replaceFragment(wednesday(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "none"
                }

                R.id.thursday ->{
                    replaceFragment(thursday(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "none"
                }

                R.id.friday ->{
                    replaceFragment(friday(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "none"
                }

                R.id.saturday ->{
                    replaceFragment(saturday(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "none"
                }

                R.id.home ->{
                    replaceFragment(homefragment(),menuitem.title.toString())
                    actionBarToggle.syncState()
                    homeViewModel.homestate = "home"
                }
                else -> {
                    Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
                }
            }
            binding.drawerlayout.closeDrawer(binding.navview)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title : String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.commit()
        setTitle(title)
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
        }
        else if(!this.binding.drawerlayout.isDrawerOpen(GravityCompat.START))
        {
            if(supportFragmentManager.findFragmentById(R.id.framelayout) != homefragment() && homeViewModel.homestate != "home")
            {
                replaceFragment(homefragment(),"Home")
                binding.navview.setCheckedItem(R.id.home)
                homeViewModel.homestate = "home"
            }
            else
            {
                if(homeViewModel.doublebacktoexit)
                {
                    finish()
                    super.onBackPressed()
                }
                homeViewModel.doublebacktoexit = true
                Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed(Runnable { homeViewModel.doublebacktoexit = false }, 2000)
            }
        }
    }
}