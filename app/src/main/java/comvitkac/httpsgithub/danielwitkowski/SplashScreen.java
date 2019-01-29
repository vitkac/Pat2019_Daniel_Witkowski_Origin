package comvitkac.httpsgithub.danielwitkowski;
import java.lang.*;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {


    boolean stopScreen=false;
    @Override
    public void onBackPressed() {
        stopScreen=true;
    }
    SessionManagment session;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       session =new SessionManagment(getApplicationContext());
       if(session.isLoggedIn()){
           Intent intent = new Intent(SplashScreen.this, MainMenuActivity.class);
           startActivity(intent);
           finish();

       }else{
            setContentView(R.layout.activity_splash_screen);
            new Handler().postDelayed(new Runnable() {

            @Override
            public void run(){
                    if (stopScreen) {

                    }
                    else{

                        Intent gotoLogin = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(gotoLogin);
                        finish();
                    }
               }
             },5000);
        }
    }
   }
