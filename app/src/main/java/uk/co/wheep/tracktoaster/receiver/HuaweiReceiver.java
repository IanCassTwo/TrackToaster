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
 * A BroadcastReceiver for intents sent by the Huawei Music Player.
 *
 * @see AbstractPlayStatusReceiver
 *
 * @author HumbleBeeBumbleBee HumbleBeeBumbleBeeDebugs@gmail.com
 * @author icass
 * @since 1.5.0
 */


public class HuaweiReceiver extends AbstractPlayStatusReceiver {

    static final String APP_NAME = "Huawei Music";
    static final String TAG = "HuaweiReceiver";

    static final class BroadcastTypes {
        static final String APP_PACKAGE = "com.android.mediacenter";
        static final String METADATA_CHANGED = APP_PACKAGE + ".metachanged";
        static final String PLAYSTATE_CHANGED = APP_PACKAGE + ".playstatechanged";
        static final String ACTION_STOP = APP_PACKAGE + ".playbackcomplete";
    }

    static private Track track = null;

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle) {

        // Play state
        if (action.equals(BroadcastTypes.ACTION_STOP)) {
            setPlaying(Boolean.FALSE);
        } else {
            boolean playing = bundle.getBoolean("isPlaying", false);
            if (playing) {
                setPlaying(Boolean.TRUE);
                Log.d(TAG, "Setting state to RESUME");
            } else {
                setPlaying(Boolean.FALSE);
                Log.d(TAG, "Setting state to PAUSE");
            }

            // Track data
            setTrack(new Track(bundle.getString("artist"), bundle.getString("album"), bundle.getString("track")));

            Log.d(TAG,
                    bundle.getString("artist") + " - "
                            + bundle.getString("track") + " ("
                            + bundle.getLong("duration", 0) + ")");
        }
    }
}
