package com.aniketvishal.hastalavista;

import android.*;
import android.Manifest;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView mProfileImage;
    private ImageView mChangeDp;
    private TextView mName,mNickName,mAbout,mHobbies,mBestMoments,mDreamJob,mPhone,mEmail;
    private String dbName="",dbNickName="",dbAbout="",dbHobbies="",dbBestMoments="",dbDreamJob="",dbImage="",dbPhone="",dbEmail="";

    private FirebaseAuth mAuth;
    private StorageReference mImageStorage;

    private ProgressDialog mMainProgress;

    private LinearLayout mShare,mMoreApps,mRateUs;

    private String userName;

    private static final int GALLEY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = getIntent().getStringExtra("Name");

        mProfileImage = (CircleImageView)findViewById(R.id.acc_dp);

        mChangeDp = (ImageView)findViewById(R.id.acc_changeImage_img);

        mName = (TextView)findViewById(R.id.acc_name);
        mName.setText(userName);
        mPhone = (TextView)findViewById(R.id.acc_phone);
        mEmail = (TextView)findViewById(R.id.acc_email);
        mNickName = (TextView)findViewById(R.id.acc_nickName);
        mAbout = (TextView)findViewById(R.id.acc_about);
        mHobbies = (TextView)findViewById(R.id.acc_hobbies);
        mBestMoments = (TextView)findViewById(R.id.acc_bestMoment);
        mDreamJob = (TextView)findViewById(R.id.acc_dreamJob);


        mShare = (LinearLayout) findViewById(R.id.title_image1);
        mRateUs = (LinearLayout) findViewById(R.id.title_image2);
        mMoreApps = (LinearLayout)findViewById(R.id.title_image);

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://play.google.com/store/apps/details?id=com.aniketvishal.hastalavista";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hasta-La-Vista: Farewell to 2k15 Batch");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        mMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("http://play.google.com/store/apps/dev?id=8793989410816601174")));

            }
        });

        mRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("https://play.google.com/store/apps/details?id=com.aniketvishal.hastalavista")));

            }
        });


        mAuth = FirebaseAuth.getInstance();
        mImageStorage = FirebaseStorage.getInstance().getReference();

        final DocumentReference mNameRef = FirebaseFirestore.getInstance().collection("Users").document(userName);
        mNameRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    dbName = documentSnapshot.getString("Name");
                    dbPhone = documentSnapshot.getString("Phone");
                    dbEmail = documentSnapshot.getString("Email");
                    dbNickName = documentSnapshot.getString("NickName");
                    dbAbout = documentSnapshot.getString("About");
                    dbHobbies = documentSnapshot.getString("Hobbies");
                    dbBestMoments = documentSnapshot.getString("BestMoments");
                    dbDreamJob = documentSnapshot.getString("DreamJob");
                    dbImage = documentSnapshot.getString("Image");

                    if (!(dbImage.equals(""))) {

                        Picasso.with(ProfileActivity.this).load(dbImage).networkPolicy(NetworkPolicy.OFFLINE)
                                .placeholder(R.drawable.profile_icon).into(mProfileImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(ProfileActivity.this).load(dbImage).placeholder(R.drawable.profile_icon).into(mProfileImage);
                            }
                        });

                    }

                    if (dbName != null && !(dbName.equals(""))) {
                        mName.setText(dbName);
                    }
                    if (dbNickName != null && !(dbNickName.equals(""))) {
                        mNickName.setText(dbNickName);
                    }
                    if (dbPhone != null && !(dbPhone.equals(""))) {
                        mPhone.setText(dbPhone);
                    }
                    if (dbEmail != null && !(dbEmail.equals(""))) {
                        mEmail.setText(dbEmail);
                    }
                    if (dbAbout != null && !(dbAbout.equals(""))) {
                        mAbout.setText(dbAbout);
                    }
                    if (dbHobbies != null && !(dbHobbies.equals(""))) {
                        mHobbies.setText(dbHobbies);
                    }
                    if (dbBestMoments != null && !(dbBestMoments.equals(""))) {
                        mBestMoments.setText(dbBestMoments);
                    }
                    if (dbDreamJob != null && !(dbDreamJob.equals(""))) {
                        mDreamJob.setText(dbDreamJob);
                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Screenshot_Activity.class);
                intent.putExtra("Image",dbImage);
                intent.putExtra(CONSTANT.textimage,R.drawable.profile_icon);
                startActivity(intent);
            }
        });

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                final AlertDialog.Builder mBulider = new AlertDialog.Builder(ProfileActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.password_layout, null);

                final TextInputLayout mTil1 = (TextInputLayout) mView.findViewById(R.id.pl_password);

                mBulider.setView(view)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Check", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String pass = mTil1.getEditText().getText().toString();

                                if (!TextUtils.isEmpty(pass)) {

                                    if (pass.equals("vista@2676")){



                                        //Inner Bilder


                                        final AlertDialog.Builder mBulider = new AlertDialog.Builder(ProfileActivity.this);
                                        View mView = getLayoutInflater().inflate(R.layout.update_profile_layout, null);

                                        final TextInputLayout mTil1 = (TextInputLayout) mView.findViewById(R.id.update_profile_til1);
                                        final TextInputLayout mTil2 = (TextInputLayout) mView.findViewById(R.id.update_profile_til2);
                                        final TextInputLayout mTil3 = (TextInputLayout) mView.findViewById(R.id.update_profile_til3);
                                        final TextInputLayout mTil4 = (TextInputLayout) mView.findViewById(R.id.update_profile_til4);
                                        final TextInputLayout mTil5 = (TextInputLayout) mView.findViewById(R.id.update_profile_til5);
                                        final TextInputLayout mTil6 = (TextInputLayout) mView.findViewById(R.id.update_profile_til6);
                                        final TextInputLayout mTil7 = (TextInputLayout) mView.findViewById(R.id.update_profile_til7);
                                        final TextInputLayout mTil8 = (TextInputLayout) mView.findViewById(R.id.update_profile_til8);

                                        mTil1.getEditText().setText(dbName);
                                        mTil2.getEditText().setText(dbNickName);
                                        mTil3.getEditText().setText(dbAbout);
                                        mTil4.getEditText().setText(dbHobbies);
                                        mTil5.getEditText().setText(dbBestMoments);
                                        mTil6.getEditText().setText(dbDreamJob);
                                        mTil7.getEditText().setText(dbPhone);
                                        mTil8.getEditText().setText(dbEmail);

                                        mBulider.setView(mView)
                                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                })
                                                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        mMainProgress = new ProgressDialog(ProfileActivity.this);
                                                        mMainProgress.setTitle("Uploading Info");
                                                        mMainProgress.setMessage("Please wait while we upload your Info");
                                                        mMainProgress.setCanceledOnTouchOutside(false);
                                                        mMainProgress.show();

                                                        String name = mTil1.getEditText().getText().toString();
                                                        String nickName = mTil2.getEditText().getText().toString();
                                                        String about = mTil3.getEditText().getText().toString();
                                                        String hobbies = mTil4.getEditText().getText().toString();
                                                        String bestMoment = mTil5.getEditText().getText().toString();
                                                        String dreamJob = mTil6.getEditText().getText().toString();
                                                        String phone = mTil7.getEditText().getText().toString();
                                                        String email = mTil8.getEditText().getText().toString();

                                                        if (!TextUtils.isEmpty(name)) {

                                                            HashMap<String, Object> user = new HashMap<>();
                                                            user.put("Name", name);
                                                            user.put("NickName", nickName);
                                                            user.put("Phone", phone);
                                                            user.put("Email", email);
                                                            user.put("About", about);
                                                            user.put("Hobbies", hobbies);
                                                            user.put("BestMoments", bestMoment);
                                                            user.put("DreamJob", dreamJob);
                                                            user.put("Image", dbImage);

                                                            DocumentReference mNameRef = FirebaseFirestore.getInstance().collection("Users").document(userName);

                                                            mNameRef.set(user, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {

                                                                        finish();
                                                                        startActivity(getIntent());

                                                                        mMainProgress.dismiss();
                                                                    } else {
                                                                        mMainProgress.dismiss();
                                                                        Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                                    }
                                                                }
                                                            });

                                                            dialogInterface.dismiss();
                                                        } else {
                                                            Toast.makeText(ProfileActivity.this, "Enter the Name", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });

                                        mBulider.setView(mView);
                                        AlertDialog dialog = mBulider.create();
                                        dialog.show();




                                    }else {
                                        Toast.makeText(ProfileActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                                    }

                                    dialogInterface.dismiss();
                                } else {
                                    dialogInterface.dismiss();
                                    Toast.makeText(ProfileActivity.this, "Enter the Password and try again.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                mBulider.setView(mView);
                AlertDialog dialog = mBulider.create();
                dialog.show();







            }
        });


        mChangeDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final AlertDialog.Builder mBulider = new AlertDialog.Builder(ProfileActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.password_layout, null);

                final TextInputLayout mTil1 = (TextInputLayout) mView.findViewById(R.id.pl_password);

                mBulider.setView(view)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Check", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String pass = mTil1.getEditText().getText().toString();

                                if (!TextUtils.isEmpty(pass)) {

                                    if (pass.equals("vista@2676")){

                                        CropImage.activity()
                                                .setGuidelines(CropImageView.Guidelines.ON)
                                                .setAspectRatio(1, 1)
                                                .start(ProfileActivity.this);

                                    }else {
                                        Toast.makeText(ProfileActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                                    }

                                    dialogInterface.dismiss();
                                } else {
                                    dialogInterface.dismiss();
                                    Toast.makeText(ProfileActivity.this, "Enter the Password and try again.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                mBulider.setView(mView);
                AlertDialog dialog = mBulider.create();
                dialog.show();
            }
        });


    }




//    private void givPer(){
//
//
//        Dexter.withActivity(this)
//                .withPermissions(
//                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
//                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.INTERNET)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        // check if all permissions are granted
//                        if (report.areAllPermissionsGranted()) {
//                            // do you work now
//                        }
//
//                        // check for permanent denial of any permission
//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            // permission is denied permenantly, navigate user to app settings
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                })
//                .onSameThread()
//                .check();
//
//
//    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mMainProgress = new ProgressDialog(ProfileActivity.this);
                mMainProgress.setTitle("Uploading Image");
                mMainProgress.setMessage("Please wait while we upload the image");
                mMainProgress.setCanceledOnTouchOutside(false);
                mMainProgress.show();

                Uri resultUri = result.getUri();

                File thumb_filePath = new File(resultUri.getPath());

                Bitmap thumb_bitmap = null;
                try {
                    thumb_bitmap = new Compressor(ProfileActivity.this)
                            .setMaxHeight(800)
                            .setMaxWidth(800)
                            .setQuality(50)
                            .compressToBitmap(thumb_filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                final byte[] thumb_byte = baos.toByteArray();

                final StorageReference filepath = mImageStorage.child("Profile_Image").child(userName + ".jpg");

                final UploadTask uploadTask = filepath.putBytes(thumb_byte);

                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()) {

                            String downloadUrl = task.getResult().getDownloadUrl().toString();

                            Map<String, Object> users = new HashMap<>();
                            users.put("Image", downloadUrl);
                            users.put("Name", dbName);
                            users.put("NickName", dbNickName);
                            users.put("Phone", dbPhone);
                            users.put("Email", dbEmail);
                            users.put("About", dbAbout);
                            users.put("Hobbies", dbHobbies);
                            users.put("BestMoments", dbBestMoments);
                            users.put("DreamJob", dbDreamJob);

                            DocumentReference mDoc = FirebaseFirestore.getInstance().collection("Users").document(userName);

                            mDoc.set(users, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {


                                        finish();
                                        startActivity(getIntent());

                                        Toast.makeText(ProfileActivity.this, "Successfully uplodad the image", Toast.LENGTH_LONG).show();

                                        mMainProgress.dismiss();

                                    } else {

                                        mMainProgress.dismiss();
                                        Toast.makeText(ProfileActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

                        } else {

                            mMainProgress.dismiss();
                            Toast.makeText(ProfileActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                });

                //Toast.makeText(getActivity(),resultUri.toString(),Toast.LENGTH_LONG).show();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

}
