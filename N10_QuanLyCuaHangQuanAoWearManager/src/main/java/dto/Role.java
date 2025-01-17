package dto;

public enum Role {
    STORE_MANAGER("Store Manager"), Manager("Manager"), EMPLOYEE("Employee");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
