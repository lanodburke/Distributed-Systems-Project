drop database bookingdatabase;
create database bookingdatabase;
use bookingdatabase;

drop table if exists Vehicle;
create table Vehicle (
   vehicle_id int NOT NULL AUTO_INCREMENT,
   registration varchar(50),
   quality varchar(50),
   mileage int,
   primary key (vehicle_id)
);

drop table if exists Customer;
create table Customer (
    customer_id int NOT NULL AUTO_INCREMENT,
    f_name varchar(25),
    s_name varchar(25),
    address varchar(250),
    dob Date,
    primary key(customer_id)
);

drop table if exists Booking;
create table Booking (
    booking_id int NOT NULL AUTO_INCREMENT,
    customer_id int,
    vehicle_id int,
    start_date Date,
    end_date Date,
    primary key(booking_id),
    foreign key(customer_id) references Customer(customer_id),
    foreign key(vehicle_id) references Vehicle(vehicle_id)
);

insert into Vehicle values(NULL, '07-MN-1628', 'Excellent', 160000);
insert into Vehicle values(NULL, '09-D-5555', 'Poor', 190000);
insert into Vehicle values(NULL, '12-G-6666', 'Good', 200000);
insert into Vehicle values(NULL, '01-G-6776', 'Very Poor', 350000);

insert into Customer values(NULL, 'John', 'Smith', 'Co. Galway', '1998-01-22');
insert into Customer values(NULL, 'Bob', 'Small', 'Killcolgan, Co. Galway', '1995-01-01');
insert into Customer values(NULL, 'Tom', 'Smith', 'Ballybane, Co. Galway', '1998-01-01');
insert into Customer values(NULL, 'Sean', 'Smith', 'Claregalway, Co. Galway', '1997-10-01');

insert into Booking values(NULL, 1, 1, '2018-11-12', '2018-12-10');
insert into Booking values(NULL, 2, 2, '2018-11-12', '2018-12-10');
insert into Booking values(NULL, 3, 3, '2018-11-12', '2018-12-10');
insert into Booking values(NULL, 4, 4, '2018-11-12', '2018-12-10');