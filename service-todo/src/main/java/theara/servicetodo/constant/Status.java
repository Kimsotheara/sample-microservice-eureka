package theara.servicetodo.constant;

public enum Status {
    SUCCESS("SUCCESS"),NOT_FOUND("NOT FOUND"), EXIST("EXIST"), NOT_EXIST("NOT EXIST"), FAILED("FAILED"), ERROR("ERROR");
    private final String value;
    Status(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
