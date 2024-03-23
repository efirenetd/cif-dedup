DROP TABLE customer_ind IF EXISTS;
DROP TABLE lookup_matching_ind IF EXISTS;

CREATE TABLE customer_ind (
  customer_no VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  date_key VARCHAR(255)
);

CREATE TABLE lookup_matching_ind (
  customer_no VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  date_key VARCHAR(255)
);

