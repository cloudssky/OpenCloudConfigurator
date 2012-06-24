CREATE database OpenCloudConfigurator;

USE OpenCloudConfigurator;

RENAME TABLE cloudservice.registration to clouduserdata;

ALTER TABLE clouduserdata CHANGE cloudvalue cpusize int not null;
ALTER TABLE clouduserdata ADD ramsize VARCHAR(8) not null AFTER cpusize;
ALTER TABLE clouduserdata ADD hddsize VARCHAR(8) not null AFTER ramsize;
ALTER TABLE clouduserdata ADD pricesize VARCHAR(10) not null AFTER hddsize;
ALTER TABLE clouduserdata ADD cloudlocation VARCHAR(16) not null AFTER pricesize;
ALTER TABLE clouduserdata ADD cloudapps VARCHAR(16) not null AFTER cloudlocation;