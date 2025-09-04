package io.github.bokalebsson.dao.database;

import java.util.Collection;

public interface People {

    DBPerson create(DBPerson dbPerson);

    Collection<DBPerson> findAll();

     DBPerson findById(int id);

    Collection<DBPerson> findByName(String name);

    DBPerson update(DBPerson dbPerson);

    boolean deleteById(int id);

}
