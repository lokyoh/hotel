-- 创建数据库
DROP DATABASE IF EXISTS hotel;
CREATE DATABASE hotel;

-- 使用数据库
USE hotel;

-- 房间类型表
DROP TABLE IF EXISTS roomtype;
CREATE TABLE roomtype
(
    `rtype`         VARCHAR(20)   NOT NULL DEFAULT '大床房' PRIMARY KEY,
    `area`          DECIMAL(5, 2) NULL,
    `number`        INT           NULL,
    `specification` VARCHAR(20)   NULL,
    `price`         DECIMAL(6, 2) NOT NULL CHECK ( price > 50 AND price < 10000),
    `rnumber`       INT           NULL
);
INSERT INTO roomtype (rtype, area, number, Specification, price, rnumber)
VALUES ('大床房', 20, 1, '1.5m', 150, 12),
       ('双人间', 25, 2, '1.2m', 200, 6),
       ('豪华大床房', 35, 1, '2m', 400, 12);

-- 房间表
DROP TABLE IF EXISTS room;
CREATE TABLE room
(
    room_id CHAR(4)     NOT NULL PRIMARY KEY,
    rtype   VARCHAR(20) NOT NULL DEFAULT '大床房',
    FOREIGN KEY (rtype) REFERENCES roomtype (rtype)
);
INSERT INTO room (room_id, rtype)
VALUES ('0101', '大床房'),
       ('0102', '大床房'),
       ('0103', '大床房'),
       ('0104', '大床房'),
       ('0105', '豪华大床房'),
       ('0106', '豪华大床房'),
       ('0107', '双人间'),
       ('0108', '双人间'),
       ('0109', '双人间'),
       ('0110', '双人间'),
       ('0201', '大床房'),
       ('0202', '大床房'),
       ('0203', '大床房'),
       ('0204', '大床房'),
       ('0205', '豪华大床房'),
       ('0206', '豪华大床房'),
       ('0207', '双人间'),
       ('0208', '双人间'),
       ('0209', '双人间'),
       ('0210', '双人间'),
       ('0301', '大床房'),
       ('0302', '大床房'),
       ('0303', '大床房'),
       ('0304', '大床房'),
       ('0305', '豪华大床房'),
       ('0306', '豪华大床房'),
       ('0307', '双人间'),
       ('0308', '双人间'),
       ('0309', '双人间'),
       ('0310', '双人间');

-- 会员表
DROP TABLE IF EXISTS members;
CREATE TABLE members
(
    membership     VARCHAR(20)    NOT NULL PRIMARY KEY,
    eligible_spent DECIMAL(10, 2) NOT NULL,
    discount       DECIMAL(3, 2)  NOT NULL
);
INSERT INTO members
VALUES ('普通', 0, 1),
       ('二级会员', 1000, 0.9),
       ('一级会员', 3000, 0.8);

-- 客户表
DROP TABLE IF EXISTS customers;
CREATE TABLE customers
(
    customer_id    INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    identification CHAR(18)    NOT NULL UNIQUE,
    cname          VARCHAR(20) NOT NULL,
    gender         VARCHAR(2),
    caddress       VARCHAR(40),
    phone          CHAR(11)    NOT NULL UNIQUE,
    membership     VARCHAR(20)    DEFAULT '普通',
    total_spent    DECIMAL(10, 2) DEFAULT 0,
    FOREIGN KEY (membership) REFERENCES members (membership)
);
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('330324200407010001', '王佳妮', '女', '甘肃省兰州市AA街道', '19817132000', '二级会员', 2200)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('330311200410010010', '黄振奇', '男', '江苏省扬州市AA街道', '18817132011', '二级会员', 1100)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('330111197806220999', '李志玲', '女', '四川省成都市AA街道', '18806872000', '普通', 200)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('320326197607110201', '陈诗诗', '女', '陕西省太原市AA街道', '19806874020', '一级会员', 3320)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('215687200102010079', '叶雨莎', '女', '浙江省温州市AA街道', '18893062000', '二级会员', 1200)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('221235200207090103', '王爱莎', '女', '浙江省台州市AA街道', '13945682620', '二级会员', 1800)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('330324199410120020', '孙慕钦', '男', '云南省丽江市AA街道', '19819452000', '一级会员', 4500)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('330324198406050521', '黄佳妮', '女', '云南省西双版纳AA街道', '19893130180', '普通', 500)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('210015197708010000', '张起灵', '男', '福建省厦门市AA街道', '16945687912', '二级会员', 1200)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('230064197807010210', '无邪', '男', '安徽省黄山市AA街道', '19826257132', '普通', 350)
;
INSERT INTO customers (identification, cname, gender, caddress, phone, membership, total_spent)
VALUES ('330046196504110035', '薛芳芳', '女', '甘肃省天水市AA街道', '17171071523', '二级会员', 1200);

-- 员工表
DROP TABLE IF EXISTS employees;
CREATE TABLE employees
(
    employee_id     INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    epassword       VARCHAR(20) NULL,
    ename           VARCHAR(20) NOT NULL,
    identification  CHAR(18)    NOT NULL UNIQUE,
    gender          CHAR(2)     NOT NULL,
    birth_date      DATE        NOT NULL,
    hire_date       DATE        NOT NULL,
    department_name VARCHAR(50) NOT NULL,
    contact_address VARCHAR(255),
    phone_number    VARCHAR(15) NOT NULL,
    email           VARCHAR(50) NOT NULL
);
INSERT INTO employees (epassword, ename, identification, gender, birth_date, hire_date, department_name,
                       contact_address, phone_number, email)
VALUES ('123456789', '圆圆', '331081200005091112', '女', '1988-2-3', '2004-3-3', '前台', '江苏省扬州市AA街道',
        '123456198', '3030333@qq.com'),
       ('1234567', '彬彬', '331004200302022211', '男', '1985-3-9', '2000-3-6', '采购部', '陕西省太原市AA街道',
        '123456188', '3030222@qq.com'),
       ('12345678', '菲菲', '330050199003230192', '女', '1978-12-3', '2002-8-9', '服务部', '浙江省温州市AA街道',
        '123456178', '3030111@qq.com');


-- 预订表
DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations
(
    reservation_id    INT                          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id       INT                          NOT NULL,
    rtype             VARCHAR(20) DEFAULT '大床房' not null,
    room_id           CHAR(4),
    number_of_guests  INT                          NOT NULL,
    expected_checkin  DATE                         NOT NULL,
    expected_checkout DATE                         NOT NULL,
    rstatus           VARCHAR(20) DEFAULT '未完成' not null,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    Foreign Key (rtype) references roomtype (rtype)
);
INSERT INTO reservations (customer_id, rtype, room_id, number_of_guests, expected_checkin, expected_checkout, rstatus)
VALUES (1, '大床房', '0101', 1, '2024-11-18', '2024-11-19', '已完成'),
       (2, '双人间', '0107', 2, '2024-11-22', '2024-11-23', '未完成'),
       (3, '大床房', '0102', 1, '2024-11-18', '2024-11-19', '已完成'),
       (4, '豪华大床房', '0105', 1, '2024-11-22', '2024-11-23', '未完成'),
       (5, '大床房', '0103', 1, '2024-11-19', '2024-11-20', '已完成'),
       (5, '豪华大床房', '0106', 1, '2024-11-24', '2024-11-25', '未完成'),
       (6, '大床房', '0103', 1, '2024-11-21', '2024-11-22', '未完成'),
       (7, '双人间', '0108', 1, '2024-11-18', '2024-11-19', '已完成'),
       (7, '大床房', '0101', 1, '2024-11-28', '2024-11-30', '未完成'),
       (8, '大床房', '0104', 1, '2024-11-19', '2024-11-20', '已完成');

-- 占用表
DROP TABLE IF EXISTS occupancies;
CREATE TABLE occupancies
(
    occupancy_id INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id  INT     NOT NULL,
    room_id      CHAR(4) NOT NULL,
    start_time   DATE    NOT NULL,
    end_time     DATE    NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE
);
INSERT INTO occupancies (customer_id, room_id, start_time, end_time)
VALUES (2, '0107', '2024-11-22', '2024-11-23'),
       (4, '0105', '2024-11-22', '2024-11-23'),
       (5, '0205', '2024-11-24', '2024-11-25'),
       (6, '0101', '2024-11-21', '2024-11-22'),
       (7, '0102', '2024-11-28', '2024-11-30');

-- 入住表
DROP TABLE IF EXISTS checkins;
CREATE TABLE checkins
(
    checkin_id     INT                          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id    INT                          NOT NULL,
    room_id        CHAR(4)                      NOT NULL,
    checkin_time   DATE                         NOT NULL,
    departure_time DATE                         NOT NULL,
    is_change      INT         DEFAULT 0,
    preid          CHAR(10)                     null,
    rstatus        VARCHAR(20) DEFAULT '未完成' not null,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);
INSERT INTO checkins (customer_id, room_id, checkin_time, departure_time, is_change, preid, rstatus)
VALUES (1, '0101', '2024-11-18', '2024-11-19', 0, null, '未完成'),
       (3, '0102', '2024-11-18', '2024-11-19', 0, null, '未完成'),
       (5, '0101', '2024-11-19', '2024-11-20', 0, null, '未完成'),
       (7, '0107', '2024-11-18', '2024-11-19', 0, null, '未完成'),
       (8, '0201', '2024-11-19', '2024-11-20', 0, null, '未完成');

-- 账单表
DROP TABLE IF EXISTS bills;
CREATE TABLE bills
(
    bill_id        INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    checkin_id     INT            NOT NULL,
    payment_time   DATETIME       NOT NULL,
    payment_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (checkin_id) REFERENCES checkins (checkin_id)
);
INSERT INTO bills (checkin_id, payment_time, payment_amount)
VALUES (1, '2024-11-19 10:20:00', 135),
       (2, '2024-11-19 11:07:50', 150),
       (3, '2024-11-20 11:20:00', 135),
       (4, '2024-11-19 12:00:00', 320),
       (5, '2024-11-20 10:30:00', 150);

-- 账号表
DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    employee_id INT           NOT NULL PRIMARY KEY,
    account     VARCHAR(20)   NOT NULL,
    password    VARCHAR(20)   NOT NULL,
    level       INT DEFAULT 2 not null
);
INSERT INTO account
VALUES (1, '987654', '123456789', 2),
       (2, '876543', '123456798', 1),
       (3, '765432', '323456798', 2);

-- 同居表
DROP TABLE IF EXISTS cohabit;
CREATE TABLE cohabit
(
    occupancy_id INT NOT NULL,
    customer_id  INT NOT NULL,
    FOREIGN KEY (occupancy_id) REFERENCES occupancies (occupancy_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE,
    PRIMARY KEY (occupancy_id, customer_id)
);
INSERT INTO cohabit
VALUES (1, 1);