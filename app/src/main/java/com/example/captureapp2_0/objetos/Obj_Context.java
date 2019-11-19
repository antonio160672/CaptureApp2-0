package com.example.captureapp2_0.objetos;

import android.content.Context;

public class Obj_Context {
    public static Context context;

    public Obj_Context(Context context) {
        this.context = context;
    }
    public static Context getContext() {
        return context;
    }


}
