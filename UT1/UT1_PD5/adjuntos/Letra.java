package org.example.UT1_TA3;

public enum Letra {
    VOCAL("aeiouAEIOU"),
    CONSONANTE("");

    private final String caracteres;

    Letra(String caracteres) {
        this.caracteres = caracteres;
    }

    public static Letra getTipo(char letra) {
        if (!Character.isLetter(letra)) {
           return null;
        }
        if (VOCAL.caracteres.contains(Character.toString(letra))) {
            return VOCAL;
        }
        else {
            return CONSONANTE;
        }

    }
}
