package netsurf_app.netsurf_direct.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Arrays;
import java.util.List;

//import javax.inject.Inject;

import butterknife.ButterKnife;
//import dagger.ObjectGraph;
import netsurf_app.netsurf_direct.NetsurfUsApp;
import netsurf_app.netsurf_direct.R;
import netsurf_app.netsurf_direct.daggerModules.ActivityModule;

public abstract class NetsurfUsBaseActivity extends AppCompatActivity {

    public static final String POSITION_TAG = "position";
    public Toolbar toolbar;
    /*@Inject
    public LoggedInUser loggedInUser;*/
    int custId;
  //  private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

      /*  activityGraph = ((NetsurfUsApp) getApplication()).getApplicationGraph().plus(getModules()
                .toArray());
        activityGraph.inject(this);*/
   //     Timber.d(NetsurfUsBaseActivity.class.getName() + " onCreate");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
      /*  if (loggedInUser == null || loggedInUser.getCustId() == 0) {
            //finish activity and go to login activity
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();
        } else {
            Timber.i("CustId:" + loggedInUser.getCustId());
            custId = loggedInUser.getCustId();
        }*/
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
     /*   loggedInUser = ((NetsurfApp) getApplication()).getLoggedInUser();
        if (loggedInUser == null || loggedInUser.getCustId() == 0) {
            //finish activity and go to login activity
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();
        } else {
            custId = loggedInUser.getCustId();
        }*/
    }

    public int getCustId() {

        return custId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // ButterKnife.c(this);
    }

    protected abstract int getLayoutResource();
}
