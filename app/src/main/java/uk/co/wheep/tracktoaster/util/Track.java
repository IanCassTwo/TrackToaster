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


package uk.co.wheep.tracktoaster.util;

/**
 * Simple "immutable" structure that holds information about a track. The only
 * way to create a track is through the {@link Builder}.
 * <p>
 *
 * @author tgwizard
 * @author icass
 * @since 0.9
 */
public class Track {

    private String mArtist;
    private String mAlbum;
    private String mTrack;

    public Track(String artist, String album, String track) {
        mArtist = artist;
        mAlbum = album;
        mTrack = track;
    }

    public String getArtist() {
        return mArtist;
    }
    public String getAlbum() {
        return mAlbum;
    }
    public String getTrack() {
        return mTrack;
    }

    @Override
    public String toString() {
        return "Track [mTrack="
                + mTrack + ", mArtist=" + mArtist + ", mAlbum=" + mAlbum
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mAlbum == null) ? 0 : mAlbum.hashCode());
        result = prime * result + ((mArtist == null) ? 0 : mArtist.hashCode());
        result = prime * result + ((mTrack == null) ? 0 : mTrack.hashCode());
        return result;
    }

    /**
     * Only checks artist, album and track strings, which
     * means that tracks sent to ScrobblingService can be properly compared.
     *
     * Temporary fix for apps with multiple broadcasts. (usually Android Music Player and SLS API.
     * SLS Receiver & Builtin Music Player.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Track other = (Track) obj;
/**        if (mAlbum == null) {
 if (other.mAlbum != null)
 return false;
 } else if (!mAlbum.equals(other.mAlbum))
 return false;
 */
        if (mArtist == null) {
            if (other.mArtist != null)
                return false;
        } else if (!mArtist.equals(other.mArtist))
            return false;
/**        if (mMusicAPI == null) {
 if (other.mMusicAPI != null)
 return false;
 } else if (!mMusicAPI.equals(other.mMusicAPI))
 return false;
 */
        if (mTrack == null) {
            if (other.mTrack != null)
                return false;
        } else if (!mTrack.equals(other.mTrack))
            return false;
        return true;
    }
}
