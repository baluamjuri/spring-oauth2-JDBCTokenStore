drop table if exists abv_user_role ;
drop table if exists abv_user ;
drop table if exists abv_role;

create table abv_user (
	user_id integer  AUTO_INCREMENT,
    username varchar(255) not null,
    password varchar(255) not null,
    salt varchar(255),
    primary key (user_id)
);

create table abv_role (
       role_id integer AUTO_INCREMENT,
        is_active boolean,
        description varchar(255),
        role_name varchar(255) not null,
        primary key (role_id)
);	
  
create table abv_user_role (
    user_id integer not null REFERENCES abv_user(user_id),
    role_id integer not null REFERENCES abv_role(role_id)	
);

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

-- drop table if exists oauth_client_token;
-- create table oauth_client_token (
  -- token_id VARCHAR(256),
  -- token LONG VARBINARY,
  -- authentication_id VARCHAR(256) PRIMARY KEY,
  -- user_name VARCHAR(256),
  -- client_id VARCHAR(256)
-- );
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
 
-- drop table if exists oauth_code;
-- create table oauth_code (
  -- code VARCHAR(256), authentication LONG VARBINARY
-- );
 
-- drop table if exists oauth_approvals;
-- create table oauth_approvals (
	-- userId VARCHAR(256),
	-- clientId VARCHAR(256),
	-- scope VARCHAR(256),
	-- status VARCHAR(10),
	-- expiresAt TIMESTAMP,
	-- lastModifiedAt TIMESTAMP
-- );
 