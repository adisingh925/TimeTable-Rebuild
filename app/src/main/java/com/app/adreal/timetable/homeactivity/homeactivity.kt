package com.app.adreal.timetable.homeactivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.adreal.timetable.MainActivity
import com.app.adreal.timetable.R
import com.app.adreal.timetable.authorization.viewmodel.authViewModel
import com.app.adreal.timetable.databinding.ActivityHomeactivityBinding
import com.app.adreal.timetable.daysfragments.*
import com.app.adreal.timetable.daysfragments.home.homefragment
import com.app.adreal.timetable.daysfragments.profile.profile
import com.app.adreal.timetable.daysfragments.profile.profileViewModel
import com.app.adreal.timetable.daysfragments.settings.settings
import com.app.adreal.timetable.timetable_fragment.timetable
import com.app.adreal.timetable.viewpageradapter.viewpageradapter


class homeactivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeactivityBinding

    lateinit var homeViewModel: homeViewModel

    lateinit var profileViewModel: profileViewModel

    lateinit var authViewModel: authViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.homeactivity.homeViewModel::class.java)

        profileViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysfragments.profile.profileViewModel::class.java)

        authViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.authorization.viewmodel.authViewModel::class.java)

        setSupportActionBar(binding.toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerlayout,binding.toolbar,0,0)
        binding.drawerlayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navview.setNavigationItemSelectedListener {
            menuitem ->

            when(menuitem.itemId)
            {
                R.id.timetable ->{
                    replaceFragment(timetable(),menuitem.title.toString())
                    actionBarDrawerToggle.syncState()
                }

                R.id.home ->{
                    replaceFragment(homefragment(),menuitem.title.toString())
                    actionBarDrawerToggle.syncState()
                }

                R.id.profile ->{
                    replaceFragment(profile(),menuitem.title.toString())
                    actionBarDrawerToggle.syncState()
                }

                R.id.settings ->{
                    replaceFragment(settings(),menuitem.title.toString())
                    actionBarDrawerToggle.syncState()
                }

                R.id.share ->{
                    val intent= Intent()
                    intent.action=Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT,"Thanks for sharing")
                    intent.type="text/plain"
                    startActivity(Intent.createChooser(intent,"Share To:"))
                }

                R.id.feedback ->{
                    val intent = Intent(Intent.ACTION_SEND)
                    val recipients = arrayOf("adrealhelp@gmail.com")
                    intent.putExtra(Intent.EXTRA_EMAIL, recipients)
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for TimeTable")
                    intent.putExtra(Intent.EXTRA_TEXT, "Nice app")
                    //intent.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com")
                    intent.type = "text/html"
                    intent.setPackage("com.google.android.gm")
                    startActivity(Intent.createChooser(intent, "Send mail"))
                }

                R.id.logout ->{
                    authViewModel.logout()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else -> {
                    Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
                }
            }
            binding.drawerlayout.closeDrawer(binding.navview)
            //supportActionBar?.setDisplayHomeAsUpEnabled(true)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title : String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out,R.anim.fade_in, R.anim.slide_out)
        transaction.replace(R.id.fragmentContainerView, fragment)
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