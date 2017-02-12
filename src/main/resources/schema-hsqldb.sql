--TODO: update schema and add audit support

create table tm_user (
	login_name     varchar(128),
	email_address  varchar(128),
	state          varchar(32),
	created_at     timestamp not null default current_date(),
	created_by     varchar(128)
);

create sequence  user_id_seq start with 1 increment by 1;

alter table tm_user alter column id set default nextval('user_id_seq');