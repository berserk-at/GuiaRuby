package at.berserk.lib;

import android.app.Application;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import java.util.Locale;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Aplicacao extends Application {
    public void definirIdioma(String idioma){
        Locale locale=new Locale(idioma);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
    public void definirIdioma(String idioma, String local){
        Locale locale=new Locale(idioma, local);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
    public boolean isIdioma(String idioma){
        Locale locale = Locale.getDefault();
        if (locale.getLanguage().equalsIgnoreCase(idioma)){
            return(true);
        } else {
            return(false);
        }
    }
    MediaPlayer mp;
    public void playSom(int som){
        try{
            if (mp!=null){
                if (mp.isPlaying()){
                    mp.stop();
                }
                mp.release();
            }
        } catch(Exception ex){

        }
        mp = MediaPlayer.create(this, som);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                mp = null;
            }
        });
        mp.start();
    }
}
