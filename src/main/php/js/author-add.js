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

    $( '#bornDateGroup' ).datepicker( {
        format: "yyyy-mm-dd",
        autoclose: true
    } );

    $( '#author-add' ).submit( function( e )
    {
        e.preventDefault();

        var data = {
                name: $( '#name' ).val(),
                gender: $( '#gender' ).val(),
                recordingLabel: $( '#recordingLabel' ).val(),
                bornDate: $( '#bornDate' ).val(),
                notes: $( '#notes' ).val()
        };

        var stringData = JSON.stringify( data );

        $.ajax(
        {
            url : 'system/proxy.php/authors/',
            type : 'PUT',
            data: stringData,
            dataType: 'text',
            contentType: 'application/json',
            success: function( data, textStatus, jqXHR )
            {
                alert( 'Author successfully added!' );
                $( '#author-add' )[0].reset();
            },
            error: function( jqXHR, textStatus, errorThrown )
            {
                alert( 'An error occurred while adding the Author\n\nServer replied \''
                        + textStatus
                        + '\': '
                        + errorThrown );
            }
        } );

    } );

} );
