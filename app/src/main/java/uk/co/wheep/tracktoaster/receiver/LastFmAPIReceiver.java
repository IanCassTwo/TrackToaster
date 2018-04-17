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

import uk.co.wheep.tracktoaster.util.Track;
import uk.co.wheep.tracktoaster.util.Util;

/**
 * A BroadcastReceiver for the Last.fm Android API. More info at <a
 * href="https://github.com/c99koder/lastfm-android/wiki/scrobbler-interface"
 * >their dev page</a>
 *
 * @see AbstractPlayStatusReceiver
 *
 * @author tgwizard
 * @author icass
 * @since 1.3.2
 */
public class LastFmAPIReceiver extends AbstractPlayStatusReceiver {

    public static final String ACTION_LASTFMAPI_METACHANGED = "fm.last.android.metachanged";
    public static final String ACTION_LASTFMAPI_PAUSERESUME = "fm.last.android.playbackpaused";
    public static final String ACTION_LASTFMAPI_STOP = "fm.last.android.playbackcomplete";

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle) throws IllegalArgumentException {

        if (action.equals(ACTION_LASTFMAPI_METACHANGED)) {
            setPlaying(Boolean.TRUE);

            // Track data
            setTrack(new Track(bundle.getString("albumartist"), bundle.getString("artist"),bundle.getString("album"),bundle.getString("track")));

        } else if (action.equals(ACTION_LASTFMAPI_PAUSERESUME)) {
            if (bundle.containsKey("position")) {
                setPlaying(Boolean.TRUE);
            } else {
                setPlaying(Boolean.FALSE);
            }
        } else if (action.equals(ACTION_LASTFMAPI_STOP)) {
            setPlaying(Boolean.FALSE);
        }
    }

}
