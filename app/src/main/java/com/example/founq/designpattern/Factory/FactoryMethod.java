package com.example.founq.designpattern.Factory;

public abstract class FactoryMethod {

    public abstract Product create();

    public static class FactoryA extends FactoryMethod {

        @Override
        public Product create() {
            return new Product.ProductA();
        }
    }
    public static class FactoryB extends FactoryMethod {

        @Override
        public Product create() {
            return new Product.ProductB();
        }
    }


}
