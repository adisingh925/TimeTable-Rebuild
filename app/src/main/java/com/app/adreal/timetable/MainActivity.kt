package com.app.adreal.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
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

        applySettings()

        applyThemeForApp()

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

    private fun applyThemeForApp() {

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        when(prefs.getString("app_theme","2"))
        {
            "1" ->{

                binding.toolbar.background.setTint(resources.getColor(R.color.orange))

                val window: Window = this.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    this,
                    R.color.orange
                )

                setTheme(R.style.OrangeTheme)
            }

            "2" ->{

                binding.toolbar.background.setTint(resources.getColor(R.color.teal))

                val window: Window = this.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    this,
                    R.color.teal
                )

                setTheme(R.style.TealTheme)
            }

            "0" ->{

                binding.toolbar.background.setTint(resources.getColor(R.color.green))

                val window: Window = this.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    this,
                    R.color.green
                )

                setTheme(R.style.GreenTheme)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navhostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

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