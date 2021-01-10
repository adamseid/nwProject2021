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

/**
 * Created by Mitch on 2016-06-04.
 */
public class Register extends AppCompatActivity {
    //Creates objects/variables
    EditText etName, etEmail, etPassword, etAddress, etState, etCountry, etPostalCode;
    String name, email, password, address, state, country, postalCode;
    Button btnRegister;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Sets up page of app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);
        //Initializes objects
        etName = (EditText) findViewById(R.id.etNewName);
        etEmail = (EditText) findViewById(R.id.logInEmail);
        etPassword = (EditText) findViewById(R.id.logInPassword);
        etAddress = (EditText) findViewById(R.id.etNewAdress);;
        etState = (EditText) findViewById(R.id.etNewState);;
        etCountry= (EditText) findViewById(R.id.etNewCountry);;
        etPostalCode = (EditText) findViewById(R.id.etNewPostalCode);;
        btnRegister = (Button) findViewById(R.id.main_log_in_button);
        fAuth = FirebaseAuth.getInstance();


        //This method starts when the user clicks registration
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initializes variables
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                address = etAddress.getText().toString();
                state = etState.getText().toString();
                country = etCountry.getText().toString();
                postalCode = etPostalCode.getText().toString();
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            String uuid = fAuth.getCurrentUser().getUid();
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference(uuid);
                            myRef.child("info").child("Username").setValue(name);
                            myRef.child("info").child("Address").setValue(address);
                            myRef.child("info").child("Province or State").setValue(state);
                            myRef.child("info").child("Country").setValue(country);
                            myRef.child("info").child("Postal Code").setValue(postalCode);

                            Intent intent = new Intent(Register.this, intermediate.class);
                            intent.putExtra("key",uuid);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Register.this, "ERROR " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
