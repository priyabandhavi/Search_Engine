-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2014 at 06:32 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ad_serving`
--

-- --------------------------------------------------------

--
-- Table structure for table `parameters`
--

CREATE TABLE IF NOT EXISTS `parameters` (
  `index_name` varchar(100) NOT NULL,
  `port` int(4) NOT NULL,
  `sort_factor` int(5) NOT NULL,
  `fl` varchar(100) NOT NULL,
  `df` varchar(100) NOT NULL,
  `qf` varchar(100) NOT NULL,
  `qf_boosts` varchar(100) NOT NULL,
  `mm` int(5) NOT NULL,
  `pf` varchar(100) NOT NULL,
  `pf_boosts` varchar(100) NOT NULL,
  `ps` int(5) NOT NULL,
  `qs` int(5) NOT NULL,
  `tie` int(5) NOT NULL,
  `hl` tinyint(1) NOT NULL,
  `hl_phrase` tinyint(1) NOT NULL,
  `hl_multiterm` tinyint(1) NOT NULL,
  `key_factor` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parameters`
--

INSERT INTO `parameters` (`index_name`, `port`, `sort_factor`, `fl`, `df`, `qf`, `qf_boosts`, `mm`, `pf`, `pf_boosts`, `ps`, `qs`, `tie`, `hl`, `hl_phrase`, `hl_multiterm`, `key_factor`) VALUES
('ad', 1001, 10, '*, score', 'desc', 'desc,keyword', '20,30', 50, 'desc', '50', 5, 1, 1, 1, 1, 1, 100),
('news', 1002, 10, '*,score', 'content', 'content,title,author,place,date', '20,15,30,30,30', 66, 'content', '50', 3, 2, 1, 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `popularity`
--

CREATE TABLE IF NOT EXISTS `popularity` (
`id` int(10) NOT NULL,
  `word` varchar(50) NOT NULL,
  `searches` int(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=82 ;

--
-- Dumping data for table `popularity`
--

INSERT INTO `popularity` (`id`, `word`, `searches`) VALUES
(1, 'sky', 86),
(2, 'index', 19),
(3, 'oil', 60),
(4, 'prduction', 2),
(5, 'prduce', 2),
(6, 'indai', 3),
(7, 'trad', 57),
(8, 'india', 224),
(9, 'oils', 37),
(10, 'sugar', 7),
(11, 'exports', 34),
(12, 'production', 4),
(13, 'in', 111),
(14, 'with', 3),
(15, 'indya', 42),
(16, 'trade', 124),
(17, 'reuter', 1),
(18, 'china', 4),
(19, 'dealers', 1),
(20, 'deals', 1),
(21, 'd', 2),
(22, 'genex', 1),
(23, 'iron', 5),
(24, 'very', 2),
(25, 'happy', 1),
(26, 'very,', 1),
(27, 'corn', 5),
(28, 'produce', 5),
(29, 'flowers', 1),
(30, 'money', 2),
(31, 'inflows', 4),
(32, '', 82),
(33, 'indi', 109),
(34, 'indian', 2),
(35, 'kill', 1),
(36, 'american', 5),
(37, 'products', 4),
(38, 'the', 3),
(39, 'year', 2),
(40, '2000', 2),
(41, 'loss', 2),
(42, 'of', 2),
(43, 'cuts', 101),
(44, 'bangladesh', 47),
(45, 'bangladsh', 60),
(46, 'inty', 11),
(47, 'intc', 2),
(48, 'indn', 5),
(49, 'inda', 2),
(50, 'tran', 2),
(51, 'taranto', 1),
(52, 'furniture', 1),
(53, 'future', 1),
(54, 'fantastic', 1),
(55, 'hello', 2),
(56, 'hemlo', 1),
(57, 'valid', 2),
(58, 'hills', 4),
(59, 'oild', 30),
(60, 'oil,', 2),
(61, 'ind.', 1),
(62, 'office', 7),
(63, 'dog', 13),
(64, 'petro', 1),
(65, 'coconut', 1),
(66, 'coffee', 3),
(67, 'export', 2),
(68, 'cofee', 2),
(69, 'code', 2),
(70, 'hi', 3),
(71, 'ind', 3),
(72, 'core', 1),
(73, 'google', 1),
(74, 'goode', 1),
(75, 'new', 1),
(76, 'york', 1),
(77, 'goods', 1),
(78, 'morton', 8),
(79, 'tad', 1),
(80, 'ipods', 1),
(81, 'ipod', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parameters`
--
ALTER TABLE `parameters`
 ADD PRIMARY KEY (`index_name`);

--
-- Indexes for table `popularity`
--
ALTER TABLE `popularity`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `popularity`
--
ALTER TABLE `popularity`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=82;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
