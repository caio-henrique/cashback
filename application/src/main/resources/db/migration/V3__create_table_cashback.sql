CREATE TABLE IF NOT EXISTS cashback (

    id SERIAL PRIMARY KEY,
    gender VARCHAR(20) NOT NULL,
    monday INT NOT NULL,
    tuesday INT NOT NULL,
    wednesday INT NOT NULL,
    thursday INT NOT NULL,
    friday INT NOT NULL,
    saturday INT NOT NULL,
    sunday INT NOT NULL,
    create_at TIMESTAMP DEFAULT now()
);

INSERT INTO cashback (gender, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES ('POP', 25, 7, 6, 2, 10, 15, 20);
INSERT INTO cashback (gender, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES ('MPB', 30, 5, 10, 15, 20, 25, 30);
INSERT INTO cashback (gender, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES ('CLASSIC', 35, 3, 5, 8, 13, 18, 25);
INSERT INTO cashback (gender, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES ('ROCK', 40, 10, 15, 15, 15, 20, 40);





