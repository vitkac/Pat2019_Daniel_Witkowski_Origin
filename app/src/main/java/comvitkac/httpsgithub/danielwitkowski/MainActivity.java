package comvitkac.httpsgithub.danielwitkowski;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN=
        Pattern.compile("^+" +
                "(?=.*[0-9])"+
                "(?=.*[a-z])"+
                "(?=.*[A-Z])"+
                "(?=.*\\S+$)"+
                ".{8,}"+
                "$");
    private static final Pattern EMAIL_ADDRESS=
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                "\\@"+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                "("+
                "\\."+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                ")+");

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email= findViewById(R.id.Email_box);
        password= findViewById(R.id.Password_box);
        Button login = findViewById(R.id.Login_button);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validateLogin(email.getText().toString(),password.getText().toString());
            }
        });
    }

    private void validateLogin(String userEmail, String userPassword){
        Boolean correctEmail=true;
        Boolean correctPassword=true;
        if(userEmail.isEmpty()){
            email.setError("Filed can't be empty");
            correctEmail=false;
        }else if (!EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Please enter a valid email address");
            correctEmail=false;
        }
        if(userPassword.isEmpty()){
            password.setError("Filed can't be empty");
            correctPassword=false;
        } else if (!PASSWORD_PATTERN.matcher(userPassword).matches()){
            password.setError(getString(R.string.password_error));
            correctPassword=false;
        }
        if ((correctEmail)&&(correctPassword)){
            Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
