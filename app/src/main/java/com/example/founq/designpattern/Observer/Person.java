package com.example.founq.designpattern.Observer;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class Person implements Observer {

    private String name;//名字
    public Person(String name) {
        this.name = name;
    }


    @Override
    public void update(Observable o, Object arg) {
        Log.e("Observer",name + ",收到了信息:" + arg+"决定去取快递.");
    }
}
