package netsurf_app.netsurf_direct.utilies;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Sagar on 17-10-2018.
 */

public class Utils {

    private static final String MOBILE_NUMBER_MATCHER = "^(\\+91[\\-\\s]?)?[789]\\d{9}$";
    private static final String LANDLINE_NUMBER_MATCHER = "^[0-9]\\d{2,4}[\\-\\s]?\\d{6,8}$";
    // private static LoadingDialog progressDialog;
    private static int centerPoint = 0;
    private static int deviceHeight = 0;
    //private static final String LANDLINE_NUMBER_MATCHER = "^[\\d]{3,4}[\\-\\s]*[\\d]{6,7}$";

    public static boolean isNetworkAvailable(Activity context) {
        try {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo()
                    .isAvailable() && connManager.getActiveNetworkInfo().isConnected()) {
                return true;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }
        return false;
    }


    public static boolean isNetworkAvailables(Context context) {
        try {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo()
                    .isAvailable() && connManager.getActiveNetworkInfo().isConnected()) {
                return true;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }
        return false;
    }

}