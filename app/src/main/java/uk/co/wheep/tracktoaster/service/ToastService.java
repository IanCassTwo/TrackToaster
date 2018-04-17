package uk.co.wheep.tracktoaster.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import uk.co.wheep.tracktoaster.util.AppSettings;
import uk.co.wheep.tracktoaster.util.CurrentTrack;
import uk.co.wheep.tracktoaster.util.Track;

/**
 * Created by ian on 26/12/2017.
 *
 * This service is responsible for announcing the current track provided
 * we've got metadata and we've not already announced this track
 *
 * @author icass
 */

public class ToastService extends Service {

    private static final String TAG = "ToastService";
    public static final String ACTION_TOAST = "uk.co.wheep.tracktoaster.service.toast";

    private AppSettings settings;
    private Track mTrack = null;
    private Boolean toasted = Boolean.FALSE;

    @Override
    public void onCreate() {
        settings = new AppSettings(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent i, int flags, int startId) {

        String action = i.getAction();
        if (action.equals(ACTION_TOAST)) {

            // Update current track details if they're different to what we have
            Track track = CurrentTrack.INSTANCE.getTrack();
            if (track != null && !track.equals(mTrack)) {
                mTrack = track;
                toasted = Boolean.FALSE;
            }

            // Make sure we don't try to toast if we don't have track details or if we've already toasted
            if (mTrack != null && !toasted)
                doToast();
        }
        return Service.START_STICKY;
    }

    void doToast() {

        Uri albumArt = findAlbumArt(mTrack.getArtist(), mTrack.getAlbum());

        if (albumArt == null) {
            albumArt = findAlbumArt(mTrack.getAlbumArtist(), mTrack.getAlbum());
        }

        if (albumArt == null) {
            albumArt = findAlbumArt("Various Artists", mTrack.getAlbum());
        }

        if (albumArt == null) {
            albumArt = findAlbumArt("Various", mTrack.getAlbum());
        }

        if (albumArt == null) {
            albumArt = findAlbumArt(mTrack.getAlbum());
        }

        String text = "" + mTrack.getArtist() + " " + mTrack.getTrack();
        Log.d(TAG, "Toasting " + text);

        LayoutInflater mInflater = LayoutInflater.from(this);
        View layout = mInflater.inflate(uk.co.wheep.tracktoaster.R.layout.toast, null);

        TextView trackView = layout.findViewById(uk.co.wheep.tracktoaster.R.id.track);
        trackView.setText(mTrack.getTrack());

        TextView albumView = layout.findViewById(uk.co.wheep.tracktoaster.R.id.album);
        albumView.setText(mTrack.getAlbum());

        TextView artistView = layout.findViewById(uk.co.wheep.tracktoaster.R.id.artist);
        artistView.setText(mTrack.getArtist());

        if (albumArt != null) {
            ImageView image = layout.findViewById(uk.co.wheep.tracktoaster.R.id.albumArt);
            image.setImageURI(albumArt);
        }

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);

        if (settings.getLongToast()) {
            Log.d(TAG, "Long toast");
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            Log.d(TAG, "Short toast");
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setView(layout);
        toast.show();
        toasted = Boolean.TRUE;
    }

    protected Uri findAlbumArt(String album) {

        // Look for albumart.jpg artist-album
        Log.d(TAG, "Looking for " + settings.getAlbumsDir() + "/" + album + "/albumart.jpg");
        File file = new File(settings.getAlbumsDir() + "/" + album + "/albumart.jpg");
        if (file.exists()) {
            Log.d(TAG, "Found " + settings.getAlbumsDir() + "/" + album + "/albumart.jpg");
            return Uri.fromFile(file);
        }
        Log.d(TAG, "Didn't find albumart.jpg");

        return null;
    }

    protected Uri findAlbumArt(String artist, String album) {

        // Look for albumart.jpg artist-album
        Log.d(TAG, "Looking for " + settings.getAlbumsDir() + "/" + artist + " - " + album + "/albumart.jpg");
        File file = new File(settings.getAlbumsDir() + "/" + artist + " - " + album + "/albumart.jpg");
        if (file.exists()) {
            Log.d(TAG, "Found " + settings.getAlbumsDir() + "/" + artist + " - " + album + "/albumart.jpg");
            return Uri.fromFile(file);
        } else {
            Log.d(TAG, "Looking for " + settings.getAlbumsDir() + "/" + artist + "/" + album + "/albumart.jpg");
            file = new File(settings.getAlbumsDir() + "/" + artist + "/" + album + "/albumart.jpg");
            if (file.exists()) {
                Log.d(TAG, "Found " + settings.getAlbumsDir() + "/" + artist + "/" + album + "/albumart.jpg");
                return Uri.fromFile(file);
            }
        }
        Log.d(TAG, "Didn't find albumart.jpg");

        return null;
    }

}

