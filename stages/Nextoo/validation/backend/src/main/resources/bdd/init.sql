DROP TABLE IF EXISTS Genres CASCADE;
DROP TABLE IF EXISTS Watched CASCADE;
DROP TABLE IF EXISTS Profile CASCADE;


CREATE TABLE Profile(
    pid SERIAL,
    name TEXT,
    tag VARCHAR(4),
    lang TEXT DEFAULT 'en',
    adult BOOL DEFAULT false,
    CONSTRAINT PK_Profile PRIMARY KEY (pid)
);


CREATE TABLE Watched(
    pid INT,
    mid INT,
    CONSTRAINT PK_Watched PRIMARY KEY (pid, mid),
    CONSTRAINT FK_Profile FOREIGN KEY (pid) REFERENCES Profile(pid)
);


CREATE TABLE Genres(
    pid INT,
    gid INT,
    CONSTRAINT PK_Genres PRIMARY KEY (pid, gid),
    CONSTRAINT FK_Profile FOREIGN KEY (pid) REFERENCES Profile(pid)
);