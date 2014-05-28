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

import it.itis.pertini.falessi.tunes.model.Album;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Singleton;

@Singleton
public final class AlbumHandler
    extends AbstractEntityHandler<Album>
{

    protected Album toEntity(ResultSet rs)
        throws SQLException
    {
        Album album = new Album();
        album.setId( rs.getLong( "id" ) );
        album.setName( rs.getString( "name" ) );
        album.setGenre( rs.getString( "genre" ) );
        album.setLength( rs.getString( "duration" ) );
        album.setNotes( rs.getString( "notes" ) );
        album.setProducer( rs.getString( "producer" ) );
        album.setReleaseDate( rs.getDate( "publication_date" ) );
        album.setTracks( rs.getInt( "track_number" ) );
        album.setHardwareSupport( rs.getString( "type" ) );
        return album;
    }

}
