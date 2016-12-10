package com.example.maart.stronglife2;


public class StrongLifeDbSchema {

    public static final class UsersTable {

        public static final String name = "users";

        public static final class Cols {

            public static final String USERID = "id";
            public static final String FIRSTNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String ADDRESSLINE1 = "addressline1";
            public static final String CITY = "city";
            public static final String COUNTY = "county";
            public static final String ZIPCODE = "zip";
            public static final String PHONENUMBER = "phone";
            public static final String ISSTUDENT = "isstudent"; //Boolean
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
        }
    }


    public static final class MembershipTable {

        public static final String name = "memberships";

        public static final class Cols {

            public static final String MEMBERSHIPID = "memberid";
            public static final String PURCHASEDATE = "purchasedate";
            public static final String EXPIRYDATE = "expiredate";
            public static final String MEMBERSHIPTYPE= "membershiptype"; //char
            public static final String ACTIVE = "active"; //Boolean
            public static final String USERID = "id"; //foreign key
        }
    }



    public static final class CoursesTable {

        public static final String name = "courses";

        public static final class Cols {

            public static final String COURSEID = "courseid";
            public static final String COURSENAME = "coursename"; //varchar or string
            public static final String USERID = "userid"; //foreign key
        }
    }

    public static final class CoursesDetailsTable {

        public static final String name = "coursedetails";

        public static final class Cols {

            public static final String COURSEID = "courseid";
            public static final String TIMEOFCOURSE = "timeofcourse";
            public static final String DATEOFCOURSE = "dateofcourse";
            public static final String COURSESDETAILSID = "coursesdetailsid";
        }
    }

    public static final class NotificationsTable {

        public static final String name = "notifications";

        public static final class Cols {

            public static final String NOTIFICATIONID = "notificationid";
            public static final String NOTIFICATIONTITLE = "notificationtitle"; //varchar or string
            public static final String CREATED = "created"; //created at date
            public static final String CHECKED = "checked"; //bool
        }
    }

    public static final class MyCoursesTable {

        public static final String name = "mycourses";

        public static final class Cols {

            public static final String MYCOURSEID = "mycourseid"; // int
            public static final String COURSEID = "courseid"; // foreign key int
            public static final String CREATED = "created"; // date
        }
    }

}
