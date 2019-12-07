CREATE TABLE IF NOT EXISTS album (

    id serial PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    release_date VARCHAR(20),
    total_tracks INT,
    artists VARCHAR(255) NOT NULL,
    price NUMERIC(19, 2) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    create_at TIMESTAMP DEFAULT now()
);