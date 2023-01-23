package com.ratulsikder.neolife;

import android.provider.BaseColumns;

public final class TaskContract {

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;

    public  static  final String SQL_CREATE_ENTRIES  = "CREATE TABLE "+ TaskEntry.TABLE_NAME + " (" +
                                                TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                TaskEntry.COLUMN_NAME_TITLE + " TEXT, "+
                                                TaskEntry.COLUMN_NAME_SUBTITLE + " TEXT, "+
                                                TaskEntry.COLUMN_NAME_DESCRIPTION + " TEXT, "+
                                                TaskEntry.COLUMN_NAME_DUE_DATE + " DATE, "+
                                                TaskEntry.COLUMN_NAME_CREATED_DATE + " DATE, "+
                                                TaskEntry.COLUMN_NAME_UPDATED_DATE + " DATE)";
    private TaskContract() {}




    public static  class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DUE_DATE = "due_date";
        public static final String COLUMN_NAME_CREATED_DATE = "created_date";
        public static final String COLUMN_NAME_UPDATED_DATE = "updated_date";
    }
}
