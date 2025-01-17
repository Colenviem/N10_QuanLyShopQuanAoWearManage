package dto;

public enum Status {
    COMPLETED("Completed"), RETURN("Return"), CANCELLED("Cancelled");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
