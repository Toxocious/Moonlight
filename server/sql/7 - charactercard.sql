CREATE TABLE `charactercard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `accountid` int DEFAULT NULL,
  `characterid` int DEFAULT NULL,
  `position` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1