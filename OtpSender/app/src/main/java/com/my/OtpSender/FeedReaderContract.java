package com.my.OtpSender;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "msghistory.db";
        public static final String TABLE_NAME = "msghistory";
        public static final String COLUMN1_NAME = "name";
        public static final String COLUMN2_NAME = "dateTime";
        public static final String COLUMN3_NAME = "otp";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                        FeedReaderContract.FeedEntry.COLUMN1_NAME + " TEXT," +
                        FeedReaderContract.FeedEntry.COLUMN2_NAME + " TEXT," +
                        FeedReaderContract.FeedEntry.COLUMN3_NAME + " TEXT)";

    }
}