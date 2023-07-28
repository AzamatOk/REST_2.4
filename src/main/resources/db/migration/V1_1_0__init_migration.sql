CREATE TABLE File(
	id int primary key not null auto_increment,
    name varchar(50),
    filePath varchar(200)
);
CREATE TABLE User(
	id int primary key not null auto_increment,
    name varchar(100)
);
CREATE TABLE Event(
	id int primary key not null auto_increment,
    userId int,
    fileId int,
    FOREIGN KEY (userId)  REFERENCES User (id),
    FOREIGN KEY (fileId)  REFERENCES File (id)
);