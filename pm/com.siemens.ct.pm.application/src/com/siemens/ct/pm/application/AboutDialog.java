package com.siemens.ct.pm.application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 5021827757641301232L;

    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AboutDialog dialog = new AboutDialog(null);
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog
     */
    @SuppressWarnings("serial")
    public AboutDialog(Frame owner) {
        super(owner);

        JLabel label;
        label = new JLabel();
        label.setIcon(new ImageIcon(this.getClass().getResource("resources/about.gif")));

        JLabel personManagerLabel;
        personManagerLabel = new JLabel();
        personManagerLabel.setFont(new Font("", Font.BOLD, 12));
        personManagerLabel.setText("Person Manager");

        JLabel aDynamicSwingLabel;
        aDynamicSwingLabel = new JLabel();
        aDynamicSwingLabel.setText("A dynamic Swing OSGi Demo");

        JLabel versionLabel;
        versionLabel = new JLabel();
        versionLabel.setText("Version:");

        JLabel contributorsLabel;
        contributorsLabel = new JLabel();
        contributorsLabel.setText("Contributors:");

        JLabel homepageLabel;
        homepageLabel = new JLabel();
        homepageLabel.setText("Homepage:");

        JLabel label_1;
        label_1 = new JLabel();
        label_1.setText("Kai Tödter, Siemens AG");

        JLabel label_2;
        label_2 = new JLabel();
        label_2.setText("0.3");

        JLabel httpmaxservermyftporgtracpmLabel;
        httpmaxservermyftporgtracpmLabel = new JLabel();
        httpmaxservermyftporgtracpmLabel.setText("http://max-server.myftp.org/trac/pm");

        JButton closeButton;
        closeButton = new JButton();

        final AboutDialog dialog = this;
        closeButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        closeButton.setText("Close");

        final GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout
                .setHorizontalGroup(groupLayout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addComponent(label)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                GroupLayout.Alignment.TRAILING)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                groupLayout
                                                                                                        .createSequentialGroup()
                                                                                                        .addGroup(
                                                                                                                groupLayout
                                                                                                                        .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                        .addGroup(
                                                                                                                                groupLayout
                                                                                                                                        .createSequentialGroup()
                                                                                                                                        .addComponent(
                                                                                                                                                contributorsLabel)
                                                                                                                                        .addPreferredGap(
                                                                                                                                                LayoutStyle.ComponentPlacement.RELATED))
                                                                                                                        .addGroup(
                                                                                                                                groupLayout
                                                                                                                                        .createSequentialGroup()
                                                                                                                                        .addComponent(
                                                                                                                                                homepageLabel)
                                                                                                                                        .addGap(
                                                                                                                                                15,
                                                                                                                                                15,
                                                                                                                                                15))
                                                                                                                        .addGroup(
                                                                                                                                groupLayout
                                                                                                                                        .createSequentialGroup()
                                                                                                                                        .addComponent(
                                                                                                                                                versionLabel)
                                                                                                                                        .addPreferredGap(
                                                                                                                                                LayoutStyle.ComponentPlacement.RELATED)))
                                                                                                        .addGroup(
                                                                                                                groupLayout
                                                                                                                        .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                        .addGroup(
                                                                                                                                groupLayout
                                                                                                                                        .createSequentialGroup()
                                                                                                                                        .addComponent(
                                                                                                                                                label_2)
                                                                                                                                        .addPreferredGap(
                                                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                184,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                        .addGroup(
                                                                                                                                groupLayout
                                                                                                                                        .createSequentialGroup()
                                                                                                                                        .addComponent(
                                                                                                                                                label_1)
                                                                                                                                        .addPreferredGap(
                                                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                68,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                        .addComponent(
                                                                                                                                httpmaxservermyftporgtracpmLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)))
                                                                                        .addComponent(
                                                                                                personManagerLabel)
                                                                                        .addComponent(
                                                                                                aDynamicSwingLabel))
                                                                        .addGap(47, 47, 47))
                                                        .addGroup(
                                                                groupLayout.createSequentialGroup()
                                                                        .addComponent(closeButton)
                                                                        .addContainerGap()))));
        groupLayout
                .setVerticalGroup(groupLayout
                        .createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(
                                groupLayout
                                        .createSequentialGroup()
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(
                                                                GroupLayout.Alignment.TRAILING)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addComponent(
                                                                                personManagerLabel)
                                                                        .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                aDynamicSwingLabel)
                                                                        .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                label_2)
                                                                                        .addComponent(
                                                                                                versionLabel))
                                                                        .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                contributorsLabel)
                                                                                        .addComponent(
                                                                                                label_1))
                                                                        .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                homepageLabel)
                                                                                        .addComponent(
                                                                                                httpmaxservermyftporgtracpmLabel))
                                                                        .addGap(20, 20, 20)
                                                                        .addComponent(closeButton)
                                                                        .addGap(11, 11, 11))
                                                        .addComponent(label)).addGap(1, 1, 1)));
        getContentPane().setLayout(groupLayout);
        pack();
        //
    }
}
