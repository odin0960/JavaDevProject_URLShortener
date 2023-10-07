create TABLE IF NOT EXISTS users (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR (200) NOT NULL UNIQUE CHECK (CHAR_LENGTH(username) >= 3),
	password VARCHAR (2000) NOT NULL CHECK (CHAR_LENGTH(password) >= 8),
	role VARCHAR (5) NOT NULL DEFAULT 'USER'
);

create TABLE IF NOT EXISTS links (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	long_link VARCHAR (2000) NOT NULL,
	token VARCHAR (8) NOT NULL UNIQUE,
	create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expire_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
	count BIGINT,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (username, password, role)
VALUES
	('admin',
	'$2a$12$..wl30OG0nSU.2zLp7mxaeFxbuVySaIgdvpfQinWunXHGIRMWbZd2', --admin
	 'ADMIN'
	);






