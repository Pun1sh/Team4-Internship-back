insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'ADDRESS_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ADDRESS_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ADDRESS_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ADDRESS_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'ADDRESS_READ')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
         (select au_id from authority where au_name= 'ADDRESS_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'ADDRESS_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'ADDRESS_DELETE')
             );
