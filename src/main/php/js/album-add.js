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

$( document ).ready( function()
{

    $( '#releaseDateGroup' ).datepicker( {
        format: "yyyy-mm-dd",
        autoclose: true
    } );

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

    $( '#album-add' ).submit( function( e )
    {
        e.preventDefault();

        var data = {
                authorId:  $( '#authorId' ).val(),
                name: $( '#name' ).val(),
                recordingLabel: $( '#recordingLabel' ).val(),
                releaseDate: $( '#releaseDate' ).val(),
                genre: $( '#genre' ).val(),
                hardwareSupport: $( '#hardwareSupport' ).val(),
                producer: $( '#producer' ).val(),
                length: $( '#length' ).val(),
                tracks: $( '#tracks' ).val(),
                notes: $( '#notes' ).val()
        };

        var stringData = JSON.stringify( data );

        $.ajax(
        {
            url : 'system/proxy.php/albums/',
            type : 'PUT',
            data: stringData,
            dataType: 'text',
            contentType: 'application/json',
            success: function( data, textStatus, jqXHR )
            {
                alert( 'Album successfully added!' );
                $( '#album-add' )[0].reset();
            },
            error: function( jqXHR, textStatus, errorThrown )
            {
                alert( 'An error occurred while adding the Album\n\nServer replied \''
                        + textStatus
                        + '\': '
                        + errorThrown );
            }
        } );

    } );

} );
