package live.autoplanter.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class intermediate extends AppCompatActivity{
    Button delivery, client;
    String value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Sets up page of app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermediate);
        //Initializes objects
        delivery = (Button) findViewById(R.id.delivery);
        client = (Button) findViewById(R.id.client);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(intermediate.this, live.autoplanter.firebase.LogIn.class);
                intent.putExtra("key",value);
                startActivity(intent);
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(intermediate.this, live.autoplanter.firebase.LogIn.class);
                intent.putExtra("key",value);
                startActivity(intent);
            }
        });
    }
}
