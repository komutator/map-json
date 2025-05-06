package com.example;

import javax.swing.*;
import java.awt.*;

public class MndPanel extends AbstractMiniPanel{


    public MndPanel(){


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами


        addField(this, gbc, 0, "MSD Verision", "2");
        addField(this, gbc, 1, "MessageID", "1");
        addField(this, gbc, 2, "VIN", "LDP91E952RE201384");
        addField(this, gbc, 3, "Storage type", "Электричество (Более 42В и 100 А/час), бензин");
        addField(this, gbc, 4, "TimeStamp", "15 августа 2024 12:36:17");
        addField(this, gbc, 5, "Control", "Ручной вызов\n эстренный вызов");


    }


}
