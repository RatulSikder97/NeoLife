package com.ratulsikder.neolife;

import android.provider.BaseColumns;

public final class TaskContract {

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;

    public  static  final String SQL_CREATE_ENTRIES  = "CREATE TABLE "+ TaskEntry.TABLE_NAME + " (" +
                                                TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                TaskEntry.COLUMN_NAME_TITLE + " TEXT, "+
                                                TaskEntry.COLUMN_NAME_SUBTITLE + " TEXT)";
    private TaskContract() {}




    public static  class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
