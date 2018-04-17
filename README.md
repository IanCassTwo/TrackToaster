# Track Toaster

Track Toaster is a simple app that shows a popup "toast" on the screen showing you which track has just started playing. I use this on my in-car tablet install. I was unsatisfied with other solutions that used Android media database or online resources to obtain album art. I wanted something that would just work. This solution works every time, however, you do need to be disciplined in how you create your directory of audio files.

Your audio directory should be laid out like one of the following :-

     <artist>-<album>/albumart.jpg    
     <artist>/<album>/albumart.jpg
     <albumartist>-<album>/albumart.jpg
     <albumartist>/<album>/albumart.jpg
     Various Artists-<album>/albumart.jpg
     Various Artists/<album>/albumart.jpg
     Various-<album>/albumart.jpg
     Various/<album>/albumart.jpg
     <album>/albumart.jpg
     
     

Your media player will almost certainly use the ID3 information within the audio file to tell TrackToaster what's playing so it goes without saying, your tags should match your directory names. I personally use [Mp3Tag](https://www.mp3tag.de/en/) on Windows to manage my ID3 tags, to create the correct directory structure and to export albumart.jpg.

When you first start the program, make sure to go into the settings and choose your media directory. Currently it only supports one directory. Feel free to fork & modify if you like.

This is based on code from "Simple Last.fm Scrobbler (SLS)". Theoretically, it should support the same media players as SLS. I can only promise that it works for Poweramp.

## Credits

All of the code is open source, released under [Apache License 2.0](LICENSE.md).

 * A lot of the code is written by Adam Renberg (Copyright 2009-2015) from the SLS project
 * This project is written by Ian Cass (Copyright 2017) using a lot of the SLS code

I use copyright here only in the sense of proper attribution. Do whatever you want with the code (as long as the licenses are followed).

### Contributors (from SLS, for code that's still in this project)
 * Adam Renberg, [github.com/tgwizard](https://github.com/tgwizard), main author
 * Argoday, [github.com/argoday](https://github.com/argoday), code fixes
 * inverse [github.com/inverse](https://github.com/inverse), core contributor
 * HumbleBeeBumbleBee, [github.com/HumbleBeeBumbleBee](https://github.com/HumbleBeeBumbleBee), core contributor
 * Sean O'Neil, [github.com/SeanPONeil](https://github.com/SeanPONeil), android 4.0
 * Andrew Thomson, support for MIUI Music Player
 * Mark Gillespie, support for Sony/Sony Ericsson/Xperia phones
 * Dmitry Kostjuchenko, support for Neutron Music Player
 * stermi, support for Rdio
 * Joseph Cooper, [github.com/grodin](https://github.com/grodin), support for JRTStudio Android Music Player
 * Shahar, [github.com/kshahar](https://github.com/kshahar), support for LG Music Player
 * theli-ua, [github.com/theli-ua](https://github.com/theli-ua), support for Amazon Music
 * metanota, [github.com/metanota](https://github.com/metanota), support for PlayerPro, bug fixes
 * Joel Teichroeb, [github.com/klusark](https://github.com/klusark), bug fixes
 * bryansills [github.com/bryansills](https://github.com/bryansills), Eclipse to Android Studio, new icon, Material Design

A complete list of SLS [contributors](https://github.com/tgwizard/sls/graphs/contributors)
 
### Test device contributors

 * Dmitry Paskal, [github.com/paskal](https://github.com/paskal)
 * Iļja Gubins, [https://github.com/the-lay](https://github.com/the-lay)

Several people have also contributed with comments, suggestions and [issues](https://github.com/tgwizard/sls/issues/).
