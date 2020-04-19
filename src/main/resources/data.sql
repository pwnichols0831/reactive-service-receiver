DROP TABLE IF EXISTS recovery_db;
 
CREATE TABLE recovery_db (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  datetime timestamp NOT NULL,
  transactionid varchar(255) NOT NULL,
  source varchar(255) NOT NULL,
  data text NOT NULL
);
 