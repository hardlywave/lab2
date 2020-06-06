package com.company.view;

import com.company.controller.ControllerMain;
import com.company.model.PetInformation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DeleteFrame extends JFrame {
    private final ControllerMain controllerMain;
    private final ChoosePanel choosePanel = new ChoosePanel();
    private JButton delete;

    DeleteFrame(ControllerMain controllerMain) {
        super("Delete frame");
        this.controllerMain = controllerMain;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        init();
        setSize(500, 150);
        setResizable(false);
    }

    private void init() {
        delete = new JButton("Delete");
        delete.setMaximumSize(new Dimension(100, 20));

        add(choosePanel);
        add(delete);
    }

    int deletePetInfo() {
        ArrayList<PetInformation> list = choosePanel.getPetInformationFromFields();
        return this.controllerMain.deletePetInformation(list.get(0).getTournament(), list.get(0).getDate(),
                list.get(0).getSport(), list.get(0).getWinner(), list.get(0).getFund(), list.get(0).getBenefits());
    }

    public JButton getDeleteButton() {
        return delete;
    }
}
