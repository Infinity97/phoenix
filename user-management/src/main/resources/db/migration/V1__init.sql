-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               PostgreSQL 14.2, compiled by Visual C++ build 1914, 64-bit
-- Server OS:
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table public.context_type
CREATE TABLE IF NOT EXISTS "context_type" (
	"id" INTEGER NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"type" VARCHAR(255) NOT NULL,
	PRIMARY KEY ("id"),
	UNIQUE ("type")
);

-- Dumping structure for table public.context
CREATE TABLE IF NOT EXISTS "context" (
	"id" VARCHAR(255) NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"name" VARCHAR(255) NOT NULL,
	"type_id" INTEGER NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkc8i2hwfxj173n0sqiwq5s8fg6" FOREIGN KEY ("type_id") REFERENCES "context_type" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);
/*!40000 ALTER TABLE "context_type" ENABLE KEYS */;
-- Dumping data for table public.context_type: -1 rows
/*!40000 ALTER TABLE "context_type" DISABLE KEYS */;
INSERT INTO "context_type" ("id", "created_at", "created_by", "deleted", "updated_at", "updated_by", "version", "type") VALUES
	(1, '2022-12-26 16:56:09', 'system', 'false', NULL, NULL, NULL, 'USER'),
	(2, '2022-12-27 19:46:10', 'system', 'false', NULL, 'system', NULL, 'SYSTEM');

-- Dumping data for table public.context: -1 rows
/*!40000 ALTER TABLE "context" DISABLE KEYS */;
INSERT INTO "context" ("id", "created_at", "created_by", "deleted", "updated_at", "updated_by", "version", "name", "type_id") VALUES
	('f11ac48f-6b8d-470e-947f-ae14e7687t59', '2022-12-26 16:56:09', 'system', 'false', NULL, NULL, NULL, 'f11ac48f-6b8d-470e-947f-ae14e76877f9', 2),
	('40289d6d855801fa01855805ff3b0002', '2022-12-28 14:47:46.255528', NULL, 'false', '2022-12-28 14:47:46.255528', NULL, 0, '40289d6d855801fa01855805ff170001', 1);
/*!40000 ALTER TABLE "context" ENABLE KEYS */;

-- Dumping data for table public.address: -1 rows
/*!40000 ALTER TABLE "address" DISABLE KEYS */;
/*!40000 ALTER TABLE "address" ENABLE KEYS */;

-- Dumping structure for table public.currency_mstr
CREATE TABLE IF NOT EXISTS "currency_mstr" (
	"id" INTEGER NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"currency_code" VARCHAR(100) NULL DEFAULT NULL,
	"currency_desc" VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY ("id")
);

-- Dumping structure for table public.country_mstr
CREATE TABLE IF NOT EXISTS "country_mstr" (
	"id" INTEGER NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"country_calling_code" VARCHAR(255) NULL DEFAULT NULL,
	"country_name" VARCHAR(100) NULL DEFAULT NULL,
	"currency_mstr_id" INTEGER NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkqy59dctedycl649lgo8br2sh2" FOREIGN KEY ("currency_mstr_id") REFERENCES "currency_mstr" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Dumping data for table public.country_mstr: -1 rows
/*!40000 ALTER TABLE "country_mstr" DISABLE KEYS */;
/*!40000 ALTER TABLE "country_mstr" ENABLE KEYS */;

-- Dumping data for table public.currency_mstr: -1 rows
/*!40000 ALTER TABLE "currency_mstr" DISABLE KEYS */;
/*!40000 ALTER TABLE "currency_mstr" ENABLE KEYS */;

-- Dumping structure for table public.users
CREATE TABLE IF NOT EXISTS "users" (
	"id" VARCHAR(255) NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"email_id" VARCHAR(255) NULL DEFAULT NULL,
	"first_name" VARCHAR(50) NULL DEFAULT NULL,
	"last_name" VARCHAR(255) NULL DEFAULT NULL,
	"login_type" VARCHAR(255) NULL DEFAULT NULL,
	"mobile_number" VARCHAR(255) NULL DEFAULT NULL,
	"password" VARCHAR(255) NULL DEFAULT NULL,
	"status" VARCHAR(255) NULL DEFAULT NULL,
	"username" VARCHAR(50) NULL DEFAULT NULL,
	"country_id" INTEGER NULL DEFAULT NULL,
	"education" VARCHAR(50) NULL DEFAULT NULL,
	"profession" VARCHAR(50) NULL DEFAULT NULL,
	"gender" VARCHAR(10) NULL DEFAULT NULL,
	"date_of_birth" TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	UNIQUE ("email_id"),
	UNIQUE ("mobile_number"),
	UNIQUE ("username"),
	CONSTRAINT "fkfhqdr1m4h00xuk5qstpbe6qqo" FOREIGN KEY ("country_id") REFERENCES "country_mstr" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Dumping structure for table public.address
CREATE TABLE IF NOT EXISTS "address" (
	"id" INTEGER NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"address_one" VARCHAR(255) NULL DEFAULT NULL,
	"address_two" VARCHAR(255) NULL DEFAULT NULL,
	"city" VARCHAR(255) NULL DEFAULT NULL,
	"country" VARCHAR(255) NULL DEFAULT NULL,
	"latitude" VARCHAR(255) NULL DEFAULT NULL,
	"longitude" VARCHAR(255) NULL DEFAULT NULL,
	"nickname" VARCHAR(255) NULL DEFAULT NULL,
	"pin_code" VARCHAR(7) NULL DEFAULT NULL,
	"state" VARCHAR(255) NULL DEFAULT NULL,
	"user_id" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fk6i66ijb8twgcqtetl8eeeed6v" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Dumping data for table public.users: -1 rows
/*!40000 ALTER TABLE "users" DISABLE KEYS */;
INSERT INTO "users" ("id", "created_at", "created_by", "deleted", "updated_at", "updated_by", "version", "email_id", "first_name", "last_name", "login_type", "mobile_number", "password", "status", "username", "country_id") VALUES
	('f11ac48f-6b8d-470e-947f-ae14e76877f9', '2022-12-26 16:56:09', 'SYSTEM', 'false', '2022-12-26 16:56:20', 'SYSTEM', NULL, 'admin@servapp.com', '', '', 'EMAIL', '', 'S3rV@Pp', 'ACTIVE', 'ServappAdmin', NULL),
	('40289d6d855801fa01855805ff170001', '2022-12-28 14:47:46.238251', NULL, 'false', '2022-12-28 14:47:46.238251', NULL, 0, NULL, NULL, NULL, NULL, '8218435723', NULL, 'ACTIVE', NULL, NULL);
/*!40000 ALTER TABLE "users" ENABLE KEYS */;

-- Dumping structure for table public.users_auth_session
CREATE TABLE IF NOT EXISTS "users_auth_session" (
	"id" VARCHAR(255) NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"expiry_timestamp" TIMESTAMP NULL DEFAULT NULL,
	"user_id" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkq1irp63f61js3pfxw1prgylm3" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Dumping data for table public.users_auth_session: -1 rows
/*!40000 ALTER TABLE "users_auth_session" DISABLE KEYS */;
INSERT INTO "users_auth_session" ("id", "created_at", "created_by", "deleted", "updated_at", "updated_by", "version", "expiry_timestamp", "user_id") VALUES
	('40289d6d855801fa01855805ff650004', '2022-12-28 14:47:46.263125', NULL, 'false', '2022-12-28 14:47:46.263125', NULL, 0, '2022-12-29 02:47:46.197087', '40289d6d855801fa01855805ff170001');
/*!40000 ALTER TABLE "users_auth_session" ENABLE KEYS */;

-- Dumping structure for table public.user_context
CREATE TABLE IF NOT EXISTS "user_context" (
	"id" VARCHAR(255) NOT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(50) NULL DEFAULT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(50) NULL DEFAULT NULL,
	"version" BIGINT NULL DEFAULT NULL,
	"context_id" VARCHAR(255) NULL DEFAULT NULL,
	"user_id" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fk8cmvnam216yp2b2y2qsqjccia" FOREIGN KEY ("context_id") REFERENCES "context" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "fkcgxbxfith07dfb3xp9sqxylkt" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Dumping data for table public.user_context: -1 rows
/*!40000 ALTER TABLE "user_context" DISABLE KEYS */;
INSERT INTO "user_context" ("id", "created_at", "created_by", "deleted", "updated_at", "updated_by", "version", "context_id", "user_id") VALUES
	('f11ac48f-6b8d-470e-947f-ae14e7612t59', NULL, 'system', 'false', NULL, 'system', NULL, 'f11ac48f-6b8d-470e-947f-ae14e7687t59', 'f11ac48f-6b8d-470e-947f-ae14e76877f9'),
	('40289d6d855801fa01855805ff540003', '2022-12-28 14:47:46.257382', NULL, 'false', '2022-12-28 14:47:46.257382', NULL, 0, '40289d6d855801fa01855805ff3b0002', '40289d6d855801fa01855805ff170001');
/*!40000 ALTER TABLE "user_context" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
