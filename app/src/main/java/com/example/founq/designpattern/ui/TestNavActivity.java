package com.example.founq.designpattern.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.founq.designpattern.R;
import com.example.founq.designpattern.recyclerTest.Test;
import com.example.founq.designpattern.recyclerTest.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestNavActivity extends AppCompatActivity {

    NavigationView navView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    private List<Test> testList = new ArrayList<>();
    private TestAdapter testAdapter;

    private Test[] tests = {new Test("123"),new Test("456"),new Test("789"),new Test("987"),new Test("654"),new Test("321")};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        toolbar = findViewById(R.id.toolbar_test);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.hide();
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_test1);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"message",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(TestNavActivity.this,"msg",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        recyclerView = findViewById(R.id.recyler_view);
        testList.clear();
        for(int i = 0; i < tests.length;i++){
            testList.add(tests[i]);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        testAdapter = new TestAdapter(testList);
        recyclerView.setAdapter(testAdapter);
    }
}
