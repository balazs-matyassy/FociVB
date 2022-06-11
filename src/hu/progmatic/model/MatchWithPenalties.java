package hu.progmatic.model;

public final class MatchWithPenalties extends Match {
    private int penaltiesA;
    private int penaltiesB;

    public MatchWithPenalties(Worldcup worldcup) {
        super(worldcup);
    }

    public MatchWithPenalties(Worldcup worldcup, String csvLine) {
        super(worldcup, csvLine);

        String[] values = csvLine.split(";");
        this.penaltiesA = Integer.parseInt(values[6]);
        this.penaltiesB = Integer.parseInt(values[7]);
    }

    @Override
    public Result getResult() {
        if (getGoalsA() > getGoalsB()) {
            return Result.A_WINS;
        } else if (getGoalsB() > getGoalsA()) {
            return Result.B_WINS;
        } else if (penaltiesA > penaltiesB) {
            return Result.A_WINS;
        } else if (penaltiesB > penaltiesA) {
            return Result.B_WINS;
        } else {
            return Result.TIE;
        }
    }

    @Override
    public String toCSVLine() {
        return super.toCSVLine()
                + ";" + penaltiesA
                + ";" + penaltiesB;
    }
}
