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
import android.os.Bundle;
import android.util.Log;

import uk.co.wheep.tracktoaster.util.Track;
import uk.co.wheep.tracktoaster.util.Util;

/**
 * A BroadcastReceiver for intents sent by the LG Optimus 4X P880 music player
 *
 * @see AbstractPlayStatusReceiver
 *
 * @author kshahar <shahar.kosti@gmail.com>
 * @author icass
 * @since 1.4.4
 */
public class LgOptimus4xReceiver extends AbstractPlayStatusReceiver {

    static final String APP_PACKAGE = "com.lge.music";
    static final String APP_NAME = "LG Music Player";

    static final String ACTION_LGE_METACHANGED = "com.lge.music.metachanged";
    static final String ACTION_LGE_PAUSERESUME = "com.lge.music.playstatechanged";
    static final String ACTION_LGE_STOP = "com.lge.music.endofplayback";

    static final String TAG = "SLSLgOptimus4xReceiver";

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle) {

        if (action.equals(ACTION_LGE_STOP)) {
            setPlaying(Boolean.FALSE);
            Log.d(TAG, "Setting state to COMPLETE");
            return;
        }

        if (action.equals(ACTION_LGE_METACHANGED)) {
            setPlaying(Boolean.TRUE);
            Log.d(TAG, "Setting state to START");
        } else if (action.equals(ACTION_LGE_PAUSERESUME)) {
            setPlaying(bundle.getBoolean("playing"));
            Log.d(TAG, "Setting state to " + bundle.getBoolean("playing"));
        }

        // Track data
        setTrack(new Track(bundle.getString("artist"),bundle.getString("album"),bundle.getString("track")));

    }
}
