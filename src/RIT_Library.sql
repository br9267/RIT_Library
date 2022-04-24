DROP DATABASE IF EXISTS RIT_Library;
CREATE DATABASE RIT_Library;
USE RIT_Library;
DROP TABLE IF EXISTS Role;
CREATE TABLE Role(
user_role VARCHAR(25) PRIMARY KEY NOT NULL DEFAULT 'User',
user_permission boolean NOT NULL DEFAULT FALSE
);

INSERT INTO Role
VALUES ( "Admin",true),("User",false);


DROP TABLE IF EXISTS User;
CREATE TABLE User(
user_id VARCHAR(20) PRIMARY KEY NOT NULL,
user_role VARCHAR(25) NOT NULL DEFAULT "User",
e_mail VARCHAR(30) NOT NULL,
CONSTRAINT FK_userRole FOREIGN KEY (user_role) REFERENCES Role(user_role)
); 

INSERT INTO User
VALUES ("Borninjo0001","Admin","br9267@rit.edu"),("Krstonjo0002","User","fh8641@rit.edu");

DROP TABLE IF EXISTS Login_credentials;
CREATE TABLE Login_credentials(
user_id VARCHAR(20) PRIMARY KEY NOT NULL,
password VARCHAR(100) NOT NULL,
CONSTRAINT FK_loginUser FOREIGN KEY (user_id) REFERENCES User(user_id)

);
INSERT INTO Login_credentials
VALUES ("Borninjo0001", "9f505621a2a40c0ee8d60202d868021c"),("Krstonjo0002", "8fc647403c480471011a2e9a687d2b07");
DROP TABLE IF EXISTS Student;
CREATE TABLE Student(
user_id VARCHAR(30) PRIMARY KEY NOT NULL,
first_name VARCHAR(30),
last_name VARCHAR(30),
number_of_books_allowed int DEFAULT 3,
major_id VARCHAR(10),
CONSTRAINT FK_studentUser FOREIGN KEY (user_id) REFERENCES User(user_id)
);

INSERT INTO Student
VALUES ("Borninjo0001", "Borna","Radosevic",10,"ISTE332"),("Krstonjo0002","Fran Krsto","Hrabric",4,"UWRT123");







DROP TABLE IF EXISTS Major;
CREATE TABLE Major(
major_id VARCHAR(10) PRIMARY KEY NOT NULL,
major_name VARCHAR(40),
major_description VARCHAR(255),
major_type VARCHAR(255)
);

INSERT INTO Major
VALUES ("ISTE332","Computer juggling","A course dedicated to jugling a computer and computer parts.","IT"),("UWRT123","Writing basics","A course where students learn how to write using a pen and paper.","GE");


DROP TABLE IF EXISTS Staff;
CREATE TABLE Staff(
user_id VARCHAR(10) PRIMARY KEY NOT NULL,
position_title VARCHAR(20),
CONSTRAINT FK_userStaff FOREIGN KEY (user_id) REFERENCES User(user_id)
);



DROP TABLE IF EXISTS payment;
CREATE TABLE payment(
payment_id INT UNSIGNED NOT NULL PRIMARY KEY,
late_fee DOUBLE,
late_days INT
);

INSERT INTO payment
VALUES (1,0,0),(2,0.5,0);


DROP TABLE IF EXISTS genre;
CREATE TABLE genre(
genre_id INT UNSIGNED NOT NULL PRIMARY KEY,
genre_name VARCHAR(255)
);

INSERT INTO genre
VALUES (0,"Action"),(1,"Mistery");


DROP TABLE IF EXISTS publisher;
CREATE TABLE publisher(
publisher_name VARCHAR(255) PRIMARY KEY,
publisher_city VARCHAR(255)
);

INSERT INTO publisher
VALUES ("Vatican Productions", "Vatican"),("Croatia writings", "Osijek");


DROP TABLE IF EXISTS course;
CREATE TABLE course(
course_name VARCHAR(255) PRIMARY KEY,
course_type VARCHAR(255)
);

INSERT INTO course
VALUES ("ISTE330","Flying"),("ISTE252","Proper nutrition while coding");


DROP TABLE IF EXISTS state;
CREATE TABLE state(
state_id INT UNSIGNED NOT NULL PRIMARY KEY,
state_type VARCHAR(255)
);

INSERT INTO state
VALUES (0,"A"),(1,"D");

DROP TABLE IF EXISTS author;
CREATE TABLE author(
author_id INT UNSIGNED NOT NULL PRIMARY KEY,
author_first_name VARCHAR(255),
author_last_name VARCHAR(255)
);

INSERT INTO author
VALUES (1,"Sanja","Polak"),(2,"Luka", "Modric");


DROP TABLE IF EXISTS review;
CREATE TABLE review(
review_grade INT(1) UNSIGNED NOT NULL PRIMARY KEY DEFAULT 0,
user_feedback VARCHAR(255) DEFAULT "",
user_recommendation BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO review
VALUES (3,"It was missing that special something", false),(5,"Great Book!!", true);


DROP TABLE IF EXISTS book;
CREATE TABLE book(
book_id INT UNSIGNED NOT NULL PRIMARY KEY,
publisher_name VARCHAR(255),
course_name VARCHAR(255),
book_description VARCHAR(255),
book_isbn INT UNIQUE,
book_published_year YEAR,
book_title VARCHAR(255),
book_genre_id INT UNSIGNED NOT NULL,
format_id VARCHAR(255),
book_state_id INT UNSIGNED NOT NULL,
review_grade INT(1) UNSIGNED NOT NULL DEFAULT 1,
CONSTRAINT FK_genreBook FOREIGN KEY (book_genre_id) REFERENCES genre(genre_id),
CONSTRAINT FK_publisherBook FOREIGN KEY (publisher_name) REFERENCES publisher(publisher_name),
CONSTRAINT FK_courseBook FOREIGN KEY (course_name)  REFERENCES course(course_name),
CONSTRAINT FK_stateBook FOREIGN KEY (book_state_id)  REFERENCES state(state_id),
CONSTRAINT FK_reviewBook FOREIGN KEY (review_grade)  REFERENCES review(review_grade)
);

INSERT INTO book
VALUES (1,"Vatican Productions","ISTE330","Intro to prayers with pictures",345789324,1994,"Pray with us!",1,"PDF",0,3),(2,"Croatia writings","ISTE252","How to balance work life with personal one",0457513344,1994,"Find the balance",0,"Physical",1,5);


DROP TABLE IF EXISTS author_book;
CREATE TABLE author_book(
author_id INT UNSIGNED NOT NULL PRIMARY KEY,
book_id INT(7) UNSIGNED NOT NULL,
CONSTRAINT FK_authorBook FOREIGN KEY (author_id)  REFERENCES author(author_id),
CONSTRAINT FK_bookAuthor FOREIGN KEY (book_id)  REFERENCES book(book_id)
);

INSERT INTO author_book
VALUES (1,1),(2,2);


DROP TABLE IF EXISTS favorites;
CREATE TABLE favorites(
favorites_id INT UNSIGNED NOT NULL PRIMARY KEY,
book_id INT(7) UNSIGNED NOT NULL,
user_id VARCHAR(20) NOT NULL,
CONSTRAINT FK_favoritesBook FOREIGN KEY (user_id)  REFERENCES User(user_id),
CONSTRAINT FK_favoritesAuthor FOREIGN KEY (book_id)  REFERENCES book(book_id)
);

INSERT INTO favorites
VALUES (1,1,"Borninjo0001"),(2,2,"Krstonjo0002");


DROP TABLE IF EXISTS book_on_loan;
CREATE TABLE book_on_loan(
user_id VARCHAR(20) PRIMARY KEY NOT NULL,
book_id INT(7) UNSIGNED NOT NULL,
book_returned BOOLEAN DEFAULT FALSE NOT NULL,
due_date DATE NOT NULL,
payment_id INT UNSIGNED NOT NULL,
CONSTRAINT FK_userLoan FOREIGN KEY (user_id) REFERENCES User(user_id),
CONSTRAINT FK_bookLoan FOREIGN KEY (book_id) REFERENCES Book(book_id),
CONSTRAINT FK_paymentLoan FOREIGN KEY (payment_id) REFERENCES payment(payment_id)
);

INSERT INTO book_on_loan
VALUES ("Borninjo0001",1,true,'2022-10-11' ,1),("Krstonjo0002",2,false,'2022-11-11',2);

