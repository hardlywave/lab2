package com.company.controller;

import com.company.model.EValidParams;
import com.company.model.PetInformation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

public class SAXParserForRead {
    private static final List<PetInformation> infoes = new ArrayList<>();
    private String tournament;
    private Date date;
    private String sport;
    private String winner;
    private String fund;
    private String benefits;
    private String endlEl;
    private PetInformation info;

    public static List<PetInformation> getInfoes() {
        return infoes;
    }

    public void parse(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean booleanTournament = false;
                boolean booleanDate = false;
                boolean booleanSport = false;
                boolean booleanWinner = false;
                boolean booleanFund = false;
                boolean booleanBenefits = false;

                public void startElement(String uri, String localName, String queryName, Attributes attributes) throws SAXException {

                    if (queryName.equalsIgnoreCase(EValidParams.TOURNAMENT.toString())) {
                        booleanTournament = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.DATE.toString())) {
                        booleanDate = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.SPORT.toString())) {
                        booleanSport = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.WINNER.toString())) {
                        booleanWinner = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.FUND.toString())) {
                        booleanFund = true;
                    }
                    if (queryName.equalsIgnoreCase(EValidParams.BENEFITS.toString())) {
                        booleanBenefits = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    endlEl = qName;
                }

                public void characters(char[] ch, int start, int length) throws SAXException {

                    if (booleanTournament) {
                        System.out.println("tournament: " + new String(ch, start, length));
                        setTournament(new String(ch, start, length));
                        booleanTournament = false;

                    } else if (booleanDate) {
                        System.out.println("date: " + new String(ch, start, length));
                        setDate(new String(ch, start, length));
                        booleanDate = false;

                    } else if (booleanSport) {
                        System.out.println("sport: " + new String(ch, start, length));
                        setSport(new String(ch, start, length));
                        booleanSport = false;

                    } else if (booleanWinner) {
                        System.out.println("winner: " + new String(ch, start, length));
                        setWinner(new String(ch, start, length));
                        booleanWinner = false;

                    } else if (booleanFund) {
                        System.out.println("fund: " + new String(ch, start, length));
                        setFund(new String(ch, start, length));
                        booleanFund = false;

                    } else if (booleanBenefits) {
                        System.out.println("benefits: " + new String(ch, start, length));
                        setBenefits(new String(ch, start, length));
                        booleanBenefits = false;

                        PetInformation info = new PetInformation();
                        info.setTournament(getTournament());
                        info.setDate(getDate());
                        info.setSport(getSport());
                        info.setWinner(getWinner());
                        info.setFund(getFund());
                        info.setBenefits(getBenefits());
                        infoes.add(info);
                    }
                }

            };
            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PetInformation getInfo() {
        return info;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String stringForDate) {
        long timeForDate;
        timeForDate = parseLong(stringForDate);
        Date date = new Date();
        date.setTime(timeForDate);
        this.date = date;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}
