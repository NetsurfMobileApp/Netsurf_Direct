DROP TABLE IF EXISTS account_info;
DROP TABLE IF EXISTS clubwise_phase_target;
DROP TABLE IF EXISTS LoggedInUserDetail;
DROP TABLE IF EXISTS phase_wise_to;
create table LoggedInUserDetail(Id INTEGER, ClubId INTEGER, CustId INTEGER, DistrictId INTEGER, IsTeamLeader INTEGER, LoginID VARCHAR, Name VARCHAR, Password VARCHAR, RefNo INTEGER, StateId INTEGER, TeamId INTEGER, LoginTokenNo VARCHAR);
create table clubwise_phase_target(Id INTEGER, AchievedCalling INTEGER, AchievedTO INTEGER, CNStatus VARCHAR, DailyCallingTarget INTEGER, HeadingStr VARCHAR, Leg1 INTEGER, Leg2 INTEGER, RemainingCalling INTEGER,RemainingTO INTEGER, Repurchases INTEGER, TOTargetForPhase INTEGER);
create table phase_wise_to(Id INTEGER, CurrArchive INTEGER, CycleName VARCHAR, Cycleid INTEGER, Flag INTEGER, Leg1TO INTEGER, Leg2TO INTEGER, Leg3TO INTEGER, Leg4TO INTEGER, Leg5TO INTEGER, Payout INTEGER, QuarterName VARCHAR, RepurchaseTO INTEGER);