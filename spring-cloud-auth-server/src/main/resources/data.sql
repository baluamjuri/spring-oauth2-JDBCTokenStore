insert into abv_user(username, password, salt) values('john', 'john', 'john');

insert into abv_role(role_name, is_active) values('ADMIN',true);
insert into abv_role(role_name, is_active) values('MANAGER',true);
insert into abv_role(role_name, is_active) values('TEAM_LEAD',true);
insert into abv_role(role_name, is_active) values('TEAM_MEMBER',true);

insert into abv_user_role(user_id, role_id) values(1, 1);

insert into 
`oauth_client_details`(
	`client_id`,
	`resource_ids`,
	`client_secret`,
	`scope`,
	`authorized_grant_types`,
	`web_server_redirect_uri`,
	`authorities`,
	`access_token_validity`,
	`refresh_token_validity`,
	`additional_information`,
	`autoapprove`) 
values ( 
	'springdeveloper123',
	NULL,
	'test',
	'read, write'
	,'password, refresh_token'
	,NULL
	,NULL
	,'300'
	,'3600'
	,NULL,
	NULL);
