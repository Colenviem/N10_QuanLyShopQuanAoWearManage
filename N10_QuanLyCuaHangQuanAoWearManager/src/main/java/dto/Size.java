package dto;

public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), ExtraLarge("XL"), ExtraExtraLarge("XXL");
    
    private String size;

    private Size(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
