package dataservice;

import datasource.InMemDB;
import datasource.MariaDB;
import models.Person;

import java.util.List;

public class PersonDataService {
    public List<Person> getAllPersons() {
        MariaDB db = new MariaDB();
        return db.getAllPersons();
        //return InMemDB.getInstance().getAllPersons();
    }

    public Person getPerson(int persId) {
        MariaDB db = new MariaDB();
        return db.getPerson(persId);
        //return InMemDB.getInstance().getPerson(persId);
    }

    public int  updatePerson(int persId, Person pers) {
        MariaDB db = new MariaDB();
        return db.updatePersList(persId, pers);
        //return InMemDB.getInstance().updatePersList(persId, pers);
    }

    public int deletePerson(int persId) {
        MariaDB db = new MariaDB();
        return db.deleteFromPersList(persId);
        //return InMemDB.getInstance().deleteFromPersList(persId);
    }

    public int addPerson(Person pers) {
        MariaDB db = new MariaDB();
        return db.insertInPersList(pers);
        //return InMemDB.getInstance().insertInPersList(pers);
    }
}
