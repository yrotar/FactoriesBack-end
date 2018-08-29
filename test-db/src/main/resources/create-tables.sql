DROP TABLE IF EXISTS company;
CREATE TABLE company (
  company_id            INT              NOT NULL AUTO_INCREMENT,
  name                  VARCHAR(255)     NOT NULL UNIQUE,
  employees             INT              NOT NULL,
  PRIMARY KEY (company_id)
);

DROP TABLE IF EXISTS phone;
CREATE TABLE phone (
  phone_id       INT            NOT NULL AUTO_INCREMENT,
  name           VARCHAR(255)   NOT NULL,
  price          INT            NOT NULL,
  company_id     INT            NOT NULL,
  PRIMARY KEY (phone_id),
  FOREIGN KEY (company_id) REFERENCES company(company_id)  ON DELETE CASCADE
);