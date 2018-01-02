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


package uk.co.wheep.tracktoaster.util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author tgwizard
 * @author icass
 */
public class AppSettings {

    private static final String TAG = "AppSettings";
    private static final String SETTINGS_NAME = "settings";


    private final Context mCtx;
    private final SharedPreferences prefs;
    private static final String KEY_ALBUMS_DIR = "albums_directory";
    private static final String KEY_LONG_TOAST = "long_toast";


    public String getAlbumsDir() {
        return prefs.getString(KEY_ALBUMS_DIR, "");
    }

    public Boolean getLongToast() {
        return prefs.getBoolean(KEY_LONG_TOAST, Boolean.FALSE);
    }

    public void setAlbumsDir(String albumsDir) {
        SharedPreferences.Editor e = prefs.edit();
        e.putString(KEY_ALBUMS_DIR , albumsDir);
        e.commit();
    }

    public void setLongToast(Boolean longToast) {
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(KEY_LONG_TOAST , longToast);
        e.commit();
    }

    public AppSettings(Context ctx) {
        mCtx = ctx;
        prefs = ctx.getSharedPreferences(SETTINGS_NAME, 0);
    }


}