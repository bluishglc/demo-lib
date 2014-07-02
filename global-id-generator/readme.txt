1. MyIsam is faster than InnoDB!
2. Plain JDBC is faster than Spring JDBC!
2. The sql REPLACE INTO sequence (stub) VALUES ('a') & SELECT LAST_INSERT_ID() have to execute with one connection, otherwise the returned id will be 0!!
3. For MyIsam, no transaction, however, the r/w for tables is sequential.
4. The next() method does not need synchronized key word!