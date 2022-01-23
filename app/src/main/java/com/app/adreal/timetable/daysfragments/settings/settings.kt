package com.app.adreal.timetable.daysfragments.settings

import android.R.attr
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.adreal.timetable.R
import android.media.RingtoneManager

import android.content.Intent
import android.media.Ringtone
import android.net.Uri
import android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
import android.provider.Settings.System.DEFAULT_RINGTONE_URI
import android.R.attr.data
import androidx.preference.*


class settings : PreferenceFragmentCompat() {

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
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

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
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, DEFAULT_RINGTONE_URI);
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