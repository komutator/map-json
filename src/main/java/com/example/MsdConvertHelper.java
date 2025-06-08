package com.example;

public class MsdConvertHelper {

    static String ToVoiceMessagePresent(MsdObject msdObject){

        if(msdObject.channel.equals("INBAND")){
            return "ДА, КАЧЕСТВО НЕ ОПРЕДЕЛЕНО";
        }

        if(msdObject.channel.equals("SMS")){
            return "НЕТ, SMS";
        }

        return "СТАТУС НЕ ИЗВЕСТЕН";
    }


    public static String ToCoordinates(MsdObject msdObject) {
        return msdObject.pos_lat + " " +  msdObject.pos_long;
    }
}
