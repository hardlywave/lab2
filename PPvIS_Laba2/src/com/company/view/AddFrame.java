package com.company.view;

import com.company.controller.ControllerMain;
import com.company.model.PetInformation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AddFrame extends JFrame {

    private final JButton add;
    private final ControllerMain controllerMainFirst;
    private final ChoosePanel choosePanel = new ChoosePanel();

    AddFrame(ControllerMain controllerMain) {
        super("Add new record to table");
        this.controllerMainFirst = controllerMain;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add = new JButton("Add");
        add.setMaximumSize(new Dimension(100, 20));

        add(choosePanel);
        add(add);

        setSize(500, 150);
        setResizable(false);
    }

    public void addNotes() {
        String tempTournament = choosePanel.getFieldTournament();
        Date tempDate = choosePanel.getFieldDate();
        String tempSport = choosePanel.getFieldSport();
        String tempWinner = choosePanel.getFieldWinner();
        String tempFund = choosePanel.getFieldFund();
        String tempBenefits = choosePanel.getFieldBenefits();
        controllerMainFirst.addPetInformation(new PetInformation(tempTournament, tempDate, tempSport, tempWinner, tempFund, tempBenefits));
    }

    public JButton addButton() {
        return add;
    }

}
