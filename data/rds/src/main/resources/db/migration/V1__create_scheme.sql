CREATE TABLE `orders`
(
  `id`          bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `created_at`  datetime(6) NOT NULL,
  `modified_at` datetime(6) NOT NULL,
  `created_by`  varchar(50) NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
