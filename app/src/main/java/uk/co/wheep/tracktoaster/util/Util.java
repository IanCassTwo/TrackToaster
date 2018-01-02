/**
 * This file is part of Simple Last.fm Scrobbler.
 * <p>
 * https://github.com/tgwizard/sls
 * <p>
 * Copyright 2011 Simple Last.fm Scrobbler Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package uk.co.wheep.tracktoaster.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * This class is way too bloated. FIXME
 *
 * @author tgwizard
 */

public class Util {
    private static final String TAG = "Util";

    /**
     * Returns the current time since 1970, UTC, in seconds.
     *
     * @return the current time since 1970, UTC, in seconds
     */
    public static long currentTimeSecsUTC() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                .getTimeInMillis() / 1000;
    }


    public static void warningDialog(Context ctx, String msg) {
        new AlertDialog.Builder(ctx).setTitle(uk.co.wheep.tracktoaster.R.string.warning).setMessage(msg)
                .setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(
                uk.co.wheep.tracktoaster.R.string.close, null).show();
    }

    public static boolean checkForInstalledApp(Context ctx, String pkgName) {
        try {
            PackageManager pm = ctx.getPackageManager();
            pm.getPackageInfo(pkgName, 0);
            // Log.d(TAG, pkgString + " is installed");
            return true;
        } catch (NameNotFoundException e) {
            // Log.d(TAG, pkgString + " is not installed");
        }
        return false;
    }

    public static String getAppName(Context ctx, String pkgName) {
        try {
            PackageManager pm = ctx.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(pkgName, 0);
            String label = pm.getApplicationLabel(appInfo).toString();
            return label;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static String getAppVersionName(Context ctx, String pkgName) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pkgInfo = pm.getPackageInfo(pkgName, 0);
            String ver = pkgInfo.versionName;
            return ver;
        } catch (NameNotFoundException e) {
            return "0";
        }
    }

}