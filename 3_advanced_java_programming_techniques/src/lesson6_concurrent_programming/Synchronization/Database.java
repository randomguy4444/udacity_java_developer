package lesson6_concurrent_programming.Synchronization;

import java.util.Objects;

public final class Database {
    private static Database database;

    private Database() {}
    // three ways to write synchronized method for singleton DB
    // method 1
    /*
    public static Database getInstance() {
        synchronized (Database.class) {
            if (database == null) {
                database = new Database();
                database.connect("/usr/local/data/users.db");
            }
        }
        return database;
    } */

    // method 2
    // challenge for this is only one thread will get through and others has to wait outside the function
    // even if the DB is initialized
    /*
    public synchronized static Database getInstance() {
        if (database == null) {
            database = new Database();
            database.connect("/usr/local/data/users.db");
        }
        return database;
    }*/

    // method 3
    // what is teh diff between this and method 1?
    // this allows return quicker if the database != null
    public static Database getInstance() {
        if (database == null) {
            synchronized (Database.class) {
                // this second null check handles when two threads are at the synchronized block
                // e.g., one thread already enters and the other thread waits outside
                // this way, only one will see the null
                if (database == null) {
                    database = new Database();
                    database.connect("/usr/local/data/users.db");
                }
            }
        }
        return database;
    }

    // Connects to the remote database.
    private void connect(String url) {
        Objects.requireNonNull(url);
    }

    public static void main(String[] args) {
        Database a = Database.getInstance();
        Database b = Database.getInstance();

        System.out.println(a == b);
    }
}