package com.app.adreal.timetable.daysfragments.settings

import android.content.Intent
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        binding = SettingsActivityBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
        {

        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            if (preference != null) {
                if(preference.key.equals("ringtone_preference"))
                {
                    val intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION)
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone")
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true)
                    intent.putExtra(
                        RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI,
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

                    val prefs = PreferenceManager.getDefaultSharedPreferences(this.context)
                    val existing = prefs.getString("ringtone_preference","")

                    if(existing!=null)
                    {
                        if(existing.isEmpty())
                        {
                            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, null as Uri?)
                        }
                        else
                        {
                            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(existing))
                        }
                    }
                    else
                    {
                        intent.putExtra(
                            RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
                            Settings.System.DEFAULT_RINGTONE_URI
                        );
                    }

                    startActivityForResult(intent, RINGTONE_REQUEST_CODE)
                }
            }
            return super.onPreferenceTreeClick(preference)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if(requestCode == RINGTONE_REQUEST_CODE)
            {
                val ringtone: Uri? = data?.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
                val prefs = PreferenceManager.getDefaultSharedPreferences(this.context)
                val editpref = prefs.edit()
                if (ringtone != null) {
                    editpref.putString("ringtone_preference",ringtone.toString())
                        .apply()
                    findPreference<Preference>("ringtone_preference")?.summary  = RingtoneManager.getRingtone(this.context,ringtone).getTitle(this.context)
                }
                else
                {
                    editpref.putString("ringtone_preference","")
                        .apply()
                    findPreference<Preference>("ringtone_preference")?.summary  = ""
                }
            }
        }

        companion object{
            const val RINGTONE_REQUEST_CODE = 999
        }
    }
}