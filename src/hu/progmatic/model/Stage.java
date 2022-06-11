package hu.progmatic.model;

public enum Stage {
    GROUP_A("Group A"),
    GROUP_B("Group B"),
    GROUP_C("Group C"),
    GROUP_D("Group D"),
    GROUP_E("Group E"),
    GROUP_F("Group F"),
    GROUP_G("Group G"),
    GROUP_H("Group H"),
    ROUND_OF_16("Round of 16"),
    QUARTER_FINALS("Quarter-finals"),
    SEMI_FINALS("Semi-finals"),
    PLAY_OFF("Third place play-off"),
    FINAL("Final");
    private final String description;

    Stage(String description) {
        this.description = description;
    }

    public static Stage fromDescription(String description) {
        return switch (description) {
            case "Group A" -> GROUP_A;
            case "Group B" -> GROUP_B;
            case "Group C" -> GROUP_C;
            case "Group D" -> GROUP_D;
            case "Group E" -> GROUP_E;
            case "Group F" -> GROUP_F;
            case "Group G" -> GROUP_G;
            case "Group H" -> GROUP_H;
            case "Round of 16" -> ROUND_OF_16;
            case "Quarter-finals" -> QUARTER_FINALS;
            case "Semi-finals" -> SEMI_FINALS;
            case "Third place play-off" -> PLAY_OFF;
            case "Final" -> FINAL;
            default -> throw new IllegalStateException("Unexpected value: " + description);
        };
    }

    public String getDescription() {
        return description;
    }
}
