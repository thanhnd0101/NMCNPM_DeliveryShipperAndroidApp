package com.example.niot.deliveryshipperandroidapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niot.deliveryshipperandroidapp.Model.Shipper;
import com.example.niot.deliveryshipperandroidapp.retrofit.CvlApi;
import com.example.niot.deliveryshipperandroidapp.retrofit.RetrofitObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private String phone_number;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginBtnClicked(View view) {
        Retrofit retrofit = RetrofitObject.getInstance();
        GetUsernamePassword();
        Map<String, String> info = new HashMap<String, String>();
        info.put("pass", password);
        info.put("sdt", phone_number);

        if(isValidUsernamePassword())
            retrofit.create(CvlApi.class)
                    .logginShipper(info)
                    .enqueue(new Callback<List<Shipper>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Shipper>> call, @NonNull Response<List<Shipper>> response) {
                            String msg;

                            if(response.body() != null){
                                List<Shipper> shippers = response.body();

                                if(shippers.size() > 0) {
                                    msg = shippers.get(0).toString();
                                    LoginToShipper();
                                }
                                else{
                                    msg = "Wrong phone number or password!";
                                }
                            }
                            else
                                msg = "Something is wrong with our server, please try next time!";
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<List<Shipper>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Request failed! Check your connection and try again.", Toast.LENGTH_SHORT).show();
                        }
                    });

        // Hide the keyboard if user click button
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void LoginToShipper() {
        Intent intent = new Intent(MainActivity.this,ContentMainActivity.class);
        this.startActivity(intent);
    }

    public void registerBtnClicked(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        this.startActivity(intent);
    }
    private boolean isValidUsernamePassword() {
        return phone_number.length() > 0 && password.length() > 0;
    }
    private void GetUsernamePassword() {
        EditText phoneET = findViewById(R.id.login_layout_edit_text_sdt);
        EditText passET = findViewById(R.id.login_layout_edit_text_password);

        phone_number = phoneET.getText().toString();
        password = passET.getText().toString();
    }
}
