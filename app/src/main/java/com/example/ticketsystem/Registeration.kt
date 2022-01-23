package com.example.ticketsystem

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout

import android.widget.TextView

import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class Registeration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var firebaseFirestore: FirebaseFirestore

    lateinit var name: TextInputEditText
    //lateinit var email: TextInputEditText
    lateinit var phone: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var confirm_password: TextInputEditText
    lateinit var register: Button
    lateinit var sendtheotp: Button
    lateinit var login: TextView
    lateinit var login2: TextView
    var isNameValid = false
    var isEmailValid: Boolean = false
    var isPhoneValid: Boolean = false
    var isPasswordValid: Boolean = false
    var isOtpValid: Boolean = false
    lateinit var nameError: TextInputLayout
    //lateinit var emailError: TextInputLayout
    lateinit var phoneError: TextInputLayout
    lateinit var passError: TextInputLayout

    var number : String =""
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration)

        name = findViewById(R.id.name);
        //email =findViewById(R.id.email);
        phone =findViewById(R.id.phone);
        password =findViewById(R.id.password);
        login =findViewById(R.id.login);
        login2 =findViewById(R.id.login2);
        register = findViewById(R.id.register);
        nameError = findViewById(R.id.nameError);
        //emailError = findViewById(R.id.emailError);
        phoneError = findViewById(R.id.phoneError);
        passError =  findViewById(R.id.passError);
        confirm_password=findViewById(R.id.confirm_password)
        register.isEnabled=true


        // Initialising auth object
        auth = Firebase.auth

        firebaseFirestore = FirebaseFirestore.getInstance()


        register.setOnClickListener {
            SetValidation()
        }

        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        login2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // This method is called when the verification is completed
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
                Log.d("GFG" , "onVerificationCompleted Success")
            }

            // Called when verification is failed add log statement to see the exception
            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("GFG" , "onVerificationFailed  $e")
            }

            // On code is sent by the firebase this method is called
            // in here we start a new activity where user can enter the OTP
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("GFG","onCodeSent: $verificationId")
                storedVerificationId = verificationId
                resendToken = token

                // Start a new activity using intent
                // also send the storedVerificationId using intent
                // we will use this id to send the otp back to firebase
                val intent = Intent(applicationContext,OtpActivity::class.java)
                intent.putExtra("storedVerificationId",storedVerificationId)
                intent.putExtra("full_name",name.text.toString())
                //intent.putExtra("email_name",email.text.toString())
                intent.putExtra("phone_no",phone.text.toString())
                intent.putExtra("pass_field",password.text.toString())
                startActivity(intent)
                finish()
            }
        }


    }

    private fun sendVerificationCode(number: String) {
        try {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            Log.d("GFG", "Auth started")
        }catch (e:Exception){
            Log.d("GFG","The exception is $e")
        }
    }


    fun SetValidation (){

        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        /*if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } /*else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } */else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }*/

        // Check for a valid phone number.
        if (phone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (!confirm_password.text.toString().equals(password.text.toString())) {
            passError.setError("Password Mismatch")
            isPasswordValid = false;
        }
            else
          {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isPhoneValid && isPasswordValid) {
            login_in()
          //  addUser()
            /*auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }

    private fun login_in() {
        number = phone.text.toString()


        // get the phone number from edit text and append the country cde with it
        if (number.isNotEmpty()){
            number = "+91$number"
            sendVerificationCode(number)
        }else{
            Toast.makeText(this,"Enter mobile number", Toast.LENGTH_SHORT).show()
        }
    }

}