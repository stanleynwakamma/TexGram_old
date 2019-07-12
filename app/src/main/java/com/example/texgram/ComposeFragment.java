package com.example.texgram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class ComposeFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    File photoFile;

    Button btnSelectPhoto;
    Button btnPost;
    EditText etCaption;
    ImageView ivPhoto;
    private File takenPhotoFile;

    public static ComposeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ComposeFragment fragment = new ComposeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPage = getArguments().getInt(ARG_PAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compose, container, false);

        btnSelectPhoto = view.findViewById(R.id.btnSelectPhoto);
        btnPost = view.findViewById(R.id.btnPost);
        ivPhoto = view.findViewById(R.id.ivPhoto);
        etCaption = view.findViewById(R.id.etCaption);

        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).onLaunchCamera();
            }
        });

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).onLaunchCamera();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption = etCaption.getText().toString();

                Post newPost = new Post();
                newPost.setDescription(caption);
                newPost.setImage(new ParseFile(takenPhotoFile));
                newPost.setUser(ParseUser.getCurrentUser());

                newPost.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        btnSelectPhoto.setVisibility(View.VISIBLE);
                        etCaption.setText("");
                        ivPhoto.setImageResource(0);
                        ((MainActivity) getContext()).bottomNavigationView.setSelectedItemId(0);
                    }
                });
            }
        });

        return view;
    }

    public void onImageTaken(File photoFile) {
        takenPhotoFile = photoFile;
        Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        // RESIZE BITMAP, see section below
        // Load the taken image into a preview
        ivPhoto.setImageBitmap(takenImage);
        btnSelectPhoto.setVisibility(View.INVISIBLE);
    }
}