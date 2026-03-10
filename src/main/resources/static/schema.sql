CREATE DATABASE  IF NOT EXISTS `openbankx` 
USE `openbankx`;
CREATE TABLE `users` (
  `UserID` bigint NOT NULL AUTO_INCREMENT,
  `Phone` varchar(15) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Role` enum('Admin','Customer','Operations','TPP') NOT NULL,
  `Status` enum('Active','Locked') NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UK7hj3i2swwdpa1ytrutvcdb9qp` (`Phone`),
  UNIQUE KEY `UKc0vefmf8kda6kju4ce7foid9n` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `authclient` (
  `ClientID` bigint NOT NULL AUTO_INCREMENT,
  `TPPAppID` bigint NOT NULL,
  `ScopesAllowed` varchar(1000) NOT NULL,
  `RedirectURIs` varchar(2000) NOT NULL,
  `ClientType` enum('Confidential','Public') NOT NULL,
  `Status` enum('Active','Revoked') NOT NULL,
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `token` (
  `ClientID` bigint NOT NULL,
  `ExpiresAt` datetime(6) NOT NULL,
  `IssuedAt` datetime(6) NOT NULL,
  `TokenID` bigint NOT NULL AUTO_INCREMENT,
  `UserID` bigint NOT NULL,
  `Scope` varchar(255) NOT NULL,
  `Status` enum('Active','Revoked') NOT NULL,
  `TokenType` enum('Access','Refresh') NOT NULL,
  PRIMARY KEY (`TokenID`),
  KEY `FK_Token_AuthClient` (`ClientID`),
  KEY `FK_Token_User` (`UserID`),
  CONSTRAINT `FK_Token_AuthClient` FOREIGN KEY (`ClientID`) REFERENCES `authclient` (`ClientID`),
  CONSTRAINT `FK_Token_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `scaevent` (
  `EventTime` datetime(6) NOT NULL,
  `SCAEventID` bigint NOT NULL AUTO_INCREMENT,
  `UserID` bigint NOT NULL,
  `ReferenceID` varchar(100) NOT NULL,
  `Method` enum('App','Device','OTP') NOT NULL,
  `Result` enum('Fail','Pass') NOT NULL,
  PRIMARY KEY (`SCAEventID`),
  KEY `FK_SCAEvent_User` (`UserID`),
  CONSTRAINT `FK_SCAEvent_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;