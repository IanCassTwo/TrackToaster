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


package uk.co.wheep.tracktoaster.receiver;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import uk.co.wheep.tracktoaster.util.Track;
import uk.co.wheep.tracktoaster.util.Util;

/**
 * A BroadcastReceiver for the <a
 * href="http://code.google.com/p/scrobbledroid/wiki/DeveloperAPI"> Scrobbler
 * Droid API</a>. New music apps are recommended to use the <a
 * href="http://code.google.com/p/a-simple-lastfm-scrobbler/wiki/Developers">
 * SLS API</a> instead.
 *
 * @see AbstractPlayStatusReceiver
 *
 * @author tgwizard
 * @author icass
 * @since 1.2
 */
public class ScrobbleDroidMusicReceiver extends AbstractPlayStatusReceiver {

    @SuppressWarnings("unused")
    private static final String TAG = "SLSSDMusicReceiver";

    public static final String SCROBBLE_DROID_MUSIC_STATUS = "net.jjc1138.android.scrobbler.action.MUSIC_STATUS";

    /**public static void dumpIntent(Bundle bundle){
     if (bundle != null) {
     Set<String> keys = bundle.keySet();
     Iterator<String> it = keys.iterator();
     Log.e(TAG,"Dumping Intent start");
     while (it.hasNext()) {
     String key = it.next();
     Log.e(TAG,"[" + key + "=" + bundle.get(key)+"]");
     }
     Log.e(TAG,"Dumping Intent end");
     }
     }*/

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle)
            throws IllegalArgumentException {

        //dumpIntent(bundle);

        if (bundle.containsKey("playing")) {
            setPlaying(bundle.getBoolean("playing"));
        } else {
            setPlaying(Boolean.FALSE);
            return;
        }

        String tracknr;
        int tnr = bundle.getInt("tracknumber", -1); // optional
        if (tnr != -1) {
            tracknr = String.valueOf(tnr);
        } else {
            tracknr = "";
        }

        // Track data
        setTrack(new Track(bundle.getString("albumartist"), bundle.getString("artist"),bundle.getString("album"),bundle.getString("track")));

    }
}
