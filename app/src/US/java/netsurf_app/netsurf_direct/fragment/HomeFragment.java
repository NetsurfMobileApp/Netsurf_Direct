package netsurf_app.netsurf_direct.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import netsurf_app.netsurf_direct.R;
import netsurf_app.netsurf_direct.activity.LandingUsActivity;
import netsurf_app.netsurf_direct.utilies.Constants;
import netsurf_app.netsurf_direct.widgets.TextViewFont;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sagar on 17-10-2018.
 */

public class HomeFragment extends Fragment {


    private static final String NETSURF = "Netsurf";
    private static final String NETSURF_APP_DOWNLOAD_TITLE = "Netsurf- updating application...";
    private static final String DATA_NOT_AVAILABLE = "Data not available.";
    private static final String FETCHING_DATA_FROM_DB = "Fetching data from database.";
    private static final String POSITION_TAG = "position";
    private static final String TAG = HomeFragment.class.getName();
    private ImageView callNow;
    private ImageView drawer;
    private ImageView turnover;
    private  int RESULT_LOAD_IMAGE = 9;


    @BindView(R.id.text_dist_name)
    TextViewFont dist_name;

    @BindView(R.id.btn_seller_submit)
    Button button_seller_submit;

    @BindView(R.id.edt_add_new_seller)
    EditText edit_seller_ref_no;

    @BindView(R.id.frameLayout_online_one)
    RelativeLayout txt_health_care;

    @BindView(R.id.frameLayout_online_two)
    RelativeLayout txt_personal_care;

    @BindView(R.id.frameLayout_online_three)
    RelativeLayout txt_agriculture;

    @BindView(R.id.frameLayout_online_four)
    RelativeLayout txt_home_care;

  /*  @BindView(R.id.img_profile)
    CircleImageView profile_image;*/

    @BindView(R.id.progress_wheel)
    ProgressBar progressWheel;

    @BindView(R.id.relative_my_account)
    RelativeLayout rl_my_account;

    @BindView(R.id.relative_club_netsurf)
    RelativeLayout rl_club_netsurf;

    @BindView(R.id.relative_events)
    RelativeLayout rl_events;

    @BindView(R.id.relative_my_region)
    RelativeLayout rl_my_region;

    @BindView(R.id.relative_customer_support)
    RelativeLayout rl_customer_support;

    @BindView(R.id.relative_product_info)
    RelativeLayout rl_product_info;

    @BindView(R.id.relative_sp_locator)
    RelativeLayout rl_sp_locate;

    @BindView(R.id.lin_shoponline)
    LinearLayout rl_shop_online;

    @BindView(R.id.relative_media)
    RelativeLayout rl_media;

    @BindView(R.id.relative_guest)
    RelativeLayout rl_guest_list;

    @BindView(R.id.relative_netsurf)
    RelativeLayout rl_other_list;

    @BindView(R.id.row_text_portion)
    RelativeLayout rl_profile;

    @BindView(R.id.relative_everyday)
    RelativeLayout rl_everyday;

    @BindView(R.id.relative_buissness)
    RelativeLayout rl_buissness;

    @BindView(R.id.relative_geo)
    RelativeLayout rl_geo;

    @BindView(R.id.layput_seller)
    LinearLayout rl_add_new_distributor;

    @BindView(R.id.home_land)
    LinearLayout lin_home;

    @BindView(R.id.scrollView_land_page)
    ScrollView scroll_view;



    private View view = null;
    private int custId = 0;
    private boolean mCheckForUpdate = false;
    private boolean mIsReceiverRegistered = false;
    private boolean mIsFrommLogin = false;
    SharedPreferences shar;
    int width;
    int height ;

    int cmlkval = 0;
    int cabinet = 0;
    int mentor = 0;
    int leader = 0;
    int key = 0;
    Uri uri;
    public  static final int RequestPermissionCode  = 1 ;

    final String PREFS_NAME = "shared_image";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public HomeFragment() {

    }




    private void selectImage() {


        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 3);

                }

                else if (options[item].equals("Choose from Gallery"))

                {


                    Intent GalIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);


                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }








    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            try {
                Uri selectedImage = data.getData();
                String stringUri;
                stringUri = selectedImage.toString();
                SharedPreferences.Editor edit = shar.edit();
                edit.putString("image_path", stringUri);
                edit.commit();
              //  profile_image.setImageURI(selectedImage);
            }catch (Exception e)
            {

            }

        }
    }

    public static Bitmap getRoundedShapeExits(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getRoundedShape(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            Intent CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");
            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 170);
            CropIntent.putExtra("outputY", 170);
            CropIntent.putExtra("aspectX", 4);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);
            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }
    //Image Crop Code End Here


    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity(),"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getActivity(),"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Timber.d(TAG + " %s", " onCreateView() creating new view");

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        if (width == 480 && height == 800)
        {
            view = inflater.inflate(R.layout.hdpiscreen, null);
            ButterKnife.bind(this, view);

        }else {

            view = inflater.inflate(R.layout.fragment_home_screen, null);
            ButterKnife.bind(this, view);

        }


        rl_add_new_distributor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                distributor(v);
            }
        });

        if (mCheckForUpdate) {
            mIsReceiverRegistered = true;


        }

        edit_seller_ref_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll_view.scrollTo(5, 500);
            }
        });



        rl_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        //Club Netsurf
        rl_club_netsurf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        //Customer Support
        rl_customer_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Product Info
        rl_product_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //sp locate
        rl_sp_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //guest list
        rl_guest_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //business list
        rl_buissness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //Events
        rl_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //My Region
        rl_my_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //shop online
        rl_shop_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        txt_health_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        txt_personal_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        txt_agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        txt_home_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        //Media library
        rl_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        //Netsurf Everyday
        rl_everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        rl_other_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });


        // Geo Retailer
        rl_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        rl_add_new_distributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return view;
    }



    public  void distributor(View view)
    {

    }
    public  void shoponline(View view)
    {
    }

    public  void notification(View view) {

    }

    public  void media(View view)
    {


    }
    public  void prospector(View view)
    {

    }


    public  void settings(View view)
    {

    }
    public  void feed(View view)
    {


    }
    public void account(View view)
    {

    }



    public interface FragmentPositionListener {
        public void onClickFragment(int fragmentPosition);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Timber.d(TAG + " %s", " onAttach()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.d(TAG + " %s", " onPause()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d(TAG + " %s", " onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();

        Timber.d(TAG + " %s", " onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.d(TAG + " %s", " onStop()");
    }



    @Override
    public void onDetach() {
        super.onDetach();
        Timber.d(TAG + " %s", " onDetach()");
    }





}
