package live.autoplanter.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity {

    //Creates objects/variables
    EditText etEmail, etPassword;
    String email, password;
    Button btnRegister;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Sets up page of app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //Initializes objects
        etEmail = (EditText) findViewById(R.id.logInEmail);
        etPassword = (EditText) findViewById(R.id.logInPassword);
        btnRegister = (Button) findViewById(R.id.main_log_in_button);
        fAuth = FirebaseAuth.getInstance();


        //This method starts when the user clicks registration
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                // Logs user in to their account
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uuid = fAuth.getUid();
                            Toast.makeText(LogIn.this,"Logged In",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogIn.this, intermediate.class);
                            intent.putExtra("key",uuid);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LogIn.this, "ERROR " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

