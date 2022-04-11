-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 11 Nis 2022, 10:38:58
-- Sunucu sürümü: 10.4.13-MariaDB
-- PHP Sürümü: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `gaming`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `bubbles`
--

CREATE TABLE `bubbles` (
  `boobleId` int(11) NOT NULL,
  `senderId` int(11) NOT NULL,
  `reciverId` int(11) NOT NULL,
  `messageId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `bubbles`
--

INSERT INTO `bubbles` (`boobleId`, `senderId`, `reciverId`, `messageId`) VALUES
(1, 1, 2, 279),
(2, 2, 1, 279),
(7, 1, 3, 284),
(8, 3, 1, 284),
(9, 3, 4, 14),
(10, 4, 3, 14),
(24, 1, 4, 259),
(25, 4, 1, 259),
(34, 1, 4, 285),
(35, 4, 1, 285);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `comments`
--

CREATE TABLE `comments` (
  `commentsId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `value` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `isSubComment` int(11) NOT NULL,
  `postId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `comments`
--

INSERT INTO `comments` (`commentsId`, `userId`, `value`, `date`, `isSubComment`, `postId`) VALUES
(1, 2, 'test2', '2021-03-26 09:49:24', 0, 80),
(2, 2, 'test1', '2021-03-26 09:49:17', 0, 80),
(3, 2, 'test karsilik', '2021-03-26 09:49:46', 1, 2),
(4, 1, 'subtestyazi', '2021-04-05 14:25:26', 1, 2),
(17, 1, 'Name', '2021-04-05 14:35:32', 0, 80),
(18, 1, 'Name', '2021-04-05 14:35:40', 1, 2),
(19, 1, 'Name', '2021-04-05 14:35:49', 1, 17),
(20, 1, 'kzkz', '2021-04-05 14:35:56', 1, 17),
(21, 1, 'Name', '2021-04-05 14:36:27', 1, 1),
(22, 1, 'Name', '2021-04-05 14:36:43', 0, 76),
(23, 1, 'Name', '2021-04-15 11:02:55', 0, 82),
(24, 1, 'Name', '2021-07-16 13:22:35', 0, 76),
(25, 1, 'Name', '2021-07-17 13:32:24', 0, 79),
(26, 1, 'Nameeeeee', '2021-07-17 13:32:33', 0, 81),
(27, 1, 'Nameaaaaa', '2021-07-17 13:32:43', 1, 26),
(28, 1, 'Name', '2021-07-17 17:07:31', 0, 81);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `files`
--

CREATE TABLE `files` (
  `fileId` int(11) NOT NULL,
  `url` text NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `files`
--

INSERT INTO `files` (`fileId`, `url`, `type`) VALUES
(3, '1614950228_IMG_20210305_113231.jpg', 0),
(4, '1614951461_IMG_20210305_113231.jpg', 0),
(5, '1614952183_IMG_20210305_113231.jpg', 0),
(6, '1615209144_VID_20210308_131203.mp4', 1),
(7, '1615210288_VID_20210308_131203.mp4', 1),
(8, '1615210947_VID_20210308_134200.mp4', 1),
(9, '1615478606_download.png', 0),
(10, '1615478627_download.jpg', 0),
(11, '1615480242_download.png', 0),
(12, '1615480517_download.png', 0),
(13, '1615480531_download.png', 0),
(14, '1615480700_download.jpg', 0),
(15, '1615546778_20200320_142450.mp4', 1),
(16, '1615977590_download.jpg', 0),
(17, '1615977590_download.jpg', 0),
(18, '1615978215_download.jpg', 0),
(19, '1615978270_images (45).jpeg', 0),
(20, '1615978298_images (43).jpeg', 0),
(21, '1615978461_FB_IMG_1613317160806.jpg', 0),
(22, '1615978469_FB_IMG_1601374614725.jpg', 0),
(23, '1615978480_IMG_20200901_202718_516.jpg', 0),
(24, '1615978608_images (42).jpeg', 0),
(25, '1615978620_IMG_20200912_193853_403.jpg', 0),
(26, '1615978634_20200406_153523.jpg', 0),
(27, '1615978712_images (40).jpeg', 0),
(28, '1615978721_images (45).jpeg', 0),
(29, '1615978727_20200828_182401.jpg', 0),
(30, '1615978739_20200828_182401-compressed.jpg', 0),
(32, '1615979126_20210201_132519.jpg', 0),
(33, '1615979133_FB_IMG_1583217976888.jpg', 0),
(34, '1615979142_Screenshot_20210315-200156_One UI Home.jpg', 0),
(35, '1615979510_images (40).jpeg', 0),
(36, '1615979631_images (43).jpeg', 0),
(37, '1615980072_Screenshot_20210306-203452_Chrome.jpg', 0),
(40, '1615980896_images (40).jpeg', 0),
(41, '1615980899_images (45).jpeg', 0),
(42, '1615980902_Screenshot_20210310-190212_Instagram.jpg', 0),
(43, '1615981494_20200320_142450.mp4', 1),
(44, '1615981604_20200320_142450.mp4', 1),
(45, '1615981747_VID_54710814_150620_451.mp4', 1),
(46, '1615981876_20201201_001231.mp4', 1),
(47, '1615981934_20200320_142450.mp4', 1),
(48, '1615982029_20200320_142450.mp4', 1),
(49, '1615982115_20200320_142450.mp4', 1),
(50, '1615982422_1615981747_VID_54710814_150620_451.mp4', 1),
(51, '1615982816_test.mp4', 1),
(52, '1615982968_test.mp4', 1),
(53, '1615982990_Screenshot_20210313-134516_Instagram.jpg', 0),
(54, '1615983173_test.mp4', 1),
(55, '1615983650_test.mp4', 1),
(56, '1615983665_download.jpg', 0),
(60, '1615984072_test.mp4', 1),
(61, '1615984315_20200320_142450.mp4', 1),
(62, '1615984989_20200320_142450.mp4', 1),
(63, '1615985023_images (44).jpeg', 0),
(64, '1615985063_test.mp4', 1),
(66, '1615985142_20200828_182401.jpg', 0),
(67, '1615985146_images (44).jpeg', 0),
(75, '1616058860_20210309_214517.mp4', 1),
(78, '1616059323_20200724_225328.mp4', 1),
(79, '1616059335_20210215_141425.jpg', 0),
(81, '1616153632_images (45).jpeg', 0),
(82, '1616153988_20210318_100258.jpg', 0),
(83, '1616154074_FB_IMG_1606558762265.jpg', 0),
(84, '1616154090_20210201_132519.jpg', 0),
(85, '1616154143_Screenshot_20210318-104551_Chrome.jpg', 0),
(86, '1616154193_images (41).jpeg', 0),
(87, '1616154205_20200828_182401.jpg', 0),
(88, '1616154215_20210201_132519.jpg', 0),
(89, '1616154217_20200828_182401.jpg', 0),
(90, '1616154250_20200318_144535.mp4', 1),
(91, '1616154263_20210201_132519.jpg', 0),
(92, '1616154330_20210318_100258.jpg', 0),
(93, '1616154332_20210318_100258.jpg', 0),
(94, '1616154334_Screenshot_20210308-174455_Messages.jpg', 0),
(95, '1616154337_images (29).jpeg', 0),
(96, '1616154340_20210201_132519.jpg', 0),
(97, '1616154344_IMG-20200605-WA0001.jpg', 0),
(98, '1616154357_IMG-20200604-WA0002.jpeg', 0),
(99, '1616154361_IMG-20200601-WA0004.jpg', 0),
(100, '1616154364_IMG-20200602-WA0007.jpg', 0),
(101, '1616154370_IMG-20200602-WA0000.jpg', 0),
(102, '1616154373_IMG-20200604-WA0004.jpg', 0),
(103, '1616154475_Screenshot_20210218-223838_Instagram.jpg', 0),
(104, '1616154478_20200828_182401.jpg', 0),
(105, '1616154480_20210310_184726.jpg', 0),
(106, '1616154483_20200406_152802.jpg', 0),
(107, '1616154485_20200329_234246.jpg', 0),
(108, '1616154488_20200323_170824.jpg', 0),
(109, '1616154490_IMG-20200607-WA0003.jpg', 0),
(110, '1616154492_FB_IMG_1603038026563.jpg', 0),
(111, '1616154496_20210201_132519.jpg', 0),
(112, '1616154498_20200329_234250.jpg', 0),
(118, '1616407702_images (44).jpeg', 0),
(119, '1616576047_Screenshot_20210323-012713_WhatsApp.jpg', 0),
(122, '1616576236_20210315_101703.jpg', 0),
(124, '1616668333_images (45).jpeg', 0),
(125, '1616669190_Screenshot_20210323-005638_Gallery.jpg', 0),
(126, '1616669196_images (37).jpeg', 0),
(127, '1616669213_20191226_152842.mp4', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `friends`
--

CREATE TABLE `friends` (
  `friendId` int(11) NOT NULL,
  `senderId` int(11) NOT NULL,
  `reciverId` int(11) NOT NULL,
  `confirmation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `friends`
--

INSERT INTO `friends` (`friendId`, `senderId`, `reciverId`, `confirmation`) VALUES
(1, 1, 2, 1),
(2, 3, 1, 1),
(3, 2, 4, 1),
(4, 3, 4, 1),
(5, 4, 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `games`
--

CREATE TABLE `games` (
  `gameID` int(11) NOT NULL,
  `gameName` varchar(50) NOT NULL,
  `gameImage` varchar(20) NOT NULL,
  `gameTypeID` int(11) NOT NULL,
  `gameUrl` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `games`
--

INSERT INTO `games` (`gameID`, `gameName`, `gameImage`, `gameTypeID`, `gameUrl`) VALUES
(1, 'League of Legends', 'lol.jpg', 1, 'https://tr.leagueoflegends.com/tr-tr/how-to-play/'),
(2, 'Counter-Strike: Global Offensive', 'csgo.jpg', 2, 'https://store.steampowered.com/app/730/CounterStrike_Global_Offensive/'),
(3, 'Dota 2', 'dota2.jpg', 1, 'https://www.dota2.com/home'),
(6, 'Valorant', 'valorant.jpg', 3, 'https://playvalorant.com/tr-tr/');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `gametypes`
--

CREATE TABLE `gametypes` (
  `gameTypeID` int(11) NOT NULL,
  `gameTypeName` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `gametypes`
--

INSERT INTO `gametypes` (`gameTypeID`, `gameTypeName`) VALUES
(1, 'MOBA'),
(2, 'fps'),
(3, 'cb-fps');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `gtusers`
--

CREATE TABLE `gtusers` (
  `gtUserID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `playerFeaturesID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `gtusers`
--

INSERT INTO `gtusers` (`gtUserID`, `userID`, `playerFeaturesID`) VALUES
(2, 1, 1),
(3, 1, 2),
(4, 1, 3),
(5, 1, 4),
(6, 2, 5),
(7, 2, 6),
(8, 3, 7),
(9, 3, 8),
(10, 3, 9),
(11, 3, 10),
(12, 144, 11),
(13, 144, 12),
(14, 145, 13),
(15, 146, 14),
(16, 4, 15),
(17, 147, 16),
(18, 185, 47);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `likes`
--

CREATE TABLE `likes` (
  `likeId` int(11) NOT NULL,
  `postId` int(11) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `likes`
--

INSERT INTO `likes` (`likeId`, `postId`, `userId`) VALUES
(3, 81, 3),
(4, 79, 4),
(6, 80, 2),
(11, 81, 2),
(36, 78, 1),
(38, 80, 1),
(42, 82, 1),
(43, 76, 1),
(44, 81, 1),
(45, 79, 1),
(46, 77, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `messages`
--

CREATE TABLE `messages` (
  `messagesId` int(11) NOT NULL,
  `senderID` int(11) NOT NULL,
  `reciverID` int(11) NOT NULL,
  `value` text NOT NULL,
  `type` int(11) NOT NULL,
  `sendDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `vis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `messages`
--

INSERT INTO `messages` (`messagesId`, `senderID`, `reciverID`, `value`, `type`, `sendDate`, `vis`) VALUES
(1, 1, 2, 'selam2', 1, '2021-03-01 13:37:00', '0'),
(2, 2, 1, 'selam1', 1, '2021-03-01 13:37:03', '0'),
(3, 3, 1, 'selamenaleykum1', 1, '2021-03-01 13:38:09', '0'),
(4, 1, 3, 'selamenaleykum3', 1, '2021-03-01 13:38:11', '0'),
(5, 2, 1, 'nasılsın', 1, '2021-03-01 14:20:48', '0'),
(6, 1, 2, 'iyiyim', 1, '2021-03-01 15:23:47', '0'),
(14, 3, 4, 'teeeeeee', 1, '2021-03-02 11:21:56', '0'),
(15, 1, 3, 'Name', 1, '2021-03-02 12:20:44', '0'),
(16, 1, 2, 'Name', 1, '2021-03-02 12:21:44', '0'),
(17, 1, 2, 'Name', 1, '2021-03-02 12:22:59', '0'),
(18, 1, 2, 'deneme', 1, '2021-03-02 12:25:02', '0'),
(19, 1, 2, '1', 1, '2021-03-02 12:27:05', '1'),
(20, 1, 2, 'Name', 1, '2021-03-02 12:28:57', '0'),
(21, 1, 2, 'r', 1, '2021-03-04 10:17:54', '0'),
(22, 1, 3, 'Naeee', 1, '2021-03-04 10:18:23', '0'),
(23, 1, 2, 'yeni', 1, '2021-03-04 10:25:27', '0'),
(24, 1, 3, 'alma', 1, '2021-03-04 10:28:03', '0'),
(25, 1, 2, 'yeni mi', 1, '2021-03-04 10:29:21', '0'),
(26, 1, 3, 'alma mi', 1, '2021-03-04 10:33:20', '0'),
(27, 1, 2, 'Name', 1, '2021-03-04 10:36:24', '0'),
(28, 1, 2, 'test', 1, '2021-03-04 10:37:10', '0'),
(29, 1, 3, 'test2', 1, '2021-03-04 10:37:21', '0'),
(30, 3, 1, 'asdasdasds', 1, '2021-03-04 10:38:02', '0'),
(31, 2, 1, 'test22213', 1, '2021-03-04 10:41:07', '0'),
(32, 2, 1, 'test23', 1, '2021-03-04 10:43:47', '0'),
(33, 2, 1, 'testttttt', 1, '2021-03-04 10:45:08', '0'),
(34, 2, 1, 'ASDSADASD', 1, '2021-03-04 10:50:05', '0'),
(35, 2, 1, 'TEST', 1, '2021-03-04 10:50:24', '0'),
(36, 2, 1, 'oldu mu', 1, '2021-03-04 10:58:50', '0'),
(37, 1, 2, 'Name', 1, '2021-03-04 10:59:49', '0'),
(38, 1, 3, 'basardim', 1, '2021-03-04 11:00:07', '0'),
(55, 1, 2, 'img', 2, '2021-03-05 12:30:38', '0'),
(56, 1, 2, 'img', 2, '2021-03-05 12:30:38', '0'),
(57, 1, 2, 'resim dosyasi', 2, '2021-03-05 13:17:08', '0'),
(58, 1, 2, 'resim dosyasi', 2, '2021-03-05 13:37:41', '0'),
(59, 1, 3, 'resim dosyasi', 2, '2021-03-05 13:49:42', '0'),
(60, 1, 3, 'Video dosyasi', 3, '2021-03-08 13:12:24', '0'),
(61, 1, 3, 'Video dosyasi', 3, '2021-03-08 13:31:28', '0'),
(62, 1, 3, 'Video dosyasi', 3, '2021-03-08 13:42:26', '0'),
(63, 1, 2, 'test', 1, '2021-03-09 13:34:34', '0'),
(64, 1, 3, 'test', 1, '2021-03-09 13:37:49', '0'),
(65, 1, 3, 'test2', 1, '2021-03-09 13:38:58', '0'),
(66, 1, 2, 'Name', 1, '2021-03-09 13:39:45', '0'),
(67, 1, 2, 'Name', 1, '2021-03-09 13:45:23', '0'),
(68, 1, 2, 'tessst', 1, '2021-03-09 13:46:47', '0'),
(69, 1, 2, 'Name', 1, '2021-03-09 13:46:56', '0'),
(70, 1, 2, 'Name', 1, '2021-03-09 13:47:42', '0'),
(71, 1, 2, 'teeest', 1, '2021-03-09 13:49:10', '0'),
(72, 1, 3, 'Name', 1, '2021-03-09 13:49:21', '0'),
(73, 1, 3, 'teeeest', 1, '2021-03-09 13:49:24', '0'),
(74, 2, 1, '10asd', 1, '2021-03-11 14:32:43', '0'),
(75, 2, 1, '10asd', 1, '2021-03-11 14:32:59', '0'),
(76, 2, 1, '10asd', 1, '2021-03-11 14:33:41', '0'),
(143, 1, 2, 'resim dosyası', 2, '2021-03-11 16:01:20', '0'),
(144, 1, 2, 'resim dosyası', 2, '2021-03-11 16:02:47', '0'),
(145, 1, 2, 'resim dosyası', 2, '2021-03-11 16:03:26', '0'),
(146, 1, 2, 'resim dosyası', 2, '2021-03-11 16:03:47', '0'),
(147, 1, 2, 'resim dosyası', 2, '2021-03-11 16:03:58', '0'),
(148, 1, 2, 'resim dosyası', 2, '2021-03-11 16:04:20', '0'),
(149, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:29:29', '0'),
(150, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:29:49', '0'),
(151, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:30:16', '0'),
(152, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:30:41', '0'),
(153, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:30:50', '0'),
(154, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:31:34', '0'),
(155, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:32:10', '0'),
(156, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:33:11', '0'),
(157, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:34:22', '0'),
(158, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:34:56', '0'),
(159, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:35:17', '0'),
(160, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:35:30', '0'),
(161, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:37:54', '0'),
(162, 1, 2, 'resim dosyasıson', 2, '2021-03-11 16:38:19', '0'),
(164, 1, 3, 'Name', 1, '2021-03-12 10:51:48', '0'),
(165, 1, 3, 'Name', 1, '2021-03-12 10:52:00', '0'),
(166, 1, 3, 'Video dosyasi', 3, '2021-03-12 10:59:38', '0'),
(167, 1, 3, 'Name', 1, '2021-03-17 12:54:02', '0'),
(168, 1, 3, 'Video dosyasi', 3, '2021-03-17 12:54:33', '0'),
(169, 1, 3, 'videodosyası yüklendi', 3, '2021-03-24 08:59:13', '0'),
(170, 1, 3, 'Name', 1, '2021-03-24 08:59:46', '0'),
(171, 2, 1, 'laaaaaaaaaa', 1, '2021-03-24 09:18:44', '0'),
(172, 2, 1, 'ssss', 1, '2021-03-24 09:23:34', '0'),
(174, 2, 1, 'son', 1, '2021-03-24 09:24:43', '0'),
(179, 1, 3, 'test', 1, '2021-03-24 09:45:13', '0'),
(180, 1, 3, 'test', 1, '2021-03-24 09:45:24', '0'),
(182, 1, 3, 'Name', 1, '2021-03-24 09:52:32', '0'),
(183, 1, 3, 'Name', 1, '2021-03-24 09:52:38', '0'),
(184, 1, 3, 'Name', 1, '2021-03-25 09:47:26', '0'),
(185, 1, 3, 'Name', 1, '2021-03-25 09:47:29', '0'),
(186, 1, 3, 'Name', 1, '2021-03-25 09:50:03', '0'),
(188, 1, 2, 'test', 0, '2021-03-25 09:51:40', '1'),
(189, 1, 2, 'test', 0, '2021-03-25 09:51:40', '1'),
(190, 1, 2, 'test', 0, '2021-03-25 09:51:42', '1'),
(191, 1, 2, 'test', 0, '2021-03-25 09:51:45', '1'),
(192, 1, 2, 'test', 0, '2021-03-25 09:51:47', '1'),
(193, 1, 2, 'test', 0, '2021-03-25 09:51:50', '1'),
(194, 1, 2, 'test', 0, '2021-03-25 09:51:52', '1'),
(195, 1, 2, 'test', 0, '2021-03-25 09:51:55', '1'),
(196, 1, 2, 'test', 0, '2021-03-25 09:51:57', '1'),
(197, 1, 2, 'test', 0, '2021-03-25 09:52:00', '1'),
(198, 1, 2, 'test', 0, '2021-03-25 09:52:02', '1'),
(199, 1, 2, 'test', 0, '2021-03-25 09:52:05', '1'),
(200, 1, 2, 'test', 0, '2021-03-25 09:52:07', '1'),
(201, 1, 2, 'test', 0, '2021-03-25 09:52:12', '1'),
(202, 1, 2, 'test', 0, '2021-03-25 09:52:12', '1'),
(203, 1, 2, 'test', 0, '2021-03-25 09:52:15', '1'),
(204, 1, 2, 'test', 0, '2021-03-25 09:52:17', '1'),
(205, 1, 2, 'test', 0, '2021-03-25 09:52:20', '1'),
(206, 1, 2, 'test', 0, '2021-03-25 09:52:22', '1'),
(207, 1, 2, 'test', 0, '2021-03-25 09:52:24', '1'),
(208, 1, 2, 'test', 0, '2021-03-25 09:52:25', '1'),
(209, 1, 2, 'test', 0, '2021-03-25 09:52:27', '1'),
(210, 1, 2, 'test', 0, '2021-03-25 09:52:30', '1'),
(211, 1, 2, 'test', 0, '2021-03-25 09:52:32', '1'),
(212, 1, 2, 'test', 0, '2021-03-25 09:52:33', '1'),
(213, 1, 2, 'test', 0, '2021-03-25 09:52:35', '1'),
(214, 1, 2, 'test', 0, '2021-03-25 09:52:37', '1'),
(215, 1, 2, 'test', 0, '2021-03-25 09:52:40', '1'),
(216, 1, 2, 'test', 0, '2021-03-25 09:52:42', '1'),
(217, 1, 2, 'test', 0, '2021-03-25 09:52:43', '1'),
(218, 1, 2, 'test', 0, '2021-03-25 09:52:44', '1'),
(219, 1, 2, 'test', 0, '2021-03-25 09:52:45', '1'),
(220, 1, 2, 'test', 0, '2021-03-25 09:52:47', '1'),
(221, 1, 2, 'test', 0, '2021-03-25 09:52:50', '1'),
(222, 1, 2, 'test', 0, '2021-03-25 09:52:51', '1'),
(223, 1, 2, 'test', 0, '2021-03-25 09:52:52', '1'),
(224, 1, 2, 'test', 0, '2021-03-25 09:52:55', '1'),
(225, 1, 2, 'test', 0, '2021-03-25 09:52:57', '1'),
(226, 1, 2, 'test', 0, '2021-03-25 09:53:00', '1'),
(227, 1, 2, 'test', 0, '2021-03-25 09:53:02', '1'),
(228, 1, 2, 'test', 0, '2021-03-25 09:53:02', '1'),
(229, 1, 2, 'test', 0, '2021-03-25 09:53:03', '1'),
(230, 1, 2, 'test', 0, '2021-03-25 09:53:05', '1'),
(231, 1, 2, 'test', 0, '2021-03-25 09:53:07', '1'),
(232, 1, 2, 'test', 0, '2021-03-25 09:53:10', '1'),
(233, 1, 2, 'test', 0, '2021-03-25 09:53:12', '1'),
(234, 1, 2, 'test', 0, '2021-03-25 09:53:15', '1'),
(235, 1, 2, 'test', 0, '2021-03-25 09:53:17', '1'),
(236, 1, 2, 'test', 0, '2021-03-25 09:53:20', '1'),
(237, 1, 2, 'test', 0, '2021-03-25 09:53:22', '1'),
(238, 1, 2, 'test', 0, '2021-03-25 09:53:25', '1'),
(239, 1, 2, 'test', 0, '2021-03-25 09:53:27', '1'),
(240, 1, 2, 'test', 0, '2021-03-25 09:53:30', '1'),
(241, 1, 2, 'test', 0, '2021-03-25 09:53:32', '1'),
(242, 1, 2, 'test', 0, '2021-03-25 09:53:35', '1'),
(243, 1, 2, 'test', 0, '2021-03-25 09:53:37', '1'),
(244, 1, 2, 'test', 0, '2021-03-25 09:53:39', '1'),
(245, 1, 2, 'test', 0, '2021-03-25 09:53:40', '1'),
(246, 1, 2, 'test', 0, '2021-03-25 09:53:42', '1'),
(247, 1, 2, 'test', 0, '2021-03-25 09:53:45', '1'),
(253, 1, 2, 'Name', 1, '2021-03-25 10:06:22', '0'),
(254, 1, 4, 'Name', 1, '2021-03-25 10:07:02', '0'),
(255, 1, 4, 'videodosyası yüklendi', 3, '2021-03-29 14:33:19', '0'),
(256, 1, 2, 'Name', 1, '2021-03-29 14:33:32', '0'),
(257, 1, 2, 'Nameeyd', 1, '2021-03-29 14:33:36', '0'),
(258, 1, 4, 'Namelbşb', 1, '2021-04-07 12:22:14', '0'),
(259, 1, 4, 'Namelbşb', 1, '2021-04-07 12:22:15', '0'),
(260, 1, 3, 'Name', 1, '2021-04-07 12:22:21', '0'),
(261, 1, 3, 'Nameııgıg', 1, '2021-04-07 12:22:26', '0'),
(262, 1, 2, 'Name', 1, '2021-04-14 13:21:58', '0'),
(263, 1, 2, 'Nameteast', 1, '2021-05-15 14:52:47', '0'),
(264, 1, 3, 'Name', 1, '2021-05-15 14:58:11', '0'),
(265, 1, 2, 'test', 1, '2021-05-15 14:59:02', '0'),
(266, 1, 4, 'test', 1, '2021-05-15 14:59:19', '0'),
(267, 1, 2, 'Name123', 1, '2021-05-15 15:02:53', '0'),
(268, 1, 4, 'yeni ama eski', 1, '2021-05-15 15:03:20', '0'),
(269, 1, 2, 'Name', 1, '2021-05-15 15:05:43', '0'),
(270, 1, 2, 'yeni mesaj', 1, '2021-05-15 15:06:41', '0'),
(271, 1, 3, 'asilyeni mesaj', 1, '2021-05-15 15:07:05', '0'),
(272, 1, 2, 'Name', 1, '2021-05-15 15:16:15', '0'),
(273, 2, 1, 'servis Uzerinden Gelen', 1, '2021-05-15 15:17:36', '0'),
(274, 1, 2, 'fotografdosyası yüklendi', 2, '2021-06-13 14:23:35', '0'),
(275, 1, 3, 'naber lan', 1, '2021-06-13 14:26:55', '0'),
(276, 1, 3, 'test', 1, '2021-06-14 16:22:20', '0'),
(277, 2, 1, 'selam', 1, '2021-06-14 16:42:20', '0'),
(278, 1, 2, 'fotografdosyası yüklendi', 2, '2021-07-16 11:46:55', '0'),
(279, 1, 2, 'fotografdosyası yüklendi', 2, '2021-07-16 11:47:21', '0'),
(280, 3, 1, 'aaa', 1, '2021-07-17 07:40:46', '1'),
(281, 3, 1, 'aaa', 1, '2021-07-17 07:43:30', '1'),
(282, 3, 1, 'aaa', 1, '2021-07-17 07:43:48', '1'),
(283, 3, 1, 'aaa', 1, '2021-07-17 07:44:06', '1'),
(284, 3, 1, 'aaa', 1, '2021-07-17 07:45:19', '1'),
(285, 1, 4, 'Name', 1, '2021-07-17 17:08:47', '0');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `messsagedata`
--

CREATE TABLE `messsagedata` (
  `messsageDataId` int(11) NOT NULL,
  `url` text NOT NULL,
  `messageId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `messsagedata`
--

INSERT INTO `messsagedata` (`messsageDataId`, `url`, `messageId`) VALUES
(3, '1614947439_download.png', 55),
(4, '1614947439_download.png', 56),
(5, '1614950228_IMG_20210305_113231.jpg', 57),
(6, '1614951461_IMG_20210305_113231.jpg', 58),
(7, '1614952183_IMG_20210305_113231.jpg', 59),
(8, '1615209144_VID_20210308_131203.mp4', 60),
(9, '1615210288_VID_20210308_131203.mp4', 61),
(10, '1615210947_VID_20210308_134200.mp4', 62),
(14, '1615478606_download.png', 145),
(15, '1615478627_download.jpg', 146),
(16, '1615480242_download.png', 152),
(17, '1615480517_download.png', 159),
(18, '1615480531_download.png', 160),
(19, '1615480700_download.jpg', 162),
(20, '1615546778_20200320_142450.mp4', 166),
(21, '1615985673_20200320_142450.mp4', 168),
(22, '1616576353_20200320_142450.mp4', 169),
(23, '1616666324_20200320_142450.mp4', 252),
(24, '1617028399_20200320_142450.mp4', 255),
(25, '1623594215_IMG_20210613_171829.jpg', 274),
(26, '1626436015_20210714_171723.jpg', 278),
(27, '1626436042_Screenshot_20210715-211616_Instagram.jpg', 279);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `playerfeatures`
--

CREATE TABLE `playerfeatures` (
  `playerFeaturesID` int(11) NOT NULL,
  `opsiyon1` varchar(20) NOT NULL,
  `opsiyon2` varchar(20) NOT NULL,
  `about` varchar(140) NOT NULL,
  `gameID` int(11) NOT NULL,
  `gUserName` varchar(20) NOT NULL,
  `source1` varchar(20) NOT NULL,
  `source2` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `playerfeatures`
--

INSERT INTO `playerfeatures` (`playerFeaturesID`, `opsiyon1`, `opsiyon2`, `about`, `gameID`, `gUserName`, `source1`, `source2`) VALUES
(1, 'Ryze', 'Mid', 'hakkında.', 1, 'Serphenix', '', ''),
(2, 'AK-47', 'Rifler', 'hakkında.', 2, 'Mecnun Ç.', '', ''),
(3, 'Axe', 'Orta Koridor', 'hakkında.', 3, 'Mecnun Ç.', '', ''),
(4, 'Yoru', 'Vandal', 'hakkında.', 6, 'Serphenix', '', ''),
(5, 'Zed', 'Orta Koridor', 'hakkında.', 1, 'Minx', '', ''),
(6, 'M4A4', 'Rifler', 'hakkında.', 2, 'Davy Jones', '', ''),
(7, 'Twitch', 'Ormancı', 'hakkında.', 1, 'Killgez', '', ''),
(8, 'Negev', 'Rifler', 'hakkında.', 2, 'Killgez', '', ''),
(9, 'Bloodseeker', 'Yan Koridor', 'hakkında.', 3, 'Halilgez', '', ''),
(10, 'Reyna', 'Phantom', 'hakkında.', 6, 'Killgez', '', ''),
(11, 'AWP', 'AWP Player', 'hakkında.', 2, '€G€', '', ''),
(13, 'Raze', 'Vandal', 'hakkında.', 6, 'HelloThere', '', ''),
(14, 'Bane', 'Kısmi Destek', 'hakkında.', 3, 'tequ', '', ''),
(15, 'AUG', 'Rifler', 'hakkında.', 2, 'Hasan Sabbah', '', ''),
(16, 'Thresh', 'Destek', 'hakkında.', 1, 'RâiderN', 'DIAMOND I', ''),
(47, 'Bard', 'Destek', 'hakknda.', 1, 'JackyMon', 'DIAMOND III', ''),
(55, 'Five-SeveN', 'The Supporter', 'a', 2, 'a', '-1', ''),
(56, 'Sage', 'Odin', 'hhhhhhhhhhhhhhhhhh', 6, 'aaaaa', '-1', '');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `postdatas`
--

CREATE TABLE `postdatas` (
  `postDataId` int(11) NOT NULL,
  `postId` int(11) NOT NULL,
  `filesId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `postdatas`
--

INSERT INTO `postdatas` (`postDataId`, `postId`, `filesId`) VALUES
(7, 15, 25),
(8, 15, 26),
(9, 15, 27),
(10, 16, 25),
(11, 16, 26),
(12, 16, 27),
(13, 17, 25),
(14, 17, 26),
(15, 17, 27),
(16, 18, 25),
(17, 18, 26),
(18, 18, 27),
(19, 19, 25),
(20, 19, 26),
(21, 19, 27),
(22, 20, 25),
(23, 20, 26),
(24, 20, 27),
(25, 21, 25),
(26, 21, 26),
(27, 21, 27),
(28, 22, 25),
(29, 22, 26),
(30, 22, 27),
(31, 47, 0),
(32, 47, 0),
(33, 47, 0),
(34, 61, 25),
(35, 61, 26),
(36, 61, 27),
(37, 62, 0),
(38, 62, 0),
(39, 62, 0),
(40, 63, 0),
(41, 63, 0),
(42, 63, 0),
(43, 64, 0),
(44, 64, 0),
(45, 64, 0),
(46, 65, 0),
(47, 65, 0),
(48, 65, 0),
(49, 66, 85),
(50, 67, 86),
(51, 68, 87),
(52, 69, 88),
(53, 69, 89),
(54, 70, 90),
(55, 70, 91),
(56, 71, 103),
(57, 71, 104),
(58, 71, 105),
(59, 71, 106),
(60, 71, 107),
(61, 71, 108),
(62, 71, 109),
(63, 71, 110),
(64, 71, 111),
(65, 71, 112),
(66, 72, 118),
(67, 74, 122),
(68, 76, 124),
(69, 82, 125),
(70, 82, 126),
(71, 82, 127);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `posts`
--

CREATE TABLE `posts` (
  `postId` int(11) NOT NULL,
  `value` text NOT NULL,
  `userId` int(11) NOT NULL,
  `creativeDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `dataIsNull` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `posts`
--

INSERT INTO `posts` (`postId`, `value`, `userId`, `creativeDate`, `dataIsNull`) VALUES
(40, 'yy', 1, '2021-03-19 10:39:39', 1),
(41, 'yyy', 1, '2021-03-19 10:47:06', 1),
(42, 'yyy', 2, '2021-03-19 10:48:21', 1),
(43, 'yyy', 2, '2021-03-19 10:48:38', 1),
(44, 'yyy', 3, '2021-03-19 10:48:44', 1),
(45, 'tesy', 3, '2021-03-19 11:33:24', 1),
(46, 'hele şükür', 2, '2021-03-19 11:33:40', 1),
(47, 'fotolu', 3, '2021-03-19 11:33:57', 0),
(48, '1', 2, '2021-03-19 11:35:13', 1),
(49, '1', 3, '2021-03-19 11:35:13', 1),
(50, '1', 3, '2021-03-19 11:35:18', 1),
(51, '1', 2, '2021-03-19 11:35:23', 1),
(52, '1', 3, '2021-03-19 11:35:28', 1),
(53, '1', 3, '2021-03-19 11:35:28', 1),
(54, '1', 2, '2021-03-19 11:35:31', 1),
(55, '1', 2, '2021-03-19 11:35:36', 1),
(56, '1', 3, '2021-03-19 11:36:29', 1),
(57, '1', 2, '2021-03-19 11:36:37', 1),
(58, '1', 2, '2021-03-19 11:37:12', 1),
(59, '1', 3, '2021-03-19 11:37:30', 1),
(60, '1', 3, '2021-03-19 11:38:33', 1),
(61, '1', 3, '2021-03-19 11:39:06', 0),
(62, '', 2, '2021-03-19 11:39:50', 0),
(63, '', 2, '2021-03-19 11:40:07', 0),
(64, '', 2, '2021-03-19 11:41:16', 0),
(65, '', 2, '2021-03-19 11:41:32', 0),
(66, '', 3, '2021-03-19 11:42:27', 0),
(67, '', 1, '2021-03-19 11:43:14', 0),
(68, 'gggy', 4, '2021-03-19 11:43:28', 0),
(69, '2goyo', 1, '2021-03-19 11:43:42', 0),
(70, '', 3, '2021-03-19 11:44:28', 0),
(71, 'test', 1, '2021-03-19 11:48:42', 0),
(72, 'test img', 3, '2021-03-22 10:08:31', 0),
(73, 'test', 1, '2021-03-24 08:25:32', 1),
(74, '', 2, '2021-03-24 08:57:18', 0),
(75, 'test', 1, '2021-03-25 10:32:06', 1),
(76, 'test', 2, '2021-03-25 10:32:15', 0),
(77, 'dd', 1, '2021-03-25 10:42:15', 1),
(78, 'asd', 4, '2021-03-25 10:44:26', 1),
(79, 'asd', 1, '2021-03-25 10:44:58', 1),
(80, 'asd', 3, '2021-03-25 10:45:28', 1),
(81, 'dd', 1, '2021-03-25 10:46:08', 1),
(82, 'djv', 2, '2021-03-25 10:47:00', 0),
(83, 'tttttuuhrrrfh', 1, '2021-04-15 11:10:25', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `name` text NOT NULL,
  `lastName` text NOT NULL,
  `email` text NOT NULL,
  `userName` text NOT NULL,
  `password` text NOT NULL,
  `phone` text NOT NULL,
  `profimg` text NOT NULL,
  `loginDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `birthDate` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`userID`, `name`, `lastName`, `email`, `userName`, `password`, `phone`, `profimg`, `loginDate`, `birthDate`) VALUES
(1, 'test1ad', 'test1soyad', 'test1@test.com', 'test1username', 'testpass', '5545698215', '', '2021-07-17 12:49:51', ''),
(2, 'test2ad', 'test2soyad', 'test2@test.com', 'test2username', 'testpass', '5545698215', 'prof.jpg', '2021-07-17 12:48:32', ''),
(3, 'test3', 'testlast3', 'test3@test.com', 'ustest3', 'pass123', '1326547895', 'prof.jpg', '2021-07-17 12:48:34', ''),
(4, 'test4', 'testlast4', 'email4@email.com', 'testttt4', 'pass123', '456654789521', 'prof.jpg', '2021-07-17 12:48:36', ''),
(5, 'test', 'test', 'passs@pass.com', 'test123', 'passs', '0554598019', '', '2021-04-02 16:13:47', '19/4/2021'),
(7, 'test', 'test', 'fatikyilmaz@hotmail.com', 'xxfetekxx', 'Fatih4242', '5545980194', '', '2021-05-15 14:50:09', '15/5/2021'),
(8, 'fatih', 'yilmaz', 'asd@hotmail.com', 'testuseri', 'Fatih4242', '5554589632', '', '2021-06-14 16:11:01', '9/6/2021'),
(9, 'asd', 'asd', 'd@d.com', 'asddsa', 'asdasdasd', '5555665525', '', '2021-06-14 16:12:00', '24/6/2021'),
(10, 'tweeesth', 'fgfth', 'aaa@aa.com', 'rrddg', 'Fatih4242', '2855428585', '', '2021-06-14 16:19:16', '16/6/2021'),
(11, 'zxc', 'zxc', 'zzzz@zzz.com', 'ttt', 'asdasd', '8555552555', '', '2021-06-14 16:23:01', '1/6/2021'),
(12, 'aaaa', 'aaaa', 'aaaaaaaa@a.com', 'asdasd', 'asdasd', '1231231231', '', '2021-06-14 16:26:53', '23/6/2021');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `bubbles`
--
ALTER TABLE `bubbles`
  ADD PRIMARY KEY (`boobleId`);

--
-- Tablo için indeksler `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`commentsId`);

--
-- Tablo için indeksler `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`fileId`);

--
-- Tablo için indeksler `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`friendId`);

--
-- Tablo için indeksler `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`gameID`);

--
-- Tablo için indeksler `gametypes`
--
ALTER TABLE `gametypes`
  ADD PRIMARY KEY (`gameTypeID`);

--
-- Tablo için indeksler `gtusers`
--
ALTER TABLE `gtusers`
  ADD PRIMARY KEY (`gtUserID`);

--
-- Tablo için indeksler `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`likeId`);

--
-- Tablo için indeksler `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`messagesId`);

--
-- Tablo için indeksler `messsagedata`
--
ALTER TABLE `messsagedata`
  ADD PRIMARY KEY (`messsageDataId`);

--
-- Tablo için indeksler `playerfeatures`
--
ALTER TABLE `playerfeatures`
  ADD PRIMARY KEY (`playerFeaturesID`);

--
-- Tablo için indeksler `postdatas`
--
ALTER TABLE `postdatas`
  ADD PRIMARY KEY (`postDataId`);

--
-- Tablo için indeksler `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`postId`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `bubbles`
--
ALTER TABLE `bubbles`
  MODIFY `boobleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Tablo için AUTO_INCREMENT değeri `comments`
--
ALTER TABLE `comments`
  MODIFY `commentsId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Tablo için AUTO_INCREMENT değeri `files`
--
ALTER TABLE `files`
  MODIFY `fileId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;

--
-- Tablo için AUTO_INCREMENT değeri `friends`
--
ALTER TABLE `friends`
  MODIFY `friendId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `likes`
--
ALTER TABLE `likes`
  MODIFY `likeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- Tablo için AUTO_INCREMENT değeri `messages`
--
ALTER TABLE `messages`
  MODIFY `messagesId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=286;

--
-- Tablo için AUTO_INCREMENT değeri `messsagedata`
--
ALTER TABLE `messsagedata`
  MODIFY `messsageDataId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Tablo için AUTO_INCREMENT değeri `postdatas`
--
ALTER TABLE `postdatas`
  MODIFY `postDataId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Tablo için AUTO_INCREMENT değeri `posts`
--
ALTER TABLE `posts`
  MODIFY `postId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
