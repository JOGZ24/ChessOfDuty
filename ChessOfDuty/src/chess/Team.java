package chess;

public enum Team {
    BLACK("\033[0;30m"),
    WHITE("\033[0;37m");

    private String color;

    Team(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
