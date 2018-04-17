/**
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
 * A BroadcastReceiver for intents sent by the Spotify Music Player.
 *
 * @see AbstractPlayStatusReceiver
 *
 * @author HumbleBeeBumbleBee HumbleBeeBumbleBeeDebugs@gmail.com
 * @author icass
 * @since 1.4.8
 * @helpers @metanota @inverse
 */

// Closing they Spotify UI can cause scrobbling to stop. Not our fault I think.
// (Lollipop 5.0 SSG S3) (Spotify 2.6.0.813) (SLS v1.4.9)

public class SpotifyReceiver extends AbstractPlayStatusReceiver {

    static final String APP_NAME = "Spotify";
    static final String TAG = "SpotifyReceiver";

    static final class BroadcastTypes {
        static final String APP_PACKAGE = "com.spotify.music";
    }

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle) {

        if (bundle.containsKey("track")) {

            // Play State
            setPlaying(Boolean.TRUE);

            // Track data
            setTrack(new Track(bundle.getString("albumartist"), bundle.getString("artist"),bundle.getString("album"),bundle.getString("track")));
        }
        if (bundle.containsKey("playing")) {
            setPlaying(bundle.getBoolean("playing"));
        }
    }
}
