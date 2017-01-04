package com.jhinchley.texttospeechtest;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private Button btnSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this,this);
        btnSpeak= (Button) findViewById(R.id.btnSpeak);


    }

    @Override
    protected void onDestroy() {
        //dont forget to shutdown tts
        if (tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","LANG not supported");
            }
            btnSpeak.setEnabled(true);
            tts.speak(getString(R.string.jarvisSpeech),TextToSpeech.QUEUE_FLUSH,null);
        }
    }
    protected void speak(){

    }
}
