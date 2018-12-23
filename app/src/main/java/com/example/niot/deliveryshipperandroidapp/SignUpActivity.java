package com.example.niot.deliveryshipperandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niot.deliveryshipperandroidapp.Model.Shipper;
import com.example.niot.deliveryshipperandroidapp.retrofit.CvlApi;
import com.example.niot.deliveryshipperandroidapp.retrofit.RetrofitObject;

import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    private String name;
    private String phone;
    private String email;
    private String password;
    private String cmnd;

    EditText nameET;
    EditText phoneET;
    EditText emailET;
    EditText passwordET;
    EditText cmndET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        nameET = findViewById(R.id.nameEdit);
        phoneET = findViewById(R.id.phoneEdit);
        passwordET = findViewById(R.id.passwordEdit);
        emailET = findViewById(R.id.emailEdit);
        cmndET = findViewById(R.id.cmndEdit);
    }

    private void getInput(){
        name = nameET.getText().toString();
        phone = phoneET.getText().toString();
        password = phoneET.getText().toString();
        email = emailET.getText().toString();
        cmnd = cmndET.getText().toString();
    }

    private boolean checkInput(){
        getInput();
        if(name.isEmpty() || phone.isEmpty() || password.isEmpty() || email.isEmpty())
            return false;
        return true;
    }


    public void submitRegisterInfoClicked(View view) {
        if(!checkInput())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Fail!").setMessage(R.string.str_missing_info).setPositiveButton(R.string.str_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        }
        else{
            Retrofit retrofit = RetrofitObject.getInstance();

            Map<String, String> info = new HashMap<String, String>();
            info.put("phone", phone);
            info.put("ten", name);
            info.put("cmnd",cmnd);
            info.put("pass", password);
            info.put("email", email);

            retrofit.create(CvlApi.class).newUser(info).enqueue(new Callback<List<Shipper>>() {

                @Override
                public void onResponse(Call<List<Shipper>> call, Response<List<Shipper>> response) {
                    String msg;
                    if(response.body() != null){
                        if(response.body().size() > 0)
                            msg = "Success! Your id: " + String.valueOf(response.body().get(0).getId());
                        else
                            msg = "Failed 1";
                    }
                    else
                        msg = "";
                    Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<Shipper>> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Failed3!", Toast.LENGTH_SHORT).show();

                    if (t instanceof IOException) {
                        Toast.makeText(SignUpActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                    }
                    else {
                        //Toast.makeText(SignUpActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
