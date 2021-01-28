package at.fhhagenberg.elevatorsys.models;

public enum Mode {
    MANUAL("MANUAL"),
    AUTOMATIC("AUTOMATIC");

    private String value;

    Mode(String mode){
        this.value = mode;
    }
}
