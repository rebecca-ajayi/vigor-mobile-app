CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(60) NOT NULL, -- bcrypt typically generates 60-char hashes
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       dob DATE NOT NULL,
                       gender VARCHAR(50) NOT NULL,
                       ethnicity VARCHAR(50) NOT NULL,
                       sexuality VARCHAR(50) NOT NULL,
                       country VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
