package com.skripsi.pendeteksikebakaran.storage;

import android.skripsi.pendeteksikebakaran.BuildConfig;

import java.io.File;

public class Common {
    //    public static final String BASE_URL = "http://192.168.0.7:8080";
//    public static final String PATH_HTTP = "http://192.168.0.7:8080/alivery/api/";
    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String SUB_PATH = BuildConfig.SUB_PATH;
//    public static final String SUB_PATH = "/bc_solo/api_form/";


    public static final String FUNCTION_MENU_HOME = "home";
    public static final String FUNCTION_MENU_ACTIVITY = "activity";
    public static final String FUNCTION_MENU_TICKET = "ticket";

    public static final String FUNCTION_TODO_VISIT = "visit";
    public static final String FUNCTION_TODO_CREATE_TICKET = "create_ticket";


    public static final String FUNCTION_MENU_SETTING = "setting";
    public static final String FUNCTION_MENU_LOGOUT = "logout";
    public static final String FUNCTION_MENU_TERMSCONDITION = "terms_condition";
    public static final String FUNCTION_MENU_CHANGEPASSWORD = "change_password";
    public static final String FUNCTION_MENU_SYCN = "sycn";
    public static final String FUNCTION_MENU_CHANGE_THEME = "change_theme";
    public static final String FUNCTION_MENU_REGENERATEFORM = "regenerate_form";
    public static final String FUNCTION_MENU_MESSAGE = "message";
    public static final String FUNCTION_MENU_NEWS = "news";
    public static final String FUNCTION_CATALOG = "catalog";
    public static final String FUNCTION_APPROVE_ITEM = "approve_item";
    public static final String FUNCTION_ABSENT = "absent";


    public static final int STATUS_JOB_ACTIVE = 0;
    public static final int STATUS_JOB_DRAFT = 1;
    public static final int STATUS_JOB_DONE = 2;
    public static final int STATUS_JOB_FINALIZE = 3;


//                                <option value="0">Open</option>
//                                <option value="1">On progress</option>
//                                <option value="2">Solved</option>
//                                <option value="3">Closed</option>

    public static final int STATUS_TICKET_OPEN = 0;
    public static final int STATUS_TICKET_ON_PROGRESS = 1;
    public static final int STATUS_TICKET_SOLVED = 2;
    public static final int STATUS_TICKET_DONE = 3;

    public static final int STATUS_APP_ACTIVE = 2;
    public static final int STATUS_APP_DONE = 1;


    public static final int FROM_PHOTO_VISIT = 1;
    public static final int FROM_PHOTO_TIKET = 2;

    public static final float ratio = (float) 0.58;
    public static final String N_FIRE_BASE = "FIRE_BASE";
    public static final int N_MESSAGE_ID = 888;
    public static final int N_ACTIVE_JOB_ID = 2;

    public static File PATH_STORAGE_CAMERA = new File(
            android.os.Environment.getExternalStorageDirectory(),
            "Android/data/com.nava//Nava_Camera");

}
