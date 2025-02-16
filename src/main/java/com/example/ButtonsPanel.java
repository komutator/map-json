package com.example;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        add(new Button("GET"));
        add(new Button("SET"));
        add(new Button("DELETE"));
    }
}
