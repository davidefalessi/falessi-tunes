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

function deleteAuthor( authorId, authorName )
{
    var answer = confirm( 'Are you sure you want to delete author "' + authorName + '"?' );

    if ( answer )
    {
        $.ajax(
        {
            url : 'system/proxy.php/authors/' + authorId,
            type : 'DELETE',
            success : function( data, textStatus, jqXHR )
            {
                $( '#panel' + authorId ).remove();

                alert( 'Author "' + authorName + ' successfully removed!"' )
            },
            error: function( jqXHR, textStatus, errorThrown )
            {
                alert( 'An error occurred while removing Author "' + authorName + '": '
                        + textStatus
                        + '\': '
                        + errorThrown );
            }
        } );
    }
}

function loadAlbums( authorId )
{
    var albumsDiv = $( '#albums' + authorId );
    albumsDiv.empty();
    albumsDiv.append( '<img src="images/ajax-loader.gif" />' );

    $.ajax(
    {
        url: 'albums.php/' + authorId,
        type: 'GET',
        success : function( albums )
        {
            albumsDiv.empty();
            albumsDiv.append( albums );
        }
    } );
}

function loadTracks( authorId, albumId )
{
    var tracksDiv = $( '#tracks' + albumId );
    tracksDiv.empty();
    tracksDiv.append( '<img src="images/ajax-loader.gif" />' );

    $.ajax(
    {
        url: 'tracks.php/' + authorId + '/' + albumId,
        type: 'GET',
        success : function( tracks )
        {
            tracksDiv.empty();
            tracksDiv.append( tracks );
        }
    } );
}

$( document ).ready( function()
{

    $.ajax(
    {
        url: 'authors.php',
        type: 'GET',
        success : function( authors )
        {
            $( '#authors' ).empty();
            $( '#authors' ).append( authors );
        }
    } );

} );
