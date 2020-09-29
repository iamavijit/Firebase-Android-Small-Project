package in.avijit.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private EditText email;
    private EditText Password;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Toast.makeText(getApplicationContext(),currentUser.getUid().toString(),Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);
        button1 = findViewById(R.id.signin);
        email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.current);

        button.setOnClickListener(new View.OnClickListener() {
            String a = email.getText().toString();
            String b = Password.getText().toString();
            @Override
            public void onClick(View v) {

                createAccount(v);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            String a = email.getText().toString();
            String b = Password.getText().toString();
            @Override
            public void onClick(View v) {

                signIn(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Sign Out",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                String u = user.getEmail();
                Log.i("TAG",u);

            }
        });


    }

    public void createAccount(View view){
        String mEmail = email.getText().toString();
        String mPassword = Password.getText().toString();
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
    public void signIn(View view){
        String mEmail = email.getText().toString();
        String mPassword = Password.getText().toString();
        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            String u = user.getEmail();
                            Toast.makeText(getApplicationContext(),u,Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
