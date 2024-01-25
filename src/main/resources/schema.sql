create table tasks (
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         description VARCHAR(50) NOT NULL,
                         status varchar(50) NOT NULL,
                         creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);