/**
 * This file is part of Simple Last.fm Scrobbler.
 * <p/>
 * https://github.com/tgwizard/sls
 * <p/>
 * Copyright 2011 Simple Last.fm Scrobbler Team
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package uk.co.wheep.tracktoaster;

import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

import com.github.angads25.filepicker.view.FilePickerPreference;

import uk.co.wheep.tracktoaster.util.AppSettings;

public class OptionsActivity extends AppCompatPreferenceActivity implements Preference.OnPreferenceChangeListener {
    private static final String TAG = "OptionsGeneralScreen";
    private AppSettings settings;
    CheckBoxPreference longToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.options);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        settings = new AppSettings(this);

        FilePickerPreference fileDialog=(FilePickerPreference)findPreference("directories");
        fileDialog.setDefaultValue(settings.getAlbumsDir());
        fileDialog.setOnPreferenceChangeListener(this);

        longToast = (CheckBoxPreference) findPreference("longToast");
        longToast.setDefaultValue(settings.getLongToast());
        longToast.setOnPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object o) {
        Log.d(TAG, "Preference " + preference.getKey());
        if(preference.getKey().equals("directories")) {
            String value=(String)o;
            String arr[]=value.split(":");
            for(String path:arr) {
                Log.d(TAG, path);
                settings.setAlbumsDir(path);
            }
            return true;
        } else if (preference.getKey().equals("longToast")) {
            Boolean isChecked = (Boolean)o;
            Log.d(TAG, "Long toast selected? " + isChecked);
            settings.setLongToast(isChecked);
            return true;
        }

        return false;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen prefScreen,
                                         Preference pref) {
        return super.onPreferenceTreeClick(prefScreen, pref);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}