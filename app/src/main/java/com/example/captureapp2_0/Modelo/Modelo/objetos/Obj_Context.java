package com.example.captureapp2_0.Modelo.Modelo.objetos;

import android.app.Application;
import android.content.Context;

public class Obj_Context {
    private static Context context;

    public static Application application;

    public Obj_Context(Context context) {
        this.context = context;
    }

    public static void setApplication(Application application) {
        Obj_Context.application = application;
    }

    public static Application getApplication() {
        return application;
    }

    public static Context getContext() {
        return context;
    }


}
