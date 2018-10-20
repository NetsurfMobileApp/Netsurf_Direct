package netsurf_app.netsurf_direct.activity;


import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import netsurf_app.netsurf_direct.R;
import netsurf_app.netsurf_direct.adapter.DrawerListAdapter;
import netsurf_app.netsurf_direct.fragment.HomeFragment;





import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

import netsurf_app.netsurf_direct.fragment.RootFragment;
import netsurf_app.netsurf_direct.model.ApplicationMenu;
import netsurf_app.netsurf_direct.model.DrawerModel;

import netsurf_app.netsurf_direct.model.NotificationModel;
import netsurf_app.netsurf_direct.utilies.ApiClient;
import netsurf_app.netsurf_direct.utilies.Constants;
import netsurf_app.netsurf_direct.utilies.UsApiInterface;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static netsurf_app.netsurf_direct.activity.NetsurfUsBaseActivity.POSITION_TAG;

/**
 * Created by shivam on 6/5/15.
 */
public class LandingUsActivity extends AppCompatActivity {

    private static final String TAG = LandingUsActivity.class.getName();

    @BindView(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.list_left_drawer)
    ListView listView;
    @BindView(R.id.txt_username)
    TextView name_user;
    private DrawerListAdapter drawerListAdapter;
    private ArrayList<String> titlesArrayList = new ArrayList<>();
    private ArrayList<Integer> imageIdsArrayList = new ArrayList<>();
    private ArrayList<Integer> colorIdsArrayList = new ArrayList<>();
    private ArrayList<DrawerModel> drawerModels = new ArrayList<>();
    private int list_position = 0;
    //fragment related
    private int currentFragmentPosition = Constants.METRO_FRAGMENT;
    private Fragment currentFragment;
    private ArrayList<ApplicationMenu.Response> drawerModelsitem = new ArrayList<>();
    private String Clubs;
    private String States;
    private String District_name;
    private HomeFragment homeFragment =  null;
    private SharedPreferences pref;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private String District_old;
    private boolean isCurrentCycleApi = false;
    private Subscription currentCycleSubscription;
    private Subscription regionListSubscription;
    private boolean mUptoDate = false;
    private boolean mPerformedChecking = false;
    private static final String CUSTOMER_ID = "custId";
    private static final String NETSURF = "Netsurf";
    private static final String NETSURF_APP_DOWNLOAD_TITLE = "Netsurf- updating application...";
    private long downloadReference;
    private String forecastJsonStr;
    private int coreTypeId ;
    int ids = 1;
    private String regIds;
    private List<String> list =  new ArrayList<String>();
    private List<String> list_menu_id =  new ArrayList<String>();
    private List<String> list_color_code =  new ArrayList<String>();
    private List<String> listraw ;
    private Subscription timerSubscribption = null;
    private Subscription autoRefreshSubscribption = null;
    public static final String ARG_PARENTS = "Parents";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private boolean skipBusinessAnalyticsFragment = false;



    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_us);
        Timber.d(TAG + " onCreate");
        ButterKnife.bind(this);
        menuItem();
        checkAndRequestPermissions();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        int fragmentPosition = getIntent().getIntExtra(POSITION_TAG, Constants.METRO_FRAGMENT);
        if (fragmentPosition != Constants.METRO_FRAGMENT) {
            toolbar.setTitle("" + titlesArrayList.get(fragmentPosition));

        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initDrawer();
        initUi();





    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onBackPressed() {



    }

    private void initDrawer() {

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                hideKeyBoard();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyBoard();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void setNothingSelectedInDrawer() {
        for (int i = 0; i < drawerModels.size(); i++) {
            drawerModels.get(i).setSelected(false);
        }
        drawerListAdapter.notifyDataSetChanged();
    }

    private void hideKeyBoard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
        } catch (Exception ignore) {

        }
    }

    private void initUi() {

        //todo change it according to selected item position
        //drawerModels.get(Constants.CLUB_NETSURF).setSelected(true);

        try {
            drawerListAdapter = new DrawerListAdapter(this, R.layout.layout_drawer_list,coreTypeId,drawerModels);


        } catch (NullPointerException e) {

        }
        name_user.setText("");

        listView.setAdapter(drawerListAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int visible_position = 0;
                list_position = position;

                for (int i = 0; i < drawerModels.size(); i++) {
                    drawerModels.get(i).setSelected(false);

                    visible_position = drawerModels.get(position).getMenuid();


                }
                drawerModels.get(position).setSelected(true);
                drawerListAdapter.notifyDataSetChanged();



                setFragment(visible_position,null);
                drawerLayout.closeDrawer(Gravity.LEFT);

            }
        });

        Intent data = getIntent();
        if (data != null) {
            int fragmentPosition = data.getIntExtra(POSITION_TAG, Constants.METRO_FRAGMENT);

            if (fragmentPosition != Constants.METRO_FRAGMENT) {
                Timber.d("fragment position is %s ", "" + fragmentPosition);
                for (int i = 0; i < drawerModels.size(); i++) {
                    drawerModels.get(i).setSelected(false);
                }
                drawerModels.get(fragmentPosition).setSelected(true);
                drawerListAdapter.notifyDataSetChanged();
                setFragment(fragmentPosition,null);
            } else {
                setFragment(Constants.METRO_FRAGMENT,null);
            }

        } else {
            Timber.d(" intent data is null ", " setting club netsurf fragment ");
            setFragment(Constants.METRO_FRAGMENT,null);
        }

    }



    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    private void getIds(List<String> name_list, List<String> ids_menu_list ,List<String> ids_img_list) {

        imageIdsArrayList.clear();
        colorIdsArrayList.clear();
        titlesArrayList.clear();
        for(int i=0 ;i<ids_img_list.size();i++)
        {

            if(ids_img_list.get(i).equalsIgnoreCase("Ibo"))
            {
                imageIdsArrayList.add(R.drawable.ic_add_user); // ibo
                colorIdsArrayList.add(R.color.cl_pink); // ibo
                titlesArrayList.add(name_list.get(i));
                //    Toast.makeText(LandingActivity.this, "data will be " + name_list.get(i), Toast.LENGTH_LONG).show();

            }
            if(ids_img_list.get(i).equalsIgnoreCase("Everyday")) {

                imageIdsArrayList.add(R.drawable.ic_my_region); // club netsurf
                colorIdsArrayList.add(R.color.everday);
                titlesArrayList.add(name_list.get(i));

                //  Toast.makeText(LandingActivity.this, "data will be " + name_list.get(i), Toast.LENGTH_LONG).show();


            }

            if(ids_img_list.get(i).equalsIgnoreCase("Live")) {

                imageIdsArrayList.add(R.drawable.ic_my_region);//locate_sp
                colorIdsArrayList.add(R.color.video_stream);
                titlesArrayList.add(name_list.get(i));
                // Toast.makeText(LandingActivity.this, "data will be " + name_list.get(i), Toast.LENGTH_LONG).show();

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Cycle")) {

                imageIdsArrayList.add(R.drawable.ic_cycle_comparision);
                colorIdsArrayList.add(R.color.cycle_comparison);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Region")) {

                imageIdsArrayList.add(R.drawable.ic_my_region);
                colorIdsArrayList.add(R.color.my_region);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Distributor")) {

                imageIdsArrayList.add(R.drawable.ic_add_user);
                colorIdsArrayList.add(R.color.add_new_distributor);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Shop")) {

                imageIdsArrayList.add(R.drawable.ic_shop_online);  //shop online
                colorIdsArrayList.add(R.color.shop_online_new);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Buisness")) {

                imageIdsArrayList.add(R.drawable.ic_business_ana);
                colorIdsArrayList.add(R.color.business_analytics);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Account")) {

                imageIdsArrayList.add(R.drawable.ic_my_account);
                colorIdsArrayList.add(R.color.my_account);
                titlesArrayList.add(name_list.get(i));

            }
            if(ids_img_list.get(i).equalsIgnoreCase("Notify")) {
                imageIdsArrayList.add(R.drawable.ic_action_reports);  // action reportors
                colorIdsArrayList.add(R.color.action_reports);
                titlesArrayList.add(name_list.get(i));
                coreTypeId = Integer.parseInt(ids_menu_list.get(i));

            }
            if(ids_img_list.get(i).equalsIgnoreCase("Guest")) {
                imageIdsArrayList.add(R.drawable.ic_prospector);  // prospector
                colorIdsArrayList.add(R.color.prospector);
                titlesArrayList.add(name_list.get(i));
            }
            if(ids_img_list.get(i).equalsIgnoreCase("Club")) {
                imageIdsArrayList.add(R.drawable.ic_club_netsurf);  // coreteam
                colorIdsArrayList.add(R.color.club_net_surf);
                titlesArrayList.add(name_list.get(i));
            }
            if(ids_img_list.get(i).equalsIgnoreCase("Pan")) {
                imageIdsArrayList.add(R.drawable.ic_my_region);
                colorIdsArrayList.add(R.color.pan_update);
                titlesArrayList.add(name_list.get(i));


            } if(ids_img_list.get(i).equalsIgnoreCase("Support")) {
            imageIdsArrayList.add(R.drawable.ic_news_feed);  // news feed
            colorIdsArrayList.add(R.color.customer_support);
            titlesArrayList.add(name_list.get(i));

        }
            if(ids_img_list.get(i).equalsIgnoreCase("Media")) {
                imageIdsArrayList.add(R.drawable.ic_media_library);
                colorIdsArrayList.add(R.color.media_library);
                titlesArrayList.add(name_list.get(i));

            }
            if(ids_img_list.get(i).equalsIgnoreCase("Event")) {
                imageIdsArrayList.add(R.drawable.ic_my_region);
                colorIdsArrayList.add(R.color.cl_redish);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Help")) {
                imageIdsArrayList.add(R.drawable.ic_help);
                colorIdsArrayList.add(R.color.help);
                titlesArrayList.add(name_list.get(i));
            }


            if(ids_img_list.get(i).equalsIgnoreCase("Sp")) {
                imageIdsArrayList.add(R.drawable.ic_my_region);//locate_sp
                colorIdsArrayList.add(R.color.locate_sp);
                titlesArrayList.add(name_list.get(i));

            }

            if(ids_img_list.get(i).equalsIgnoreCase("Calling")) {
                imageIdsArrayList.add(R.drawable.ic_my_region);//locate_sp
                colorIdsArrayList.add(R.color.calling_target);
                titlesArrayList.add(name_list.get(i));

            }
            if(ids_img_list.get(i).equalsIgnoreCase("Setting")) {
                imageIdsArrayList.add(R.drawable.ic_settings);
                colorIdsArrayList.add(R.color.settings);
                titlesArrayList.add(name_list.get(i));
            }

            if(ids_img_list.get(i).equalsIgnoreCase("Logout")) {
                imageIdsArrayList.add(R.drawable.ic_logout);
                colorIdsArrayList.add(android.R.color.black);
                titlesArrayList.add(name_list.get(i));


            }


        }



        for (int i = 0; i < ids_menu_list.size(); i++) {
            DrawerModel drawerModel = new DrawerModel();
            drawerModel.setColorId(colorIdsArrayList.get(i));
            drawerModel.setImageId(imageIdsArrayList.get(i));
            drawerModel.setTitle(titlesArrayList.get(i));
            drawerModel.setMenu_name(ids_img_list.get(i));
            drawerModel.setMenuid(Integer.parseInt(ids_menu_list.get(i)));
            drawerModel.setSelected(false);
            drawerModels.add(drawerModel);
        }

    }



    public void setFragment(int position,String Title) {
        setFragment(position,Title, null, null);
    }

    public void setFragment(int position, String title, NotificationModel notificationModel, String param) {

        boolean isSameFragment;
        if (currentFragmentPosition == position) {
            isSameFragment = true;
        } else {
            isSameFragment = false;
        }



        if (position == 0) {

          /*  if (iboFragment == null) {
                //  Toast.makeText(LandingActivity.this, "data is now null", Toast.LENGTH_LONG).show();
                if (notificationModel != null) {

                    iboFragment = IBOFragment.newInstance(true, notificationModel.getParam1());
                    //  currentFragment = iboFragment;

                } else if (param != null) {


                    iboFragment = IBOFragment.newInstance(true, param);
                    //  currentFragment = iboFragment;

                } else {

                    iboFragment = IBOFragment.newInstance(false, null);

                }

            } else {

                try {
                    getSupportFragmentManager().beginTransaction().remove(iboFragment).commit();
                    iboFragment = IBOFragment.newInstance(false, null);

                } catch (IllegalStateException e) {

                }
            }
            currentFragment = iboFragment;*/

        } else if (position == 1) {

          /*  try {
                if (status_gr == -1) {

                    if (pageFragment == null) {
                        pageFragment = new PageFragment();
                    }
                    currentFragment = pageFragment;
                    *//**//*
                        final com.netsurfnetwork.android.widgets.NetsurfDialog dialog = new com.netsurfnetwork.android.widgets.NetsurfDialog(this,
                                "Netsurf Everyday",
                                "Not Registered User!",
                                getResources().getString(com.netsurfnetwork.android.R.string.ok),
                                "");


                        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                            }
                        });

                        dialog.show();*//**//*

                } else {
                    fragmentVideoPlay = new FragmentVideoPlay();
                    currentFragment = fragmentVideoPlay;
                }

            } catch (Exception e) {

            }*/

        } else if (position == 2) {
/*

            try {


                //   if (live_mode.equalsIgnoreCase("video")) {

                Intent browserIntent = new Intent(LandingActivity.this, LinkActivity.class);
                startActivity(browserIntent);
                setNothingSelectedInDrawer();


            } catch (Exception e) {

            }
*/

        } else if (position == 3) {
          /*  cycleComparisonFragment = new CycleComparisonFragment();
            currentFragment = cycleComparisonFragment;*/

        } else if (position == 4) {
          /*  myRegionFragment = new MyRegionFragment();
            currentFragment = myRegionFragment;
*/
        } else if (position == 5) {
            RootFragment fragment = new RootFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.root_frame, fragment);

            transaction.commit();

        } else if (position == 6) {
     /*       if (shopOnlineFragment == null) {
                shopOnlineFragment = new ShopOnlineFragment();
            }
            currentFragment = shopOnlineFragment;
*/
        } else if (position == 7) {
            /*if (skipBusinessAnalyticsFragment) {
                Intent startStatusUpgrade = new Intent(LandingActivity.this,
                        BusinessAnalyticsSubTaskActivity.class);
                startStatusUpgrade.putExtra(Constants.INTENT_TAG_POSITION, Constants
                        .STATUS_UPGRADE);
                startActivity(startStatusUpgrade);
                return;

            } else {
                if (businessAnalyticsFragment == null) {
                    businessAnalyticsFragment = new BusinessAnalyticsFragment();
                }
                currentFragment = businessAnalyticsFragment;


            }*/

        } else if (position == 8) {
         /*   if (panFragment == null) {
                panFragment = new PanFragment();
            }
            currentFragment = panFragment;
*/
        } else if (position == 9) {
          /*  myAccountFragment = new MyAccountFragment();
            currentFragment = myAccountFragment;
*/
        } else if (position == 10) {
           /* if (actionReportsFragment == null) {
                actionReportsFragment = new ActionReportsFragment();
            }
            currentFragment = actionReportsFragment;
*/
        } else if (position == 11) {
          /*  prospectorFragment = new ProspectorFragment();
            currentFragment = prospectorFragment;*/


        } else if (position == 12) {
           /* try {
                if (clubNetSurfFragment == null) {
                    clubNetSurfFragment = new ClubNetSurfFragment();
                }
                currentFragment = clubNetSurfFragment;
            } catch (Exception e) {

            }
*/
        } else if (position == 13) {
           /* if (panFragment == null) {
                panFragment = new PanFragment();
            }
            currentFragment = panFragment;*/

        } else if (position == 14) {
        /*    if (mCustomerSupportFragment == null) {
                mCustomerSupportFragment = new CustomerSupportFragment();
            }
            currentFragment = mCustomerSupportFragment;*/

        } else if (position == 15) {
          /*  mediaLibraryFragment = new MediaLibraryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param", param);
            bundle.putParcelable(Constants.INTENT_TAG_DATA, notificationModel);
            mediaLibraryFragment.setArguments(bundle);
            currentFragment = mediaLibraryFragment;
*/
        } else if (position == 16) {


        } else if (position == 17) {


        } else if (position == 18) {


        } else if (position == 19) {


        } else if (position == 20) {

        } else if (position == 21) {

            return;

        } else {

          /*  homeFragment = new MetroLandFragment();
            currentFragment = homeFragment;*/
        }


    }












    private void menuItem() {

        UsApiInterface netsurfApiInterfaces = ApiClient.getApiClient(this,
                true);

        if (netsurfApiInterfaces != null) {
            ApplicationMenu.Request menu_item = new ApplicationMenu.Request();
            menu_item.setMyType("android");
            menu_item.setDashboard("0");

            Observable<ArrayList<ApplicationMenu.Response>> firebasedata = netsurfApiInterfaces.getAppMenu(menu_item);
            Timber.i("firebase id is %s ", "" + firebasedata);

            Subscription subscription = firebasedata.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ArrayList<ApplicationMenu.Response>>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            try {
                                Toast.makeText(LandingUsActivity.this, "error" + e.getMessage(), Toast.LENGTH_LONG).show();
                            } catch (NullPointerException ex) {
                                Timber.i("error while setting data not available  %s ",
                                        "" + ex.getMessage());
                            } catch (IllegalStateException ex) {
                                Timber.i("error while setting data not available  %s ",
                                        "" + ex.getMessage());
                            }
                        }

                        @Override
                        public void onNext(ArrayList<ApplicationMenu.Response> responsnew) {

                            try {
                                if (responsnew != null) {

                                    drawerModelsitem.clear();
                                    drawerModelsitem.addAll(responsnew);


                                        //      listraw =  new ArrayList<String>();
                                        //    list = (Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.drawer_menu_list)));
                                        list.clear();
                                        list_menu_id.clear();
                                        list_color_code.clear();
                                       /* for(int k = 0;k < drawerModelsitem.size()-1;k++)
                                        {
                                            list.add(drawerModelsitem.get(k).getMenuName());



                                        }*/
                                        for(int m = 0;m < drawerModelsitem.size();m++)
                                        {
                                            list.add(drawerModelsitem.get(m).getMenuName());
                                            list_menu_id.add(""+drawerModelsitem.get(m).getMenuId());
                                            list_color_code.add(drawerModelsitem.get(m).getImageName());


                                        }



                                    getIds(list,list_menu_id,list_color_code);
                                } else {
                                    Timber.d("region list response%s ", "empty.");

                                }
                            } catch (NullPointerException ex) {
                                ex.printStackTrace();
                            } catch (IllegalStateException ex) {

                            }
                        }
                    });

        } else {

        }

    }





    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int storage1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int loc = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int loc2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int call = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int contacts = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int SMS = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int Wcalendar = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);
        int Rcalendar = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);
        //   int contacts = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (storage1 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (loc2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (call != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (SMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (contacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (Wcalendar != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALENDAR);
        }
        if (Rcalendar != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CALENDAR);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
