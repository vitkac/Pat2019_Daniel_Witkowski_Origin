package comvitkac.httpsgithub.danielwitkowski;
import java.lang.*;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    int stop=0;//zmienna pomocnicza do zatrzymania ekranu startowego

    @Override
    //przeciazenie funcji uruchamianej po nacisnieciu przycisku Back
    public void onBackPressed() { stop=1;}

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            //Funkcja uruchamiajaca ekran glowny
            public void run(){
                if (stop == 1) { }
                else{
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
            },5000);
    }

}
