package netsurf_app.netsurf_direct.model;

/**
 * Created by Sagar on 21-09-2017.
 */
public class ApplicationMenu {

    public static class Request {

  /*{"myType":"android","dashboard":"0"}
*/
          private String myType;
        private String dashboard;

    public String getmyType() {
        return myType;
    }


    public void setMyType(String myType) {
        this.myType = myType;
    }

        public String getDashboard() {
            return dashboard;
        }


        public void setDashboard(String dashboard) {
            this.dashboard = dashboard;
        }
}


    public static class Response {


        /**
         [
         {
         "MenuId": 17,
         "MenuName": "JIYO Retailer",
         "IsEnabled": true,
         "MenuOrder": 0
         "IsAndroid": true,
         "IsDashBoard": false
         }
         ]
         */
        private int MenuId;
        private String MenuName;
        private boolean IsEnabled;
        private int  MenuOrder;
        private String ImageName;
        private boolean IsAndroid;
        private boolean IsDashBoard;

        // private String VideoServerFilePath;

        public int getMenuId() {
            return MenuId;
        }

        public void setMenuId(int MenuId) {
            this.MenuId = MenuId;
        }

        public String getMenuName() {
            return MenuName;
        }

        public void setMenuName(String MenuName) {
            this.MenuName = MenuName;
        }

        public boolean getIsEnabled() {
            return IsEnabled;
        }

        public void setIsEnabled(boolean IsEnabled) {
            this.IsEnabled = IsEnabled;
        }
        public int getMenuOrder() {
            return MenuOrder;
        }

        public void setMenuOrder(int MenuOrder) {
            this.MenuOrder = MenuOrder;
        }

        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String ImageName) {
            this.ImageName = ImageName;
        }


        public boolean getIsAndroid() {
            return IsAndroid;
        }

        public void setIsAndroid(boolean IsAndroid) {
            this.IsAndroid = IsAndroid;
        }

        public boolean getIsDashBoard() {
            return IsDashBoard;
        }

        public void setDashBoard(boolean IsDashBoard) {
            this.IsDashBoard = IsDashBoard;
        }




    }







}
