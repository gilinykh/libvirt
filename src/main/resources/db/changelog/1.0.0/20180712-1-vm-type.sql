--liquibase 20180712-1-vm-type sql

--changeset gilinykh:1
create table vm_type (
  id varchar(100) constraint pk_vm_type_id primary key,
  name varchar(100) not null unique,
  server_name varchar(100) not null,
  cpu_type varchar(100) not null,
  cpu_count integer not null,
  ram integer not null,
  ssd integer not null,
  gpu varchar(1000) not null
);

create table vm_type_inventory (
  type_id varchar(100) primary key references vm_type(id),
  stocked integer not null,
  reserved integer not null,
  allocated integer not null
);

create table vm_type_price (
  type_id varchar(100) primary key references vm_type(id),
  price_hour decimal(10, 2) not null,
  price_day decimal(10, 2) not null
);