-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 18-06-15 09:41 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `parkingmanagement`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `beacon`
--

CREATE TABLE IF NOT EXISTS `beacon` (
  `uuid` varchar(50) NOT NULL,
  `major` varchar(10) NOT NULL,
  `minor` varchar(10) NOT NULL,
  `place` varchar(20) NOT NULL,
  `no` int(11) NOT NULL,
  `status` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `beacon`
--

INSERT INTO `beacon` (`uuid`, `major`, `minor`, `place`, `no`, `status`) VALUES
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26331', '한세대학교,도서관', 1, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26330', '한세대학교,도서관', 2, 'using'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26329', '한세대학교,도서관', 3, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26328', '한세대학교,도서관', 4, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26327', '한세대학교,도서관', 5, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26326', '한세대학교,도서관', 6, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26325', '한세대학교,도서관', 7, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26324', '한세대학교,도서관', 8, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26323', '한세대학교,도서관', 9, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26322', '한세대학교,도서관', 10, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26321', '한세대학교,도서관', 11, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26320', '한세대학교,도서관', 12, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26319', '한세대학교,도서관', 13, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26318', '한세대학교,도서관', 14, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26317', '한세대학교,도서관', 15, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26316', '한세대학교,도서관', 16, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26315', '한세대학교,도서관', 17, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26314', '한세대학교,도서관', 18, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26313', '한세대학교,도서관', 19, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26312', '한세대학교,도서관', 20, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26311', '한세대학교,도서관', 21, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26310', '한세대학교,도서관', 22, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26309', '한세대학교,도서관', 23, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26308', '한세대학교,도서관', 24, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26307', '한세대학교,도서관', 25, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26306', '한세대학교,도서관', 26, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26305', '한세대학교,도서관', 27, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26304', '한세대학교,도서관', 28, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26303', '한세대학교,도서관', 29, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26302', '한세대학교,도서관', 30, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26301', '한세대학교,도서관', 31, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26300', '한세대학교,도서관', 32, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26299', '한세대학교,도서관', 33, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26298', '한세대학교,도서관', 34, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26297', '한세대학교,도서관', 35, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26296', '한세대학교,도서관', 36, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26295', '한세대학교,도서관', 37, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26294', '한세대학교,도서관', 38, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26293', '한세대학교,도서관', 39, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26292', '한세대학교,도서관', 40, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26291', '한세대학교,도서관', 41, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26290', '한세대학교,도서관', 42, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26289', '한세대학교,도서관', 43, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26288', '한세대학교,도서관', 44, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26287', '한세대학교,도서관', 45, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26286', '한세대학교,도서관', 46, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26285', '한세대학교,도서관', 47, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26284', '한세대학교,도서관', 48, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26283', '한세대학교,도서관', 49, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26282', '한세대학교,도서관', 50, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26281', '한세대학교,도서관', 51, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26280', '한세대학교,도서관', 52, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26279', '한세대학교,도서관', 53, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26278', '한세대학교,도서관', 54, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26277', '한세대학교,도서관', 55, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26276', '한세대학교,도서관', 56, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26275', '한세대학교,도서관', 57, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26274', '한세대학교,도서관', 58, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26273', '한세대학교,도서관', 59, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26272', '한세대학교,도서관', 60, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26271', '한세대학교,도서관', 61, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26270', '한세대학교,도서관', 62, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26269', '한세대학교,본관', 1, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26268', '한세대학교,본관', 2, 'using'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26267', '한세대학교,본관', 3, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26266', '한세대학교,본관', 4, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26265', '한세대학교,본관', 5, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26264', '한세대학교,본관', 6, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26263', '한세대학교,본관', 7, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26262', '한세대학교,본관', 8, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26261', '한세대학교,본관', 9, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26260', '한세대학교,본관', 10, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26259', '한세대학교,본관', 11, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26258', '한세대학교,본관', 12, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26257', '한세대학교,본관', 13, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26256', '한세대학교,본관', 14, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26255', '한세대학교,본관', 15, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26254', '한세대학교,본관', 16, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26253', '한세대학교,본관', 17, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26252', '한세대학교,본관', 18, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26251', '한세대학교,본관', 19, 'wait'),
('24DDF411-8CF1-440C-87CD-E368DAF9C93E', '501', '26250', '한세대학교,본관', 20, 'wait');

-- --------------------------------------------------------

--
-- 테이블 구조 `current_using`
--

CREATE TABLE IF NOT EXISTS `current_using` (
  `id` varchar(20) NOT NULL,
  `place` varchar(40) NOT NULL,
  `beacon_no` int(11) NOT NULL,
  `check_in` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `current_using`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `PW` varchar(20) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(40) CHARACTER SET utf8 NOT NULL,
  `PhoneNumber` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `customer`
--

INSERT INTO `customer` (`ID`, `PW`, `Name`, `Email`, `PhoneNumber`) VALUES
('root', '1234', '이재국', 'dlwornr02@naver.com', '010'),
('kboxxx', '39789', '김병용', 'kboxxx@hanmail.net', '01032377363'),
('root2', '1234', '관리자임ㅎ', 'dlwornr02@naver.com', '없음');

-- --------------------------------------------------------

--
-- 테이블 구조 `details_of_usage`
--

CREATE TABLE IF NOT EXISTS `details_of_usage` (
  `ID` varchar(20) NOT NULL,
  `CheckIn` datetime NOT NULL,
  `CheckOut` datetime NOT NULL,
  `Place` varchar(20) NOT NULL,
  KEY `details_of_usage_customer_fk` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `details_of_usage`
--

INSERT INTO `details_of_usage` (`ID`, `CheckIn`, `CheckOut`, `Place`) VALUES
('root', '2018-06-14 06:44:44', '2018-06-14 07:44:52', '한세대학교,도서관'),
('root', '2018-06-13 06:45:18', '2018-06-13 08:45:23', '한세대학교,도서관'),
('root', '2018-06-14 09:29:31', '2018-06-14 09:55:37', '한세대학교,도서관'),
('root', '2018-06-13 23:00:04', '2018-06-15 09:31:59', '한세대학교,도서관'),
('root', '2018-06-15 09:32:21', '2018-06-15 09:38:12', '한세대학교,도서관'),
('root', '2018-06-15 09:38:30', '2018-06-15 09:39:43', '한세대학교,도서관'),
('root', '2018-06-15 09:39:50', '2018-06-15 09:39:55', '한세대학교,도서관');

-- --------------------------------------------------------

--
-- 테이블 구조 `parkinglots`
--

CREATE TABLE IF NOT EXISTS `parkinglots` (
  `place` varchar(20) CHARACTER SET utf8 NOT NULL,
  `address` varchar(30) CHARACTER SET utf8 NOT NULL,
  `number_of_beacon` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 테이블의 덤프 데이터 `parkinglots`
--

INSERT INTO `parkinglots` (`place`, `address`, `number_of_beacon`) VALUES
('한세대학교,도서관', '경기도 군포시 한세로 30', 62);
