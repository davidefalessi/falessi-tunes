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

import com.sun.jersey.api.provider.jaxb.XmlHeader;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "author" )
public class Author
    extends AbstractEntity
{

    @XmlElement
    private String gender;

    @XmlElement
    private String recordingLabel;

    @XmlElement
    @XmlJavaTypeAdapter( DateAdapter.class )
    private Date bornDate;

    public String getGender()
    {
        return gender;
    }

    public void setGender( String gender )
    {
        this.gender = gender;
    }

    public String getRecordingLabel()
    {
        return recordingLabel;
    }

    public void setRecordingLabel( String recordingLabel )
    {
        this.recordingLabel = recordingLabel;
    }

    public Date getBornDate()
    {
        return bornDate;
    }

    public void setBornDate( Date bornDate )
    {
        this.bornDate = bornDate;
    }

}
