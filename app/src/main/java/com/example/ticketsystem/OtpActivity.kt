package com.example.ticketsystem

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class OtpActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var get_full_name = " "
    var get_email = " "
    var get_phone_no = " "
    var get_password = " "
    var user_id = " "
    lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        auth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()

        // get storedVerificationId from the intent
        val storedVerificationId = intent.getStringExtra("storedVerificationId")
        get_full_name = intent.getStringExtra("full_name").toString()
        get_email = intent.getStringExtra("email_name").toString()
        get_phone_no = intent.getStringExtra("phone_no").toString()
        get_password = intent.getStringExtra("pass_field").toString()


        // fill otp and call the on click on button
        findViewById<Button>(R.id.login).setOnClickListener {
            val otp = findViewById<EditText>(R.id.et_otp).text.trim().toString()
            if (otp.isNotEmpty()) {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp
                )
                checkforduplicate()
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkforduplicate() {


        /*admin.auth().getUserByPhoneNumber(phoneNumber)
            .then(function(userRecord) {
                // User found.
            })
            .catch(function(error) {
                console.log("Error fetching user data:", error);
            });*/
    }

    // verifies if the code matches sent by firebase
    // if success start the new activity in our case it is main Activity
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUser()
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun addUser() {
        user_id = auth.currentUser?.uid.toString()
        val currentDocRef: DocumentReference =
            firebaseFirestore.collection("Registered Users").document(user_id)
        val user = hashMapOf(
            "name" to get_full_name,
            "mobile_number" to get_phone_no,
            "password" to get_password
        )
        currentDocRef.set(user).addOnSuccessListener {

            Toast.makeText(applicationContext,"Sign Up Success",Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext,"You are reistered",Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,LoginActivity::class.java)

        }.addOnFailureListener {
            Toast.makeText(applicationContext,"Sign Up Failure",Toast.LENGTH_LONG).show()
        }


            /*firebaseFirestore.collection("Registered")
                .add(user)
                .addOnSuccessListener { documentReference->
                    Toast.makeText(applicationContext,"You are reistered",Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,LoginActivity::class.java)
                    startActivity(intent)
                }*/
    }
}