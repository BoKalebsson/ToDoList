package io.github.bokalebsson.dao.sequencers;

public class PersonIdSequencer {

    private static int currentId = 0;

    public static int nextId(){
        return ++currentId;
    }

    public static int getCurrentId(){
        return currentId;
    }

    // Used for testing only.
    static void reset() {
        currentId = 0;
    }

    public static void setCurrentId(int id){
        if(id < 1) {
            throw new IllegalArgumentException("Id cannot be zero or negative.");
        }
        if(id < currentId) {
            throw new IllegalArgumentException("Id cannot be less than the current ID");
        }
        currentId = id;
    }

}
