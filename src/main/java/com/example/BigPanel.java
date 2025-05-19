package com.example;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class BigPanel extends AbstractMiniPanel{

    public class ScreenDataField{

        public ScreenDataField(String label, String value){
            this.label = label;
            this.value = value;
        }

        public String label;
        public String value;

    }

    ArrayList<ScreenDataField> fields;
    GridBagConstraints gbc;

    public BigPanel(){

        fields = new ArrayList<>();

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами



        fields.add(new ScreenDataField("НАЛИЧИЕ ГОЛОСОВОГО СООБЩЕНИЯ", "ДА, КАЧЕСТВО НЕ ОПРЕДЕЛЕНО"));
        fields.add(new ScreenDataField("ТИП ВЫЗОВА", "АВТОМАТИЧЕСКИЙ"));
        fields.add(new ScreenDataField("МАРКА/МОДЕЛЬ", "Yohun Yohun PASSION"));
        fields.add(new ScreenDataField("VIN", "LDP91E952RE201384"));
        fields.add(new ScreenDataField("КООРДИНАТЫ", "56\"23\' 106.125, 37\"19\'09.387"));


        Integer yPos = 0;
        for (ScreenDataField f : fields){
            addField(this, gbc, yPos, f.label, f.value);
            yPos = yPos + 1;
        }




    }

    public void UpdateFields(){
//        fields.clear();
        Integer yPos = 0;
        for (ScreenDataField f : fields){
            addValue(this, gbc, yPos, f.value);
            yPos = yPos + 1;
        }

    }


}
