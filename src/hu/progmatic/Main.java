package hu.progmatic;

import hu.progmatic.model.Match;
import hu.progmatic.model.Result;
import hu.progmatic.model.Stage;
import hu.progmatic.model.Worldcup;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Map<Integer, Worldcup> worldcups = loadWorldcups("worldcups.csv");
            List<Match> matches = new ArrayList<>();
            matches.addAll(loadMatches(worldcups.get(2010), "matches_2010.csv"));
            matches.addAll(loadMatches(worldcups.get(2014), "matches_2014.csv"));
            matches.addAll(loadMatches(worldcups.get(2018), "matches_2018.csv"));

            System.out.println("Number of matches: " + matches.size());

            /* System.out.println("Year of worldcup:");
            int year = Integer.parseInt(reader.readLine());
            Worldcup selected = worldcups.get(year); */

            System.out.println("Name of host:");
            String host = reader.readLine();

            Worldcup selected = null;

            for (Worldcup worldcup : worldcups.values()) {
                if (worldcup.getHost().equalsIgnoreCase(host)) {
                    selected = worldcup;
                    break;
                }
            }

            saveMatches("selected.csv", selected.getMatches());

            // 2.
            int maxDifference = Integer.MIN_VALUE;

            for (Match match : selected.getMatches()) {
                if (match.getGoalDifference() > maxDifference) {
                    maxDifference = match.getGoalDifference();
                }
            }

            System.out.println("2. Maximum goal difference: " + maxDifference);

            // 3.
            int counter = 0;

            for (Match match : selected.getMatches()) {
                if (match.getResult() == Result.A_WINS) {
                    counter++;
                }
            }

            System.out.println("3. The player has won " + counter + " bets.");

            // 4.
            Map<Stage, Integer> totalGoalsByStage = new LinkedHashMap<>();

            for (Match match : selected.getMatches()) {
                int total = totalGoalsByStage.getOrDefault(match.getStage(), 0);
                totalGoalsByStage.put(match.getStage(), total + match.getGoalsTotal());
            }

            System.out.println("4. Total goals by stage:");

            for (Stage stage : totalGoalsByStage.keySet()) {
                System.out.println(stage.getDescription() + ": " + totalGoalsByStage.get(stage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Worldcup> loadWorldcups(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Map<Integer, Worldcup> worldcups = new TreeMap<>();

            // header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                Worldcup worldcup = new Worldcup(line);
                worldcups.put(worldcup.getYear(), worldcup);
            }

            return worldcups;
        }
    }

    private static List<Match> loadMatches(Worldcup worldcup, String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<Match> matches = new ArrayList<>();

            // header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                Match match = MatchFactory.createMatch(worldcup, line);
                worldcup.getMatches().add(match);
                matches.add(match);
            }

            return matches;
        }
    }

    private static void saveMatches(String path, List<Match> matches) throws IOException {
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.println("stage;date;team_a;team_b;goals_a;goals_b;penalties_a;penalties_b");

            for (Match match : matches) {
                writer.println(match.toCSVLine());
            }
        }
    }
}
