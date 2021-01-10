package live.autoplanter.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    //Creates Buttons objects
    Button btnRegister, btnLogin;
    //Creates EditText objects
    EditText etEmail,etPassword;
    //Creates Strings
    String stringEmail,stringPassword;
    //Creates a DatabaseReference object
    DatabaseReference myRef;
    //Creates a FirebaseAuth object
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Sets up page of app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes objects and variables
        fAuth = FirebaseAuth.getInstance();
        btnRegister = (Button) findViewById(R.id.main_sign_up_button);
        btnLogin = (Button) findViewById(R.id.main_log_in_button);

        //Redirects the user so they can register a new account
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, live.autoplanter.firebase.LogIn.class);
                startActivity(intent);
            }
        });

        //Redirects the user so they can register a new account
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, live.autoplanter.firebase.Register.class);
                startActivity(intent);
            }
        });
    }
}