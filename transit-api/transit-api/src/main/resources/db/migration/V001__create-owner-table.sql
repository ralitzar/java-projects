create table owner (
  id BIGINT not null auto_increment,
  name VARCHAR (60) not null,
  email VARCHAR (255) not null,
  phone VARCHAR(20) not null,

  primary key (id)
);

alter table owner
add constraint uk_owner unique (email);