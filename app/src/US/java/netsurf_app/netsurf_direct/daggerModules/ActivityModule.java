package netsurf_app.netsurf_direct.daggerModules;

import android.app.Activity;
/*

import dagger.Module;
import netsurf_app.netsurf_direct.NetsurfUsApp;
import netsurf_app.netsurf_direct.activity.NetsurfUsBaseActivity;

@Module(
        injects = {
                NetsurfUsBaseActivity.class,

        },
        addsTo = NetsurfUsApp.class,
        library = true,
        complete = false,
        overrides = true
)*/
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /*@Provides @Singleton
    public LoggedInUser provideLoggedInUser() {
        if (loggedInUser != null) {
            return loggedInUser;
        } else {
            return loggedInUser = LoggedInUser.getLoggedInUser();
        }
    }
*/
}