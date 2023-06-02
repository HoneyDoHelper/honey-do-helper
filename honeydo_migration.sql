USE honeydo_helper_db;
CREATE TABLE users (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   first_name CHAR(20) NOT NULL,
   last_name  CHAR(20) NOT NULL,
   email CHAR(25) NOT NULL,
   password CHAR(25) NOT NULL,
   is_admin BOOL NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (email)
);
CREATE TABLE user_details (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  address VARCHAR(50) NOT NULL,
  address2  VARCHAR(20),
  city VARCHAR(30) NOT NULL,
  state VARCHAR(2) DEFAULT 'TX',
  zipcode VARCHAR(5) NOT NULL,
  phone VARCHAR(10) NOT NULL,
  img_file_path VARCHAR(255) NOT NULL,
  user_id INT UNSIGNED DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE honeydoers (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    rating FLOAT NOT NULL,
    about_self VARCHAR(255),
    user_id INT UNSIGNED DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE honeydoers_images (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   img_file_path VARCHAR(125),
   honeydoer_id INT UNSIGNED DEFAULT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (honeydoer_id) REFERENCES honeydoers (id)
);
CREATE TABLE categories (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    description VARCHAR(255) NOT NULL,
    img_file_path VARCHAR(125) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE services (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  description VARCHAR(255) NOT NULL,
  img_file_path VARCHAR(125) NOT NULL,
  category_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories (id)
);
CREATE TABLE honeydoers_services (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     rate FLOAT NOT NULL,
     about_service VARCHAR(255) NOT NULL,
     service_id INT UNSIGNED NOT NULL,
     honeydoer_id INT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (service_id) REFERENCES services (id),
     FOREIGN KEY (honeydoer_id) REFERENCES honeydoers (id)
);
CREATE TABLE tasks (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   task_details VARCHAR(255) NOT NULL,
   date_assigned DATE NOT NULL,
   date_completed DATE,
   status VARCHAR(15),
   is_accepted BOOL NOT NULL,
   user_id INT UNSIGNED NOT NULL,
   honeydoers_services_id INT UNSIGNED NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (user_id) REFERENCES users (id),
   FOREIGN KEY (honeydoers_services_id) REFERENCES honeydoers_services (id)
);
CREATE TABLE honeydoers_services (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     honeydoers_pay FLOAT NOT NULL,
     site_pay FLOAT NOT NULL,
     total_user_cost FLOAT NOT NULL,
     taxes FLOAT DEFAULT 8.25,
     task_id INT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (task_id) REFERENCES tasks (id)
);
CREATE TABLE honeydoers_services (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     comment VARCHAR(255),
     task_id INT UNSIGNED NOT NULL,
     user_id INT UNSIGNED NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (task_id) REFERENCES tasks (id),
     FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE honeydoers_reviews (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    stars ENUM(1,2,3,4,5) NOT NULL,
    comment TEXT,
    task_id INT UNSIGNED NOT NULL,
    honeydoer_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (honeydoer_id) REFERENCES honeydoers (id)
);
CREATE TABLE honeydoers_reviews (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    stars ENUM(1,2,3,4,5) NOT NULL,
    comment TEXT,
    task_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);