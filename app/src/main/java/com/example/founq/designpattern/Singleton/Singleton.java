package com.example.founq.designpattern.Singleton;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.ref.WeakReference;

public class Singleton implements Serializable {

    private static boolean flag = true;
    //view和context相似，也会造成oom，解决方案是采用弱引用
    private WeakReference<View> view = null;
    //context会造成内存泄漏
    private static Context mContext;
    private WindowManager windowManager;;
    private DisplayMetrics dm = new DisplayMetrics();


    private Singleton(){
        //可以通过当第二次调用构造函数时抛出异常来防止破坏单例
        if(flag){
            flag = !flag;
        } else {
            throw new RuntimeException("单例模式被破坏");
        }

        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
    }

    public static Singleton getInstance(Context context){
        mContext = context;
        return SingletonHoledew.sInstance;
    }

    public void setView(View view){
        this.view = new WeakReference<>(view);
    }

    private static class SingletonHoledew{
        private static final Singleton sInstance = new Singleton();
    }

    //反序列化也会破坏单例模式，可以重写readResolve方法避免
    private Object readResolve() throws ObjectStreamException{
        return SingletonHoledew.sInstance;
    }

    public int getScreenWidth(){
        return dm.widthPixels;
    }

    public int getScreenHeight(){
        return dm.heightPixels;
    }

}
