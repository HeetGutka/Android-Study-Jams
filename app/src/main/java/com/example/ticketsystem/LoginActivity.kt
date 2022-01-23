package com.example.ticketsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout

import android.widget.TextView

import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {

    lateinit var phone: EditText
    lateinit var login: Button
    lateinit var register: TextView
    lateinit var forgotpass: TextView
    lateinit var phoneError: TextInputLayout

    lateinit var auth: FirebaseAuth
    var isPhoneValid: Boolean = false

    var number : String =""
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phone = findViewById(R.id.phone_number_login);
        login = findViewById(R.id.login);
        register =findViewById(R.id.register);
        phoneError = findViewById(R.id.phone_error_login);
forgotpass=findViewById(R.id.forgot_pass_login)
        auth = FirebaseAuth.getInstance()

        auth.signOut()

        login.setOnClickListener {

            SetValidation()
        }

        register.setOnClickListener {
            val intent = Intent(this, Registeration::class.java)
            startActivity(intent)
        }

        forgotpass.setOnClickListener {
            val intent = Intent(this, ForgotPass::class.java)
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
                val intent = Intent(applicationContext,OtpActivityLogin::class.java)
                intent.putExtra("storedVerificationId",storedVerificationId)
                startActivity(intent)
                finish()
            }
        }
    }

    fun SetValidation(){

        if (phone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a valid password.


        if (isPhoneValid==true) {

            if(phone.text.equals("1234567890")){
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)

            }


            login_in()

           // Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
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

    private fun sendVerificationCode(number: String) {
        startActivity(Intent(applicationContext, NewDashboard::class.java))
        finish()

//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(number) // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(this) // Activity (for callback binding)
//            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("GFG" , "Auth started")
    }


}