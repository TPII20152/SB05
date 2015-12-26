package br.ufc.banco.ui;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by thiagoisaias on 12/20/15.
 */
public class UserInterface {

    public UserInterface(){
        build();
    }

    public void build(){

        JFrame mainFrame = new JFrame("TAA Banco 24h");
        mainFrame.setSize(300,500);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton btn1 = new JButton("Cadastrar Conta");
        btn1.setActionCommand("cadastrar");
        JButton btn2 = new JButton("Fazer Deposito");
        btn2.setActionCommand("deposito");
        JButton btn3 = new JButton("Realizar Saque");
        btn3.setActionCommand("saque");
        JButton btn4 = new JButton("Transferencia");
        btn4.setActionCommand("transferencia");
        JButton btn5 = new JButton("Visualizar Saldo");
        btn5.setActionCommand("saldo");
        JButton btn6 = new JButton("Remover Conta");
        btn6.setActionCommand("remover");
        JButton btn7 = new JButton("Render Juros");
        btn7.setActionCommand("juros");
        JButton btn8 = new JButton("Render Bonus");
        btn8.setActionCommand("bonus");
        JButton btn9 = new JButton("Sair");
        btn9.setActionCommand("sair");

        controlPanel.add(btn1);
        controlPanel.add(btn2);
        controlPanel.add(btn3);
        controlPanel.add(btn4);
        controlPanel.add(btn5);
        controlPanel.add(btn6);
        controlPanel.add(btn7);
        controlPanel.add(btn8);
        controlPanel.add(btn9);

        btn1.addActionListener(new ButtonListener());
        btn2.addActionListener(new ButtonListener());
        btn3.addActionListener(new ButtonListener());
        btn4.addActionListener(new ButtonListener());
        btn5.addActionListener(new ButtonListener());
        btn6.addActionListener(new ButtonListener());
        btn7.addActionListener(new ButtonListener());
        btn8.addActionListener(new ButtonListener());
        btn9.addActionListener(new ButtonListener());


        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);

    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }
}
