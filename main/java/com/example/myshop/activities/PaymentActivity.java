package com.example.myshop.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myshop.R;

public class PaymentActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView subTotal, discount, shipping, total;
    Button easyPaisa, jazzCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        // Change status bar color
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.textHeading));

        //Toolbar
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.white));
        toolbar.setTitleTextAppearance(this, R.style.MainToolbarTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        double amount = 0.0;
        amount = getIntent().getDoubleExtra("amount",0.0);

        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.textView17);
        shipping = findViewById(R.id.textView18);
        total = findViewById(R.id.total_amt);
        easyPaisa = findViewById(R.id.easypaisa_btn);
        jazzCash = findViewById(R.id.jazzcash_btn);

        easyPaisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = "pk.com.telenor.phoenix";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);

                if (intent != null) {
                    startActivity(intent);
                } else {
                    Log.d("JazzCashApp", "App not found, opening Play Store...");
                    Intent playStoreEP = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                    startActivity(playStoreEP);
                }
            }
        });
        jazzCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = "com.techlogix.mobilinkcustomer";
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);

                if (intent != null) {
                    startActivity(intent);
                } else {
                    Log.d("JazzCashApp", "App not found, opening Play Store...");
                    Intent playStoreJC = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                    startActivity(playStoreJC);
                }
            }
        });


        subTotal.setText(amount+"$");
    }
}