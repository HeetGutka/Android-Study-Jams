package com.example.ticketsystem

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PasswordChangeActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    lateinit var old_pass: TextView
    var user_id = " "
    var old_pass_text = " "
    var isPasswordValid: Boolean = false
    lateinit var passError: TextInputLayout
    lateinit var password: EditText
    lateinit var confirm_password: EditText
    lateinit var change_the_pass: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        old_pass = findViewById(R.id.old_pass_was)
        passError = findViewById(R.id.passError_pass_change);
        password = findViewById(R.id.password_pass_change);
        confirm_password = findViewById(R.id.confirm_password_pass_change)
        change_the_pass = findViewById(R.id.change_pass_pass_change)

        user_id = auth.currentUser?.uid.toString()

        val docRef = firestore.collection("Registered Users").document(user_id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    old_pass_text = document.getString("password").toString()
                    old_pass.setText("Your Old password was : " + old_pass_text)
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        change_the_pass.setOnClickListener {

            SetValidation()
        }
    }

    fun SetValidation() {
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (!confirm_password.text.toString().equals(password.text.toString())) {
            passError.setError("Password Mismatch")
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isPasswordValid) {
            val docRef = firestore.collection("Registered Users").document(user_id)
            docRef.update("password",password.text.toString()).addOnSuccessListener {
                old_pass.setText("Your New password is : " + old_pass_text)
                Toast.makeText(applicationContext,"Password has been updated",Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext,Dashboard::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(applicationContext,"Password updation Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}