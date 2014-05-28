--
--   Copyright 2014 Davide Falessi
--
--   Licensed under the Apache License, Version 2.0 (the "License");
--   you may not use this file except in compliance with the License.
--   You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--   Unless required by applicable law or agreed to in writing, software
--   distributed under the License is distributed on an "AS IS" BASIS,
--   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--   See the License for the specific language governing permissions and
--   limitations under the License.

DROP TABLE IF EXISTS author;
CREATE TABLE author(
    id INT(2) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    born_date DATE,
    gender VARCHAR(30),
    label VARCHAR(40),
    notes MEDIUMTEXT
);

DROP TABLE IF EXISTS album;
CREATE TABLE album(
    id INT(2) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    type VARCHAR(10),
    publication_date DATE,
    producer VARCHAR(50),
    genre VARCHAR(20),
    duration VARCHAR(10),
    track_number INT(2),
    notes MEDIUMTEXT,
    author_key INT(2),
    FOREIGN KEY (author_key) references author(id)
);

DROP TABLE IF EXISTS track;
CREATE TABLE track(
    id INT(2) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    duration VARCHAR(5),
    label VARCHAR(20),
    producer VARCHAR(30),
    genre VARCHAR(20),
    notes MEDIUMTEXT,
    album_key INT(2),
    FOREIGN KEY (album_key) references album(id)
);
