DROP DATABASE IF EXISTS `yourcaryourway`;
CREATE DATABASE `yourcaryourway`;
USE `yourcaryourway`;



CREATE TABLE IF NOT EXISTS `Pays` (
   `pays_id` TINYINT AUTO_INCREMENT,
   `pays_nom` VARCHAR(255) NOT NULL,
   PRIMARY KEY(`pays_id`),
   UNIQUE(`pays_nom`)
);

CREATE TABLE IF NOT EXISTS `Ville` (
   `ville_id` INT AUTO_INCREMENT,
   `ville_nom` VARCHAR(255) NOT NULL,
   `pays_id` TINYINT NOT NULL,
   PRIMARY KEY(`ville_id`)
);

CREATE TABLE IF NOT EXISTS `Adresse` (
   `adresse_id` INT AUTO_INCREMENT,
   `adresse__champs_1` VARCHAR(100) NOT NULL,
   `adresse__champs_2` VARCHAR(100) NOT NULL,
   `adresse__champs_3` VARCHAR(100) NOT NULL,
   `adresse__champs_4` VARCHAR(100) NOT NULL,
   `adresse__champs_5` VARCHAR(100) NOT NULL,
   `ville_id` INT NOT NULL,
   PRIMARY KEY(`adresse_id`)
);

CREATE TABLE IF NOT EXISTS `Agence` (
   `agence_id` INT AUTO_INCREMENT,
   `agence_nom` VARCHAR(255) NOT NULL,
   `adresse_id` INT NOT NULL,
   PRIMARY KEY(`agence_id`),
   UNIQUE(`adresse_id`)
);

CREATE TABLE IF NOT EXISTS `Acriss` (
   `acriss_id` INT AUTO_INCREMENT,
   `acriss_code` CHAR(4) NOT NULL,
   PRIMARY KEY(`acriss_id`),
   UNIQUE(`acriss_code`)
);

CREATE TABLE IF NOT EXISTS `Trajet` (
   `trajet_id` INT AUTO_INCREMENT,
   `ville_id_depart` INT NOT NULL,
   `ville_id_arrivee` INT NOT NULL,
   PRIMARY KEY(`trajet_id`)
);

CREATE TABLE IF NOT EXISTS `PeriodeTemps` (
   `periode_temps_id` INT AUTO_INCREMENT,
   `periode_temps_date_debut` DATETIME NOT NULL,
   `periode_temps_date_fin` DATETIME NOT NULL,
   PRIMARY KEY(`periode_temps_id`)
);

CREATE TABLE IF NOT EXISTS `Chat` (
   `chat_id` INT AUTO_INCREMENT,
   `chat_date` DATETIME NOT NULL,
   PRIMARY KEY(`chat_id`)
);

CREATE TABLE IF NOT EXISTS `FilMessages` (
   `fil_messages_id` INT AUTO_INCREMENT,
   PRIMARY KEY(`fil_messages_id`)
);

CREATE TABLE IF NOT EXISTS `Marque` (
   `marque_id` TINYINT AUTO_INCREMENT,
   `marque_nom` VARCHAR(50) NOT NULL,
   PRIMARY KEY(`marque_id`),
   UNIQUE(`marque_nom`)
);

CREATE TABLE IF NOT EXISTS `Modele` (
   `modele_id` INT AUTO_INCREMENT,
   `modele_nom` VARCHAR(50) NOT NULL,
   `modele_couleur` VARCHAR(50) NOT NULL,
   `marque_id` TINYINT NOT NULL,
   PRIMARY KEY(`modele_id`)
);

CREATE TABLE IF NOT EXISTS `Utilisateur` (
   `utilisateur_id` INT AUTO_INCREMENT,
   `utilisateur_prenom` VARCHAR(255) NOT NULL,
   `utilisateur_nom` VARCHAR(255) NOT NULL,
   `utilisateur_email` VARCHAR(255) NOT NULL,
   `utilisateur_motdepasse` VARCHAR(255) NOT NULL,
   `utilisateur_date_naissance` DATE NOT NULL,
   `utilisateur_date_creation_compte` DATETIME,
   `utilisateur_is_stripe_registered` BOOLEAN NOT NULL,
   `adresse_id` INT NOT NULL,
   PRIMARY KEY(`utilisateur_id`),
   UNIQUE(`utilisateur_email`)
);

CREATE TABLE IF NOT EXISTS `Vehicule` (
   `vehicule_id` INT AUTO_INCREMENT,
   `vehicule_date_dispo_debut` DATETIME NOT NULL,
   `vehicule_date_dispo_fin` DATETIME,
   `vehicule_image_path` VARCHAR(255),
   `modele_id` INT NOT NULL,
   `acriss_id` INT NOT NULL,
   PRIMARY KEY(`vehicule_id`)
);

CREATE TABLE IF NOT EXISTS `Message` (
   `message_id` INT AUTO_INCREMENT,
   `message_texte` VARCHAR(255) NOT NULL,
   `message_date` DATETIME NOT NULL,
   `utilisateur_id` INT NOT NULL,
   `fil_messages_id` INT NOT NULL,
   PRIMARY KEY(`message_id`)
);

CREATE TABLE IF NOT EXISTS `Question` (
   `question_id` INT AUTO_INCREMENT,
   `question_texte` VARCHAR(255) NOT NULL,
   `utilisateur_id` INT NOT NULL,
   `chat_id` INT NOT NULL,
   PRIMARY KEY(`question_id`)
);

CREATE TABLE IF NOT EXISTS `OffreLocation` (
   `offre_location_id` INT AUTO_INCREMENT,
   `offre_location_tarif` REAL NOT NULL,
   `trajet_id` INT NOT NULL,
   `periode_temps_id` INT NOT NULL,
   `vehicule_id` INT NOT NULL,
   PRIMARY KEY(`offre_location_id`)
);

CREATE TABLE IF NOT EXISTS `Location` (
   `location_id` INT AUTO_INCREMENT,
   `offre_location_id` INT NOT NULL,
   `agence_id` INT NOT NULL,
   `utilisateur_id` INT NOT NULL,
   PRIMARY KEY(`location_id`),
   UNIQUE(`offre_location_id`)
);

CREATE TABLE IF NOT EXISTS `ReservationStatut` (
   `reservation_statut_id` INT AUTO_INCREMENT,
   `reservation_statut_date` DATETIME NOT NULL,
   `reservation_statut_type` VARCHAR(50) NOT NULL,
   `location_id` INT NOT NULL,
   PRIMARY KEY(`reservation_statut_id`)
);

CREATE TABLE IF NOT EXISTS `Paiement` (
   `paiement_id` INT AUTO_INCREMENT,
   `paiement_statut` VARCHAR(50) NOT NULL,
   `paiement_prix` REAL NOT NULL,
   `paiement_date` DATETIME NOT NULL,
   `location_id` INT NOT NULL,
   PRIMARY KEY(`paiement_id`),
   UNIQUE(`location_id`)
);

-- Ajout des clés étrangères
ALTER TABLE `Ville` ADD FOREIGN KEY (`pays_id`) REFERENCES `Pays` (`pays_id`);
ALTER TABLE `Adresse` ADD FOREIGN KEY (`ville_id`) REFERENCES `Ville` (`ville_id`);
ALTER TABLE `Agence` ADD FOREIGN KEY (`adresse_id`) REFERENCES `Adresse` (`adresse_id`);
ALTER TABLE `Trajet` ADD FOREIGN KEY (`ville_id_depart`) REFERENCES `Ville` (`ville_id`);
ALTER TABLE `Trajet` ADD FOREIGN KEY (`ville_id_arrivee`) REFERENCES `Ville` (`ville_id`);
ALTER TABLE `Modele` ADD FOREIGN KEY (`marque_id`) REFERENCES `Marque` (`marque_id`);
ALTER TABLE `Utilisateur` ADD FOREIGN KEY (`adresse_id`) REFERENCES `Adresse` (`adresse_id`);
ALTER TABLE `Vehicule` ADD FOREIGN KEY (`modele_id`) REFERENCES `Modele` (`modele_id`);
ALTER TABLE `Vehicule` ADD FOREIGN KEY (`acriss_id`) REFERENCES `Acriss` (`acriss_id`);
ALTER TABLE `Message` ADD FOREIGN KEY (`utilisateur_id`) REFERENCES `Utilisateur` (`utilisateur_id`);
ALTER TABLE `Message` ADD FOREIGN KEY (`fil_messages_id`) REFERENCES `FilMessages` (`fil_messages_id`);
ALTER TABLE `Question` ADD FOREIGN KEY (`utilisateur_id`) REFERENCES `Utilisateur` (`utilisateur_id`);
ALTER TABLE `Question` ADD FOREIGN KEY (`chat_id`) REFERENCES `Chat` (`chat_id`);
ALTER TABLE `OffreLocation` ADD FOREIGN KEY (`trajet_id`) REFERENCES `Trajet` (`trajet_id`);
ALTER TABLE `OffreLocation` ADD FOREIGN KEY (`periode_temps_id`) REFERENCES `PeriodeTemps` (`periode_temps_id`);
ALTER TABLE `OffreLocation` ADD FOREIGN KEY (`vehicule_id`) REFERENCES `Vehicule` (`vehicule_id`);
ALTER TABLE `Location` ADD FOREIGN KEY (`offre_location_id`) REFERENCES `OffreLocation` (`offre_location_id`);
ALTER TABLE `Location` ADD FOREIGN KEY (`agence_id`) REFERENCES `Agence` (`agence_id`);
ALTER TABLE `Location` ADD FOREIGN KEY (`utilisateur_id`) REFERENCES `Utilisateur` (`utilisateur_id`);
ALTER TABLE `ReservationStatut` ADD FOREIGN KEY (`location_id`) REFERENCES `Location` (`location_id`);
ALTER TABLE `Paiement` ADD FOREIGN KEY (`location_id`) REFERENCES `Location` (`location_id`);


-- Insertion de 2 pays
INSERT INTO `Pays` (`pays_nom`) VALUES ('France');
INSERT INTO `Pays` (`pays_nom`) VALUES ('Belgique');

-- Insertion de 10 villes
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Paris', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Lyon', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Marseille', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Lille', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Nice', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Toulouse', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Nantes', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Bordeaux', 1);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Bruxelles', 2);
INSERT INTO `Ville` (`ville_nom`, `pays_id`) VALUES ('Anvers', 2);

-- Insertion de 2 adresses
INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('123', 'Rue', 'de la Paix', 'Bâtiment A', '', 1);

INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('456', 'Avenue', 'Louise', '','', 9);

-- Insertion de 5 adresses supplémentaires
INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('789', 'Boulevard', 'Saint-Germain', 'Bâtiment C', '', 1);

INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('101', 'Place', 'de l\'Opéra', '', '', 1);

INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('202', 'Rue', 'Victor Hugo', '', '', 2);

INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('303', 'Avenue', 'des Champs-Élysées', '', '', 3);

INSERT INTO `Adresse` (`adresse__champs_1`, `adresse__champs_2`, `adresse__champs_3`, `adresse__champs_4`, `adresse__champs_5`, `ville_id`) 
VALUES ('404', 'Boulevard', 'Haussmann', '', '', 4);

-- Insertion de 3 utilisateurs
INSERT INTO `Utilisateur` (`utilisateur_prenom`, `utilisateur_nom`, `utilisateur_email`, `utilisateur_motdepasse`, `utilisateur_date_naissance`, `utilisateur_date_creation_compte`, `utilisateur_is_stripe_registered`, `adresse_id`) 
VALUES ('John', 'Doe', 'john.doe@example.com', 'password123', '1990-01-01', NOW(), true, 1);

INSERT INTO `Utilisateur` (`utilisateur_prenom`, `utilisateur_nom`, `utilisateur_email`, `utilisateur_motdepasse`, `utilisateur_date_naissance`, `utilisateur_date_creation_compte`, `utilisateur_is_stripe_registered`, `adresse_id`) 
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'password123', '1985-05-15', NOW(), true, 2);

INSERT INTO `Utilisateur` (`utilisateur_prenom`, `utilisateur_nom`, `utilisateur_email`, `utilisateur_motdepasse`, `utilisateur_date_naissance`, `utilisateur_date_creation_compte`, `utilisateur_is_stripe_registered`, `adresse_id`) 
VALUES ('Alice', 'Brown', 'alice.brown@example.com', 'password123', '1992-07-20', NOW(), true, 3);

-- Insertion de marques
INSERT INTO `Marque` (`marque_nom`) VALUES ('Toyota');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Honda');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Ford');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Chevrolet');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Nissan');
INSERT INTO `Marque` (`marque_nom`) VALUES ('BMW');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Mercedes');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Audi');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Volkswagen');
INSERT INTO `Marque` (`marque_nom`) VALUES ('Peugeot');

-- Insertion de modèles
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('Corolla', 'Red', 1);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('Civic', 'Blue', 2);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('Focus', 'Black', 3);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('Cruze', 'White', 4);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('Altima', 'Grey', 5);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('320i', 'Silver', 6);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('C-Class', 'Black', 7);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('A4', 'Blue', 8);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('Golf', 'Red', 9);
INSERT INTO `Modele` (`modele_nom`, `modele_couleur`, `marque_id`) VALUES ('308', 'White', 10);

-- Insertion de codes acriss
INSERT INTO `Acriss` (`acriss_code`) VALUES ('CDMR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('IDAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('SDAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('LDAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('PDAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('XCAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('LCAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('FCAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('PCAR');
INSERT INTO `Acriss` (`acriss_code`) VALUES ('SCAR');

-- Insertion de véhicules
INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-01 08:00:00', '2024-01-10 08:00:00', 1, 1);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-02 08:00:00', '2024-01-11 08:00:00', 2, 2);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-03 08:00:00', '2024-01-12 08:00:00', 3, 3);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-04 08:00:00', '2024-01-13 08:00:00', 4, 4);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-05 08:00:00', '2024-01-14 08:00:00', 5, 5);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-06 08:00:00', '2024-01-15 08:00:00', 6, 6);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-07 08:00:00', '2024-01-16 08:00:00', 7, 7);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-08 08:00:00', '2024-01-17 08:00:00', 8, 8);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-09 08:00:00', '2024-01-18 08:00:00', 9, 9);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-10 08:00:00', '2024-01-19 08:00:00', 10, 10);

-- Insertion de 10 trajets
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (1, 2);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (2, 3);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (3, 4);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (4, 5);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (5, 6);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (6, 7);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (7, 8);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (8, 9);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (9, 10);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (10, 1);


-- Insertion de 10 véhicules
INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-01 08:00:00', '2024-01-10 08:00:00', 1, 1);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-02 08:00:00', '2024-01-11 08:00:00', 2, 2);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-03 08:00:00', '2024-01-12 08:00:00', 3, 3);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-04 08:00:00', '2024-01-13 08:00:00', 4, 4);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-05 08:00:00', '2024-01-14 08:00:00', 5, 5);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-06 08:00:00', '2024-01-15 08:00:00', 6, 6);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-07 08:00:00', '2024-01-16 08:00:00', 7, 7);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-08 08:00:00', '2024-01-17 08:00:00', 8, 8);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-09 08:00:00', '2024-01-18 08:00:00', 9, 9);

INSERT INTO `Vehicule` (`vehicule_date_dispo_debut`, `vehicule_date_dispo_fin`, `modele_id`, `acriss_id`) 
VALUES ('2024-01-10 08:00:00', '2024-01-19 08:00:00', 10, 10);

-- Insertion de 10 trajets
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (1, 2);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (2, 3);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (3, 4);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (4, 5);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (5, 6);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (6, 7);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (7, 8);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (8, 9);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (9, 10);
INSERT INTO `Trajet` (`ville_id_depart`, `ville_id_arrivee`) VALUES (10, 1);


-- Insertion de 2 agences
INSERT INTO `Agence` (`agence_nom`, `adresse_id`) VALUES ('Agence Paris', 1);
INSERT INTO `Agence` (`agence_nom`, `adresse_id`) VALUES ('Agence Bruxelles', 2);

-- Insertion de 10 périodes de temps de location (d'une durée d'un jour)
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-01 08:00:00', '2024-01-02 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-03 08:00:00', '2024-01-04 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-05 08:00:00', '2024-01-06 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-07 08:00:00', '2024-01-08 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-09 08:00:00', '2024-01-10 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-11 08:00:00', '2024-01-12 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-13 08:00:00', '2024-01-14 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-15 08:00:00', '2024-01-16 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-17 08:00:00', '2024-01-18 08:00:00');
INSERT INTO `PeriodeTemps` (`periode_temps_date_debut`, `periode_temps_date_fin`) VALUES ('2024-01-19 08:00:00', '2024-01-20 08:00:00');


-- Conversion des tables au charset utf8mb4_unicode_ci
ALTER TABLE `Pays` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Ville` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Adresse` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Agence` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Acriss` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Trajet` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `PeriodeTemps` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Chat` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `FilMessages` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Marque` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Modele` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Utilisateur` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Vehicule` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Message` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Question` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `OffreLocation` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Location` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `ReservationStatut` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE `Paiement` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
