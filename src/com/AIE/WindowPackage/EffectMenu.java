package com.AIE.WindowPackage;

import EffectsPackage.Effect;
import EffectsPackage.GaussianBlur;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EffectMenu extends JMenu {

    private static final HashMap<String, Effect> effects = new HashMap<>();

    public EffectMenu(MainFrame frame) {
        super("Effects");

        effects.put("Gaussian Blur", new GaussianBlur(frame));

        JMenu blurMenu = new JMenu("Blurs");

        ActionListener listener = e -> {
            for(String key : effects.keySet()) {
                if(key.equals(((JMenuItem)e.getSource()).getActionCommand())) {
                    effects.get(key).setVisible(true);
                    break;
                }
            }
        };

        addMenuItemTo(blurMenu, "Gaussian Blur", listener);
        add(blurMenu);
    }

    private void addMenuItemTo(JMenu menu, String name, ActionListener listener) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(listener);
        menu.add(item);
    }

}