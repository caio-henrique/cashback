CREATE TABLE IF NOT EXISTS sale (

    id SERIAL PRIMARY KEY,
    total NUMERIC(19, 2) NOT NULL,
    cashback NUMERIC(19, 2) NOT NULL,
    create_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS product (

    id SERIAL PRIMARY KEY,
    description VARCHAR(500) NOT NULL,
    price NUMERIC(19, 2) NOT NULL,
    cashback NUMERIC(19, 2) NOT NULL,
    sale_id INTEGER REFERENCES sale(id),
    create_at TIMESTAMP DEFAULT now()
);