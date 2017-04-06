CREATE TABLE IF NOT EXISTS GOOD_LINE_CLI_SCHEME.Users (
user_id INT NOT NULL AUTO_INCREMENT,
user_resource_id INT NOT NULL,
user_name VARCHAR(45) NOT NULL,
user_login VARCHAR(45) NOT NULL,
user_pass_hash VARCHAR(255) NOT NULL,
user_salt VARCHAR(255) NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS GOOD_LINE_CLI_SCHEME.User_Resources (
user_resource_id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
user_resource_path VARCHAR(45) NOT NULL,
user_resource_role VARCHAR(15) NOT NULL,
PRIMARY KEY (user_resource_id),
FOREIGN KEY (user_id) REFERENCES Users(user_id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS GOOD_LINE_CLI_SCHEME.User_Seans (
user_seans_id INT NOT NULL AUTO_INCREMENT,
user_resource_id INT NOT NULL,
user_seans_date_start DATE NOT NULL,
user_seans_date_end DATE NOT NULL,
user_seans_volume INT NOT NULL,
PRIMARY KEY (user_seans_id),
FOREIGN KEY (user_resource_id) REFERENCES User_Resources(user_resource_id)
ON DELETE CASCADE
ON UPDATE CASCADE
);


