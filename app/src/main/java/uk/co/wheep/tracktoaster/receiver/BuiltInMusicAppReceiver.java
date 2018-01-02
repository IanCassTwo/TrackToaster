/**
 * This file is part of Simple Last.fm Scrobbler.
 * 
 *     http://code.google.com/p/a-simple-lastfm-scrobbler/
 * 
 * Copyright 2011 Simple Last.fm Scrobbler Team
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.wheep.tracktoaster.receiver;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.math.BigDecimal;

import uk.co.wheep.tracktoaster.util.Track;
import uk.co.wheep.tracktoaster.util.Util;

/**
 * A BroadcastReceiver for intents sent by music apps such as Android Music and
 * Hero Music. Specialized classes inherit from this class to deal with the
 * small differences.
 * 
 * @see AndroidMusicReceiver
 * @see HeroMusicReceiver
 * @author tgwizard
 * @author icass
 * @since 1.2.7
 */
public abstract class BuiltInMusicAppReceiver extends
	AbstractPlayStatusReceiver {

	private static final String TAG = "BuiltInMusicAppReceiver";

	static final int NO_AUDIO_ID = -1;

	final String stop_action;

	final String app_package;
	final String app_name;


	public BuiltInMusicAppReceiver(String stopAction, String appPackage,
		String appName) {
		super();
		stop_action = stopAction;
		app_package = appPackage;
		app_name = appName;
	}
	
	/**
	 * Depending on the action received decide whether it should signal a stop or not.
	 * By default, it compares it to the unique `this.stop_action`, but there might be
	 * multiple actions that cause a stop signal.
	 * 
	 * @param action	the received action
	 * @return			true when the received action is a stop action, false otherwise
	 */
	protected boolean isStopAction(String action) {
		return action.equals(stop_action);
	}

	@Override
	protected void parseIntent(Context ctx, String action, Bundle bundle)
		throws IllegalArgumentException {


		// Play State
        if (isStopAction(action)) {
            setPlaying(Boolean.FALSE);
        } else if (action.equals("com.android.music.playbackcomplete")){
            setPlaying(Boolean.FALSE);
        } else {
            setPlaying(Boolean.TRUE);
        }

        if(bundle.containsKey("playing")){
            setPlaying(bundle.getBoolean("playing"));
        }

        // Track data
		setTrack(parseTrack(bundle));
	}

	Track parseTrack(Bundle bundle) {

		Log.d(TAG, "Will read data from intent");

		String ar = bundle.getString("artist");
		String tr = bundle.getString("track");

		if (ar == null || tr == null) {
			throw new IllegalArgumentException("null track values");
		}

        String al = "Unknown";
		if (bundle.containsKey("album")) {
			al = bundle.getString("album");
		}
		return new Track(ar, al, tr);
    }
}
