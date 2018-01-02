package uk.co.wheep.tracktoaster.receiver;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import uk.co.wheep.tracktoaster.util.Track;

public class SEMCMusicReceiver extends BuiltInMusicAppReceiver {

    private static final String TAG = "SEMCMusicReceiver";

    static final String APP_PACKAGE = "com.sonyericsson.music";
    static final String ACTION_SEMC_STOP_LEGACY = "com.sonyericsson.music.playbackcontrol.ACTION_PLAYBACK_PAUSE";
    static final String ACTION_SEMC_STOP = "com.sonyericsson.music.playbackcontrol.ACTION_PAUSED";
    static final String ACTION_SEMC_METACHANGED = "com.sonyericsson.music.metachanged";


    public SEMCMusicReceiver() {
        super(ACTION_SEMC_STOP, APP_PACKAGE, "Sony Ericsson Music Player");
    }

    /**
     * @param action the received action
     * @Override /**
     * Checks that the action received is either the one used in the
     * newer Sony Xperia devices or that of the previous versions
     * of the app.
     * @return true when the received action is a stop action, false otherwise
     * <p>
     * protected boolean isStopAction(String action) {
     * return ;
     * }
     */

    @Override
    protected void parseIntent(Context ctx, String action, Bundle bundle) throws IllegalArgumentException {
        super.parseIntent(ctx, action, bundle);
    }

    @Override
    Track parseTrack(Bundle bundle) {

        Log.d(TAG, "Will read data from SEMC intent");

        String ar = bundle.getString("ARTIST_NAME");
        String al = bundle.getString("ALBUM_NAME");
        String tr = bundle.getString("TRACK_NAME");

        if (ar == null || al == null || tr == null) {
            throw new IllegalArgumentException("null track values");
        }

        return new Track(ar, al, tr);
    }

}
