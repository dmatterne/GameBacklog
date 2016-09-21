INSERT INTO GAME(ID,DESCRIPTION,GENRE,PLATFORM,TITLE,PROGRESS) VALUES (-1,'Save the always getting caught Princess Peach','Platform','DS','Super Mario','Owned');
INSERT INTO GAME(ID,DESCRIPTION,GENRE,PLATFORM,TITLE,PROGRESS) VALUES (-2,'You are link and will be known in this famous story of Zelda.. wait WHAT?','RPG','DS','Legend of Zelda', 'Installed');
INSERT INTO GAME(ID,DESCRIPTION,GENRE,PLATFORM,TITLE,PROGRESS) VALUES (-3,'Eat your heart out but make sure the chef does not catch you','Platform','DS','Packman', 'Completed');
INSERT INTO GAME(ID,DESCRIPTION,GENRE,PLATFORM,TITLE,PROGRESS) VALUES (-4,'Kill other players in the most obscene way','Fighting','PLAYSTATION_4','Mortal Combat','Busy');

INSERT INTO GAMELIST (ID,NAME,DESCRIPTION,CREATION_DATE) VALUES (-1,'Favorites','List of Favorite Games',SYSDATE);

INSERT INTO LIST_GAMES(LIST_ID,GAME_ID) VALUES (-1,-1);

INSERT INTO GAME_DEVELOPER (ID,NAME,LOGO,HOME_PAGE) VALUES ('-1', 'SQUARE ENIX', 'a', 'www.square-enix.com');