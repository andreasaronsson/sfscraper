CREATE TABLE sqlite_sequence(name,seq);
CREATE TABLE posts (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "name" TEXT NOT NULL,
    "post" TEXT,
    "hash" INTEGER,
    "sent" TEXT
);
CREATE UNIQUE INDEX "id" on posts (id ASC);
CREATE TABLE links (
    "id" INTEGER PRIMARY KEY NOT NULL,
    "link" TEXT,
    "hash" INTEGER
);
