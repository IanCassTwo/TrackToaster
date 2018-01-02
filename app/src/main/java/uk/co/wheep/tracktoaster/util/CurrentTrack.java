package uk.co.wheep.tracktoaster.util;

/**
 * Created by ian on 26/12/2017.
 *
 * Responsible for containing details of the currently playing track
 *
 * Set by the receivers, read by the ToastService
 *
 * @author icass
 */

public class CurrentTrack {
    public final static CurrentTrack INSTANCE = new CurrentTrack();
    private Track track;

    private CurrentTrack() {}

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}
