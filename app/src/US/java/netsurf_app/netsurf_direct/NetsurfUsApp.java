package netsurf_app.netsurf_direct;

import android.app.Application;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import dagger.ObjectGraph;
import netsurf_app.netsurf_direct.daggerModules.AppModule;

/**
 * Created by Sagar on 16-10-2018.
 */

public class NetsurfUsApp extends Application {

    private static final String TAG = NetsurfUsApp.class.getSimpleName();

    private static NetsurfUsApp baseApplicationContext = null;
//    private LoggedInUser loggedInUser;
    // fonts
    private Typeface typefaceRobotoMedium = null;
    private Typeface typefaceRobotoLight = null;
    private Typeface typefaceRobotoRegular = null;
    private Typeface typefaceRobotoThin = null;
    private Typeface typefaceRobotoBlack = null;
    private Typeface typefaceRobotoCondensedLight = null;
    private Typeface typefaceRobotoCondensedRegular = null;
    private Typeface typefaceRobotoCondensedBold = null;
    private Typeface typefaceRobotoBold = null;
   // private ObjectGraph applicationGraph;

    private static final String RX_EXCEPTION_TO_IGNORE = "rx.exceptions.OnErrorNotImplementedException";
    // private static final String PARSE_CHANNEL = "Netsurf_Dev";
    private static final String PARSE_CHANNEL = "Netsurf";
    //private ArrayList<RegionListModel> regionListModels = new ArrayList<>();
    public static final String PARSE_API_KEY = "LYSqGRxixSPQ3mfNCGXfJnUGP012XNQlXDKwFoL1";
    public static final String PARSE_CLIENT_ID = "DNFgAxu04MeX2jj3StVdf5cK89LAU4farfTY4uWk";


    public static NetsurfUsApp getInstance() {

        return baseApplicationContext;
    }

    @Override
    public void onCreate() {
       // MultiDex.install(this);
        super.onCreate();
    //    initializeAppGraph();
      //  ActiveAndroid.initialize(this);
        baseApplicationContext = this;

        if (BuildConfig.DEBUG) {
           // Timber.plant(new Timber.DebugTree());
        } else {
           // Fabric.with(this, new Crashlytics());
         //   Timber.plant(new CrashlyticsTree());
        }

        initializeAppFonts();

    /*    RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable ex) {
                Timber.i(NetsurfApp.class.getName() + " rx java error detected : %s", "" + ex
                        .getMessage());
            }
        });
        // initializeParse(getApplicationContext());
        createBasicCategoryModel(true);

        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .name("Netsurf_REALM")
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);*/
    }

    private void initializeAppFonts() {
        try {
            typefaceRobotoBlack = Typeface.createFromAsset(getAssets(), "fonts/roboto_black.ttf");
            typefaceRobotoCondensedLight = Typeface.createFromAsset(getAssets(),
                    "fonts/roboto_condensed_light.ttf");
            typefaceRobotoCondensedRegular = Typeface.createFromAsset(getAssets(),
                    "fonts/roboto_condensed_regular.ttf");
            typefaceRobotoCondensedBold = Typeface.createFromAsset(getAssets(),
                    "fonts/roboto_condensed_bold.ttf");
            typefaceRobotoLight = Typeface.createFromAsset(getAssets(), "fonts/roboto_light.ttf");
            typefaceRobotoMedium = Typeface.createFromAsset(getAssets(), "fonts/roboto_medium.ttf");
            typefaceRobotoRegular = Typeface.createFromAsset(getAssets(), "fonts/roboto_regular.ttf");
            typefaceRobotoThin = Typeface.createFromAsset(getAssets(), "fonts/roboto_thin.ttf");
            typefaceRobotoBold = Typeface.createFromAsset(getAssets(), "fonts/roboto_bold.ttf");
        }catch (Exception e)
        {


        }
    }


    public Typeface getTypefaceRobotoMedium() {
        return typefaceRobotoMedium;
    }

    public Typeface getTypefaceRobotoLight() {
        return typefaceRobotoLight;
    }

    public Typeface getTypefaceRobotoRegular() {
        return typefaceRobotoRegular;
    }

    public Typeface getTypefaceRobotoThin() {
        return typefaceRobotoThin;
    }

    public Typeface getTypefaceRobotoBlack() {
        return typefaceRobotoBlack;
    }

    public Typeface getTypefaceRobotoCondensedLight() {
        return typefaceRobotoCondensedLight;
    }

    public Typeface getTypefaceRobotoCondensedRegular() {
        return typefaceRobotoCondensedRegular;
    }

    public Typeface getTypefaceRobotoCondensedBold() {
        return typefaceRobotoCondensedBold;
    }

    public Typeface getTypefaceRobotoBold() {
        return typefaceRobotoBold;
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

/*    public ObjectGraph getApplicationGraph() {
        if (applicationGraph != null) {
            return applicationGraph;
        } else {
            initializeAppGraph();
            return applicationGraph;
        }
    }

    public void setApplicationGraph(ObjectGraph applicationGraph) {
        this.applicationGraph = applicationGraph;
    }

    public void initializeAppGraph() {
        //create object graph
        applicationGraph = ObjectGraph.create(getModules().toArray());
        applicationGraph.inject(this);
    }*/

}
