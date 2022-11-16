-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2022 at 11:49 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crudusers`
--

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments`
(
    `id`   bigint(20) NOT NULL,
    `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `name`)
VALUES (1, ''Computer Science''),
       (2, ''Engineering Management''),
       (3, ''Aeronautical Engineering''),
       (4, ''Department of Nursing''),
       (5, ''Biology''),
       (6, ''Physics''),
       (7, ''Civil''),
       (8, ''Mechanical'');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events`
(
    `event_id`      bigint(20) NOT NULL,
    `description`   varchar(255) NOT NULL,
    `event_date`    date         NOT NULL,
    `event_time`    time         NOT NULL,
    `title`         varchar(255) NOT NULL,
    `venue`         varchar(255) NOT NULL,
    `department_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `description`, `event_date`, `event_time`, `title`, `venue`, `department_id`)
VALUES (1, ''Paint'', ''2022 - 10 - 19 '', ''12:00:00'', ''Halloween'', ''UC'', 6),
       (2, ''Fall 2022 Career Fair'', ''2022 - 09 - 28 '', ''11:00:00'', ''Career Fair'', ''MAC'', 1),
       (4, ''Fall 2022 Career Fair'', ''2022 - 09 - 28 '', ''11:00:00'', ''Career Fair'', ''MAC'', 6),
       (5, ''Fall 2022 Career Fair'', ''2022 - 09 - 28 '', ''11:00:00'', ''t'', ''MAC'', 6),
       (6, ''Paint'', ''2022 - 10 - 19 '', ''11:00:00'', ''Halloween'', ''UC'', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
    ADD PRIMARY KEY (`event_id`),
  ADD KEY `FK2oib4sxiq6yfydyf20mopjqh4` (`department_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
    MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `events`
--
ALTER TABLE `events`
    ADD CONSTRAINT `FK2oib4sxiq6yfydyf20mopjqh4` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
