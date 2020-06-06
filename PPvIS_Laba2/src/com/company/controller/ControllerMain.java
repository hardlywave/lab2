package com.company.controller;

import com.company.model.PetInformation;
import com.company.view.ChoosePanel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerMain {
    private final ChoosePanel choosePanel = new ChoosePanel();
    private List<PetInformation> tableInfo = new ArrayList<PetInformation>();
    private boolean booleanTournament;
    private boolean booleanDate;
    private boolean booleanSport;
    private boolean booleanWinner;
    private boolean booleanFund;
    private boolean booleanBenefits;


    private List<PetInformation> findTemplate(String tournament, Date date, String sport, String winner, String fund, String benefits) {
        List<PetInformation> temp = new ArrayList<>();
        if  (date == null) {
            for (PetInformation information : tableInfo) {
                boolean bIsFits = (information.getTournament().equals(tournament) || booleanTournament)
                        && (information.getSport().compareTo(sport) == 0 || booleanSport)
                        && (information.getWinner().equals(winner) || booleanWinner)
                        && (information.getFund().equals(fund) || booleanFund)
                        && (information.getBenefits().equals(benefits) || booleanBenefits);
                if (bIsFits) {
                    temp.add(information);
                }
            }
        } else {
            for (PetInformation information : tableInfo) {
                boolean bIsFits = (information.getTournament().equals(tournament) || booleanTournament)
                        && (information.getDate().compareTo(date) == 0 || booleanDate)
                        && (information.getSport().equals(sport) || booleanSport)
                        && (information.getWinner().equals(winner) || booleanWinner)
                        && (information.getFund().equals(fund) || booleanFund)
                        && (information.getBenefits().equals(benefits) || booleanBenefits);
                if (bIsFits) {
                    temp.add(information);
                }
            }
        }

        return temp;
    }

    public int deletePetInformation(String tournament, Date date, String sport, String winner, String fund, String benefits) {

        booleanTournament = (tournament.isEmpty());
        booleanDate = (date == null);
        booleanSport = (sport.isEmpty());
        booleanWinner = (winner.isEmpty());
        booleanFund = (fund.isEmpty());
        booleanBenefits = (benefits.isEmpty());

        List<PetInformation> temp = findTemplate(tournament, date, sport, winner, fund, benefits);
        int amount = temp.size();
        tableInfo.removeAll(temp);
        return amount;
    }

    public List<PetInformation> findPetInformation(String tournament, Date date, String sport, String winner, String fund, String benefits) {
        booleanTournament = (tournament.isEmpty());
        booleanDate = (date == null);
        booleanSport = (sport.isEmpty());
        booleanWinner = (winner.isEmpty());
        booleanFund = (fund.isEmpty());
        booleanBenefits = (benefits.isEmpty());

        return findTemplate(tournament, date, sport, winner, fund, benefits);
    }

    public void addPetInformation(PetInformation info) {
        tableInfo.add(info);
    }

    public void readPetInformation(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserForRead saxParserForRead = new SAXParserForRead();
        saxParserForRead.parse(path);
        tableInfo = SAXParserForRead.getInfoes();
    }

    public void writePetInformation(String path) {
        DOMParser domParser = new DOMParser();
        domParser.setInfoes(tableInfo, path);
        domParser.setBooks();
    }

    public boolean isExists(int index) {
        return index < tableInfo.size();
    }

    public PetInformation atIndex(int index) {
        return tableInfo.get(index);
    }

    public void setNotes(List<PetInformation> info) {
        tableInfo = info;
    }
}
