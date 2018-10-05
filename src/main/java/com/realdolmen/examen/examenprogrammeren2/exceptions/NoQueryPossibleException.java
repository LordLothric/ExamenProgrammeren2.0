package com.realdolmen.examen.examenprogrammeren2.exceptions;

public class NoQueryPossibleException extends Exception {

    private static final String MESSAGE = "There is no query possible here";

    public NoQueryPossibleException() {
        super(MESSAGE);
    }
}
