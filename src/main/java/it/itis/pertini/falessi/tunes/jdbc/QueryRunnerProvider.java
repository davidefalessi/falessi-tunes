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

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.google.inject.Provider;

public final class QueryRunnerProvider
    implements Provider<QueryRunner>
{

    private final DataSource dataSource;

    @Inject
    public QueryRunnerProvider( DataSource dataSource )
    {
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    public QueryRunner get()
    {
        return new QueryRunner( dataSource );
    }

}
