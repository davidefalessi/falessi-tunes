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

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import it.itis.pertini.falessi.tunes.jdbc.AlbumListHandler;
import it.itis.pertini.falessi.tunes.model.Album;

import java.util.Collection;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.dbutils.QueryRunner;

@Singleton
@Path( "albums" )
public final class AlbumService
    extends AbstractService<Album>
{

    @Inject
    public AlbumService( QueryRunner queryRunner, AlbumListHandler entityListHandler, Properties queries )
    {
        super( queryRunner, entityListHandler, queries );
    }

    @GET
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Collection<Album> getAll()
    {
        return getAll( "album.selectAll" );
    }

    @GET
    @Path( "{albumId:\\d+}" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Album getAlbumById( @PathParam( "albumId" ) Long albumId )
    {
        return getById( "album.select", albumId );
    }

    @PUT
    @Consumes( { APPLICATION_XML, APPLICATION_JSON } )
    public Response insertOrUpdate( Album album )
    {
        return insertOrUpdate( "album.insertOrUpdate",
                               album.getId(),
                               album.getName(),
                               album.getHardwareSupport(),
                               album.getReleaseDate(),
                               album.getProducer(),
                               album.getGenre(),
                               album.getLength(),
                               album.getTracks(),
                               album.getNotes(),
                               album.getAuthorId() );
    }

    @DELETE
    @Path( "{albumId:\\d+}" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Response delete( @PathParam( "albumId" ) Long albumId )
    {
        return delete( "album.delete", albumId );
    }

}
