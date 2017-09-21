--
-- Table structure for table `application`
--

CREATE TABLE IF NOT EXISTS `application` (
  `id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `id` char(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `registered_user`
--

CREATE TABLE IF NOT EXISTS `registered_user` (
  `id` char(36) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `actv_ind` enum('Y','N') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_city`
--

CREATE TABLE IF NOT EXISTS `user_city` (
  `user_id` char(36) NOT NULL,
  `city_id` char(36) NOT NULL,
  PRIMARY KEY (`user_id`,`city_id`),
  KEY `city_id` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

--CREATE TABLE IF NOT EXISTS `user_role` (
--  `user_id` char(36) NOT NULL,
--  `role_id` varchar(10) NOT NULL,
--  `app_id` varchar(20) NOT NULL,
--  PRIMARY KEY (`user_id`,`role_id`,`app_id`),
--  KEY `role_id` (`role_id`),
--  KEY `app_id` (`app_id`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
----
---- Constraints for dumped tables
----
--
----
---- Constraints for table `user_city`
----
--ALTER TABLE `user_city`
--  ADD CONSTRAINT `user_city_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `registered_user` (`id`),
--  ADD CONSTRAINT `user_city_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`);

--
-- Constraints for table `user_role`
--
--ALTER TABLE `user_role`
--  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `application` (`id`),
--  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
--  ADD CONSTRAINT `user_role_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `registered_user` (`id`);
--COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
