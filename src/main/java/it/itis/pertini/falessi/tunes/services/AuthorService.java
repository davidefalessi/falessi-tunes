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
import it.itis.pertini.falessi.tunes.jdbc.AuthorListHandler;
import it.itis.pertini.falessi.tunes.jdbc.TrackListHandler;
import it.itis.pertini.falessi.tunes.model.Album;
import it.itis.pertini.falessi.tunes.model.Author;
import it.itis.pertini.falessi.tunes.model.Track;

import java.sql.SQLException;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.dbutils.QueryRunner;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

@Singleton
@Path( "authors" )
public final class AuthorService
    extends AbstractService<Author>
{

    private final AlbumListHandler albumListHandler;

    private final TrackListHandler trackListHandler;

    @Inject
    public AuthorService( QueryRunner queryRunner,
                          AuthorListHandler artistListHandler,
                          AlbumListHandler albumListHandler,
                          TrackListHandler trackListHandler,
                          Properties queries )
    {
        super( queryRunner, artistListHandler, queries );
        this.albumListHandler = albumListHandler;
        this.trackListHandler = trackListHandler;
    }

    @PUT
    @Consumes( { APPLICATION_XML, APPLICATION_JSON } )
    public Response insertOrUpdate( Author author )
    {
        return insertOrUpdate( "author.insertOrUpdate",
                               author.getId(),
                               author.getName(),
                               author.getBornDate(),
                               author.getGender(),
                               author.getRecordingLabel(),
                               author.getNotes() );
    }

    @DELETE
    @Path( "{authorId:\\d+}" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Response delete( @PathParam( "authorId" ) Long authorId )
    {
        return delete( "author.delete", authorId );
    }

    @GET
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Collection<Author> getAll()
    {
        return getAll( "author.selectAll" );
    }

    @GET
    @Path( "{authorId:\\d+}" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Author getArtistById( @PathParam( "authorId" ) Long authorId )
    {
        return getById( "author.select", authorId );
    }

    @GET
    @Path( "{authorId:\\d+}/albums" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Collection<Album> getAlbumsByArtistId( @PathParam( "authorId" ) Long authorId )
    {
        try
        {
            return getQueryRunner().query( getQuery( "album.selectAllByAuthor" ),
                                           albumListHandler, authorId );
        }
        catch ( SQLException e )
        {
            throw new WebApplicationException( new ResponseBuilderImpl()
                                              .entity( toErrorMessage( e ) )
                                              .status( Response.Status.INTERNAL_SERVER_ERROR )
                                              .build() );
        }
    }

    @GET
    @Path( "{authorId:\\d+}/albums/{albumId:\\d+}" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Album getAlbumByArtistIds( @PathParam( "authorId" ) Long authorId,
                                      @PathParam( "albumId" ) Long albumId )
    {
        try
        {
            return getQueryRunner().query( getQuery( "album.selectByAuthor" ),
                                           albumListHandler.getEntityHandler(), authorId, albumId );
        }
        catch ( SQLException e )
        {
            throw new WebApplicationException( new ResponseBuilderImpl()
                                              .entity( toErrorMessage( e ) )
                                              .status( Response.Status.INTERNAL_SERVER_ERROR )
                                              .build() );
        }
    }

    @GET
    @Path( "{authorId:\\d+}/albums/{albumId:\\d+}/tracks" )
    @Produces( { APPLICATION_XML, APPLICATION_JSON } )
    public Collection<Track> getTracksByArtisAndAlbumIds( @PathParam( "authorId" ) Long authorId,
                                                          @PathParam( "albumId" ) Long albumId )
    {
        try
        {
            return getQueryRunner().query( getQuery( "track.selectAllByAlbumAndAuthor" ),
                                           trackListHandler, authorId, albumId );
        }
        catch ( SQLException e )
        {
            throw new WebApplicationException( new ResponseBuilderImpl()
                                              .entity( toErrorMessage( e ) )
                                              .status( Response.Status.INTERNAL_SERVER_ERROR )
                                              .build() );
        }
    }

}
