package netsurf_app.netsurf_direct.utilies;

import android.provider.ContactsContract;


import java.util.ArrayList;
import java.util.List;

import netsurf_app.netsurf_direct.model.AdvertiseModel;
import netsurf_app.netsurf_direct.model.ApplicationMenu;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Sagar on 17-10-2018.
 */

public interface UsApiInterface {


    @GET("/GetAdvertiseDetail")
    Observable<ArrayList<AdvertiseModel.Response>>  getAddDetails();
    @POST("/getApplicationMenu")
    Observable<ArrayList<ApplicationMenu.Response>> getAppMenu(@Body ApplicationMenu.Request request);


}