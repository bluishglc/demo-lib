DROP DATABASE IF EXISTS id_generator_myisam;

CREATE DATABASE id_generator_myisam;
USE id_generator_myisam;

DROP TABLE IF EXISTS sequence;

CREATE TABLE `sequence` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `stub` char(1) NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `stub` (`stub`)
) ENGINE=MyISAM;


DROP DATABASE IF EXISTS id_generator_innodb;

CREATE DATABASE id_generator_innodb;
USE id_generator_innodb;

DROP TABLE IF EXISTS sequence;

CREATE TABLE `sequence` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `stub` char(1) NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `stub` (`stub`)
) ENGINE=InnoDB;