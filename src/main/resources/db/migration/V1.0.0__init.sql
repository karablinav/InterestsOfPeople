CREATE SEQUENCE sector_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  START WITH 600;

CREATE SEQUENCE users_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 10;

CREATE SEQUENCE users_info_id_seq
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 10;


CREATE TABLE users
(
  id   INTEGER DEFAULT nextval('users_id_seq') NOT NULL,
  name VARCHAR(100) UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE users_info
(
  id     INTEGER DEFAULT nextval('users_info_id_seq') NOT NULL,
  user_id bigint NOT NULL,
  checkbox BOOLEAN,
  PRIMARY KEY(id),
  CONSTRAINT user_info_reference FOREIGN KEY (user_id) REFERENCES users (id)

);

CREATE TABLE sector
(
  id        INTEGER DEFAULT nextval('sector_id_seq') NOT NULL PRIMARY KEY,
  name      VARCHAR(100)                             NOT NULL,
  parent_id INTEGER
);

CREATE TABLE users_info_sectors
(
  user_info_id INTEGER,
  sector_id    INTEGER,
  CONSTRAINT users_info_sector_pkey PRIMARY KEY (user_info_id, sector_id),
  FOREIGN KEY (user_info_id) REFERENCES users_info (id),
  FOREIGN KEY (sector_id) REFERENCES sector (id)
);


