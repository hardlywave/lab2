package com.company.model;

import java.util.Date;

public class PetInformation {
    private String tournament, winner, sport, fund, benefits;
    private Date date;

    public PetInformation(String tournament, Date date, String sport, String winner, String fund, String benefits) {
        this.tournament = tournament;
        this.date = date;
        this.sport = sport;
        this.winner = winner;
        this.fund = fund;
        this.benefits = benefits;
    }

    public PetInformation() {
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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) { this.sport = sport; }

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
