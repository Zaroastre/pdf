package io.nirahtech.librairies.pdf;

enum PageFormat {
    
    A0(841, 1189),
    A1(594, 841),
    A2(420, 594),
    A3(297, 420),
    A4(210, 297),
    A5(148, 210),
    A6(105, 148),
    A7(74, 105),
    A8(52, 74),
    A9(37, 52),
    A10(26, 37)
    ;
    
    private static final float POINTS_PER_POUCE = 72.0F;
    private static final float ONE_POUCE_IN_MILLIMETERS = 25.4F;

    private final int withInMillimeters;
    private final int heightInMillimeters;

    private PageFormat(final int width, final int height) {
        this.withInMillimeters = width;
        this.heightInMillimeters = height;
    }

    public int getWithInMillimeters() {
        return withInMillimeters;
    }
    public int getHeightInMillimeters() {
        return heightInMillimeters;
    }
    public float getWidthInPoints() {
        return this.withInMillimeters * (POINTS_PER_POUCE / ONE_POUCE_IN_MILLIMETERS);
    }
    
    public float getHeightInPoints() {
        return this.heightInMillimeters * (POINTS_PER_POUCE / ONE_POUCE_IN_MILLIMETERS);
    }
}
