package io.github.bokalebsson.dao.database;

import java.sql.SQLException;
import java.util.Collection;

public interface People {

    DBPerson create(DBPerson dbPerson) throws SQLException;

    Collection<DBPerson> findAll() throws SQLException;

     DBPerson findById(int id) throws SQLException;

    Collection<DBPerson> findByName(String name) throws SQLException;

    DBPerson update(DBPerson dbPerson) throws SQLException;

    boolean deleteById(int id) throws SQLException;

}
