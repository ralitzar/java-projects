create table vehicle(
	id bigint not null auto_increment,
    owner_id bigint not null,
    make varchar(20) not null,
    model varchar(20) not null,
    license_number varchar(10) not null,
    state varchar (20) not null,
    register_date datetime not null,
    capture_date datetime,

    primary key (id)
);

alter table vehicle add constraint fk_vehicle_owner foreign key (owner_id) references owner(id);
alter table vehicle add constraint uk_vehicle unique (license_number);