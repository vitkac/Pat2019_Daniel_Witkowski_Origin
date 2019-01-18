package comvitkac.httpsgithub.danielwitkowski;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button logout = findViewById(R.id.Logout_button);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent returnLogin = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(returnLogin);
                finish();
            }
        });
    }
}
