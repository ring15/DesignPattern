package com.example.founq.designpattern.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.founq.designpattern.Factory.Factory;
import com.example.founq.designpattern.Factory.FactoryMethod;
import com.example.founq.designpattern.Factory.Product;
import com.example.founq.designpattern.Observer.Person;
import com.example.founq.designpattern.Observer.Post;
import com.example.founq.designpattern.R;
import com.example.founq.designpattern.Singleton.Singleton;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnObserver,btnFactory,btnSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnObserver = findViewById(R.id.btn_observer);
        btnFactory = findViewById(R.id.btn_factory);
        btnSingle = findViewById(R.id.btn_single);

        btnObserver.setOnClickListener(this);
        btnFactory.setOnClickListener(this);
        btnSingle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_observer:
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                testObserver();
                break;
            case R.id.btn_factory:
                testFactory();
                break;
            case R.id.btn_single:
                testSingleton();
                break;
                default:
                    break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if((grantResults.length > 0&&grantResults[0] == PackageManager.PERMISSION_GRANTED)){

                } else {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }

    private void testSingleton() {
        //不能直接传this，会使singleton一直持有MainActivity到退出程序，导致oom
        Singleton singleton = Singleton.getInstance(getApplicationContext());
        int mScreenWidth = singleton.getScreenWidth();
        int mScreenHeight = singleton.getScreenHeight();
        Log.e("Singleton",mScreenWidth+"+"+mScreenHeight);
    }

    private void testFactory(){
        FactoryMethod factoryA = new FactoryMethod.FactoryA();
        Product productA = factoryA.create();
        productA.show();

        FactoryMethod factoryB = new FactoryMethod.FactoryB();
        Product productB = factoryB.create();
        productB.show();

        Factory.create(Product.ProductA.class).show();
        Factory.create(Product.ProductB.class).show();
    }

    private void testObserver() {
        Observer observer = new Person("Susan");
        Observer observer1 = new Person("Bob");
        Observable observable = new Post();
        observable.addObserver(observer);
        observable.addObserver(observer1);
        observable.notifyObservers("快递到了，请下楼领取");
    }


}
