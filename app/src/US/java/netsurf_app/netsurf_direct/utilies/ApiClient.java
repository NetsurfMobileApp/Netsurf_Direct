package netsurf_app.netsurf_direct.utilies;

import android.app.Activity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import netsurf_app.netsurf_direct.BuildConfig;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * Created by Sagar on 17-10-2018.
 */

public class ApiClient {
    private static final String TAG = ApiClient.class.getName();
    private static UsApiInterface netsurfApiInterface;
    public static UsApiInterface getApiClient(final Activity context,
                                                   boolean showNetworkNotAvailableDialog) {
        if (Utils.isNetworkAvailable(context)) {
            if (netsurfApiInterface == null) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .registerTypeAdapter(Date.class, new DateTypeAdapter())
                        .create();

                RestAdapter.Builder builder = new RestAdapter.Builder()
                        .setConverter(new GsonConverter(gson))
                        .setClient(new OkClient(okHttpClient));

                RestAdapter restAdapter;

            /*    if (com.netsurfnetwork.android.BuildConfig.DEBUG) {
                    builder.setEndpoint(Constants.BASE_URL_FOR_DEBUG);
                    restAdapter = builder.build();
                    restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
                } else {*/
                builder.setEndpoint(Constants.BASE_URL_FOR_RELEASE);
                restAdapter = builder.build();
                //   }


                netsurfApiInterface = restAdapter.create(UsApiInterface.class);
            }
            return netsurfApiInterface;
        } else {
            if (showNetworkNotAvailableDialog && context != null) {
                try {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    // todo remove after auto update application code testing done
    public static UsApiInterface getApiClient(final Activity context,
                                                   boolean showNetworkNotAvailableDialog, String
                                                           baseUrl) {
        if (Utils.isNetworkAvailable(context)) {
            UsApiInterface netsurfApiInterface;
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .registerTypeAdapter(Date.class, new DateTypeAdapter())
                    .create();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(baseUrl)
                    .setConverter(new GsonConverter(gson))
                    .setClient(new OkClient(okHttpClient))
                    .build();
            if (BuildConfig.DEBUG) {
                restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
            }
            netsurfApiInterface = restAdapter.create(UsApiInterface.class);

            return netsurfApiInterface;
        } else {
            if (showNetworkNotAvailableDialog && context != null) {
                try {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static UsApiInterface getApiClient(final Activity context) {
        if (Utils.isNetworkAvailable(context)) {
            if (netsurfApiInterface == null) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .registerTypeAdapter(Date.class, new DateTypeAdapter())
                        .create();

                RestAdapter.Builder builder = new RestAdapter.Builder()
                        .setConverter(new GsonConverter(gson))
                        .setClient(new OkClient(okHttpClient));
                RestAdapter restAdapter;


            /*if (com.netsurfnetwork.android.BuildConfig.DEBUG) {
                    builder.setEndpoint(Constants.BASE_URL_FOR_DEBUG);
                    restAdapter = builder.build();
                    restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
                } else {*/
                builder.setEndpoint(Constants.BASE_URL_FOR_RELEASE);
                restAdapter = builder.build();
                //}


                netsurfApiInterface = restAdapter.create(UsApiInterface.class);
            }
            return netsurfApiInterface;
        }
        return null;
    }


}
