# SWE266-Bank
# Introduction
This is the system project for build phase assignment for MSWE 266P.

Group 7

Group Member: Xin Tan, Shuaihua Niu, Jiahan Tu, Chao Liu.

# Project Architecture
Front end:
1. HTML

Back End:
1. Spring Boot

Database:
1. MySql Database

# Build/Test Instruction
## Set up database
1. Start your MySql server
<img width="668" alt="image" src="https://user-images.githubusercontent.com/51472016/167574737-4247cf53-f4aa-42ee-8c3c-7bbb2eb50c7b.png">
2. Create your local MySQL database use any name you want.
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/51472016/167574804-c1e9452b-32a0-42e1-9273-1287db007b85.png">

3. Use MySQLWorkbench to create your tables for your database. Use the following SQL command: 
~~~~sql 
drop table if exists user;
create table user(
 `id` int not null auto_increment PRIMARY KEY,
 `username` VARCHAR(45) null default null,
 `password` VARCHAR(45) null default null,
 `deposit` double(16,2)
) 
~~~~

## Build and test the web app
1. Unzip the package into any directory you like.
2. Use intelliJ to open the Maven project and make sure you use Java 11 or above.
3. Go to the file: ```src/main/resources/application.properties```
4. configure your datasource.url to your local database url.
5. configure your username and password.
6. Make sure the port of 8080 is not occupied by other processes. If it is occupied by other process, please kill them first.
8. Go to the file ```src/main/java/com/swe266/bank/BankApplication.java```
9. Run the main function.
10. Turn on your browser and go to ```localhost:8080```
