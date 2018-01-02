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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import uk.co.wheep.tracktoaster.service.ToastService;
import uk.co.wheep.tracktoaster.util.CurrentTrack;
import uk.co.wheep.tracktoaster.util.Track;

/**
 * Base class for play status receivers.
 *
 * The receivers are responsible for getting track data, recording play status
 * and notifying the ToastService if we're playing.
 *
 * @see SLSAPIReceiver
 * @see ScrobbleDroidMusicReceiver
 * @see AndroidMusicReceiver
 * @see HeroMusicReceiver
 *
 * @author tgwizard
 * @author icass
 * @since 1.0.1
 */
public abstract class AbstractPlayStatusReceiver extends BroadcastReceiver {

    private static final String TAG = "AbstractPlayStatusRecv";

    private Intent mService = null;
    //private MusicAPI mMusicAPI = null;
    private Track mTrack = null;
    private Boolean isPlaying = Boolean.FALSE;
    final public static Uri sArtworkUri = Uri
            .parse("content://media/external/audio/albumart");

    /**
     public static void dumpIntent(Bundle bundle){
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

    void setPlaying(Boolean isPlaying) {this.isPlaying = isPlaying;}
    public Boolean isPlaying() {return isPlaying;}

    @Override
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();


        //dumpIntent(bundle);

        Log.e(TAG, "Action received was: " + action);

        // check to make sure we actually got something
        if (action == null) {
            Log.w(TAG, "Got null action");
            return;
        }

        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }

        try {
            parseIntent(context, action, bundle); // might throw

            // set current track
            if (mTrack != null)
                CurrentTrack.INSTANCE.setTrack(mTrack);

            // Toast if track is playing
            if (isPlaying()) {
                showToast(context);
            }

        } catch (IllegalArgumentException e) {
            Log.i(TAG, "Got a bad track from: "
                    + ", ignoring it (" + e.getMessage() + ")");
        }
    }

    void showToast(Context context) {

        mService = new Intent(context, ToastService.class);
        mService.setAction(ToastService.ACTION_TOAST);
        context.startService(mService);

    }


    /**
     * Sets the {@link Track} for this scrobble request
     *
     * @param track
     *            the Track for this scrobble request
     */
    protected final void setTrack(Track track) {
        mTrack = track;
    }

    /**
     * Parses the API / music app specific parts of the received broadcast.
     * @see #setTrack(Track)
     *
     * @param ctx
     *            to be able to create {@code MusicAPIs}
     * @param action
     *            the action/intent used for this scrobble request
     * @param bundle
     *            the data sent with this request
     * @throws IllegalArgumentException
     *             when the data received is invalid
     */
    protected abstract void parseIntent(Context ctx, String action,
                                        Bundle bundle) throws IllegalArgumentException;

}
