CREATE TABLE car(
    id    BIGSERIAL PRIMARY KEY,
    brand VARCHAR(255)   NOT NULL,
    model VARCHAR(255)   NOT NULL,
    price NUMERIC(9, 2) NOT NULL CHECK ( prise > 0 )
);

CREATE TABLE human
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    age            INTEGER     NOT NULL CHECK (age > 0 ),
    license        BOOLEAN,
    car_id         BIGINT,
    FOREIGN KEY (car_id) REFERENCES car
);
