package io.nirahtech.librairies.pdf;

public final record Color (int red, int green, int blue, int alpha) {
    public static final Color fromRGB(int red, int green, int blue) {
        return new Color(red, green, blue, 0);
    }
}
