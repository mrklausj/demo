package datasource;

import models.Person;

import java.util.ArrayList;
import java.util.List;

public class InMemDB {
    private static int nextId = 1;
    private static List<Person> personList;
    private static InMemDB instance;

    private int getNextId() {
        return nextId++;
    }

    private InMemDB() {
        personList = new ArrayList<>();
        personList.add(new Person(getNextId(), "Zalazar", "zal@mail.com", "Diktator"));
    }

    public static synchronized InMemDB getInstance() {
        if (instance == null) {
            instance = new InMemDB();
        }
        return instance;
    }

    public List<Person> getAllPersons() {
        return personList;
    }

    public Person getPerson(int persId) {
        for (Person p: personList) {
            if (p.getPersId() == persId) {
                return p;
            }
        }
        return null;
    }

    public int updatePersList(int persId, Person pers) {
        for (Person p: personList) {
            if (p.getPersId() == persId) {
                pers.setPersId(persId);
                personList.set(personList.indexOf(p), pers);
                return 1;
            }
        }
        return 0;
    }

    public int deleteFromPersList(int persId) {
        for (Person p: personList) {
            if (p.getPersId() == persId) {
                personList.remove(p);
                return 1;
            }
        }
        return 0;
    }

    public int insertInPersList(Person pers) {
        pers.setPersId(getNextId());
        personList.add(pers);
        return 1;
    }
}
