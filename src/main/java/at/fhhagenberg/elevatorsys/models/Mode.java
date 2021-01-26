package at.fhhagenberg.elevatorsys.models;

import java.util.HashMap;
import java.util.Map;

public enum Mode {
    MANUAL("MANUAL"),
    AUTOMATIC("AUTOMATIC");

    private String mode;

    private Mode(String mode){
        this.mode = mode;
    }
}
