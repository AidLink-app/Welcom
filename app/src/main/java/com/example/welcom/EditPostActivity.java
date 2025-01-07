package com.example.welcom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditPostActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription, editTextDate, editTextLocation, editTextOrganization, editTextCategory, editTextImageUrl;
    private Button buttonUpdate;
    private FirebaseFirestore db;
    private String postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post); // Reusing the same layout

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Link UI elements
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextOrganization = findViewById(R.id.editTextOrganization);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextImageUrl = findViewById(R.id.editTextImageUrl);
        buttonUpdate = findViewById(R.id.buttonSubmit); // Reusing the Submit button as Update

        // Get Post Data from Intent
        Intent intent = getIntent();
        postId = intent.getStringExtra("postId");
        editTextTitle.setText(intent.getStringExtra("postTitle"));
        editTextDescription.setText(intent.getStringExtra("postDescription"));
        editTextDate.setText(intent.getStringExtra("postDate"));
        editTextLocation.setText(intent.getStringExtra("postLocation"));
        editTextOrganization.setText(intent.getStringExtra("postOrganization"));
        editTextCategory.setText(intent.getStringExtra("postCategory"));
        editTextImageUrl.setText(intent.getStringExtra("postImageUrl"));

        buttonUpdate.setText("Update Post"); // Change button text

        buttonUpdate.setOnClickListener(v -> updatePost());
    }

    private void updatePost() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String organization = editTextOrganization.getText().toString().trim();
        String category = editTextCategory.getText().toString().trim();
        String imageUrl = editTextImageUrl.getText().toString().trim();

        // Validate all fields
        if (title.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()
                || organization.isEmpty() || category.isEmpty() || imageUrl.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields before submitting!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> updatedPost = new HashMap<>();
        updatedPost.put("title", title);
        updatedPost.put("description", description);
        updatedPost.put("date", date);
        updatedPost.put("location", location);
        updatedPost.put("organization", organization);
        updatedPost.put("category", category);
        updatedPost.put("imageUrl", imageUrl);

        // Update Firestore document
        db.collection("posts")
                .document(postId)
                .update(updatedPost)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Post updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditPostActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to update post: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}