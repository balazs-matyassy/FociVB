package hu.progmatic.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Match {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private final Worldcup worldcup;
    private Stage stage;
    private LocalDate date;
    private String teamA;
    private String teamB;
    private int goalsA;
    private int goalsB;

    public Match(Worldcup worldcup) {
        this.worldcup = worldcup;
    }

    public Match(Worldcup worldcup, String csvLine) {
        String[] values = csvLine.split(";");

        this.worldcup = worldcup;
        this.stage = Stage.fromDescription(values[0]);
        this.date = LocalDate.parse(values[1], formatter);
        this.teamA = values[2];
        this.teamB = values[3];
        this.goalsA = Integer.parseInt(values[4]);
        this.goalsB = Integer.parseInt(values[5]);
    }

    public final Worldcup getWorldcup() {
        return worldcup;
    }

    public final Stage getStage() {
        return stage;
    }

    public final void setStage(Stage stage) {
        this.stage = stage;
    }

    public final LocalDate getDate() {
        return date;
    }

    public final void setDate(LocalDate date) {
        this.date = date;
    }

    public final String getTeamA() {
        return teamA;
    }

    public final void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public final String getTeamB() {
        return teamB;
    }

    public final void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public final int getGoalsA() {
        return goalsA;
    }

    public final void setGoalsA(int goalsA) {
        this.goalsA = goalsA;
    }

    public final int getGoalsB() {
        return goalsB;
    }

    public final void setGoalsB(int goalsB) {
        this.goalsB = goalsB;
    }

    public final int getGoalsTotal() {
        return goalsA + goalsB;
    }

    public final int getGoalDifference() {
        return goalsA >= goalsB
                ? goalsA - goalsB
                : goalsB -goalsA;
    }

    public Result getResult() {
        if (goalsA > goalsB) {
            return Result.A_WINS;
        } else if (goalsB > goalsA) {
            return Result.B_WINS;
        } else {
            return Result.TIE;
        }
    }

    public String toCSVLine() {
        return stage.getDescription()
                + ";" + date.format(formatter)
                + ";" + teamA
                + ";" + teamB
                + ";" + goalsA
                + ";" + goalsB;
    }
}
