DROP TABLE IF EXISTS NotificationModel;
create table NotificationModel(CustID INTEGER, "action" VARCHAR, alert VARCHAR, title VARCHAR, Param1 VARCHAR, Param2 VARCHAR, Param3 VARCHAR , NotificationId INTEGER, isRead INTEGER);
ALTER TABLE LoggedInUserDetail ADD (COLUMN IsIBO CHAR(5),
                                    COLUMN IsOnline CHAR(5));