package netsurf_app.netsurf_direct.fragment;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import netsurf_app.netsurf_direct.R;
import netsurf_app.netsurf_direct.utilies.Utils;
import timber.log.Timber;

/**
 * Created by Sagar on 20-10-2018.
 */

public class AddNewNboFragment  extends BaseUsFragment {

    private final String TEAM_ID = "&TeamId=";
    private android.webkit.ValueCallback<android.net.Uri> mUploadMessage;
    public android.webkit.ValueCallback<android.net.Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    View view;
    public AddNewNboFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        view = inflater.inflate(R.layout.add_new_nbo, null);

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();


    }



}
