-- Users schema

-- !Ups

CREATE TABLE bank (
  id  varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  bank_identifier BIGINT NOT NULL,
  PRIMARY KEY (id)
);


CREATE UNIQUE INDEX uq_bank_identifier ON bank (bank_identifier);

-- !Downs

DROP TABLE bank;