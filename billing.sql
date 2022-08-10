-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2016 at 09:09 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `cust`
--

CREATE TABLE IF NOT EXISTS `cust` (
  `name` varchar(30) NOT NULL,
  `address` varchar(150) NOT NULL,
  `city` varchar(20) NOT NULL,
  `postalcode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cust`
--

INSERT INTO `cust` (`name`, `address`, `city`, `postalcode`) VALUES
('akilam', '662 662 street', 'dbsjbd', 65432),
('sjsndndn', 'dnnsnbd dnnsnbd dbdbd', '626189', 626189);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_sales`
--

CREATE TABLE IF NOT EXISTS `invoice_sales` (
  `invoice` varchar(20) NOT NULL DEFAULT '',
  `partyname` varchar(100) NOT NULL,
  `date` varchar(20) NOT NULL,
  `netamount` varchar(50) NOT NULL,
  PRIMARY KEY (`invoice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice_sales`
--

INSERT INTO `invoice_sales` (`invoice`, `partyname`, `date`, `netamount`) VALUES
('IN16', 'Name', '16/07/2016 , 7:25 PM', '8.25'),
('PR1', 'Name', '16/07/2016 , 11:44 P', '0.0'),
('PR10', 'Name', '16/07/2016 , 2:50 PM', '82.5'),
('PR13', 'Name', '16/07/2016 , 4:01 PM', '8.25'),
('PR14', 'Name', '16/07/2016 , 4:03 PM', '82.5'),
('PR15', 'Name', '16/07/2016 , 6:51 PM', '1072.5'),
('PR16', 'Name', '16/07/2016 , 7:18 PM', '1797.9499999999998'),
('PR18', 'Name', '16/07/2016 , 7:26 PM', '54.0'),
('PR9', 'Name', '15/07/2016 , 5:20 PM', '8250.0');

-- --------------------------------------------------------

--
-- Table structure for table `july_2016invoice`
--

CREATE TABLE IF NOT EXISTS `july_2016invoice` (
  `invoice` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `qty` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `per` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `july_2016invoice`
--

INSERT INTO `july_2016invoice` (`invoice`, `name`, `qty`, `price`, `per`, `amount`) VALUES
('IN16', '7cm Coloured Sparklers', '1', '15.0', '1 Box', '15');

-- --------------------------------------------------------

--
-- Table structure for table `july_2016profoma`
--

CREATE TABLE IF NOT EXISTS `july_2016profoma` (
  `invoice` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `qty` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `per` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `july_2016profoma`
--

INSERT INTO `july_2016profoma` (`invoice`, `name`, `qty`, `price`, `per`, `amount`) VALUES
('PR9', 'Gold Lakshmi', '50', '100.0', 'BOX', '5000'),
('PR9', 'Flower Pots', '50', '200.0', 'BOX', '10000'),
('PR10', '7cm Coloured Sparklers', '10', '15.0', '1 Box', '150'),
('PR13', '7cm Coloured Sparklers', '1', '15.0', '1 Box', '15'),
('PR14', '7cm Coloured Sparklers', '10', '15.0', '1 Box', '150'),
('PR15', '7cm Coloured Sparklers', '4', '15.0', '1 Box', '60'),
('PR15', '15cm Panchavarnam 5 in One', '4', '70.0', '1 Box', '280'),
('PR15', 'Golden Daffodil(2 Pcs)', '7', '230.0', '1 Box', '1610'),
('PR15', '7cm Electric Sparklers', '4', '14.0', '1 Box', '56'),
('PR15', 'Silver Bloom(2 Pcs)', '4', '230.0', '1 Box', '920'),
('PR16', '7cm Coloured Sparklers', '14', '15.0', '1 Box', '210'),
('PR16', '10cm Five in One Sparklers', '14', '43.5', '1 Box', '609'),
('PR16', 'Flowerpots Green', '1', '175.0', '1 Box', '2450'),
('PR18', '7cm Coloured Sparklers', '4', '15.0', '1 Box', '60'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0'),
('PR1', '', '0', '0.0', '', '0');

-- --------------------------------------------------------

--
-- Table structure for table `july_2016sales_invoice`
--

CREATE TABLE IF NOT EXISTS `july_2016sales_invoice` (
  `invoice` varchar(20) NOT NULL DEFAULT '',
  `partyname` varchar(100) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `netamount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`invoice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `july_2016sales_invoice`
--

INSERT INTO `july_2016sales_invoice` (`invoice`, `partyname`, `date`, `netamount`) VALUES
('IN16', 'Name', '16/07/2016 , 7:25 PM', '8.25');

-- --------------------------------------------------------

--
-- Table structure for table `july_2016sales_profoma`
--

CREATE TABLE IF NOT EXISTS `july_2016sales_profoma` (
  `invoice` varchar(20) NOT NULL DEFAULT '',
  `partyname` varchar(100) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `netamount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`invoice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `july_2016sales_profoma`
--

INSERT INTO `july_2016sales_profoma` (`invoice`, `partyname`, `date`, `netamount`) VALUES
('', '', '', ''),
('PR1', 'Name', '16/07/2016 , 11:44 P', '0.0'),
('PR10', 'Name', '16/07/2016 , 2:50 PM', '82.5'),
('PR13', 'Name', '16/07/2016 , 4:01 PM', '8.25'),
('PR14', 'Name', '16/07/2016 , 4:03 PM', '82.5'),
('PR15', 'Name', '16/07/2016 , 6:51 PM', '1072.5'),
('PR16', 'Name', '16/07/2016 , 7:18 PM', '1797.9499999999998'),
('PR18', 'Name', '16/07/2016 , 7:26 PM', '54.0'),
('PR9', 'Name', '15/07/2016 , 5:20 PM', '8250.0');

-- --------------------------------------------------------

--
-- Table structure for table `jun_2016sales_invoice`
--

CREATE TABLE IF NOT EXISTS `jun_2016sales_invoice` (
  `invoice` varchar(20) NOT NULL DEFAULT '',
  `partyname` varchar(100) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `netamount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`invoice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jun_2016sales_invoice`
--

INSERT INTO `jun_2016sales_invoice` (`invoice`, `partyname`, `date`, `netamount`) VALUES
('IN39', 'Name', '28/06/2016 , 2:31 AM', '12650.0'),
('IN41', 'Name', '28/06/2016 , 2:35 AM', '1650.0'),
('IN42', 'Akilan', '05-04-016', '16222');

-- --------------------------------------------------------

--
-- Table structure for table `jun_2016sales_profoma`
--

CREATE TABLE IF NOT EXISTS `jun_2016sales_profoma` (
  `invoice` varchar(20) NOT NULL DEFAULT '',
  `partyname` varchar(100) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `netamount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`invoice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jun_2016sales_profoma`
--

INSERT INTO `jun_2016sales_profoma` (`invoice`, `partyname`, `date`, `netamount`) VALUES
('', '', '', ''),
('IN35', '', '', '550.0'),
('IN36', '', '', '2200.0'),
('IN37', '', '', '9350.0'),
('IN38', 'Navin A D C', '28/06/2016 , 2:23 AM', '7700.0'),
('IN40', 'Name', '28/06/2016 , 2:35 AM', '12650.0');

-- --------------------------------------------------------

--
-- Table structure for table `profoma_sales`
--

CREATE TABLE IF NOT EXISTS `profoma_sales` (
  `invoice` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `qty` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `per` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `rate` decimal(10,2) DEFAULT NULL,
  `per` varchar(20) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `code`, `name`, `rate`, `per`, `stock`) VALUES
(1, 'pt1', '7cm Electric Sparklers', 14.00, '1 Box', 96),
(2, 'pt2', '7cm Coloured Sparklers', 15.00, '1 Box', 96),
(3, 'pt3', '9cm X 10 Electric Sparklers', 15.10, '1 Box', 100),
(4, 'pt4', '9cm X 10 Coloured Sparklers', 17.20, '1 Box', 100),
(5, 'pt5', '10cm Red Sparklers', 17.00, '1 Box', 100),
(6, 'pt6', '10cm Green Sparklers', 15.00, '1 Box', 100),
(7, 'pt7', '10cm Blue Sparklers', 41.00, '1 Box', 100),
(8, 'pt8', '10cm Five in One Sparklers', 43.50, '1 Box', 86),
(9, 'pt9', '12cm Electric Sparklers', 34.00, '1 Box', 100),
(10, 'pt10', '12cm Coloured Sparklers', 40.00, '1 Box', 100),
(11, 'pt11', '15cm Electric Sparklers', 60.00, '1 Box', 100),
(12, 'pt12', '15cm Coloured Sparklers', 70.00, '1 Box', 100),
(13, 'pt13', '15cm/30cm Ruby Sparklers', 59.00, '1 Box', 100),
(14, 'pt14', '15cm/30cm Emerald Sparklers', 48.00, '1 Box', 100),
(15, 'pt15', '15cm Panchavarnam 5 in One', 70.00, '1 Box', 96),
(16, 'pt16', '30cm Electric Sparklers', 60.00, '1 Box', 100),
(17, 'pt17', '30cm Coloured Sparklers', 70.00, '1 Box', 100),
(18, 'pt18', '50cm Electric', 190.00, '1 Box', 100),
(19, 'pt19', '75cm Electric', 300.00, '1 Box', 100),
(20, 'pt20', 'Paradise Sparklers(Multi-Colour)', 210.00, '1 Box', 100),
(21, 'pt21', 'Rajadhani Sparklers(Multi-Colour)', 320.00, '1 Box', 100),
(22, 'pt22', 'Ground Chakkar Medium(25 Pcs)', 80.00, '1 Box', 100),
(23, 'pt23', 'Ground Chakkar Big(25 Pcs)', 115.00, '1 Box', 100),
(24, 'pt24', 'Ground Chakkar Special(10 Pcs)', 95.00, '1 Box', 100),
(25, 'pt25', 'Ground Chakkar Deluxe(10 Pcs)', 168.00, '1 Box', 100),
(26, 'pt26', 'Ground Chakkar Red', 52.50, '1 Box', 100),
(27, 'pt27', 'Ground Chakkar Special Green(10 Pcs)', 105.00, '1 Box', 100),
(28, 'pt28', 'Giant Wheel, Joke Wheel, Glittering Wheel(5 Pcs)', 135.00, '1 Box', 100),
(29, 'pt29', 'Star Wheel(10 Pcs)', 125.00, '1 Box', 100),
(30, 'pt30', 'Classic Wheel(10 Pcs)', 230.00, '1 Box', 100),
(31, 'pt31', 'Whistling Wheel(5 Pcs)', 150.00, '1 Box', 100),
(32, 'pt32', 'Tinto Wheel(Red', 84.00, '1 Box', 100),
(33, 'pt33', 'Flowerpots Small', 79.00, '1 Box', 100),
(34, 'pt34', 'Flowerpots Big', 110.00, '1 Box', 100),
(35, 'pt35', 'Flowerpots Special', 160.00, '1 Box', 100),
(36, 'pt36', 'Flowerpots Giant', 333.00, '1 Box', 100),
(37, 'pt37', 'Flowerpots Deluxe(1 X 2 Pcs)', 107.00, '1 Box', 100),
(38, 'pt38', 'Flowerpots Deluxe(1 X 5 Pcs)', 218.00, '1 Box', 100),
(39, 'pt39', 'Flowerpots Red', 178.00, '1 Box', 100),
(40, 'pt40', 'Flowerpots Green', 175.00, '1 Box', 99),
(41, 'pt41', 'Colour Koti Deluxe', 340.00, '1 Box', 100),
(42, 'pt42', 'Tri Colour Fountain(1 X 5 Pcs)', 340.00, '1 Box', 100),
(43, 'pt43', 'Rangoli(5 Pcs)', 210.00, '1 Box', 100),
(44, 'pt44', 'Jadugar(4 Pcs)', 200.00, '1 Box', 100),
(45, 'pt45', 'Manoranjan(4 Pcs)', 200.00, '1 Box', 100),
(46, 'pt46', 'Roop Khela(1 X 10)', 250.00, '1 Box', 100),
(47, 'pt47', 'Varnajal(1 X 10)', 330.00, '1 Box', 100),
(48, 'pt48', 'Kiddy''s Joy(5 Colours)', 150.00, '1 Box', 100),
(49, 'pt49', 'Silver Bloom(2 Pcs)', 230.00, '1 Box', 96),
(50, 'pt50', 'Golden Daffodil(2 Pcs)', 230.00, '1 Box', 93),
(51, 'pt51', 'Super Star', 150.00, '1 Box', 100),
(52, 'pt52', 'Royal Majestic(1 X 2)', 300.00, '1 Box', 100),
(53, 'pt53', 'Dil Se(1 X 3)', 340.00, '1 Box', 100),
(54, 'pt54', 'Yolo Green(3 Pcs)', 340.00, '1 Box', 100),
(55, 'pt55', 'Double Mystica(2 Pcs)', 105.00, '1 Box', 100),
(56, 'pt56', 'Fire Drops(25 Box)', 56.00, '1 Box', 100),
(57, 'pt57', 'Snow Patrol(1 Pce)(25 Box)', 56.00, '1 Box', 100),
(58, 'pt58', 'Pure Gold(2 Pcs)', 110.00, '1 Box', 100),
(59, 'pt59', 'Twinkling Star - 45 cms', 44.00, '1 Box', 100),
(60, 'pt60', 'Twinkling Star - 60 cms', 66.00, '1 Box', 100),
(61, 'pt61', 'Twinkling Star - 120 cms', 135.00, '1 Box', 100),
(62, 'pt62', 'Ruby Whip', 65.00, '1 Box', 100),
(63, 'pt63', 'Emerals Whip', 65.00, '1 Box', 100),
(64, 'pt64', 'Electric Diamond(25 Pcs)', 67.00, '1 Box', 100),
(65, 'pt65', 'Pencil(20 Pcs)', 112.50, '1 Box', 100),
(66, 'pt66', 'Pencil Deluxe(10 Pcs)', 106.00, '1 Box', 100),
(67, 'pt67', 'Green Candle(10 Pcs)', 120.00, '1 Box', 100),
(68, 'pt68', 'Red Candle(10 Pcs)', 140.00, '1 Box', 100),
(69, 'pt69', 'Rainbow Candle(5 Pcs)', 160.00, '1 Box', 100),
(70, 'pt70', 'Mini Bullet', 33.00, '1 Box', 100),
(71, 'pt71', 'Bullet Bomb', 45.00, '1 Box', 100),
(72, 'pt72', 'Square Bomb', 59.00, '1 Box', 100),
(73, 'pt73', 'Hydrogen Bomb', 64.00, '1 Box', 100),
(74, 'pt74', 'Cake Bomb Big', 100.00, '1 Box', 100),
(75, 'pt75', 'Shogun Bomb', 120.00, '1 Box', 100),
(76, 'pt76', 'Silver Rain Thunder(Flower Bomb)', 157.50, '1 Box', 100),
(77, 'pt77', 'Ganesh Crackers/Bengal Prince 5 "', 50.00, '1 Pkt', 100),
(78, 'pt78', 'Lakshmi Crackers 4"', 25.00, '1 Pkt', 100),
(79, 'pt79', 'Jawan Crackers 3', 18.00, '1 Pkt', 100),
(80, 'pt80', 'Bird Crackers 2', 11.00, '1 Pkt', 100),
(81, 'pt81', 'Double Sound Small', 28.50, '1 Pkt', 100),
(82, 'pt82', 'Double Sound Big', 37.00, '1 Pkt', 100),
(83, 'pt83', 'Three Sound with Colour', 52.00, '1 Pkt', 100),
(84, 'pt84', 'Loose Crackers Red Bijili(100 Pcs)', 55.00, '1 Bag', 100),
(85, 'pt85', 'Loose Crackers Stripped Bijili(100 Pcs)', 59.00, '1 Bag', 100),
(86, 'pt86', 'Goa Crackers', 19.30, '1 Pkt', 100),
(87, 'pt87', '28 Wala Chorsa', 24.00, '1 Pkt', 100),
(88, 'pt88', '56 Wala Chorsa', 48.00, '1 Pkt', 100),
(89, 'pt89', '28 Wala Giant Crackers', 33.00, '1 Pkt', 100),
(90, 'pt90', '56 Wala Giant Crackers', 64.00, '1 Pkt', 100),
(91, 'pt91', '24 Wala Asoka Crackers', 52.00, '1 Pkt', 100),
(92, 'pt92', '24 Wala Deluxe Crackers', 72.00, '1 Pkt', 100),
(93, 'pt93', '32 Wala Deluxe Crackers', 84.00, '1 Pkt', 100),
(94, 'pt94', '48 Wala Deluxe Crackers', 140.00, '1 Pkt', 100),
(95, 'pt95', '50''s Mega Deluxe Crackers', 330.00, '1 Pkt', 100),
(96, 'pt96', '100 Wala Deluxe Crackers', 280.00, '1 Pkt', 100),
(97, 'pt97', 'Celebration Crackers - 100', 81.00, '1 Box', 100),
(98, 'pt98', 'Celebration Crackers - 200', 136.50, '1 Box', 100),
(99, 'pt99', 'Celebration Crackers - 300', 230.00, '1 Box', 100),
(100, 'pt100', 'Celebration Crackers - 600', 440.00, '1 Box', 100),
(101, 'pt101', 'Celebration Crackers - 1000', 660.00, '1 Box', 100),
(102, 'pt102', 'Celebration Crackers - 2000', 1260.00, '1 Box', 100),
(103, 'pt103', 'Celebration Crackers - 3000', 1830.00, '1 Box', 100),
(104, 'pt104', 'Celebration Crackers - 5000', 3270.00, '1 Box', 100),
(105, 'pt105', 'Celebration Crackers - 10000', 6385.00, '1 Box', 100),
(106, 'pt106', 'Colour Rocket', 85.00, '1 Box', 100),
(107, 'pt107', 'Rocket Bomb', 87.50, '1 Box', 100),
(108, 'pt108', 'Flower Missile', 95.00, '1 Box', 100),
(109, 'pt109', 'Sound Missile', 115.00, '1 Box', 100),
(110, 'pt110', 'Multi Colour Rocket', 133.50, '1 Box', 100),
(111, 'pt111', 'Double Sound Rocket', 139.00, '1 Box', 100),
(112, 'pt112', 'Deluxe Rocket Colour', 131.00, '1 Box', 100),
(113, 'pt113', 'Classic Rocket Sound', 153.00, '1 Box', 100),
(114, 'pt114', 'Parachute Rocket(4 Pcs)', 280.00, '1 Box', 100),
(115, 'pt115', 'Air Whistle', 175.00, '1 Box', 100),
(116, 'pt116', 'Super Whistle', 285.00, '1 Box', 100),
(117, 'pt117', 'Atlas(Green Blink) (1 X 6)', 410.00, '1 Box', 100),
(118, 'pt118', 'Apollo(Silver Blink) (1 X 6)', 410.00, '1 Box', 100),
(119, 'pt119', 'Titan(Purple Stars) (1 X 6)', 410.00, '1 Box', 100),
(120, 'pt120', 'Pop Pop', 70.00, '1 Box', 100),
(121, 'pt121', 'Seven Shot Green', 360.00, '1 Box', 100),
(122, 'pt122', 'Seven Shot Gold(15 Pcs)', 390.00, '1 Box', 100),
(123, 'pt123', 'Dancing Butterfly', 170.00, '1 Box', 100),
(124, 'pt124', 'Funny ''Bunny'', Magic Mystery, Chori Chori, Mini Mini', 165.00, '1 Box', 100),
(125, 'pt125', 'Colour Shot(1 X 5)', 200.00, '1 Box', 100),
(126, 'pt126', 'Sparkling Thunder(1 X 5)', 160.00, '1 Box', 100),
(127, 'pt127', 'Ganga(1 X 5)', 200.00, '1 Box', 100),
(128, 'pt128', 'Sky Wheel(1 X 5)', 285.00, '1 Box', 100),
(129, 'pt129', 'Chandhi Ka Chand(1 X 5)', 295.00, '1 Box', 100),
(130, 'pt130', 'Crackling Star(1 X 5)', 340.00, '1 Box', 100),
(131, 'pt131', 'Chota Bhai - Mini Siren(1 X 5)', 195.00, '1 Box', 100),
(132, 'pt132', 'Super Sonica - Siren(1 X 3)', 250.00, '1 Box', 100),
(133, 'pt133', 'Pikachu(1 X 3)', 180.00, '1 Box', 100),
(134, 'pt134', 'Three Angels(1 X 3)', 250.00, '1 Box', 100),
(135, 'pt135', 'Colour Fantasy(1 X 5)', 195.00, '1 Box', 100),
(136, 'pt136', 'Colour Shark', 150.00, '1 Box', 100),
(137, 'pt137', 'Ninja Chakkar(1 X 2)', 95.00, '1 Box', 100),
(138, 'pt138', 'Crackling Red, Green, Yellow(1 X 3)', 280.00, '1 Box', 100),
(139, 'pt139', 'Pogo Silver, Ciolet, Gold, Red, Green(1 X 2)', 75.00, '1 Box', 100),
(140, 'pt140', '1', 1355.00, '1 Bundle', 100),
(141, 'pt141', 'Green Maruel', 90.00, '1 Pce', 100),
(142, 'pt142', 'Silver Blink', 90.00, '1 Pce', 100),
(143, 'pt143', 'Ultra Violet', 90.00, '1 Pce', 100),
(144, 'pt144', 'Solar Magic, Gold Bink, Golden Dew', 90.00, '1 Pce', 100),
(145, 'pt145', 'Mars, Venus, Jupiter, Pluto, Mercury, Rio', 90.00, '1 Pce', 100),
(146, 'pt146', 'Crackling Queen', 380.00, '1 Box', 100),
(147, 'pt147', 'Owtario (Crackling)', 380.00, '1 Box', 100),
(148, 'pt148', 'Osaka(Crackling)', 380.00, '1 Box', 100),
(149, 'pt149', 'War Zone(Crackling)', 380.00, '1 Box', 100),
(150, 'pt150', '1', 360.00, '1 Box', 100),
(151, 'pt151', 'Thunder Popcorn(Crackling)', 225.00, '1 Pce', 100),
(152, 'pt152', 'Crackling Beauty', 225.00, '1 Pce', 100),
(153, 'pt153', 'Aerial Assorted(Mixed)(20 Items) 2" Shells', 200.00, '1 Pce', 100),
(154, 'pt154', '3" Shell Assorted(Mixed)(1 X 1)', 390.00, '1 Pce', 100),
(155, 'pt155', '4" Shell Assorted(Mixed)(1 X 2)', 780.00, '1 Box', 100),
(156, 'pt156', 'Dekhe Man - 12 Shot 5 Variety', 190.00, '1 Pce', 100),
(157, 'pt157', 'Dragon Fight - 16 Shot', 380.00, '1 Box', 100),
(158, 'pt158', 'Maja - 30 Shot', 670.00, '1 Box', 100),
(159, 'pt159', 'Chal Mere Sath - 25 Shot Red', 400.00, '1 Pce', 100),
(160, 'pt160', 'Dil Maange More - 50 Shot Red', 650.00, '1 Pce', 100),
(161, 'pt161', 'Ayyan Power - 60 Shot', 1400.00, '1 Box', 100),
(162, 'pt162', 'Balle Balle - 25 White Revolving Chakkars', 495.00, '1 Pce', 100),
(163, 'pt163', 'Shanghai Night - 25 Shot', 725.00, '1 Pce', 100),
(164, 'pt164', 'Sky Storm - 12 Shot Crackling', 250.00, '1 Pce', 100),
(165, 'pt165', 'Soneri Barsath - 26 Shot Crackling', 495.00, '1 Pce', 100),
(166, 'pt166', 'Secret Warrior - 6 Shot', 280.00, '1 Pce', 100),
(167, 'pt167', 'Royal Salute - 6 Shot Multi Colour', 350.00, '1 Pce', 100),
(168, 'pt168', 'Sky Crackling - Crackling', 640.00, '1 Pce', 100),
(169, 'pt169', 'Icy Blue, Fiery Red, Mink Green Cool Silver, Blazing Yellow, Desert Warrior(25 Shot)', 1230.00, '1 Pce', 100),
(170, 'pt170', 'Flora Fantasy - 100 Shot Multi Colour(Aerial Display)', 4380.00, '1 Pce', 100),
(171, 'pt171', 'Floral Fantasy - 180 Shot Multi Colour', 7730.00, '1 Pce', 100),
(172, 'pt172', 'Fun Club - 24 Shot Multi Colour', 6230.00, '1 Pce', 100),
(173, 'pt173', 'Classic Night - 120 Shot Four Colours(Colourful Night)', 2060.00, '1 Pce', 100),
(174, 'pt174', 'Classic Night - 240 Shot Four Colours', 4085.00, '1 Pce', 100),
(175, 'pt175', 'Arabian Delight - 120 Shot Multi Colour', 2375.00, '1 Pce', 100),
(176, 'pt176', 'Arabian Delight - 240 Shot Multi Colour', 4720.00, '1 Pce', 100),
(177, 'pt177', 'Subha Mangal - 500 Shot Multi Colour', 10795.00, '1 Pce', 100),
(178, 'pt178', 'Sarva Mangal - 1000 Shot Multi Colour', 21400.00, '1 Pce', 100),
(179, 'pt179', 'Festival Special - 50 Shot Multi Colour', 2370.00, '1 Pce', 100),
(180, 'pt180', 'Festival Special - 100 Shot Multi Colour', 4880.00, '1 Pce', 100),
(181, 'pt181', 'Joker 4 in 1 Aerials', 820.00, 'Box', 100),
(182, 'pt182', 'Megamind Computer Aerial', 1500.00, 'Box', 100),
(183, 'pt183', 'Angel 12 in 1 Multi Colour', 2500.00, 'Box', 100),
(184, 'pt184', 'Josh (1 X 2 Pcs)', 230.00, '1 Pce', 100),
(185, 'pt185', 'Dhadkan (1 X 2 Pcs)', 210.00, '1 Pce', 100),
(186, 'pt186', 'Junior Pack (22 Items)', 450.00, '1 Pce', 100),
(187, 'pt187', 'Apoorva (22 Items)', 990.00, '1 Pce', 100),
(188, 'pt188', 'Poornima (25 Items)', 1280.00, '1 Pce', 100),
(189, 'pt189', 'Gift Box - A (25 Items)', 610.00, '1 Pce', 100),
(190, 'pt190', 'Gift Box - B (29 Items)', 850.00, '1 Pce', 100),
(191, 'pt191', 'Gift Box - c (33 Items)', 1070.00, '1 Pce', 100),
(192, 'pt192', 'Gift Box - D (36 Items)', 1220.00, '1 Pce', 100),
(193, 'pt193', 'Gift Box - Dino (40 Items)', 1380.00, '1 Pce', 100),
(194, 'pt194', 'Skyway (37 Items)', 1200.00, '1 Pce', 100),
(195, 'pt195', 'Dalailama (29 Items)', 700.00, '1 Pce', 100),
(196, 'pt196', 'Aurora (52 Items)', 3000.00, '1 Pce', 100),
(197, 'pt197', 'Lion', 150.00, '1 Unit', 100),
(198, 'pt198', 'Snake Tablets Big', 200.00, '1 Unit', 100),
(199, 'pt199', 'Snake Tablets Small', 100.00, '1 Unit', 100),
(200, 'pt200', 'Anagonda Tablet', 100.00, 'Box', 100),
(201, 'pt201', 'Elephant', 5.00, 'Box', 100),
(202, 'pt202', 'Camel Colour Matches', 45.00, 'Box', 100),
(203, 'pt203', 'Camel Pops Combination Pack Matches', 75.00, 'Box', 100),
(204, 'pt204', 'Camel Josh Combination Pack Matches', 90.00, 'Box', 100),
(205, 'pt205', 'Camel Ninja Combination Pack Matches', 100.00, 'Box', 100),
(206, 'pt206', 'Camel Rainbow Combination Pack', 150.00, 'Box', 100),
(207, 'pt207', 'Camel Dasara(10 in one)', 200.00, 'Box', 100);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
