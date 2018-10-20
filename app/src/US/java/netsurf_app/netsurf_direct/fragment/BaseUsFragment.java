package netsurf_app.netsurf_direct.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import netsurf_app.netsurf_direct.activity.LandingUsActivity;

/**
 * Created by Sagar on 16-10-2018.
 */

public class BaseUsFragment  extends Fragment {

    public final int HEADER_VIEW = 0;
    public final int DETAILS_VIEW = 1;
    public final int DATA_NOT_AVAILABLE_VIEW = 2;
    public final int PROGRESS_VIEW = 3;
    public final int INTERNAL_DATA_NOT_AVAILABLE_VIEW = 4;
    private LandingUsActivity landingActivity;

    public BaseUsFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        landingActivity = ((LandingUsActivity) getActivity());
    }

 /*   public void setFragment(int position) {
        landingActivity.setFragment(position);
    }*/
  /*  public void setFragment(int position,String title) {
        landingActivity.setFragment(position);
    }*/
}
