CREATE TABLE health_profiles (
                                 id SERIAL PRIMARY KEY,
                                 user_id INTEGER NOT NULL REFERENCES users(id),
                                 health_conditions VARCHAR(255),
                                 medications VARCHAR(255),
                                 fitness_activity VARCHAR(255),
                                 dietary_preferences VARCHAR(255),
                                 weight INTEGER,
                                 height INTEGER,
                                 research_participation_consent BOOLEAN
);
