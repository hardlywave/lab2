package com.company.view;

import com.company.controller.ControllerMain;
import com.company.model.PetInformation;

import javax.swing.*;
import java.util.List;

public class FindFrame extends JFrame {

    private final ControllerMain controllerMain;
    private final ChoosePanel choosePanel = new ChoosePanel();
    private final JButton find;
    private TablePanel tablePanel;

    FindFrame(ControllerMain controllerMain) {
        super("Find frame");
        this.controllerMain = controllerMain;
        setSize(500, 320);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        find = new JButton("Find");
        find.addActionListener(actionEvent -> {
            List<PetInformation> list = choosePanel.getPetInformationFromFields();
            ControllerMain temp = new ControllerMain();
            temp.setNotes(this.controllerMain.findPetInformation(list.get(0).getTournament(), list.get(0).getDate(),
                    list.get(0).getSport(), list.get(0).getWinner(), list.get(0).getFund(), list.get(0).getBenefits()));
            tablePanel.setNotes(temp);
            tablePanel.showTable(temp);
        });

        tablePanel = new TablePanel(controllerMain);
        JPanel panel = new JPanel();

        add(choosePanel);
        add(find);
        add(panel);
        add(tablePanel);

    }

    public JButton getFindButton() {
        return find;
    }
}
