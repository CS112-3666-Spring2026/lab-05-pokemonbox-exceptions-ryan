public class PokemonBox {
    public static final int DEFAULT_CAPACITY = 10;

    private Pokemon[] caught;
    private int numCaught;

    public PokemonBox(Pokemon[] caught) {
        if (caught == null || caught.length == 0) {
            throw new IllegalArgumentException("Invalid Pokemon array provided to PokemonBox.");
        }

        this.numCaught = caught.length;
        this.caught = deepCopyArray(caught, numCaught * 2);
    }

    public PokemonBox() {
        caught = new Pokemon[DEFAULT_CAPACITY];
        numCaught = 0;
    }

    public int getLocation(String pokemonName) {
        int location = -1;
        int count = 0;

        while (count < numCaught && location == -1) {
            if (caught[count].getName().equalsIgnoreCase(pokemonName)) {
                location = count;
            } else {
                count++;
            }
        }

        return location;
    }

    public Pokemon getPokemon(int location) {
        if (location < 0 || location >= numCaught) {
            throw new IndexOutOfBoundsException("Invalid Pokemon location.");
        }

        return new Pokemon(caught[location]);
    }

    public int getNumCaught() {
        return numCaught;
    }

    public boolean isEmpty() {
        return numCaught == 0;
    }

    public boolean hasPokemon(String pokemonName) {
        return getLocation(pokemonName) != -1;
    }

    public void add(Pokemon newPoke) throws PokemonAlreadyExistsException {
        if (newPoke == null) {
            throw new IllegalArgumentException("Cannot add null Pokemon.");
        }

        if (hasPokemon(newPoke.getName())) {
            throw new PokemonAlreadyExistsException(newPoke.getName() + " already exists in this Pokemon Box.");
        }

        if (numCaught == caught.length) {
            caught = deepCopyArray(caught, numCaught * 2);
        }

        caught[numCaught] = new Pokemon(newPoke);
        numCaught++;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "This box is empty";
        }

        String all = "\t01. " + caught[0].toRow();

        for (int i = 1; i < numCaught; i++) {
            all += String.format("%n\t%02d. %s", i + 1, caught[i].toRow());
        }

        return String.format("This box has %d Pokemon, which are:%n%s", numCaught, all);
    }

    private Pokemon[] deepCopyArray(Pokemon[] p, int newLength) {
        Pokemon[] deepCopy = new Pokemon[newLength];

        for (int i = 0; i < numCaught; i++) {
            deepCopy[i] = new Pokemon(p[i]);
        }

        return deepCopy;
    }
}