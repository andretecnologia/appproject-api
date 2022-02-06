
CREATE TABLE IF NOT EXISTS refreshtoken  (
	id         	int8 NOT NULL,
	expiry_date	timestamp NOT NULL,
	token      	varchar(255) NOT NULL,
	user_id    	int8 NULL,
	PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS roles  (
	id  	serial NOT NULL,
	name	varchar(20) NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS  users  (
	id      	bigserial NOT NULL,
	email   	varchar(50) NULL,
	password	varchar(120) NULL,
	username	varchar(20) NULL,
	PRIMARY KEY(id)
);

ALTER TABLE users
	ADD CONSTRAINT users_username
	UNIQUE (username);

ALTER TABLE users
	ADD CONSTRAINT users_email
	UNIQUE (email);



CREATE TABLE IF NOT EXISTS user_roles  (
	user_id	int8 NOT NULL,
	role_id	int4 NOT NULL,
	PRIMARY KEY(user_id,role_id)
);

ALTER TABLE user_roles
	ADD CONSTRAINT user_roles_user_id
	FOREIGN KEY(user_id)
	REFERENCES users(id);

ALTER TABLE user_roles
	ADD CONSTRAINT user_roles_role_id
	FOREIGN KEY(role_id)
	REFERENCES roles(id);

ALTER TABLE refreshtoken
    ADD CONSTRAINT refreshtoken_token
    UNIQUE (token);

ALTER TABLE refreshtoken
    ADD CONSTRAINT refreshtoken_user
    FOREIGN KEY(user_id)
    REFERENCES users(id);

INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_MODERATOR');