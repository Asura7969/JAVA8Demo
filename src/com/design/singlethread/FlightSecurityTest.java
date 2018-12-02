package com.design.singlethread;

public class FlightSecurityTest{

    static class Passengers extends Thread{
        //机场安检类
        private final FlightSecurity flightSecurity;
        //旅客的身份证
        private final String idCard;
        //旅客的登机牌
        private final String boardingPass;
        //构造旅客时传入的身份证、登机牌以及机场安检类
        public Passengers(FlightSecurity flightSecurity,String idCard,String boardingPass){
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                //旅客不断地安检
                flightSecurity.pass(boardingPass,idCard);
            }
        }
    }

    public static void main(String[] args) {
        //定义三个旅客,身份证和登机牌首字母相同
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity,"A123456","AF123456").start();
        new Passengers(flightSecurity,"B123456","BF123456").start();
        new Passengers(flightSecurity,"C123456","CF123456").start();
    }

}
