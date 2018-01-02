/**
 * This file is part of Simple Last.fm Scrobbler.
 * <p>
 * http://code.google.com/p/a-simple-lastfm-scrobbler/
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

package uk.co.wheep.tracktoaster.receiver;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import uk.co.wheep.tracktoaster.util.Track;
import uk.co.wheep.tracktoaster.util.Util;

/**
 * A BroadcastReceiver for the Simple Last.fm Scrobbler API. More info available
 * at the SLS <a
 * href="http://code.google.com/p/a-simple-lastfm-scrobbler/wiki/Developers">
 * dev page</a>.
 *
 * @see AbstractPlayStatusReceiver
 *
 * @author tgwizard
 * @author icass
 * @since 1.2.3
 */
public class SLSAPIReceiver extends AbstractPlayStatusReceiver {
    @SuppressWarnings("unused")
    private static final String TAG = "SLSAPIReceiver";

    public static final String SLS_API_BROADCAST_INTENT = "uk.co.wheep.tracktoaster.notify.playstatechanged";

    public static final int STATE_START = 0;
    public static final int STATE_RESUME = 1;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_COMPLETE = 3;

    private int getIntFromBundle(Bundle bundle, String key, boolean throwOnFailure)
            throws IllegalArgumentException {
        long value = -1;
        Object obj;
        try {
            obj = bundle.get(key);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        if (obj instanceof Long)
            value = (Long) obj;
        else if (obj instanceof Integer)
            value = (Integer) obj;
        else if (obj instanceof Double)
            value = ((Double) obj).intValue();
        else if (obj instanceof String)
            value = Long.valueOf((String) obj).longValue();
        else if (throwOnFailure)
            throw new IllegalArgumentException(key + "not found in intent");

        return (int) value;
    }

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle)
            throws IllegalArgumentException {

        CharSequence pkgTest = null;
        String appname;
        String apppkg;
        PackageManager packageManager = ctx.getPackageManager();
        if (bundle.containsKey("gonemad.gmmp")) {
            pkgTest = "gonemad.gmmp";
        }
        try {
            appname = packageManager.getApplicationLabel(packageManager.getApplicationInfo(pkgTest.toString(), PackageManager.GET_META_DATA)).toString();
            apppkg = pkgTest.toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            // music api stuff
            // app-name, required
            appname = bundle.getString("app-name");
            // app-package, required
            apppkg = bundle.getString("app-package");
        }

        // state, required
        int state = getIntFromBundle(bundle, "state", true);

        if (state == STATE_START)
            setPlaying(Boolean.TRUE);
        else if (state == STATE_RESUME)
            setPlaying(Boolean.TRUE);
        else if (state == STATE_PAUSE)
            setPlaying(Boolean.FALSE);
        else if (state == STATE_COMPLETE)
            setPlaying(Boolean.FALSE);
        else
            throw new IllegalArgumentException("bad state: " + state);


        // album name, optional (recommended)
        String album = "";
        if (bundle.containsKey("album")) {
            CharSequence al = bundle.getCharSequence("album");
            if (al != null && !"Unknown album".equals("" + al) && !"Unknown".equals("" + al)) {
                album = "" + al;
            }
        }

        // tracknr, optional
        int tracknr = getIntFromBundle(bundle, "track-number", false);

        // Track data
        setTrack(new Track(bundle.getString("artist"),bundle.getString("album"),bundle.getString("track")));

    }
}
