package netsurf_app.netsurf_direct.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;



import java.util.ArrayList;
import java.util.List;


import netsurf_app.netsurf_direct.R;
import netsurf_app.netsurf_direct.model.DrawerModel;
import netsurf_app.netsurf_direct.widgets.TextViewFont;

/**
 * Created by shivam on 6/5/15.
 */
public class DrawerListAdapter extends ArrayAdapter<DrawerModel> {

    private Activity activity = null;
    private ArrayList<DrawerModel> data = new ArrayList<>();

    private LayoutInflater layoutInflater = null;
    private int resourceId = 0;
    private  int coreTypeId;
   // LoggedInUser loggedInUser;

    public DrawerListAdapter(Activity activity, int resourceId, int coreTypeId, ArrayList<DrawerModel> data) {

        super(activity, resourceId, data);

        if (activity != null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            this.data = data;
            this.resourceId = resourceId;
            this.coreTypeId = coreTypeId;
            this.activity = activity;
        }
    }


    /*public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0;
    }
*/
   /* public boolean isEnabled(int position) {

        if(coreTypeId == 0) {
            // return false if position == position you want to disable
            return true;
        }else
        {
            return false;
        }
    }*/




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
      //  Toast.makeText(activity,position +" id " +coreTypeId, Toast.LENGTH_LONG).show();
        DrawerModel drawerModel = data.get(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextViewFont) convertView.findViewById(R.id
                    .txt_drawer_list_title);
            viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id
                    .img_drawer_list_icon);
            viewHolder.imageViewArrow = (ImageView) convertView.findViewById(R.id
                    .img_drawer_list_arrow);
            viewHolder.relativeLayoutImage = (RelativeLayout) convertView.findViewById(R.id
                    .rel_img);
            viewHolder.notification = (TextViewFont) convertView.findViewById(R.id
                    .text);
           /* viewHolder.rlayout = (RelativeLayout) convertView.findViewById(com.netsurfnetwork.android.R.id
                    .rl1);*/

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
/*
        loggedInUser = LoggedInUser.getLoggedInUser();
        if (loggedInUser.isIBO()) {

            if(data.get(position).getMenu_name().equalsIgnoreCase("Notify")) {

                viewHolder.notification.setVisibility(View.VISIBLE);
            }else {
                viewHolder.notification.setVisibility(View.INVISIBLE);
            }

            List<NotificationModel> unReadNotifications = NotificationModel.getAllUnReadNotification();
            if(unReadNotifications != null) {
                viewHolder.notification.setText("" + unReadNotifications.size());
            }else {
                viewHolder.notification.setText("0");
            }

        }else
        {
           // Toast.makeText(activity,position +" id " +coreTypeId, Toast.LENGTH_LONG).show();
            if(data.get(position).getMenu_name().equalsIgnoreCase("Notify")) {
                viewHolder.notification.setVisibility(View.VISIBLE);
            }else {
                viewHolder.notification.setVisibility(View.INVISIBLE);

            }

            List<NotificationModel> unReadNotifications = NotificationModel.getAllUnReadNotification();
            if(unReadNotifications != null) {
                viewHolder.notification.setText("" + unReadNotifications.size());
            }else {
                viewHolder.notification.setText("0");
            }
        }*/
        viewHolder.textViewTitle.setText(drawerModel.getTitle());
        viewHolder.imageViewIcon.setImageDrawable(activity.getResources().getDrawable(drawerModel
                .getImageId()));


  /*   if(position == 0)
   {

       viewHolder.rlayout.setVisibility(android.view.View.INVISIBLE);
    *//*viewHolder.relativeLayoutImage.getLayoutParams().height = 0;
    viewHolder.relativeLayoutImage.getLayoutParams().width = 0;

       viewHolder.textViewTitle.getLayoutParams().height = 0;
       viewHolder.textViewTitle.getLayoutParams().width = 0;*//*


   }*/
        if (!drawerModel.isSelected()) {
            viewHolder.relativeLayoutImage.setBackgroundColor(activity.getResources().getColor
                    (drawerModel
                            .getColorId()));
            viewHolder.textViewTitle.setBackgroundColor(activity.getResources().getColor(android
                    .R.color.white));
            viewHolder.textViewTitle.setTextColor(activity.getResources().getColor(android
                    .R.color.black));
            viewHolder.imageViewArrow.setImageDrawable(activity.getResources().getDrawable(R
                    .drawable.ic_arrow_right));

        } else {
            viewHolder.relativeLayoutImage.setBackgroundColor(activity.getResources().getColor(R
                    .color.drawer_selected_color));
            viewHolder.textViewTitle.setBackgroundColor(activity.getResources().getColor(R.color
                    .drawer_selected_color));
            viewHolder.imageViewArrow.setImageDrawable(activity.getResources().getDrawable(R
                    .drawable.ic_arrow_white));
            viewHolder.textViewTitle.setTextColor(activity.getResources().getColor(android
                    .R.color.white));
        }
        return convertView;
    }

    private static class ViewHolder {
        private RelativeLayout relativeLayoutImage;
        private TextViewFont textViewTitle;
        private ImageView imageViewIcon;
        private ImageView imageViewArrow;
        private TextViewFont notification;
    }
}