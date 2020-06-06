package com.company.view;

import com.company.controller.ControllerMain;
import com.company.model.PetInformation;

import javax.swing.*;

public class TablePanel extends JPanel {
    private final JTable table;
    ControllerMain controllerMain;
    private JPanel controlPane;
    private JLabel count;
    private JLabel page;

    private int left;
    private int right;
    private int pageCounter;

    TablePanel(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        table = new JTable(10, 6);

        makeControlButtons();
        createLabels();
        add(table);
        add(controlPane);
    }

    private void createLabels() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel tournament = new JLabel("Turnament");
        JLabel date = new JLabel("Date");
        JLabel sport = new JLabel("Sport");
        JLabel winner = new JLabel("Winner");
        JLabel fund = new JLabel("Prize fund");
        JLabel benefits = new JLabel("Benefits");

        labelPanel.add(tournament);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(date);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(sport);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(winner);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(fund);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(benefits);
        add(labelPanel);
    }

    private void makeControlButtons() {
        left = 1;
        right = 10;
        pageCounter = 1;

        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.LINE_AXIS));
        count = new JLabel("Notes " + left + " - " + right);
        page = new JLabel("Page: " + pageCounter);

        JButton nextPage = new JButton("Next");
        nextPage.addActionListener(actionEvent -> {
            if (controllerMain.isExists(right + 1)) {
                left += 10;
                right += 10;
                pageCounter++;
                count.setText("Notes " + left + " - " + right);
                page.setText("Page: " + pageCounter);
                showTable(controllerMain);
            }
        });

        JButton prevPage = new JButton("Previous");
        prevPage.addActionListener(actionEvent -> {
            if (pageCounter > 1) {
                left -= 10;
                right -= 10;
                pageCounter--;
                count.setText("Notes " + left + " - " + right);
                page.setText("Page: " + pageCounter);
                showTable(controllerMain);
            }
        });

        controlPane.add(count);
        controlPane.add(Box.createHorizontalGlue());
        controlPane.add(page);
        controlPane.add(Box.createHorizontalGlue());
        controlPane.add(prevPage);
        controlPane.add(nextPage);
    }


    private void addEmpty(int row) {
        table.setValueAt(" ", row, 0);
        table.setValueAt(" ", row, 1);
        table.setValueAt(" ", row, 2);
        table.setValueAt(" ", row, 3);
        table.setValueAt(" ", row, 4);
        table.setValueAt(" ", row, 5);
    }

    private void addNotes(int row, PetInformation info) {
        System.out.println(info.getDate());
        table.setValueAt(info.getTournament(), row, 0);
        table.setValueAt(info.getDate(), row, 1);
        table.setValueAt(info.getSport(), row, 2);
        table.setValueAt(info.getWinner(), row, 3);
        table.setValueAt(info.getFund(), row, 4);
        table.setValueAt(info.getBenefits(), row, 5);
    }

    public void setNotes(ControllerMain cont) {
        controllerMain = cont;
    }

    public void showTable(ControllerMain controllerMain) {
        int index = 0;
        for (int i = left - 1; i < right; i++) {
            if (controllerMain.isExists(i)) {
                addNotes(index, controllerMain.atIndex(i));
            } else {
                addEmpty(index);
            }
            index++;
        }
    }
}