public class AircraftCarrier {

    protected String[] coordinates;
    public String getName() {
        return "Aircraft Carrier";
    }

    public int getLength() {
        return 5;
    }

    /**
     * Used to pass the location of an AircraftCarrier
     * @param coordinates a location and length should be 2
     */
    public AircraftCarrier(String coordinates) {
        this.coordinates = coordinates.split(" ");
        if (this.coordinates.length != 2) {
            throw new ArrayStoreException("More than 2 elements stored!"); // TODO: Test line
        }
    }
}

class Battleship {
    protected String[] coordinates;
    public int getLength() {
        return 4;
    }

    public String getName() {
        return "Battleship";
    }

    /**
     * Used to pass the location of a Battleship
     * @param coordinates a location and length should be 2
     */
    public Battleship(String coordinates) {
        this.coordinates = coordinates.split(" ");
        if (this.coordinates.length != 2) {
            throw new ArrayStoreException("More than 2 elements stored!"); // TODO: Test line
        }
    }
}

class Submarine {
    protected String[] coordinates;
    public int getLength() {
        return 3;
    }

    public String getName() {
        return "Submarine";
    }

    /**
     * Used to pass the location of a Submarine
     * @param coordinates a location and length should be 2
     */
    public Submarine(String coordinates) {
        this.coordinates = coordinates.split(" ");
        if (this.coordinates.length != 2) {
            throw new ArrayStoreException("More than 2 elements stored!"); // TODO: Test line
        }
    }
}

class Cruiser {
    protected String[] coordinates;
    public int getLength() {
        return 3;
    }

    public String getName() {
        return "Cruiser";
    }

    /**
     * Used to pass the location of a Cruiser
     * @param coordinates a location and length should be 2
     */
    public Cruiser(String coordinates) {
        this.coordinates = coordinates.split(" ");
        if (this.coordinates.length != 2) {
            throw new ArrayStoreException("More than 2 elements stored!"); // TODO: Test line
        }
    }
}

class Destroyer {
    protected String[] coordinates;
    public int getLength() {
        return 2;
    }

    public String getName() {
        return "Destroyer";
    }

    /**
     * Used to pass the location of a Destroyer
     * @param coordinates a location and length should be 2
     */
    public Destroyer(String coordinates) {
        this.coordinates = coordinates.split(" ");
        if (this.coordinates.length != 2) {
            throw new ArrayStoreException("More than 2 elements stored!"); // TODO: Test line
        }
    }
}
