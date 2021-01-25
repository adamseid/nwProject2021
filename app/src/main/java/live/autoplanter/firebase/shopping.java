package live.autoplanter.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class shopping extends AppCompatActivity {
    //Creates objects/variables
    String value;
    Button goBack, showList;
    LinearLayout linearLayout;
    ArrayList<String> arrayList;
    DatabaseReference myRef;
    Query checkItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Sets up page of app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);
        //Recieve uuid after the user has logged in or signed up
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }
        //Initializes objects
        goBack = (Button) findViewById(R.id.goBack);
        showList = (Button) findViewById(R.id.showList);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        arrayList = new ArrayList<>();
        myRef = FirebaseDatabase.getInstance().getReference(value);
        checkItem = myRef.orderByKey();

        // Display all items in client shopping list
        myRef.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.getKey();
                    System.out.println(key);
                    arrayList.add(key + " " + ds.getValue());
                    System.out.println(ds.getValue());
                }
                System.out.println(arrayList.size());
                for (int i = 0; i < arrayList.size(); i++) {
                    System.out.println(arrayList.get(i));
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText(arrayList.get(i));
                    linearLayout.addView(cb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

        // Go back to the client page
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopping.this, client.class);
                intent.putExtra("key",value);
                startActivity(intent);
            }
        });

    }

    public void showList(View v){
        myRef.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.getKey();
                    System.out.println(key);
                    arrayList.add(key + " " + ds.getValue());
                    System.out.println(ds.getValue());
                }
                System.out.println(arrayList.size());
                for (int i = 0; i < arrayList.size(); i++) {
                    System.out.println(arrayList.get(i));
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText(arrayList.get(i));
                    linearLayout.addView(cb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
    }

//
//    boolean performClick() {
//        System.out.println("HOORAY!!!!!!!");
//
//        checkItem.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String key = ds.getKey();
//                    System.out.println(key);
//                    arrayList.add(key + " " + ds.getValue());
//                    System.out.println(ds.getValue());
//                }
//                System.out.println(arrayList.size());
//                for (int i = 0; i < arrayList.size(); i++) {
//                    System.out.println(arrayList.get(i));
//                    CheckBox cb = new CheckBox(getApplicationContext());
//                    cb.setText(arrayList.get(i));
//                    linearLayout.addView(cb);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//        return true;
//    }
}
