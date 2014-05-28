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
package it.itis.pertini.falessi.tunes;

import static com.google.inject.Scopes.SINGLETON;
import it.itis.pertini.falessi.tunes.jdbc.AlbumHandler;
import it.itis.pertini.falessi.tunes.jdbc.AlbumListHandler;
import it.itis.pertini.falessi.tunes.jdbc.AuthorHandler;
import it.itis.pertini.falessi.tunes.jdbc.AuthorListHandler;
import it.itis.pertini.falessi.tunes.jdbc.BasicDataSourceProvider;
import it.itis.pertini.falessi.tunes.jdbc.QueryRunnerProvider;
import it.itis.pertini.falessi.tunes.jdbc.TrackHandler;
import it.itis.pertini.falessi.tunes.jdbc.TrackListHandler;
import it.itis.pertini.falessi.tunes.services.AlbumService;
import it.itis.pertini.falessi.tunes.services.AuthorService;
import it.itis.pertini.falessi.tunes.services.TrackService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public final class TunesContextListener
    extends GuiceServletContextListener
{

    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector( new JerseyServletModule()
        {

            @Override
            protected void configureServlets()
            {
                // get and binds JDBC properties
                Names.bindProperties( binder(), loadProperties( "jdbc.properties" ) );
                bind( Properties.class ).toInstance( loadProperties( "queries.properties" ) );

                // bind JDBC components
                bind( DataSource.class ).toProvider( BasicDataSourceProvider.class ).in( SINGLETON );
                bind( QueryRunner.class ).toProvider( QueryRunnerProvider.class ).in( SINGLETON );
                bind( AuthorHandler.class );
                bind( AuthorListHandler.class );
                bind( AlbumHandler.class );
                bind( AlbumListHandler.class );
                bind( TrackHandler.class );
                bind( TrackListHandler.class );

                // JSON serializer
                bind( JacksonJsonProvider.class )
                .toInstance( new JacksonJsonProvider( new ObjectMapper()
                .setAnnotationIntrospector( new JaxbAnnotationIntrospector() ) ) );

                // main servlet
                serve( "/*" ).with( GuiceContainer.class );

                // bind services
                bind( AuthorService.class );
                bind( AlbumService.class );
                bind( TrackService.class );
            }

        } );
    }

    private Properties loadProperties( String fileName )
    {
        Properties properties = new Properties();

        InputStream input = getClass().getClassLoader().getResourceAsStream( fileName );
        try
        {
            properties.load( input );
        }
        catch ( IOException e )
        {
            // cannot happen
        }
        finally
        {
            if ( input != null )
            {
                try
                {
                    input.close();
                }
                catch ( IOException e )
                {
                    // close quietly
                }
            }
        }

        return properties;
    }

}
