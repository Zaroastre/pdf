package io.nirahtech.librairies.pdf;

public record Text (String value, Font font, int fontSize, Color color, Position position) {

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BT\n")
        .append(String.format("%s %s %s rg", this.color.red(), this.color.green(), this.color.blue()))
        .append("\n")
        .append(String.format("/%s %s Tf", this.font.getAlias(), this.fontSize))
        .append("\n")
        .append(String.format("%s %s Td", this.position.x(), this.position.y()))
        .append("\n")
        .append(String.format("( %s ) Tj", this.value))
        .append("\n")
        .append("ET\n");
        return builder.toString();
    }
}
