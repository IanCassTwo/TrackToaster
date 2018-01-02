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


package uk.co.wheep.tracktoaster;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import uk.co.wheep.tracktoaster.util.Util;

public class AboutDialog {
    @SuppressWarnings("unused")
    private static final String TAG = "AboutDialog";
    private final Context mCtx;

    public AboutDialog(Context ctx) {
        super();
        this.mCtx = ctx;
    }

    public void show() {
        final LayoutInflater factory = LayoutInflater.from(mCtx);

        View dialogView = factory.inflate(R.layout.about, null);

        innerUpdate(dialogView);

        AlertDialog.Builder adBuilder = new AlertDialog.Builder(mCtx).setTitle(
                R.string.about).setIcon(android.R.drawable.ic_dialog_info).setView(
                dialogView).setNegativeButton(R.string.close,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        adBuilder.show();
    }

    private void innerUpdate(View dialogView) {
        TextView appName = dialogView.findViewById(R.id.app_name);
        TextView author = dialogView.findViewById(R.id.author);
        TextView license = dialogView.findViewById(R.id.license);
        TextView musicApps = dialogView.findViewById(R.id.supported_musicapps);
        TextView website = dialogView.findViewById(R.id.website);
        TextView issues = dialogView.findViewById(R.id.issues);
        TextView tShoot = dialogView.findViewById(R.id.tShoot);

        // app name & version
        String appText = Util.getAppName(mCtx, mCtx.getPackageName()) + " v"
                + Util.getAppVersionName(mCtx, mCtx.getPackageName());
        appName.setText(appText);

        // author
        author.setText(R.string.by_adam);

        // license
        license.setText(R.string.license);

          // supported music apps
        musicApps.setText(mCtx.getString(R.string.supported_apps, mCtx.getString(R.string.supported_musicapps)));

        // website
        website.setText(mCtx.getString(R.string.website, mCtx.getString(R.string.website_url)));

        // email
        issues.setText(mCtx.getString(R.string.issues, mCtx.getString(R.string.issues_url)));

        // trouble shooting
        tShoot.setText(mCtx.getString(R.string.troubShoot, mCtx.getString(R.string.trouble_shoot)));
    }
}
