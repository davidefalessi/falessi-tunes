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
package it.itis.pertini.falessi.tunes.model;

import it.itis.pertini.falessi.tunes.jaxb.DateAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "album" )
public class Album
    extends AbstractEntity
{

    @XmlElement
    private Long authorId;

    @XmlElement
    private String genre;

    @XmlElement
    private String length;

    @XmlElement
    private int tracks;

    @XmlElement
    @XmlJavaTypeAdapter( DateAdapter.class )
    private Date releaseDate;

    @XmlElement
    private String producer;

    @XmlElement
    private String hardwareSupport;

    public Long getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId( Long authorId )
    {
        this.authorId = authorId;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre( String genre )
    {
        this.genre = genre;
    }

    public String getLength()
    {
        return length;
    }

    public void setLength( String length )
    {
        this.length = length;
    }

    public int getTracks()
    {
        return tracks;
    }

    public void setTracks( int tracks )
    {
        this.tracks = tracks;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate( Date releaseDate )
    {
        this.releaseDate = releaseDate;
    }

    public String getProducer()
    {
        return producer;
    }

    public void setProducer( String producer )
    {
        this.producer = producer;
    }

    public String getHardwareSupport()
    {
        return hardwareSupport;
    }

    public void setHardwareSupport( String hardwareSupport )
    {
        this.hardwareSupport = hardwareSupport;
    }

}
