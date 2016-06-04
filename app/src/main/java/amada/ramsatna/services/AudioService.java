package amada.ramsatna.services;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Hamza on 31/05/2016.
 */
public class AudioService {

    private static final String AUDIO_ENDPOINT = "http://3on.ae/clients/DM/audio/";
    private static final String TAG = "AudioService";
    private URL url;

    public void getAudio(final String id) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String endpoint = AUDIO_ENDPOINT + id + ".caf";
                    url = new URL(endpoint);
                    InputStream is = url.openStream();

                    MediaPlayer player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(endpoint);
                    player.prepare();
                    player.start();

                  /*  Scanner scan = new Scanner(is);
                    while (scan.hasNext()) {
                        Log.d(TAG, "run: " + scan.next());
                    } */

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }

}
