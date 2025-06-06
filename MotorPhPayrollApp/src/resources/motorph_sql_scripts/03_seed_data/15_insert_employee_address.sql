-- Inserts cleaned address data into employee_address
START TRANSACTION;
-- disable FKs to allow truncation
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE employee_address;

-- re-enable FKs
SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO employee_address
  (employee_id, house_number, street, barangay, municipality, province, postal_code, country, address_type)
VALUES
  (10001,      NULL,		'Valero Carpark Building Valero Street', 					NULL,      	'Makati',  		NULL,   				'1227', 'Philippines', 1),
  (10002,      NULL,		'San Antonio De Padua 2, Block 1 Lot 8 and 2',    			NULL,      	'Dasmarinas',	'Cavite',				NULL,   'Philippines', 1),
  (10003,      '402',		'4th Floor Jiao Building Timog Avenue Cor. Quezon Avenue',	NULL,		'Quezon',  		NULL,   				'1100', 'Philippines', 1),
  (10004,      '460',		'Solanda Street',                               			NULL,      	'Intramuros',	'Manila',				'1000', 'Philippines', 1),
  (10005,      NULL,		'National Highway',                             			NULL,      	'Gingoog',  	'Misamis Occidental',	NULL,	'Philippines', 1),
  (10006,      '17/85',		'Stracke Via Suite 042',                        			'Poblacion','Las Pinas',	'Dinagat Islands',		'4783',	'Philippines', 1),
  (10007,      '99',		'Strosin Hills',                                			'Poblacion','Bislig',   	'Tawi-Tawi',			'5340',	'Philippines', 1),
  (10008,      '12A/33',	'Upton Isle Apt. 420',                          			NULL,      	'Roxas City',	'Surigao del Norte',	'1814',	'Philippines', 1),
  (10009,      '90A',		'Dibbert Terrace Apt. 190',                     			NULL,      	'San Lorenzo',	'Davao del Norte',		'6056',	'Philippines', 1),
  (10010,      '284',		'T. Morato corner, Scout Rallos Street',        			NULL,      	'Quezon',   	NULL,    				NULL,   'Philippines', 1),
  (10011,      '93/54',		'Shanahan Alley Apt. 183',                      			NULL,      	'Santo Tomas',	'Masbate',				'1572',	'Philippines', 1),
  (10012,      '49',		'Springs Apt. 266',                             			'Poblacion','Taguig',  		'Occidental Mindoro',	'3200',	'Philippines', 1),
  (10013,      '42/25',		'Sawayn Stream',                                			NULL,      	'Ubay',     	'Zamboanga del Norte',	'1208',	'Philippines', 1),
  (10014,      '37/46',		'Kulas Roads',                                  			NULL,      	'Maragondon',	'Quirino',				'0962',	'Philippines', 1),
  (10015,      '22A/52',	'Lubowitz Meadows',                             			NULL,      	'Pililla',  	'Zambales',				'4895',	'Philippines', 1),
  (10016,      '90',		'O\'Keefe Spur Apt. 379',                       			NULL,      	'Catigbian',	'Sulu',					'2772',	'Philippines', 1),
  (10017,      '89A',		'Armstrong Trace',                              			NULL,      	'Compostela',	'Maguindanao',			'7874',	'Philippines', 1),
  (10018,      '8',			'Grant Drive Suite 406',                        			'Poblacion','Iloilo City',	'La Union',				'9186',	'Philippines', 1),
  (10019,      '93A/21',	'Berge Points',                                 			NULL,      	'Tapaz',    	'Quezon',				'2180',	'Philippines', 1),
  (10020,      '65',		'Murphy Center Suite 094',                      			'Poblacion','Palayan', 		'Quirino',				'5636',	'Philippines', 1),
  (10021,      '47A/94',	'Larkin Plaza Apt. 179',                        			'Poblacion','Caloocan',		'Quirino',				'2751',	'Philippines', 1),
  (10022,      '06A',		'Gulgowski Extensions',                         			NULL,      	'Bongabon', 	'Zamboanga del Sur',	'6085',	'Philippines', 1),
  (10023,      '99A',		'Padberg Spring',                               			'Poblacion','Mabalacat',	'Lanao del Sur',		'3959',	'Philippines', 1),
  (10024,      '80A/48',	'Ledner Ridges',                                			'Poblacion','Kabankalan',	'Marinduque',			'8870',	'Philippines', 1),
  (10025,      '96/48',		'Watsica Flats Suite 734',                      			'Poblacion','Malolos',		'Ifugao',				'1844',	'Philippines', 1),
  (10026,      '58A',		'Wilderman Walks',                              			'Poblacion','Digos',   		'Davao del Sur',		'5822',	'Philippines', 1),
  (10027,      '60',		'Goyette Valley Suite 219',                     			'Poblacion','Tabuk',   		'Lanao del Sur',		'3159',	'Philippines', 1),
  (10028,      '66/77',		'Mann Views',                                   			NULL,      	'Luisiana', 	'Dinagat Islands',		'1263',	'Philippines', 1),
  (10029,      '72/70',		'Stamm Spurs',                                  			NULL,      	'Bustos',   	'Iloilo',				'4550',	'Philippines', 1),
  (10030,      '50A/83',	'Bahringer Oval Suite 145',                     			NULL,      	'Kiamba',   	'Nueva Ecija',			'7688',	'Philippines', 1),
  (10031,      '95', 		'Cremin Junction',                              			NULL,      	'Surallah',		'Cotabato',				'2809',	'Philippines', 1),
  (10032,      NULL,		'Hi-way',                                       			'Yati',     'Liloan',   	'Cebu',					'6002',	'Philippines', 1),
  (10033,      NULL,		'Bulala',                                       			NULL,      	'Camalaniugan',	'Cagayan',				'3510',	'Philippines', 1),
  (10034,      NULL,		'Agapita Building',                             			NULL,      	NULL,       	'Manila', 				NULL,   'Philippines', 1);

COMMIT;
