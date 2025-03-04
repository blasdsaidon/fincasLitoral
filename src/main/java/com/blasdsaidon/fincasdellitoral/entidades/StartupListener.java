/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import javax.swing.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {
    private static JWindow loadingWindow;

    public static void showLoadingScreen() {
        loadingWindow = new JWindow();
        JLabel label = new JLabel("Cargando aplicación...", SwingConstants.CENTER);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        loadingWindow.getContentPane().add(label);
        loadingWindow.setSize(300, 100);
        loadingWindow.setLocationRelativeTo(null);
        loadingWindow.setVisible(true);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (loadingWindow != null) {
            loadingWindow.setVisible(false);
            loadingWindow.dispose();
        }
        JOptionPane.showMessageDialog(null, "Aplicación iniciada con éxito.", "Listo", JOptionPane.INFORMATION_MESSAGE);
    }
}

