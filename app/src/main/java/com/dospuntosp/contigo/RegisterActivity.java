package com.dospuntosp.contigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword, signupName, getSignupPassword2;
    private Button signupButton;
    private TextView loginRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.register_email);
        signupPassword = findViewById(R.id.register_password);
        signupButton = findViewById(R.id.register_button);
        signupName = findViewById(R.id.register_nombre);
        getSignupPassword2 = findViewById(R.id.register_repeatpassword);
        loginRedirectText = findViewById(R.id.loginRedirectText);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupEmail.getText().toString().trim();
                String name = signupName.getText().toString();
                String passsword = signupPassword.getText().toString().trim();
                String pass2 = getSignupPassword2.getText().toString();
                SQLiteDatabase db = openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)");
                String sql = "insert into usuario(name)values(?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, name);
                statement.execute();
                if (user.isEmpty()){
                    signupEmail.setError("Por Favor Ingrese Email");
                }if (passsword.isEmpty()){
                    signupPassword.setError("Por Favor Ingrese su contraseña");
                }if (pass2.isEmpty()){
                    getSignupPassword2.setError("Comfirme su contraseña");
                }if (name.isEmpty()){
                    signupName.setError("Por Favor Ingrese su Nombre");
                }else {
                    auth.createUserWithEmailAndPassword(user, passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registro Completado", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registro Fallido" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}