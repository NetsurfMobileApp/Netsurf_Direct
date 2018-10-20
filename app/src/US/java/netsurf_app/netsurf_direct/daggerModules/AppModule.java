package netsurf_app.netsurf_direct.daggerModules;




import netsurf_app.netsurf_direct.NetsurfUsApp;

/*
 * Created by chetan on 02/07/15.
 *//*
@Module(injects = {
        NetsurfUsApp.class
},
        library = true,
        complete = false
)*/
public class AppModule {
    NetsurfUsApp netsurfApp;
  //  LoggedInUser loggedInUser;

    public AppModule(NetsurfUsApp netsurfApp) {
        this.netsurfApp = netsurfApp;
    }

    /*@Provides
    @Singleton
    public NetsurfApp provideApplication() {
        return netsurfApp;
    }*/
/*
    @Provides
    @Singleton
    public LoggedInUser provideLoggedInUser() {
        if (loggedInUser != null) {
            return loggedInUser;
        } else {
            return loggedInUser = LoggedInUser.getLoggedInUser();
        }*/
    }
//}
