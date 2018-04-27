package com.example.tj.cafeteriaadmin;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddFoodActivity extends AppCompatActivity {

    private static final int GALLREQ = 1;
    Spinner venueSpinner, subVenueSpinner, categoriesSpinner, categories2Spinner, typeFoodSpinner;
    EditText name, desc, price;
    Button add, addImage;
    Enteries entries;
    ImageView foodImage;
    StorageReference storageReference = null;
    DatabaseReference mRoot;
    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        venueSpinner = (Spinner) findViewById(R.id.venueSpinner);
        subVenueSpinner = (Spinner) findViewById(R.id.sub_venue_spinner);
        categoriesSpinner = (Spinner) findViewById(R.id.categorySpinner);
        categories2Spinner = (Spinner) findViewById(R.id.havmorCatSpinner);
        name = (EditText) findViewById(R.id.nameText);
        desc = (EditText) findViewById(R.id.descText);
        price = (EditText) findViewById(R.id.priceText);
        typeFoodSpinner = (Spinner) findViewById(R.id.typeSpinner);
        add = (Button) findViewById(R.id.addButton);
        addImage = (Button) findViewById(R.id.imageAddButton);

        storageReference = FirebaseStorage.getInstance().getReference();
        mRoot = FirebaseDatabase.getInstance().getReference("venue");


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        venueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    subVenueSpinner.setVisibility(View.VISIBLE);
                } else {
                    subVenueSpinner.setVisibility(View.INVISIBLE);
                    categoriesSpinner.setVisibility(View.VISIBLE);
                    categories2Spinner.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        subVenueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    categories2Spinner.setVisibility(View.VISIBLE);
                    categoriesSpinner.setVisibility(View.INVISIBLE);

                } else {
                    categories2Spinner.setVisibility(View.INVISIBLE);
                    categoriesSpinner.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addAllItems();
            }
        });
    }

    public void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLREQ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLREQ && resultCode == RESULT_OK) {
            uri = data.getData();
            foodImage = (ImageView) findViewById(R.id.addImageView);
            foodImage.setImageURI(uri);

        }


    }

    public void addAllItems() {
        final String venueSelected = venueSpinner.getSelectedItem().toString();
        final String subVenueSelected = subVenueSpinner.getSelectedItem().toString();
        final String category1Selected = categoriesSpinner.getSelectedItem().toString();
        final String category2Selected = categories2Spinner.getSelectedItem().toString();
        final String itemName = name.getText().toString().trim();
        final String itemDesc = desc.getText().toString().trim();
        final String itemPrice = price.getText().toString().trim();
        final String itemType = typeFoodSpinner.getSelectedItem().toString();


        if (!TextUtils.isEmpty(itemName) || !TextUtils.isEmpty(itemDesc) || !TextUtils.isEmpty(itemPrice)) {
            if (venueSelected.equals("Square One")) {
                if (subVenueSelected.equals("Havmor")) {

                    StorageReference filepath = storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            String imageUrl = downloadUrl.toString();
                            entries = new Enteries(itemName, itemDesc, itemPrice, imageUrl);
                            mRoot.child(venueSelected).child(subVenueSelected).child(category2Selected).child(itemName).setValue(entries);
                            name.setText(" ");
                            desc.setText(" ");
                            price.setText(" ");
                            categoriesSpinner.setVisibility(View.VISIBLE);
                            categories2Spinner.setVisibility(View.INVISIBLE);
                            subVenueSpinner.setVisibility(View.INVISIBLE);

                        }
                    });
                } else {
                    StorageReference filepath = storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            String imageUrl = downloadUrl.toString();
                            entries = new Enteries(itemName, itemDesc, itemPrice, itemType, imageUrl);
                            mRoot.child(venueSelected).child(subVenueSelected).child(category1Selected).child(itemName).setValue(entries);
                            name.setText(" ");
                            desc.setText(" ");
                            price.setText(" ");

                            categoriesSpinner.setVisibility(View.VISIBLE);
                            categories2Spinner.setVisibility(View.INVISIBLE);
                            subVenueSpinner.setVisibility(View.INVISIBLE);

                        }
                    });
                }
            } else {

                StorageReference filepath = storageReference.child(uri.getLastPathSegment());
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        String imageUrl = downloadUrl.toString();
                        entries = new Enteries(itemName, itemDesc, itemPrice, itemType, imageUrl);
                        mRoot.child(venueSelected).child(category1Selected).child(itemName).setValue(entries);
                        name.setText(" ");
                        desc.setText(" ");
                        price.setText(" ");
                        foodImage.setVisibility(View.GONE);
                        categoriesSpinner.setVisibility(View.VISIBLE);
                        categories2Spinner.setVisibility(View.INVISIBLE);
                        subVenueSpinner.setVisibility(View.INVISIBLE);

                    }
                });
            }


            Toast.makeText(this, "Item Added", Toast.LENGTH_LONG).show();

        }
    }
}

