CREATE TABLE user_site (
                       id VARCHAR(50) PRIMARY KEY,
                       first_name VARCHAR(200) NOT NULL,
                       last_name VARCHAR(200) NOT NULL,
                       email VARCHAR(200) NOT NULL UNIQUE,
                       username VARCHAR(200) NOT NULL UNIQUE,
                       password VARCHAR(200) NOT NULL,
                       phone VARCHAR(200) NOT NULL,
                       role VARCHAR(200) NOT NULL,
                       create_at_utc Date,
                       update_at_utc Date

)

