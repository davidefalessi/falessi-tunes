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

function selectAlbums( authorId )
{
    $( '#albumId' ).empty();

    $.ajax(
    {
        url : "system/proxy.php/authors/" + authorId + "/albums/",
        type : "GET",
        success : function( authors )
        {
            $.each( authors, function( index, author )
            {
                $( '#albumId' ).append( $( '<option></option>' )
                                        .attr( 'value', author.id )
                                        .text( author.name ) ); 
            } );
        }
    } );
}

$( document ).ready( function()
{

    $.ajax(
    {
        url : "system/proxy.php/authors/",
        type : "GET",
        success : function( authors )
        {
            $.each( authors, function( index, author )
            {
                $( '#authorId' ).append( $( '<option></option>' )
                                      .attr( 'value', author.id )
                                      .text( author.name ) ); 
            } );
        }
    } );

    $( '#track-add' ).submit( function( e )
    {
        e.preventDefault();

        var data = {
                albumId:  $( '#albumId' ).val(),
                name: $( '#name' ).val(),
                genre: $( '#genre' ).val(),
                length: $( '#length' ).val(),
                producer: $( '#producer' ).val(),
                label: $( '#label' ).val(),
                notes: $( '#notes' ).val()
        };

        var stringData = JSON.stringify( data );

        $.ajax(
        {
            url : 'system/proxy.php/tracks/',
            type : 'PUT',
            data: stringData,
            dataType: 'text',
            contentType: 'application/json',
            success: function( data, textStatus, jqXHR )
            {
                alert( 'Track successfully added!' );
                $( '#track-add' )[0].reset();
            },
            error: function( jqXHR, textStatus, errorThrown )
            {
                alert( 'An error occurred while adding the Track\n\nServer replied \''
                        + textStatus
                        + '\': '
                        + errorThrown );
            }
        } );

    } );

} );
