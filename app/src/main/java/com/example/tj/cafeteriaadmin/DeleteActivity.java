package com.example.tj.cafeteriaadmin;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class DeleteActivity extends AppCompatActivity {
    EditText nameVenue,itemToBeDeleted;
    Button deleteFoodItem;
    private static final String TAG = "MyActivity";

    DatabaseReference deleteRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);




         deleteFoodItem = (Button) findViewById(R.id.deleteButton);

        deleteFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });




    }
    public void deleteItem(){

        nameVenue = (EditText) findViewById(R.id.deleteVenueName);
        itemToBeDeleted = (EditText) findViewById(R.id.item);
         String deleteItemVenue = nameVenue.toString().trim();
         String deleteItemName = itemToBeDeleted.toString().trim();

        if (!TextUtils.isEmpty(deleteItemName)){
            Log.i(TAG,"I entered here again");
            if(deleteItemVenue.equals("Lapinoz")){
                Log.i(TAG,"I entered here 2nd time");
                deleteRef = FirebaseDatabase.getInstance().getReference("venue").child("Square One").child("Lapinoz").child("Food").child("Garden Special");
                deleteRef.removeValue();
            }
        }
    }
}
