INSERT INTO venue (name, street_address, city, state, zip) VALUES ('The Met Philadelphia', '858 N Broad St', 'Philadelphia', 'PA', '19130');
INSERT INTO venue (name, street_address, city, state, zip) VALUES ('Union Transfer', '1026 Spring Garden St', 'Philadelphia', 'PA', '19123');
INSERT INTO venue (name, street_address, city, state, zip) VALUES ('The Fillmore', '29 E Allen St', 'Philadelphia', 'PA', '19123');
INSERT INTO venue (name, street_address, city, state, zip) VALUES ('9:30 Club', '815 V St NW', 'Washington', 'D.C.', '20001');
INSERT INTO venue (name, street_address, city, state, zip) VALUES ('College Street Music Hall', '238 College St', 'New Haven', 'CT', '06510');

INSERT INTO artist (name, genre) VALUES ('The National', 'alternative rock');
INSERT INTO artist (name, genre) VALUES ('Spoon', 'alternative rock');
INSERT INTO artist (name, genre) VALUES ('Pink', 'pop');

INSERT INTO concert (name, date, venue_id) VALUES ("The National's Venn Diagram Tour", "08/03/2023", 1);
INSERT INTO concert (name, date, venue_id) VALUES ("Spoon New Year's Eve", "12/31/2017", 4);
INSERT INTO concert (name, date, venue_id) VALUES ("Orville Peck Bronco Tour", "10/12/2021", 5);