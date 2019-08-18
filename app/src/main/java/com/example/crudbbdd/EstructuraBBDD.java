package com.example.crudbbdd;

public class EstructuraBBDD {

    private EstructuraBBDD() {
    }

    /* Inner class that defines the table contents */
    //public static class FeedEntry implements BaseColumns {
    public static final String TABLE_NAME = "datosPeronales";
    public static final String COLUMN_NAME_ID = "Id";
    public static final String COLUMN_NAME_NAME = "Nombre";
    public static final String COLUMN_NAME_LASTNAME = "Apellido";
    //}
    //private static final String TEXT_TYPE = "TEXT";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBBDD.TABLE_NAME + " (" +
                    EstructuraBBDD.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    EstructuraBBDD.COLUMN_NAME_NAME + " TEXT," +
                    EstructuraBBDD.COLUMN_NAME_LASTNAME + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBBDD.TABLE_NAME;

}
