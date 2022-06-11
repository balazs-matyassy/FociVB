package hu.progmatic.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Worldcup {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private int year;
    private String host;
    private String confederation;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private final List<Match> matches;

    public Worldcup() {
        this.matches = new ArrayList<>();
    }

    public Worldcup(String csvLine) {
        String[] values = csvLine.split(";");

        this.year = Integer.parseInt(values[0]);
        this.host = values[1];
        this.confederation = values[2];
        this.dateFrom = LocalDate.parse(values[3], formatter);
        this.dateTo = LocalDate.parse(values[4], formatter);
        this.matches = new ArrayList<>();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
