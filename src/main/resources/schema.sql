--
-- Database: app_user_role
--

-- --------------------------------------------------------

--
-- Table structure for table application
--

CREATE TABLE IF NOT EXISTS application (
  id char(36) NOT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table city
--

CREATE TABLE IF NOT EXISTS city (
  id char(36) NOT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table registered_user
--

CREATE TABLE IF NOT EXISTS registered_user (
  id char(36) NOT NULL,
  firstname varchar(100) NOT NULL,
  lastname varchar(100) NOT NULL,
  mobile varchar(10) DEFAULT NULL,
  email varchar(100) NOT NULL,
  actv_ind enum('Y','N') NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY user_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table role
--

CREATE TABLE IF NOT EXISTS role (
  id char(36) NOT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY role_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table user_city
--

CREATE TABLE IF NOT EXISTS user_city (
  user_id char(36) NOT NULL,
  city_id char(36) NOT NULL,
  PRIMARY KEY (user_id,city_id),
  KEY city_id (city_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table user_role
--

CREATE TABLE IF NOT EXISTS user_role (
  user_id char(36) NOT NULL,
  role_id char(36) NOT NULL,
  app_id char(36) NOT NULL,
  PRIMARY KEY (user_id,role_id,app_id),
  KEY role_id (role_id),
  KEY app_id (app_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

----
---- Constraints for dumped tables
----
--
----
-- Constraints for table user_city
--
ALTER TABLE user_city ADD CONSTRAINT FOREIGN KEY (city_id) REFERENCES city (id);
ALTER TABLE user_city ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES registered_user (id);

--
-- Constraints for table user_role
--
ALTER TABLE user_role
  ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES registered_user (id),
  ADD CONSTRAINT FOREIGN KEY (role_id) REFERENCES role (id),
  ADD CONSTRAINT FOREIGN KEY (app_id) REFERENCES application (id);
COMMIT;

