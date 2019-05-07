package com.example.founq.designpattern.Observer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Post extends Observable {

    private ArrayList<Observer> observers = new ArrayList<>();


    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        observers.add(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
        for (Observer observer : observers){
            observer.update(this,arg);
        }
    }
}
