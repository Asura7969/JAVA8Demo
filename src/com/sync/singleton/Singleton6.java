package com.sync.singleton;

public class Singleton6 {

    private byte[] data = new byte[1024];

    private Singleton6(){}

    private enum EnumHolder{
        INSTANCE;
        private Singleton6 instance;
        EnumHolder() {
            this.instance = new Singleton6();
        }

        private Singleton6 getInstance(){
            return instance;
        }
    }

    public static Singleton6 getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }

}
