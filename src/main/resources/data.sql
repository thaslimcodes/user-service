
--
-- Database: app_user_role
--

--
-- Dumping data for table application
--

INSERT IGNORE INTO application (id, name) VALUES
('bf66ef7f-1f37-40d8-b08c-b82d20f408f0', 'APP_ROLE');

--
-- Dumping data for table city
--

INSERT IGNORE INTO city (id, name) VALUES
('2febe4cb-8b44-4cb9-b60c-1d053ec369f9', 'Bengaluru'),
('3e2feb9c-0a6a-4706-aa7d-55949c486097', 'Chennai'),
('48581a9c-0bcc-4081-bc80-0ae156bf2a7a', 'Guwahati');

--
-- Dumping data for table registered_user
--

INSERT IGNORE INTO registered_user (id, firstname, lastname, mobile, email, actv_ind) VALUES
('f7ce13bf-14ef-4d66-852f-a07dff45090e', 'Thaslim', 'Abdul Azees', '9600822990', 'ta@ysg.co.in', 'Y');

--
-- Dumping data for table role
--

INSERT IGNORE INTO role (id, name) VALUES
('COMP_HEAD', 'Compliance Head'),
('FIN_HEAD', 'Finance Head'),
('SUPER_ADMIN', 'SUPER_ADMIN');

--
-- Dumping data for table user_city
--

INSERT IGNORE INTO user_city (user_id, city_id) VALUES
('f7ce13bf-14ef-4d66-852f-a07dff45090e', '2febe4cb-8b44-4cb9-b60c-1d053ec369f9');

--
-- Dumping data for table user_role
--

INSERT IGNORE INTO user_role (user_id, role_id, app_id) VALUES
('f7ce13bf-14ef-4d66-852f-a07dff45090e', 'SUPER_ADMIN', 'bf66ef7f-1f37-40d8-b08c-b82d20f408f0');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
