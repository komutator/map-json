package com.example;

class HexToPos {

    static double HexToLat(long hex){
        return hex * 90 / Math.pow(2,28);
//        return hex * 90 / 100_000_000;

    }

    static double HexToLon(long hex){
        return  hex * 180 / Math.pow(2,28);
//        return  hex * 180 / 100_000_000;
    }

}
