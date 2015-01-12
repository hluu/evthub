# --- !Ups
CREATE TABLE EVENTS (ID INT NOT NULL AUTO_INCREMENT, 
                      NAME VARCHAR(256) NOT NULL,
                      DESCRIPTION TEXT NOT NULL,
                      START_DATE TIMESTAMP NOT NULL,
                      END_DATE TIMESTAMP NOT NULL,
                      CATEGORY VARCHAR(30) NOT NULL,
                      SUBMITTER VARCHAR(100) NOT NULL,
                      TAGS VARCHAR(256) NOT NULL,
                      PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index EVENT_NAME_INDEX on EVENTS (NAME);

# --- !Downs
drop table EVENTS;