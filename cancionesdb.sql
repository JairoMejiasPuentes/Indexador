-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-02-2021 a las 17:25:48
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cancionesdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canciones`
--

CREATE TABLE `canciones` (
  `titulo` varchar(150) NOT NULL,
  `artista` varchar(150) NOT NULL,
  `album` varchar(150) DEFAULT NULL,
  `ano` varchar(4) DEFAULT NULL,
  `genero` varchar(25) DEFAULT NULL,
  `duracion` smallint(6) DEFAULT NULL,
  `caratula` mediumblob DEFAULT NULL,
  `ruta` varchar(300) NOT NULL,
  `nombreFichero` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `canciones`
--

INSERT INTO `canciones` (`titulo`, `artista`, `album`, `ano`, `genero`, `duracion`, `caratula`, `ruta`, `nombreFichero`) VALUES
('07 Track 07', 'Rondò Veneziano', 'The Genius Of Mozart', NULL, 'Classic Rock', 475, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Musica classica - Mozart - Il flauto magico (Rondo Veneziano).mp3', 'Musica classica - Mozart - Il flauto magico (Rondo Veneziano).mp3'),
('5th Symphony 1st Movement', 'Beethoven', 'Telarc Collection, Volume 5: 17 Selections From The World\'s Finest Sounding Recordings', '1992', 'Other', 425, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Beethoven - 5th Symphony.mp3', 'Beethoven - 5th Symphony.mp3'),
('9th Symphony', 'asfasd', 'The Best of Beethoven', '1993', 'Other', 707, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Beethoven - 9th Symphony.mp3', 'Beethoven - 9th Symphony.mp3'),
('Boccherini  nº 6 Opus 30', 'BSO Master and Comander', 'BSO Master and Comander', '2003', 'Clásica', 564, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\BSO Master and Comander -14- Boccherini Nº6 Op30.mp3', 'BSO Master and Comander -14- Boccherini Nº6 Op30.mp3'),
('Boccherini La Musica Notturna Delle Strade Di Madrid, No. 6, Op. 30 [String Quintet In C]', 'Ira Davies, Christopher Gordon, Richard Tognetti', 'Master And Commander: The Far Side Of The World', '2003', 'Soundtrack', 564, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\BSO Master And Commander (Boccherini) - No 6, Op 30 (String Quintet In C).mp3', 'BSO Master And Commander (Boccherini) - No 6, Op 30 (String Quintet In C).mp3'),
('BSO El Piano', 'Michael Nyman', 'Pure Moods', '1997', 'Other', 146, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\bso - El piano.mp3', 'bso - El piano.mp3'),
('Canon for three violins and cello', 'THE SCOTTISH CHAMBER ORCHESTRA directed by JAIME LAREDO', 'String masterpieces', NULL, 'Other', 270, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\classical - Bach - Pachebel - Canon for Three Violins & Cello.mp3', 'classical - Bach - Pachebel - Canon for Three Violins & Cello.mp3'),
('Canon in D (Piano Solo)', 'Pachelbel', 'Lifescapes', '1996', 'Classical', 379, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Bach - Pachebel\'s Canon (Piano Solo).mp3', 'Bach - Pachebel\'s Canon (Piano Solo).mp3'),
('Canon Rocks (Covered)', 'Jerry C', NULL, '2005', 'Other', 321, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Johann Pachelbel - Cannon In D Major (Canon Rock - Guitar Play By Jerryc).mp3', 'Johann Pachelbel - Cannon In D Major (Canon Rock - Guitar Play By Jerryc).mp3'),
('desconocido', 'asfasd', NULL, NULL, 'Other', 857, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Beethoven - Bethoven - 7Th Symphony.mp3', 'Beethoven - Bethoven - 7Th Symphony.mp3'),
('Fur Elise', 'Beethoven', NULL, NULL, 'Other', 302, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Beethoven - Per Elisa (piano).mp3', 'Beethoven - Per Elisa (piano).mp3'),
('grande valse brillante (chopin)', 'bethoven chopin schubert mozart', 'le piano romantique', NULL, 'genre', 329, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\bethoven, chopin, schubert and mozart - grande valse brillante.mp3', 'bethoven, chopin, schubert and mozart - grande valse brillante.mp3'),
('Handel - Sarabande', 'Andrés Segovia', 'The Baroque Guitar', NULL, 'Classical Guitar', 240, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Classica Guitar- Andres Segovia - Haendel sarabande.mp3', 'Classica Guitar- Andres Segovia - Haendel sarabande.mp3'),
('Ode to joy 4th', 'Beethoven', 'The Best of Beethoven, Vol. 2', NULL, 'Other', 1420, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\(Musica Classica) Beethoven - Sinfonia n° 9 - Inno alla Gioia.mp3', '(Musica Classica) Beethoven - Sinfonia n° 9 - Inno alla Gioia.mp3'),
('Prelude (as used in the film Master and Commander: The Far Side of the', 'BSO - OST', 'Master and commander - The far side of the world', '2003', 'Other', 148, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Bach - Cello Suite No 1.1 - Prelude (Master And Commander).mp3', 'Bach - Cello Suite No 1.1 - Prelude (Master And Commander).mp3'),
('Requiem', 'Wolfgang Amadeus Mozart', 'Amadeus Soundtrack', NULL, 'Classical', 668, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Mozart - Requiem (Amadeus Soundtrack).mp3', 'Mozart - Requiem (Amadeus Soundtrack).mp3'),
('Sarabande [From Barry Lyndon]', 'City of Prague Philharmonic Orchestra', '2001: Music From the Films of Stanley Kubrick', '2005', 'Easy Listening', 250, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Classical - Haendel - Sarabande (Barry Lindon).mp3', 'Classical - Haendel - Sarabande (Barry Lindon).mp3'),
('Sicilian mandolin', 'BSO El Padrino', 'The Godfather', '', 'Soundtrack', 14, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\BSO El Padrino (04) - Sicilian mandolin (Clasica).mp3', 'BSO El Padrino (04) - Sicilian mandolin (Clasica).mp3'),
('Sonata Claro de Luna', 'Bethoven', 'Music Of The elements', '1991', 'Other', 52, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\beethoven - Sonata Claro de Luna.mp3', 'beethoven - Sonata Claro de Luna.mp3'),
('Symphony No. 40', 'Mozart', 'Mozart in the Morning', '2000', 'Other', 460, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\classica Mozart - Symphony No. 40.mp3', 'classica Mozart - Symphony No. 40.mp3'),
('Vals - El Lago de los Cisnes', 'Tchaikovsky', 'Tchaikovsky Forever', NULL, 'Classical', 425, NULL, 'C:\\Users\\Fran\\Desktop\\clasica\\Tchaikovsky - Vals De El Lago De Los Cisnes.mp3', 'Tchaikovsky - Vals De El Lago De Los Cisnes.mp3');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `canciones`
--
ALTER TABLE `canciones`
  ADD PRIMARY KEY (`titulo`,`artista`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
