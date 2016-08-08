
INSERT INTO city(id, name) VALUES(1, 'Bangalore');

insert into cabtype (id,type,description,model) values (1,'Indica','Indica 4 seater','Low');  
insert into cabtype (id,type,description,model) values (2,'Swift ','Swift 4 seater','Low');
insert into cabtype (id,type,description,model) values (3,'Ronault ','Ronault 4 seater','Medium');
insert into cabtype (id,type,description,model) values (4,'Ford ','Ford 4 seater','High');

insert into LOCATION(id,name,latitude,longitude) values (1,'Jayanagar',100,101);
insert into LOCATION(id,name,latitude,longitude) values (2,'Majestic',200,201);
insert into LOCATION(id,name,latitude,longitude) values (3,'Ramamurthy Nagar',300,301);
insert into LOCATION(id,name,latitude,longitude) values (4,'Sarjapur Road',400,401);


insert into CUSTOMER (id,name,email_id,mobile_number,address) values (1,'Vishal','test@gmail.com','9845530047','Mahalaxmi Layout');

insert into cabdetail(ID, ACTIVE, CAB_NUMBER, COLOUR, DRIVER_NAME, EMAIL_ID, MOBILE_NUMBER, CAB_TYPE) values (1,true,'AG 09 45332','pink','rajeev','mini@gmail.com','8989898990',(select id from cabtype where type='Indica'));