package live.autoplanter.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class client extends AppCompatActivity{
    //Creates objects/variables
    Button btnadd;
    EditText etItems,etAmount;
    String value, items, amount;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //Sets up page of app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientpage);
        //Initializes objects
        btnadd = (Button) findViewById(R.id.delivery);
        etItems = (EditText) findViewById(R.id.item);
        etAmount = (EditText) findViewById(R.id.amount);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }

        //Adds items to noSQL table
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sets variables
                amount = etAmount.getText().toString();
                items = etItems.getText().toString();
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference(value);
                myRef.child("Items").child(amount + " " + items).setValue("Individual");
            }
        });
    }
}

