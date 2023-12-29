package com.example.app1_new.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app1_new.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements IMyActivity{

    public static final  String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}";
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    //validation
    private TextView txtValidate;
    private Boolean isValidEmail = false;
    //login and register by firebase
    private FirebaseAuth mAuth;
    private Context context = LoginActivity.this;
    private Boolean stateLogin = false;
    private Boolean stateSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        setupAction();
    }

    @Override
    public void setupUI() {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtValidate = (TextView) findViewById(R.id.txtValidate);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void setupAction() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // di chuyen tu man hinh A sang B
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if(signIn(email, password)) {
                    Intent intent = new Intent(LoginActivity.this, PlacesActivity.class);
                    startActivity(intent);
                }
            }
        });

        //validation
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validate here
                txtValidate.setText("");
                String email = txtEmail.getText().toString().trim();
                isValidEmail = (email.matches(emailPattern) && s.length() > 0); //s.length() > 0 khi go vao moi validate
                if(!isValidEmail) {
                    txtValidate.setTextColor(Color.rgb(255, 0, 0));
                    txtValidate.setText("Invalid email address");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public Boolean createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(context, "Register successfully!", Toast.LENGTH_SHORT).show();
                            stateSignUp = true;
                        } else {
                            // If sign in fails
                            Toast.makeText(context, "Register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return stateSignUp;
    }

    public Boolean signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            stateLogin = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return stateLogin;
    }
}