-- Drop tables if they exist to allow reinitialization
DROP TABLE IF EXISTS birth_certificate;
DROP TABLE IF EXISTS person;

-- Create the 'person' table
CREATE TABLE person (
                        id UUID PRIMARY KEY,
                        first_name TEXT NOT NULL,
                        last_name TEXT NOT NULL,
                        birth_date DATE NOT NULL
);

-- Create the 'birth_certificate' table
CREATE TABLE birth_certificate (
                                   id UUID PRIMARY KEY,
                                   person_id UUID REFERENCES person(id),
                                   birth_place TEXT NOT NULL
);