package com.example.yueuy.dream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yueuy on 18-1-29.
 */

public class UserDataManager {

    private static final String DB_NAME = "user_data";
    private static final String TABLE_NAME = "users";
    private static final String ID = "id";
    private static final String USER_NAME = "user_name";
    private static final String USER_PWD = "user_pwd";
    private static final int DB_VERSION = 0;
    private Context mContext = null;

    private static final String DB_CREATE = "CREATE TABLE "+ TABLE_NAME + " ( "+
            ID + " integer primary key,"+
            USER_NAME + " varchar, "+
            USER_PWD + "varchar );";

    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mManagementHelper = null;

    private static class DataBaseManagementHelper extends SQLiteOpenHelper{
        DataBaseManagementHelper(Context context){
            super(context, DB_NAME, null ,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
            onCreate(db);
        }
    }

    public UserDataManager(Context context){
        mContext = context;
    }

    public void openDatabase() throws SQLException {
        mManagementHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mManagementHelper.getWritableDatabase();
    }

    public void closeDatabase() throws SQLException{
        mManagementHelper.close();
    }

    public long insertUserData(UserData userData){
        String userName = userData.getAccount();
        String userPwd = userData.getPassword();
        ContentValues val = new ContentValues();
        val.put(USER_NAME,userName);
        val.put(USER_PWD,userPwd);
        return mSQLiteDatabase.insert(TABLE_NAME,ID,val);
    }

    public Cursor getUserData(int id) throws SQLException {
        Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME,null,ID +"="+id, null,null,null,null,null);
        if (mCursor !=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getAllUserData(){
        return mSQLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
    }

    public boolean deleteUserData(int id){
        return mSQLiteDatabase.delete(TABLE_NAME ,ID+"="+id,null)>0;
    }

    public boolean check(String name){
        boolean result = false ;
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME,null,USER_NAME+"="+name,null,null,null,null);
        if (mCursor != null){
            result =  true;
            mCursor.close();
        }
        return result;
    }

    public boolean login(String name ,String pwd){
        boolean result = false;
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME,null,USER_NAME+"="+name+" and "+USER_PWD+"="+pwd,null,null,null,null);
        if (mCursor != null){
            result = true;
            mCursor.close();
        }
        return result;
    }
}
