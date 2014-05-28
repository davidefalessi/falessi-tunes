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

import javax.inject.Named;
import javax.inject.Provider;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.google.inject.Inject;

public final class BasicDataSourceProvider
    implements Provider<DataSource>
{

    private final BasicDataSource dataSource = new BasicDataSource();

    @Inject
    public void setDriverClassName( @Named("JDBC.driver") final String driverClassName )
    {
        dataSource.setDriverClassName( driverClassName );
    }

    @Inject
    public void setUrl( @Named("JDBC.url") String url )
    {
        dataSource.setUrl( url );
    }

    @Inject
    public void setUser( @Named( "JDBC.username" )
    final String username )
    {
        dataSource.setUsername( username );
    }

    @Inject
    public void setPassword( @Named( "JDBC.password" )
    final String password )
    {
        dataSource.setPassword( password );
    }

    @Inject
    public void setAutoCommit( @Named( "JDBC.autoCommit" )
    final boolean autoCommit )
    {
        dataSource.setDefaultAutoCommit( autoCommit );
    }

    /**
     * {@inheritDoc}
     */
    public DataSource get()
    {
        return dataSource;
    }

}
