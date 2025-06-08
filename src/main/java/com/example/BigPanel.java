package com.example;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class BigPanel extends AbstractMiniPanel{

    public int getFieldIndex(String s) {
        int i = 0;
        for(ScreenDataField dataField: fields){
            if(dataField.label == s){
                return i;
            }
            i++;
        }
        return -1;
    }

    public void setFieldValue(String fieldLabel, String s) {
        int z = getFieldIndex(fieldLabel);
        if(z!=-1) {
            panelItems.get(z).textField.setText(s);
        } else {
            panelItems.get(z).textField.setText("ОШИБКА, ПОЛЕ НЕ НАЙДЕНО");
        }
    }

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
        setBorderName("Данные МНД");
        Dimension d = new Dimension(450,500);
        setMinimumSize(d);
        fields = new ArrayList<>();
//        Dimension d = new Dimension(900, 800);
//        this.setMaximumSize(d);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами

        // Что на рисунке:
        //0 НАЛИЧИЕ ГОЛОСОВОГО СООБЩЕНИЯ
        //1 ТИП ВЫЗОВА
        //2 МАРКА/МОДЕЛЬ
        //3 Координаты сш вд
        //4 Адрес на КВ - автодром
        //

        // Вторая панель? МНД-1
        //5 MSD Version
        //6 Message-id
        //7 VIN
        //8 Storage type (тип бензобака?)
        //9 Timestamp
        //10 Transport system (ПТМ голосовой канал)
        //11 PTM activation (не определен)
        //12 Control (ручной вызов, экстренный вызов)
        //

        // Третья панель
        // Время получения МНД
        // МНД принят системой
        // Number of pass = 2
        // Location = lon lat
        // Direction = 77

// todo: Доделать эту панель

        fields.add(new ScreenDataField("НАЛИЧИЕ ГОЛОСОВОГО СООБЩЕНИЯ", "ДА, КАЧЕСТВО НЕ ОПРЕДЕЛЕНО"));
        fields.add(new ScreenDataField("ТИП ВЫЗОВА", "АВТОМАТИЧЕСКИЙ"));
        fields.add(new ScreenDataField("МАРКА/МОДЕЛЬ", "Yohun Yohun PASSION"));
        fields.add(new ScreenDataField("КООРДИНАТЫ", "56\"23\' 106.125, 37\"19\'09.387"));
        fields.add(new ScreenDataField("Адрес на КВ", "Село Покровское"));
        // Panel 2
        fields.add(new ScreenDataField("MSD Version", "1"));
        fields.add(new ScreenDataField("Message ID", "1"));
        fields.add(new ScreenDataField("VIN", "18049850938098"));
        fields.add(new ScreenDataField("Storage type", "Benzo"));
        fields.add(new ScreenDataField("Timestamp", "12.10.2025 14.55.00"));
        fields.add(new ScreenDataField("Transport system", "ПТМ голосовой канал"));


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
