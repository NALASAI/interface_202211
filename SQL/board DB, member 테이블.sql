create database board;
use board;

CREATE TABLE MEMBER(
	email VARCHAR(50) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    profile VARCHAR(255),
    tel_number VARCHAR(20) NOT NULL,
    address TEXT NOT NULL
)