public class Pokemon {
    public static final String[] TYPES = { "Normal", "Fire", "Fighting", "Water", "Flying", "Grass", "Poison",
            "Electric", "Ground", "Psychic", "Rock", "Ice", "Bug", "Dragon", "Ghost", "Dark", "Steel", "Fairy" };
    public static final String DEFAULT_NAME = "Missingno.";
    public static final String DEFAULT_TYPE1 = "Normal";
    public static final String DEFAULT_TYPE2 = null;

    private String name;
    private String type1;
    private String type2;

    public Pokemon(String name, String type1, String type2) {
        if (!setAll(name, type1, type2)) {
            throw new IllegalArgumentException("Invalid Pokemon name or type.");
        }
    }

    public Pokemon(String name, String type) {
        this(name, type, null);
    }

    public Pokemon() {
        this(DEFAULT_NAME, DEFAULT_TYPE1, DEFAULT_TYPE2);
    }

    public Pokemon(Pokemon p) {
        if (p == null) {
            throw new IllegalArgumentException("Cannot copy null Pokemon.");
        }

        setAll(p.name, p.type1, p.type2);
    }

    public boolean setName(String name) {
        if (name != null && name.length() > 0) {
            this.name = toTitleCase(name);
            return true;
        }

        return false;
    }

    public boolean setType1(String type1) {
        if (isValidType(type1)) {
            this.type1 = toTitleCaseWord(type1);
            return true;
        }

        return false;
    }

    public boolean setType2(String type2) {
        if (type2 == null) {
            this.type2 = null;
            return true;
        } else if (isValidType(type2)) {
            this.type2 = toTitleCaseWord(type2);
            return true;
        }

        return false;
    }

    public boolean setAll(String name, String type1, String type2) {
        if (name != null && name.length() > 0 && isValidType(type1)
                && (type2 == null || isValidType(type2))) {
            this.name = toTitleCase(name);
            this.type1 = toTitleCaseWord(type1);
            this.type2 = type2 == null ? null : toTitleCaseWord(type2);
            return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    @Override
    public String toString() {
        return "Pokemon: Name = " + name + ", Type 1: " + type1 + ", Type 2: " + type2;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Pokemon otherPokemon = (Pokemon) other;
        boolean type2Equals = (type2 == null && otherPokemon.type2 == null)
                || (type2 != null && otherPokemon.type2 != null && type2.equals(otherPokemon.type2));

        return name.equals(otherPokemon.name) && type1.equals(otherPokemon.type1) && type2Equals;
    }

    public String toRow() {
        if (type2 == null) {
            return String.format("%s [%s]", name, type1);
        }

        return String.format("%s [%s - %s]", name, type1, type2);
    }

    private boolean isValidType(String type) {
        boolean isValid = false;
        int count = 0;

        while (type != null && count < TYPES.length && !isValid) {
            isValid = TYPES[count].equalsIgnoreCase(type);
            count++;
        }

        return isValid;
    }

    private String toTitleCase(String s) {
        String[] words = s.split(" ");
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            builder.append(" ").append(toTitleCaseWord(word));
        }

        return builder.toString().substring(1);
    }

    private String toTitleCaseWord(String word) {
        char firstLetter = word.toUpperCase().charAt(0);
        String restOfWord = word.toLowerCase().substring(1);
        return firstLetter + restOfWord;
    }
}