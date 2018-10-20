package netsurf_app.netsurf_direct.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import netsurf_app.netsurf_direct.R;
import timber.log.Timber;

/**
 * Created by Sagar on 20-10-2018.
 */

public class RootFragment  extends Fragment {

    private static final String TAG = "RootFragment";
    private int categorycode;
    private int keyval;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_fragment, container, false);

        HomeFragment fragment = new HomeFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.root_frame, fragment);

        transaction.commit();

        return view;
    }


}
