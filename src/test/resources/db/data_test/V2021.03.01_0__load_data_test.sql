-- Create SCHEMA
DROP TABLE IF EXISTS PHONES_CATALOGUE;

CREATE TABLE IF NOT EXISTS PHONES_CATALOGUE
(
  PC_ID             UUID UNIQUE             NOT NULL,
  PC_NAME           VARCHAR(50) UNIQUE      NOT NULL,
  PC_PRICE          INT                     NOT NULL,
  PC_IMAGE          VARCHAR(100)            NOT NULL,
  PC_DESCRIPTION    VARCHAR(150)            NOT NULL,

  CONSTRAINT PK_PC_ID PRIMARY KEY (PC_ID)
);

-- INSERT a default Phone Catalog Data
INSERT INTO PHONES_CATALOGUE(PC_ID, PC_NAME, PC_PRICE, PC_IMAGE, PC_DESCRIPTION) VALUES
(uuid_generate_v4(), 'EricssonTest', 100, 'http://www.ericsson.com/image.png', 'Ericsson Mobile'),
(uuid_generate_v4(), 'NokiaTest', 100, 'http://www.nokia.com/image.png', 'Nokia Mobile'),
(uuid_generate_v4(), 'iPhoneTest', 100, 'http://www.apple.com/image.png', 'Apple Mobile'),
(uuid_generate_v4(), 'BlackberryTest', 100, 'http://www.blackberry.com/image.png', 'Blackberry Mobile'),
(uuid_generate_v4(), 'SamsungTest', 100, 'http://www.samsung.com/image.png', 'Samsung Mobile'),
(uuid_generate_v4(), 'XiaomiTest', 100, 'http://www.xiaomi.com/image.png', 'Xiaomi Mobile');

