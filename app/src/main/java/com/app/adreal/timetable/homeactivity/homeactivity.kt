package com.app.adreal.timetable.homeactivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.ActivityHomeactivityBinding
import com.app.adreal.timetable.daysfragments.profile.profileViewModel

class homeactivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener{

    lateinit var binding: ActivityHomeactivityBinding

    lateinit var homeViewModel: homeViewModel

    lateinit var profileViewModel: profileViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.toolbar.setupWithNavController(navController)

        binding.toolbar.inflateMenu(R.menu.topright_navmenu)

        binding.toolbar.setOnMenuItemClickListener()
        {
            when(it.itemId)
            {
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

                R.id.share ->{
                    val intent= Intent()
                    intent.action=Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT,"Thanks for sharing")
                    intent.type="text/plain"
                    startActivity(Intent.createChooser(intent,"Share To:"))
                }

                R.id.settings ->{
                    findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeactivityfragment_to_settingsActivity)
                }
            }
            true
        }

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)

        //applyThemeForApp()

        applySettings()

        homeViewModel =
            ViewModelProvider(this).get(com.app.adreal.timetable.homeactivity.homeViewModel::class.java)

        profileViewModel =
            ViewModelProvider(this).get(com.app.adreal.timetable.daysfragments.profile.profileViewModel::class.java)

    }

        override fun onSupportNavigateUp(): Boolean {
            val navcontroller = findNavController(R.id.fragmentContainerView)
            return navcontroller.navigateUp()
        }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == "nightMode")
        {
            when(sharedPreferences?.getString(key,"0"))
            {
                "1" ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

                "2" ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

                "0" ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
        if(key == "app_theme")
        {
            this.recreate()
        }
    }

//    private fun applyThemeForApp() {
//
//        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//        when(prefs.getString("app_theme","2"))
//        {
//            "1" ->{
//
//                binding.toolbar.background.setTint(resources.getColor(R.color.orange))
//
//                val window: Window = this.window
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                window.statusBarColor = ContextCompat.getColor(
//                    this,
//                    R.color.orange
//                )
//
//                setTheme(R.style.OrangeTheme)
//            }
//
//            "2" ->{
//
//                binding.toolbar.background.setTint(resources.getColor(R.color.teal))
//
//                val window: Window = this.window
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                window.statusBarColor = ContextCompat.getColor(
//                    this,
//                    R.color.teal
//                )
//
//                setTheme(R.style.TealTheme)
//            }
//
//            "0" ->{
//
//                binding.toolbar.background.setTint(resources.getColor(R.color.green))
//
//                val window: Window = this.window
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                window.statusBarColor = ContextCompat.getColor(
//                    this,
//                    R.color.green
//                )
//
//                setTheme(R.style.GreenTheme)
//            }
//        }
//    }

    private fun applySettings()
    {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        when(prefs.getString("nightMode","0"))
        {
            "1" ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            "2" ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            "0" ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}