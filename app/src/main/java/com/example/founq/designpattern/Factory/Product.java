package com.example.founq.designpattern.Factory;

import android.util.Log;

//抽象产品类
public abstract class Product {

    public abstract void show();

    public static class ProductA extends Product {
        @Override
        public void show() {
            Log.e("Factory","product A");
        }
    }

    public static class ProductB extends Product {
        @Override
        public void show() {
            Log.e("Factory","product B");
        }
    }

}
