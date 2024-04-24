# sql_injection_experiment

run setup.sh or the commands in it

    > the versions used in the moment the project was made were
        - maria-db: 10.11.6
        - java: 17.0.11
        - maven: 3.8.7 

after everything is properly installed, set up mariaDB configs

    'sudo mysql -u root -p'

the default password for root user in mariaDB is an empty string, so just pressing enter may do the job

in sql shell, run the following commands

    CREATE USER 'username' IDENTIFIED BY 'password';

    GRANT ALL PRIVILEGES ON db.* TO 'username';

    FLUSH PRIVILEGES;

    CREATE DATABASE db;

    USE db;

    CREATE TABLE Users (username varchar(20), password varchar(20));

    INSERT INTO Users VALUES ('usuario1', 'senha1');
    INSERT INTO Users VALUES ('usuario2', 'senha2');
    INSERT INTO Users VALUES ('usuario3', 'senha3');
    INSERT INTO Users VALUES ('usuario4', 'senha4');

to start server, first compile with

    'mvn compile'

WARNING: maven may download some packages for the first compilation. That's fine. DONT PANIC

then run with

    'mvn exec:java'

WARNING: maven may download some packages for the first time it run. That's fine. DONT PANIC