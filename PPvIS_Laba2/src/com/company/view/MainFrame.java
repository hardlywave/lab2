package com.company.view;

import com.company.controller.ControllerMain;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends Component {
    private final JFrame mainFrame = new JFrame("Patient information");
    private final JPanel mainPanel = new JPanel();
    ControllerMain controllerMain = new ControllerMain();
    private TablePanel tablePanel;
    private JButton deleteButton, findButton;
    private JButton addButton;
    private JButton showButton, saveButton;

    private AddFrame addFrame;
    private DeleteFrame deleteFrame;
    private FindFrame findFrame;

    public void initialize() {
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setLayout(null);

        tablePanel = new TablePanel(this.controllerMain);
        tablePanel.setBounds(390, 40, 1000, 400);
        mainPanel.add(tablePanel);

        addButton = new JButton("Add new tournament information in table");
        addButton.setBounds(20, 20, 350, 40);
        mainPanel.add(addButton);

        addFrame = new AddFrame(this.controllerMain);
        AddListener addListener = new AddListener();

        addButton.addActionListener(addListener);
        addFrame.addButton().addActionListener(actionEvent -> {
            addFrame.addNotes();
            tablePanel.showTable(this.controllerMain);
        });

        deleteButton = new JButton("Delete from table tournament information");
        deleteButton.setBounds(20, 80, 350, 40);
        mainPanel.add(deleteButton);

        deleteFrame = new DeleteFrame(this.controllerMain);
        DeleteListener deleteListener = new DeleteListener();


        deleteButton.addActionListener(deleteListener);
        deleteFrame.getDeleteButton().addActionListener(actionEvent -> {
            int amount = deleteFrame.deletePetInfo();
            JOptionPane.showMessageDialog(mainFrame, "Deleted: " + amount);
            tablePanel.showTable(this.controllerMain);
        });

        findButton = new JButton("Find from table turnament information");
        findButton.setBounds(20, 140, 350, 40);
        mainPanel.add(findButton);

        findFrame = new FindFrame(this.controllerMain);
        FindListener findListener = new FindListener();

        findButton.addActionListener(findListener);

        mainPanel.setLayout(null);

        showButton = new JButton("Show data from XML file");
        showButton.setBounds(20, 200, 350, 40);
        showButton.addActionListener(new LoadListener());
        mainPanel.add(showButton);

        saveButton = new JButton("Save changes to XML file");
        saveButton.setBounds(20, 260, 350, 40);
        saveButton.addActionListener(new SaveListener());
        mainPanel.add(saveButton);

        mainPanel.setVisible(true);
        mainFrame.add(mainPanel);
        mainFrame.setSize(1400, 370);
        mainFrame.setVisible(true);
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            addFrame.setVisible(!addFrame.isVisible());
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteFrame.setVisible(!deleteFrame.isVisible());
        }
    }

    private class FindListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            findFrame.setVisible(!findFrame.isVisible());
        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special XML file", "xml");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                controllerMain.writePetInformation(path);
            }

        }
    }

    private class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special xml file", "xml");
            chooser.setFileFilter(filter);
            int returnValue = chooser.showOpenDialog(chooser);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                try {
                    controllerMain.readPetInformation(path);
                    tablePanel.showTable(controllerMain);
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException exc) {
                    exc.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
