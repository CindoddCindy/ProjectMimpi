package cindodcindy.sirihpinang.prayernote.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.auth.SaveSharedPreference;
import cindodcindy.sirihpinang.prayernote.view.ExpandLayout;
import cindodcindy.sirihpinang.prayernote.view.ListDoa;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class LoginUser extends AppCompatActivity {

    private Button button_login;
    private EditText editText_nama, editText_password;

    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        button_login=findViewById(R.id.btn_login);
        editText_nama=findViewById(R.id.et_nama);
        editText_password=findViewById(R.id.et_password);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            editText_nama.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            editText_password.setText(sharedpreferences.getString(Email, ""));

        }

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), ListDoa.class);
            startActivity(intent);
        }

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = editText_nama.getText().toString();
                String e = editText_password.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, n);
                editor.putString(Email, e);
                editor.commit();
                String name=editText_nama.getText().toString();
                String password = editText_password.getText().toString();
                userLogin(name,password);


            }
        });



    }


    public void userLogin(String name, String password){

        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
        Intent intent = new Intent(getApplicationContext(), ListDoa.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
