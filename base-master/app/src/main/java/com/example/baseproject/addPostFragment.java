package com.example.baseproject;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static android.os.Environment.getExternalStoragePublicDirectory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addPostFragment extends Fragment {


    ImageView imageView;
    TextView textVw;
    EditText editText;
    Button postBtn, galleryBtn, cameraBtn;

    private static final String TAG = "AddPostFragment: ";
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALLERY_REQUEST_CODE = 105;
    private static final int CAMERA_PERMISSION_CODE = 108;
    String timeStamp = " ";
    String imageFileName = " ";
    File image ;
    File storageDir;
    File photoFile;
    Uri photoUri;
    String filePath = " ";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addPostFragment newInstance(String param1, String param2) {
        addPostFragment fragment = new addPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        textVw = view.findViewById(R.id.content_title);
        editText = view.findViewById(R.id.description_text);
        postBtn = view.findViewById(R.id.save_post_btn); //save to timeline feed button
        galleryBtn = view.findViewById(R.id.gallery_button); //access gallery button
        imageView = view.findViewById(R.id.media_source_placeholder); //display image or video content
        cameraBtn = view.findViewById(R.id.camera_button); //access camera button

        //button to update post to timeline feed
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        //button to access camera
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakeAction();
                askCameraPermissions();
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               //startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //button to pick photo from gallery
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);

            }
        });
        return view;
    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        } else {
            dispatchPictureTakeAction();
        }

    }

    private void dispatchPictureTakeAction()  {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            photoFile = null;
            //the image will go to this file
            try {
                photoFile = createPhotoFile();

            } catch (Exception x) {
                x.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(
                        getActivity(),
                        "com.example.android.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            }
        }
    }

    //creates the image file and filename.
    private File createPhotoFile()  {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "JPEG_"+timeStamp +"_";
        image = null;
        storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        try {
            image = File.createTempFile(
                    imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir /* directory */
            );
            filePath = image.getAbsolutePath(); //created file path
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
//               Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//               imageView.setImageBitmap(bitmap);
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);

               //locate file using file path
                File f = new File(filePath);
               imageView.setImageURI(Uri.fromFile(f));
               Log.d("tag", "The file path is " + Uri.fromFile(f));

                //Save image to gallery
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                getActivity().sendBroadcast(mediaScanIntent);

            }
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format( new Date());
                imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                Log.d(TAG, "onActivityResult: Gallery Image Uri:  " + imageFileName);
                imageView.setImageURI(contentUri);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver content = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(content.getType(contentUri));
    }



    /*  @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    } */




//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//            if (requestCode == REQUEST_CODE) {
//                if (resultCode == Activity.RESULT_OK) {
//
//                    Bitmap bmp = (Bitmap) data.getExtras().get("data");
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                    byte[] byteArray = stream.toByteArray();
//
//                    // convert byte array to Bitmap
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                    imageView.setImageBitmap(bitmap);
//
//                }
//            }
//
//    }




}


