package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MsdConvertHelperTest {

    @Test
    void toVoiceMessagePresent() {
        MsdObject msdObject = new MsdObject();
        msdObject.channel = "SMS";
        assertEquals(MsdConvertHelper.ToVoiceMessagePresent(msdObject), "НЕТ, SMS");
        msdObject.channel = "INBAND";
        assertEquals(MsdConvertHelper.ToVoiceMessagePresent(msdObject), "ДА, КАЧЕСТВО НЕ ОПРЕДЕЛЕНО");
        msdObject.channel = "GKJHKJHF";
        assertEquals(MsdConvertHelper.ToVoiceMessagePresent(msdObject), "СТАТУС НЕ ИЗВЕСТЕН");
        msdObject.channel = "";
        assertEquals(MsdConvertHelper.ToVoiceMessagePresent(msdObject), "СТАТУС НЕ ИЗВЕСТЕН");

    }

    @Test
    void toCoordinates() {
    }
}