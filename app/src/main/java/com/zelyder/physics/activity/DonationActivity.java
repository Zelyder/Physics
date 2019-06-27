package com.zelyder.physics.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.zelyder.user.physics.R;

public class DonationActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler, View.OnClickListener {

    Button btnDonation50, btnDonation100, btnDonation200, btnDonation500, btnDonation1000;
    TextView tvError, tvDonationComment;
    BillingProcessor bp;
    SharedPreferences preferences;
    public static final String PREFERENCES_ADS = "Ads";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (preferences.getBoolean("cb_pref_dark_style", false)) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);



        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm3w5lD1L4gzoRSikWqjWt7d4DkLXMVUM3Z1j90XKeldtK+/Pn20cbN1KvvzlymfrWUZ8Ko0sbLUGU/SAmDHV+Bg+MTNVCaEx5EDzTIS+y9JSxnh3lSE395EciGTdryUAQKti6LNrDUbg0vzT4u4a29w6YuEdWZ4NMmlrYHBt3HPQIWuMHwRLWJ/lO6Yc6L1O7ZTA/RSJbBKwg7uXBuxc38jt3zKHA5YWXxw3uT3WSyAk0cS3LfElhWZJTsJpoUGVyJkFYI3Dy7xdS0Muzv4TBGJlvZAz8+ix/1LDkxj5cavHRdHxjRLfHuep9FiQxnDad5DZ2mlbSlLgzP4RbY8c/wIDAQAB", this);
        bp.initialize();

        btnDonation50 = findViewById(R.id.btnDonation50);
        btnDonation100 = findViewById(R.id.btnDonation100);
        btnDonation200 = findViewById(R.id.btnDonation200);
        btnDonation500 = findViewById(R.id.btnDonation500);
        btnDonation1000 = findViewById(R.id.btnDonation1000);

        tvError = findViewById(R.id.donationTvError);
        tvDonationComment = findViewById(R.id.tvDonationComment);



        btnDonation50.setOnClickListener(this);
        btnDonation100.setOnClickListener(this);
        btnDonation200.setOnClickListener(this);
        btnDonation500.setOnClickListener(this);
        btnDonation1000.setOnClickListener(this);
        if(preferences.getBoolean(PREFERENCES_ADS,false)){
            tvDonationComment.setText(R.string.donation_comment_after_payment);
        }





    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        preferences.edit().putBoolean(PREFERENCES_ADS, true).apply();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
     //   Toast.makeText(this,"Что-то не так",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {
        tvError.setVisibility(View.GONE);
        btnDonation50.setVisibility(View.VISIBLE);
        btnDonation100.setVisibility(View.VISIBLE);
        btnDonation200.setVisibility(View.VISIBLE);
        btnDonation500.setVisibility(View.VISIBLE);
        btnDonation1000.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDonation50:
                bp.consumePurchase("remove_ads_p50");
                bp.purchase(DonationActivity.this, "remove_ads_p50");
                break;
            case R.id.btnDonation100:
                bp.consumePurchase("remove_ads_p100");
                bp.purchase(DonationActivity.this, "remove_ads_p100");
                break;
            case R.id.btnDonation200:
                bp.consumePurchase("remove_ads_p200");
                bp.purchase(DonationActivity.this, "remove_ads_p200");
                break;
            case R.id.btnDonation500:
                bp.consumePurchase("remove_ads_p500");
                bp.purchase(DonationActivity.this, "remove_ads_p500");
                break;
            case R.id.btnDonation1000:
                bp.consumePurchase("remove_ads_p1000");
                bp.purchase(DonationActivity.this, "remove_ads_p1000");
                break;

        }
    }
}
