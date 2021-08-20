INSERT INTO AUTHORITY (id ,name) VALUES (1, 'ROLE_PATIENT');
INSERT INTO AUTHORITY (id ,name) VALUES (2, 'ROLE_PHARMACIST');
INSERT INTO AUTHORITY (id ,name) VALUES (3, 'ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (id ,name) VALUES (4, 'ROLE_PHARMACY_ADMINISTRATOR');
INSERT INTO AUTHORITY (id ,name) VALUES (5, 'ROLE_SYSTEM_ADMINISTRATOR');
INSERT INTO AUTHORITY (id ,name) VALUES (6, 'ROLE_SUPPLIER');
INSERT INTO AUTHORITY (id ,name) VALUES (7, 'ROLE_UNAUTHORISED');

INSERT INTO COUNTRY (id, name) VALUES (1100, 'Srbija');
INSERT INTO COUNTRY (id, name) VALUES (1101, 'Slovenija');

INSERT INTO CITY (id, name, country_id) VALUES (200, 'Novi Sad', 1100);
INSERT INTO CITY (id, name, country_id) VALUES (201, 'Beograd', 1100);
INSERT INTO CITY (id, name, country_id) VALUES (202, 'Ljubljana', 1101);

INSERT INTO ADDRESS (id, street_name, street_number, city_id) VALUES (300, 'Kisacka', 55,201);
INSERT INTO ADDRESS (id, street_name, street_number, city_id) VALUES (301, 'Kisacka', 77,200);
INSERT INTO ADDRESS (id, street_name, street_number, city_id) VALUES (302, 'Brace Ribnikara', 88,200);
INSERT INTO ADDRESS (id, street_name, street_number, city_id) VALUES (303, 'Sekspirova', 99,200);
INSERT INTO ADDRESS (id, street_name, street_number, city_id) VALUES (304, 'Dalmatinska', 65,201);

INSERT INTO PHARMACY_INVENTORY(id) VALUES (8);
INSERT INTO PHARMACY_INVENTORY(id) VALUES (9);

INSERT INTO PHARMACY(id, all_grades, description, name, number_of_grades, address_id, pharmacy_inventory_id) VALUES (50, 10,'First Pharmacy', 'Tilia', 10, 300, 8);
INSERT INTO PHARMACY(id, all_grades, description, name, number_of_grades, address_id, pharmacy_inventory_id) VALUES (51, 8,'Second Pharmacy', 'Zegin', 10, 303, 9);

INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id) VALUES('Dermatologist', 100,'0655552525', 'mila@gmail.com', true, true, 'Ivanovic', 'Mila', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id, main_admin) VALUES('SystemAdministrator', 101,'0655552525', 'pera@pera.com', true, true, 'Peric', 'Pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300, true);

INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id,pharmacy_id) VALUES('Pharmacist', 102,'0655552525', 'ivan@gmail.com', true, true, 'Mikic', 'Ivan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300,50);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id, penalty) VALUES('Patient', 113,'06201023325', 'kristinadjuric65@gmail.com', true, true, 'Saric', 'Sara', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 302,0);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id, penalty) VALUES('Patient', 114,'06256306363', 'miki@miki', true, true, 'Mikic', 'Miki', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 303, 0);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id, penalty) VALUES('Patient', 115,'06125478958', 'tara@tara.com', true, true, 'Anic', 'Tara', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 304, 0);

INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id) VALUES('Supplier', 103,'0657552525', 'mina@mina.com', true, true, 'Maric', 'Mina', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id,pharmacy_id) VALUES('PharmacyAdministrator', 104,'0657552525', 'marko@marko.com', true, true, 'Maric', 'Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300,50);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id) VALUES('Supplier', 105,'0657552525', 'ana@ana.com', true, true, 'Maric', 'Ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300);
INSERT INTO PERSON(dtype, id, contact, email, enabled, first_logged, last_name, name, password, address_id, penalty) VALUES('Patient', 106,'06125478958', 'maja18isa@gmail.com', true, true, 'Anic', 'Ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 300,0);


INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (100, 3);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (101, 5);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (102, 2);

INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (113, 1);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (114, 1);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (115, 1);

-- Dermatologist task 3.14
INSERT INTO PERIOD(id, end_date, end_time, start_date, start_time) VALUES (40, '2021-07-10', '15:00', '2021-07-10', '07:00' );
INSERT INTO PERIOD(id, end_date, end_time, start_date, start_time) VALUES (41, '2021-07-11', '15:00', '2021-07-11', '07:00' );
INSERT INTO PERIOD(id, end_date, end_time, start_date, start_time) VALUES (42, '2021-07-12', '20:00', '2021-07-12', '13:00' );
INSERT INTO PERIOD(id, end_date, end_time, start_date, start_time) VALUES (43, '2021-07-13', '15:00', '2021-07-13', '07:00' );
INSERT INTO PERIOD(id, end_date, end_time, start_date, start_time) VALUES (44, '2021-07-14', '20:00', '2021-07-14', '13:00' );
INSERT INTO PERIOD(id, end_date, end_time, start_date, start_time) VALUES (45, '2021-07-15', '20:00', '2021-07-15', '13:00' );

INSERT INTO BUSINESS_DAY_DERMATOLOGIST(id, pharmacy_id, dermatologist_id, shift_id) VALUES (400, 50, 100, 40);
INSERT INTO BUSINESS_DAY_DERMATOLOGIST(id, pharmacy_id, dermatologist_id, shift_id) VALUES (401, 50, 100, 41);
INSERT INTO BUSINESS_DAY_DERMATOLOGIST(id, pharmacy_id, dermatologist_id, shift_id) VALUES (402, 50, 100, 42);
INSERT INTO BUSINESS_DAY_DERMATOLOGIST(id, pharmacy_id, dermatologist_id, shift_id) VALUES (403, 50, 100, 43);
INSERT INTO BUSINESS_DAY_DERMATOLOGIST(id, pharmacy_id, dermatologist_id, shift_id) VALUES (404, 51, 100, 44);
INSERT INTO BUSINESS_DAY_DERMATOLOGIST(id, pharmacy_id, dermatologist_id, shift_id) VALUES (405, 50, 100, 45);

INSERT INTO BUSINESS_DAY_PHARMACIST(id, pharmacist_id, shift_id) VALUES (406, 102, 44);
INSERT INTO BUSINESS_DAY_PHARMACIST(id, pharmacist_id, shift_id) VALUES (407, 102, 45);

INSERT INTO PHARMACY_DERMATOLOGISTS(dermatologist_id, pharmacy_id) VALUES (100, 50);
INSERT INTO PHARMACY_DERMATOLOGISTS(dermatologist_id, pharmacy_id) VALUES (100, 51);

INSERT INTO EXAMINATION(id, diagnosis, duration, examination_price, examination_status, start_date, start_time, end_time, dermatologist_id, patient_id, pharmacy_id) VALUES (500, '', 30, 5000, 0, '2021-08-18', '08:30', '09:00', 100, 113, 50);
INSERT INTO EXAMINATION(id, diagnosis, duration, examination_price, examination_status, start_date, start_time, end_time, dermatologist_id, pharmacy_id) VALUES (501, '', 30, 3000, 0, '2021-09-24', '10:30', '11:00', 100, 50);
INSERT INTO EXAMINATION(id, diagnosis, duration, examination_price, examination_status, start_date, start_time, end_time, dermatologist_id, patient_id, pharmacy_id) VALUES (502, '', 30, 4000, 0, '2021-09-21', '16:30', '17:00', 100, 115, 51);

INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (103, 6);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (104, 4);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (105, 6);
INSERT INTO USER_AUTHORITY(user_id, authority_id) VALUES (106, 1);


INSERT INTO PURCHASE_ORDER (id,due_date,purchase_order_status,pharmacy_administrator_id)
VALUES (411,'2021-07-12', 1,104);
INSERT INTO PURCHASE_ORDER (id,due_date,purchase_order_status,pharmacy_administrator_id)
VALUES (412,'2021-07-11', 0,104);
INSERT INTO PURCHASE_ORDER (id,due_date,purchase_order_status,pharmacy_administrator_id)
VALUES (413,'2021-07-10', 0,104);

INSERT INTO DRUG_SPECIFICATION (id,compositions, contraindications,therapy,therapy_duration)
VALUES (711,'celuloza, laktoza','alergy','2 times a day',5);
INSERT INTO DRUG_SPECIFICATION (id,compositions, contraindications,therapy,therapy_duration)
VALUES (712,'antihistaminic','tiredness','1 times a day', 7);
INSERT INTO DRUG_SPECIFICATION (id,compositions, contraindications,therapy,therapy_duration)
VALUES (713,'antihistaminic','sleepiness','2 times a day', 7);
INSERT INTO DRUG_SPECIFICATION (id,compositions, contraindications,therapy,therapy_duration)
VALUES (714,'antihistaminic','sleepiness','3 times a day', 14);
INSERT INTO DRUG_SPECIFICATION (id,compositions, contraindications,therapy,therapy_duration)
VALUES (715,'acetilsalicilna kiselina','alergy','1 times a day',5);

INSERT INTO DRUG (id,code,drug_form,drug_type,issuance,name,number_of_grades,producer,drug_specification_id)
VALUES (700,'M65',0,1,1,'brufen',8,'zegin',711);
INSERT INTO DRUG (id,code,drug_form,drug_type,issuance,name,number_of_grades,producer,drug_specification_id)
VALUES (702,'M60',0,0,0,'brufen',10,'tilia',711);
INSERT INTO DRUG (id,code,drug_form,drug_type,issuance,name,number_of_grades,producer,drug_specification_id)
VALUES (701,'M35',0,1,1,'aspirin',9,'galenika',715);
INSERT INTO DRUG (id,code,drug_form,drug_type,issuance,name,number_of_grades,producer,drug_specification_id)
VALUES (703,'M50',2,2,1,'xyzal',10,'sanofi',712);
INSERT INTO DRUG (id,code,drug_form,drug_type,issuance,name,number_of_grades,producer,drug_specification_id)
VALUES (704,'M55',2,2,1,'aerius',9,'plough',713);
INSERT INTO DRUG (id,code,drug_form,drug_type,issuance,name,number_of_grades,producer,drug_specification_id)
VALUES (705,'M56',3,2,1,'cetirizin',9,'plough',714);

INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (712, 704);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (712, 705);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (713, 703);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (713, 705);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (714, 704);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (714, 703);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (715, 702);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (715, 700);
INSERT INTO MEDICATIONS_ALTERNATIVES(drug_specification_id, drug_alternative_id) values (711, 701);

INSERT INTO PURCHASE_ORDER_DRUG (id,amount,drug_id)
VALUES (600,45,700);
INSERT INTO PURCHASE_ORDER_DRUG (id,amount,drug_id)
VALUES (601,65,701);
INSERT INTO PURCHASE_ORDER_DRUG (id,amount,drug_id)
VALUES (602,20,703);

INSERT INTO PURCHASE_ORDER_PURCHASE_ORDER_DRUGS(purchase_order_id,purchase_order_drugs_id)
VALUES (411,600);
INSERT INTO PURCHASE_ORDER_PURCHASE_ORDER_DRUGS(purchase_order_id,purchase_order_drugs_id)
VALUES (412,601);
INSERT INTO PURCHASE_ORDER_PURCHASE_ORDER_DRUGS(purchase_order_id,purchase_order_drugs_id)
VALUES (413,602);



INSERT INTO SUPPLIER_DRUG_STOCK (id,code,name,quantity,reserved_quantity,supplier_id)
VALUES (800,67,'brufen',150,19,103);
INSERT INTO SUPPLIER_DRUG_STOCK (id,code,name,quantity,reserved_quantity,supplier_id)
VALUES (801,68,'xyzal',130,10,103);
INSERT INTO SUPPLIER_DRUG_STOCK (id,code,name,quantity,reserved_quantity,supplier_id)
VALUES (802,69,'andol',167,12,103);
INSERT INTO SUPPLIER_DRUG_STOCK (id,code,name,quantity,reserved_quantity,supplier_id)
VALUES (803,70,'aspirin',145,10,103);

INSERT INTO PERIOD (id,start_date,end_date)
VALUES (900,'2021-06-20', '2021-07-15');

INSERT INTO PROMOTION (id,description,period_id,pharmacy_id)
VALUES (950,'snizenje lekova 20%', 900,50);
INSERT INTO PROMOTION (id,description,period_id,pharmacy_id)
VALUES (951,'snizenje lekova 30%', 900,51);

INSERT INTO EXAMINATION (id,diagnosis,duration,examination_status,examination_price,start_date,start_time,end_time,dermatologist_id,patient_id,pharmacy_id)
VALUES (850,'grip',30,2,1500,'2021-09-25','11:30','12:30',100,106,50);

INSERT INTO COUNSELING (id,counseling_status,diagnosis,start_date,start_time,end_time,patient_id,pharmacist_id, duration)
VALUES (860,2,'osip','2021-07-14','13:30','14:30',106,102, 30);
INSERT INTO COUNSELING (id,counseling_status,diagnosis,start_date,start_time,end_time,patient_id,pharmacist_id, duration)
VALUES (861,2,'osip','2021-07-15','15:30','16:00',115,102, 30);


INSERT INTO PRESCRIPTION (id,amount_of_drug,duration_of_therapy,prescription_date,prescription_status,patient_id, pharmacy)
VALUES (870,3,5,'2021-05-11',1,106,51);
INSERT INTO PRESCRIPTION (id,amount_of_drug,duration_of_therapy,prescription_date,prescription_status,patient_id, pharmacy)
VALUES (871,3,5,'2021-05-11',1,115,51);
INSERT INTO PRESCRIPTION_DRUGS (prescription_id,drug_id)
VALUES (870,700);
INSERT INTO PRESCRIPTION_DRUGS (prescription_id,drug_id)
VALUES (871,700);

INSERT INTO DRUG_INFO(id,drug_amount,price,drug_id,period_id)
VALUES(30,50,2000,700,900);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (8,30);
INSERT INTO DRUG_INFO(id,drug_amount,price,drug_id,period_id)
VALUES(31,10,2900,702,900);
INSERT INTO DRUG_INFO(id,drug_amount,price,drug_id,period_id)
VALUES(32,15,2500,702,900);
INSERT INTO DRUG_INFO(id,drug_amount,price,drug_id,period_id)
VALUES(33,25,2000,701,900);
INSERT INTO DRUG_INFO(id,drug_amount,price,drug_id,period_id)
VALUES(34,30,3000,704,900);
INSERT INTO DRUG_INFO(id,drug_amount,price,drug_id,period_id)
VALUES(35,19,4000,705,900);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (8,31);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (9,32);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (9,33);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (8,33);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (8,34);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (9,35);
INSERT INTO PHARMACY_INVENTORY_DRUG_INFOS(pharmacy_inventory_id,drug_infos_id)
VALUES (9,30);


INSERT INTO PATIENT_ALLERGIES(patient_id, drug_id) VALUES (113, 700);
INSERT INTO PATIENT_ALLERGIES(patient_id, drug_id) VALUES (114, 702);
INSERT INTO PATIENT_ALLERGIES(patient_id, drug_id) VALUES (115, 705);
INSERT INTO PATIENT_ALLERGIES(patient_id, drug_id) VALUES (106, 705);

INSERT INTO DRUG_RESERVATION(resevation_id, drug_id, due_date, pharmacy_id, status, patient_id) VALUES (1001, 700, '2021-07-11', 50, 0, 106);
INSERT INTO DRUG_RESERVATION(resevation_id, drug_id, due_date, pharmacy_id, status, patient_id) VALUES (1002, 700, '2021-07-11', 51, 0, 114);
INSERT INTO DRUG_RESERVATION(resevation_id, drug_id, due_date, pharmacy_id, status, patient_id) VALUES (1003, 700, '2021-05-11', 50, 0, 115);

INSERT INTO OFFER(id,delivery_time,offer_status,total_price,purchase_order_id,supplier_id)
VALUES (1000,'2021-07-09',1,6000,412,103);
INSERT INTO OFFER(id,delivery_time,offer_status,total_price,purchase_order_id,supplier_id)
VALUES (1001,'2021-07-08',2,4000,413,103);

INSERT INTO COMPLAINT(id,complaint_date,description,is_answered,role,dermatologist_id,patient_id,pharmacy_id)
VALUES (1005,'2021-06-03','complaint for dermatologist',false,'dermatologist',100,106,50);

