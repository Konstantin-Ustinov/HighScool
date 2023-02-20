CREATE TABLE car (
    id integer PRIMARY KEY,
    brand varchar NOT NULL,
    model varchar NOT NULL,
    coast integer ADD CONSTRAINT coast_constraint CONSTRAINT (coast > 0)
)

CREATE TABLE person (
    id integer PRIMARY KEY,
    name varchar NOT NULL,
    age integer ADD CONSTRAINT coast_constraint CONSTRAINT (age > 0),
    have_license boolean DEFAULT false,
    car_id integer REFERENCES car (id) NOT NULL
)