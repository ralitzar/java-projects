create table action(
	id bigint not null auto_increment,
    vehicle_id bigint not null,
    description text not null,
    fine_value decimal(10,2) not null,
    incident_date datetime not null,

    primary key (id)
    );

    alter table action add constraint fk_action_vehicle
    foreign key (vehicle_id) references vehicle (id);