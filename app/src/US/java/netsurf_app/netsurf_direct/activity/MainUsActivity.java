package netsurf_app.netsurf_direct.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



import butterknife.BindView;
import butterknife.ButterKnife;

import netsurf_app.netsurf_direct.R;
import netsurf_app.netsurf_direct.model.AdvertiseModel;
import netsurf_app.netsurf_direct.model.Crypto;
import netsurf_app.netsurf_direct.utilies.ApiClient;
import netsurf_app.netsurf_direct.utilies.UsApiInterface;
import com.squareup.picasso.Callback;


import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;


public class MainUsActivity extends AppCompatActivity {

    private static final String TAG = MainUsActivity.class.getName();

    private Observable<ArrayList<AdvertiseModel.Response>> observableadvertise;
    private ArrayList<AdvertiseModel.Response> addData = new ArrayList<>();
    @BindView(R.id.imageview)
    ImageView imageView;
    @BindView(R.id.progressBar)
    ProgressBar progressBarcir;
    @BindView(R.id.button_view)
    Button btn_view;
    @BindView(R.id.button_skip)
    Button btn_skip;



    Subscription subscription;
    private ProgressBar spinner;
    private  String live_status;
    private String url_facebook;
    private  String live_status_intent;
    private Button view_details,skip;
    private  String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);
        ButterKnife.bind(this);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(MainUsActivity.this, LandingUsActivity.class);
                startActivity(browserIntent);
            }
        });
        getAdvertiseDetails();
    }






    private void getAdvertiseDetails() {


        UsApiInterface netsurfApiInterface = ApiClient.getApiClient(this,
                true);

        if (netsurfApiInterface != null) {
            observableadvertise = netsurfApiInterface.getAddDetails();
            subscription = observableadvertise.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ArrayList<AdvertiseModel.Response>>() {

                        @Override
                        public void onCompleted() {

                            AdvertiseModel.Response offerDetails = addData.get(0);
                            imagepath = offerDetails.getimage_path();
                            String url = offerDetails.getimage_path();
                            Timber.i("paimg is %s ", "" + offerDetails.getimage_path());
                            android.widget.ImageView img = (android.widget.ImageView) findViewById(R.id.imageview);
                            com.squareup.picasso.Picasso.with(getApplication())
                                    .load(url)
                                    .fit()
                                   // .resize(600, 300)
                                    // .error(R.drawable.abc_btn_check_material)      // optional.resize(600, 300)
                                    .into(img, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            progressBarcir.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    });

                        }

                        @Override
                        public void onError(Throwable e) {
                            try {
                                Toast.makeText(MainUsActivity.this, "data is now "+e.getMessage(), Toast.LENGTH_LONG).show();
                            } catch (NullPointerException ex) {
                                Timber.i("error while setting data not available  %s ",
                                        "" + ex.getMessage());
                            } catch (IllegalStateException ex) {
                                Timber.i("error while setting data not available  %s ",
                                        "" + ex.getMessage());
                            }
                        }

                        @Override
                        public void onNext(ArrayList<AdvertiseModel.Response>
                                                   respons) {

                            try {
                                if (respons != null && respons.size() > 0) {
                                    addData.clear();
                                    addData.addAll(respons);

                                    live_status  = addData.get(0).getButtonText();
                                    url_facebook = addData.get(0).getWebURL();


                                }
                                else {
                                    Timber.d("region list response%s ", "empty.");
                                }
                            } catch (NullPointerException ex) {
                                ex.printStackTrace();
                            } catch (IllegalStateException ex) {

                            }
                        }
                    });

        } else {
            Toast.makeText(MainUsActivity.this, "web url is", Toast.LENGTH_LONG).show();
        }

    }

    private void handleResults(List<Crypto> marketList) {
        if (marketList != null && marketList.size() != 0) {
            //   recyclerViewAdapter.setData(marketList);


        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}
