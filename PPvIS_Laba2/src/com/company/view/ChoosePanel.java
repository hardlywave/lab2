package com.company.view;

import com.company.controller.ControllerForCalendar;
import com.company.model.PetInformation;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;


public class ChoosePanel extends JPanel {

    private final long millisecondsToRoundTime = 86400000;
    private final long millisecondsToRoundThreeHours = 10800000;
    private final ControllerForCalendar controllerForCalendar = new ControllerForCalendar();
    private JPanel textPanel;
    private JTextField tournamentField;
    private JDatePickerImpl jDatePickerForDateOfBirth;
    private JTextField sportField;
    private JTextField winnerField;
    private JTextField fundField;
    private JTextField benefitsField;



    public ChoosePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createLabels();
        createText();
    }

    private void createLabels() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

        JLabel tournamentLabel = new JLabel("  Turnament");
        JLabel dateLabel = new JLabel("            Date");
        JLabel sportLabel = new JLabel("                   Sport");
        JLabel winnerLabel = new JLabel("   Winner");
        JLabel fundLabel = new JLabel("Prize fund");
        JLabel benefitsLabel = new JLabel("Benefits");

        labelPanel.add(tournamentLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(dateLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(sportLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(winnerLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(fundLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(benefitsLabel);

        add(labelPanel);
    }

    private void createText() {
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));

        final int WIDTH = 100;
        final int HEIGHT = 20;

        tournamentField = new JTextField();
        tournamentField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        tournamentField.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        controllerForCalendar.setCalendar(jDatePickerForDateOfBirth, WIDTH, HEIGHT, textPanel);
        jDatePickerForDateOfBirth = controllerForCalendar.getCalendar();
        sportField = new JTextField();
        sportField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        sportField.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        winnerField = new JTextField();
        winnerField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        winnerField.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        fundField = new JTextField();
        fundField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        fundField.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        benefitsField = new JTextField();
        benefitsField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        benefitsField.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        textPanel.add(tournamentField);
        textPanel.add(jDatePickerForDateOfBirth);
        textPanel.add(sportField);
        textPanel.add(winnerField);
        textPanel.add(fundField);
        textPanel.add(benefitsField);

        add(textPanel);
    }

    public ArrayList<PetInformation> getPetInformationFromFields() {
        ArrayList<PetInformation> result = new ArrayList<>();
        PetInformation petInformation = new PetInformation(getFieldTournament(), getFieldDate(),
                getFieldSport(), getFieldWinner(), getFieldFund(), getFieldBenefits());
        result.add(petInformation);

        return result;
    }

    public String getFieldTournament() {
        return tournamentField.getText();
    }

    public String getFieldWinner() {
        return winnerField.getText();
    }

    public String getFieldFund() { return fundField.getText();}

    public String getFieldSport() { return sportField.getText();}

    public String getFieldBenefits() { return benefitsField.getText();}

    public Date getFieldDate() {
        Date dateBirth = (Date) jDatePickerForDateOfBirth.getModel().getValue();
        if (dateBirth == null) {
            return null;
        }
        long time = 0;
        time = dateBirth.getTime();
        long newTime = time - time % millisecondsToRoundTime - millisecondsToRoundThreeHours;
        dateBirth.setTime(newTime);
        return dateBirth;
    }
}
