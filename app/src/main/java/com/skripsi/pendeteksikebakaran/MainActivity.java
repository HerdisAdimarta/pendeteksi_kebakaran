package com.skripsi.pendeteksikebakaran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.skripsi.pendeteksikebakaran.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.skripsi.pendeteksikebakaran.api.ApiBasic;
import com.skripsi.pendeteksikebakaran.api.ApiGetRecent;
import com.skripsi.pendeteksikebakaran.api.GetRecent;
import com.skripsi.pendeteksikebakaran.api.rest.ErrorUtils;
import com.skripsi.pendeteksikebakaran.api.rest.REST_Controller;
import com.skripsi.pendeteksikebakaran.framework.ActivityFramework;
import com.skripsi.pendeteksikebakaran.storage.SharedPreferencesProvider;
import com.skripsi.pendeteksikebakaran.utils.UtilsDialog;

import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NonNull;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ActivityFramework {
    public static final String ACTION_NEW_TASK = "200";
    public String tmp;
    Handler mHandler;
    ProgressDialog mProgressDialog;
    GetRecent dataAda;
    @BindView(R.id.tvApi)
    TextView tvApi;
    @BindView(R.id.tvAsap)
    TextView tvAsap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,5000);
        fcmToken();
        if(SharedPreferencesProvider.getInstance().get_pref_fcm_token(mActivity).isEmpty() ||
                SharedPreferencesProvider.getInstance().get_pref_fcm_token(mActivity) == null){
            fcmToken();
        }
        Log.e("ini_fcm:","ada-"+SharedPreferencesProvider.getInstance().get_pref_fcm_token(mActivity));
        getData();
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            Toast.makeText(MainActivity.this,"in runnable",Toast.LENGTH_SHORT).show();
            MainActivity.this.mHandler.postDelayed(m_Runnable, 5000);
            getData();
        }

    };//runnable

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        finish();

    }
    public void getData(){
//        mProgressDialog = UtilsDialog.showLoading(mActivity, mProgressDialog);

        REST_Controller.CLIENT.getData().enqueue(new Callback<ApiGetRecent>() {
            @Override
            public void onResponse(Call<ApiGetRecent> call, Response<ApiGetRecent> response) {
                if (response.isSuccessful()) {
                    Log.e("MASUK", "kesini");
                    dataAda = response.body().getData();
                    UtilsDialog.dismissLoading(mProgressDialog);
                    if (dataAda.getApi().equals("1")){
                        tvApi.setText("Tidak ada api");
                    } else if (dataAda.getApi().equals("0")){
                        tvApi.setText("Ada api");
                    }
                    if (dataAda.getAsap() <= 50){
                        tvAsap.setText("Baik");
                    } else if (dataAda.getAsap() > 50 == dataAda.getAsap() <= 99){
                        tvAsap.setText("Sedang");
                    } else if (dataAda.getAsap() > 100 == dataAda.getAsap() <= 199){
                        tvAsap.setText("Tidak Sehat");
                    } else if (dataAda.getAsap() > 200 == dataAda.getAsap() <= 299){
                        tvAsap.setText("Sangat Tidak Sehat");
                    } else if (dataAda.getAsap() >= 300){
                        tvAsap.setText("Bahaya");
                    }
                } else {
                    Log.e("GAGAl", "kesini");
                    ApiBasic error = ErrorUtils.parseError(response, mActivity);
                    UtilsDialog.showBasicDialog(mActivity, "OK", error.getMessage()).show();
                    UtilsDialog.dismissLoading(mProgressDialog);
                }
            }

            @Override
            public void onFailure(Call<ApiGetRecent> call, Throwable t) {
                Log.e("FAILURE", "kesini");

                UtilsDialog.dismissLoading(mProgressDialog);
                UtilsDialog.showBasicDialog(mActivity, "OK", ErrorUtils.parseError(t.toString()).getMessage()).show();

            }
        });
    }

    void fcmToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            SharedPreferencesProvider.getInstance().set_pref_fcm_token(mActivity, FirebaseInstanceId.getInstance().getToken());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.e("isi_message", token);
                        SharedPreferencesProvider.getInstance().set_pref_fcm_token(mActivity, token);
                    }
                });
    }

}