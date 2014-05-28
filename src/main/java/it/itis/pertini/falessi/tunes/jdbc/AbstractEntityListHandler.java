/*
 *    Copyright 2014 Davide Falessi
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package it.itis.pertini.falessi.tunes.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public abstract class AbstractEntityListHandler<E>
    implements ResultSetHandler<Collection<E>>
{

    private final AbstractEntityHandler<E> entityHandler;

    public AbstractEntityListHandler( AbstractEntityHandler<E> entityHandler )
    {
        this.entityHandler = entityHandler;
    }

    public AbstractEntityHandler<E> getEntityHandler()
    {
        return entityHandler;
    }

    public final Collection<E> handle( ResultSet rs )
        throws SQLException
    {
        List<E> entitiesList = new LinkedList<E>();

        while ( rs.next() )
        {
            E entity = entityHandler.toEntity( rs );
            if ( entity != null )
            {
                entitiesList.add( entity );
            }
        }

        return entitiesList;
    }

}
