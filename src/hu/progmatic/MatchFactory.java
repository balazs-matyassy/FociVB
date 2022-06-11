package hu.progmatic;

import hu.progmatic.model.Match;
import hu.progmatic.model.MatchWithPenalties;
import hu.progmatic.model.Worldcup;

public class MatchFactory {
    public static Match createMatch(Worldcup worldcup, String csvLine) {
        String[] values = csvLine.split(";");

        // return new Match(worldcup, csvLine);

        if (values.length >= 8) {
            return new MatchWithPenalties(worldcup, csvLine);
        } else {
            return new Match(worldcup, csvLine);
        }
    }
}
