DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) NOT NULL DEFAULT '',
  `description` char(100) DEFAULT NULL,
  `price` int(7) NOT NULL,
  `pic` char(20) DEFAULT NULL,
  `uid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


INSERT INTO `product` VALUES (1,'小米10','小米10是个好手机',4000,'小米10.jpg',1001),(2,'Apple iPhone 11','iphone11是个贵手机',4888,'Apple iPhone 11 .jpg',1001),(3,'三星note9','三星note9是个炸弹机',3500,'三星note9.jpg',1001),(4,'锤子手机','锤子手机无敌，不接受反驳',1999,'chuizi.jpg',1002),(5,'OPPO Find X2','牛逼plus的oppo手机',9988,'oppo.jpg',1002),(6,'华为 HUAWEI nova 5z','华为手机最爱国',2500,'nova.jpg',1003);
