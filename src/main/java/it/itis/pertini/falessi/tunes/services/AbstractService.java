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
package it.itis.pertini.falessi.tunes.services;

import it.itis.pertini.falessi.tunes.jdbc.AbstractEntityListHandler;
import it.itis.pertini.falessi.tunes.model.ErrorMessage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.dbutils.QueryRunner;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

abstract class AbstractService<E>
{

    private final QueryRunner queryRunner;

    private final AbstractEntityListHandler<E> entityListHandler;

    private final Properties queries;

    public AbstractService( QueryRunner queryRunner, AbstractEntityListHandler<E> entityListHandler, Properties queries )
    {
        this.queryRunner = queryRunner;
        this.entityListHandler = entityListHandler;
        this.queries = queries;
    }

    protected final QueryRunner getQueryRunner()
    {
        return queryRunner;
    }

    protected final AbstractEntityListHandler<E> getEntityListHandler()
    {
        return entityListHandler;
    }

    protected final Collection<E> getAll( String queryId )
    {
        try
        {
            return queryRunner.query( getQuery( queryId ), entityListHandler );
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            throw new WebApplicationException( new ResponseBuilderImpl()
                                               .entity( toErrorMessage( e ) )
                                               .status( Response.Status.INTERNAL_SERVER_ERROR )
                                               .build() );
        }
    }

    protected final E getById( String queryId, long id )
    {
        try
        {
            E entity = queryRunner.query( getQuery( queryId ), entityListHandler.getEntityHandler(), id );

            if ( entity == null )
            {
                throw new WebApplicationException( new ResponseBuilderImpl()
                                                  .status( Response.Status.NOT_FOUND )
                                                  .build() );
            }

            return entity;
        }
        catch ( SQLException e )
        {
            throw new WebApplicationException( new ResponseBuilderImpl()
                                               .entity( toErrorMessage( e ) )
                                               .status( Response.Status.INTERNAL_SERVER_ERROR )
                                               .build() );
        }
    }

    protected final Response insertOrUpdate( String queryId, Object...params )
    {
        try
        {
            int updated = queryRunner.update( getQuery( queryId ), params );

            Status status;
            if ( updated != 0 )
            {
                status = Status.CREATED;
            }
            else
            {
                status = Status.NOT_FOUND;
            }

            return Response.status( status ).build();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();

            throw new WebApplicationException( new ResponseBuilderImpl()
                                              .entity( toErrorMessage( e ) )
                                              .status( Response.Status.INTERNAL_SERVER_ERROR )
                                              .build() );
        }
    }

    protected final Response delete( String queryId, Long id )
    {
        try
        {
            int deleted = queryRunner.update( getQuery( queryId ), id );

            Status status;
            if ( deleted != 0 )
            {
                status = Status.OK;
            }
            else
            {
                status = Status.NOT_FOUND;
            }

            return Response.status( status ).build();
        }
        catch ( SQLException e )
        {
            throw new WebApplicationException( new ResponseBuilderImpl()
                                              .entity( toErrorMessage( e ) )
                                              .status( Response.Status.INTERNAL_SERVER_ERROR )
                                              .build() );
        }
    }

    protected final String getQuery( String queryId )
    {
        return queries.getProperty( queryId );
    }

    protected static ErrorMessage toErrorMessage( SQLException e )
    {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode( e.getErrorCode() );
        errorMessage.setSqlState( e.getSQLState() );

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace( new PrintWriter( stringWriter ) );
        errorMessage.setStackTrace( stringWriter.toString() );

        return errorMessage;
    }

}
