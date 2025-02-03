insert into town(id, postcode, name) VALUES (uuid('fb74d086-5a4a-11e7-907b-a6006ad3dba0'), '34000', 'Montpellier');

insert into address(id, number, street, town_id) VALUES (uuid('fe6ae8fc-d4f9-4448-a9e1-6a5de4284b92'), 1, 'rue de la bière', uuid('fb74d086-5a4a-11e7-907b-a6006ad3dba0'));
insert into address(id, number, street, town_id) VALUES (uuid('77643c27-d221-4f34-b11c-3732405cd83a'), 2, 'rue de la bière', uuid('fb74d086-5a4a-11e7-907b-a6006ad3dba0'));