-- INSERT a default Phone Catalog Data
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- Necessary module to generate random UUID

INSERT INTO PHONES_CATALOGUE(PC_ID, PC_NAME, PC_PRICE, PC_IMAGE, PC_DESCRIPTION) VALUES
(uuid_generate_v4(), 'Ericsson', 100, 'http://www.ericsson.com/image.png', 'Ericsson Mobile'),
(uuid_generate_v4(), 'Nokia', 100, 'http://www.nokia.com/image.png', 'Nokia Mobile'),
(uuid_generate_v4(), 'iPhone', 100, 'http://www.apple.com/image.png', 'Apple Mobile'),
(uuid_generate_v4(), 'Blackberry', 100, 'http://www.blackberry.com/image.png', 'Blackberry Mobile'),
(uuid_generate_v4(), 'Samsung', 100, 'http://www.samsung.com/image.png', 'Samsung Mobile'),
(uuid_generate_v4(), 'Xiaomi', 100, 'http://www.xiaomi.com/image.png', 'Xiaomi Mobile');
