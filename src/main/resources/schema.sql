-- 创建数据库
DROP DATABASE IF EXISTS hotel;
CREATE DATABASE hotel;

-- 使用数据库
USE hotel;

-- 1房间类型表
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

-- 2房间表
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

-- 3会员表
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

-- 4客户表
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

-- 5员工表
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

-- 6预订表
DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations
(
    reservation_id    INT                          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id       INT                          NOT NULL,
    rtype             VARCHAR(20) DEFAULT '大床房'  not null,
    room_id           CHAR(4),
    expected_checkin  DATE                         NOT NULL,
    expected_checkout DATE                         NOT NULL,
    rstatus           VARCHAR(20) DEFAULT '未完成'  NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    Foreign Key (rtype) references roomtype (rtype)
);

-- 7占用表
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

-- 8入住表
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
    rstatus        VARCHAR(20) DEFAULT '未完成'  not null,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

-- 9账单表
DROP TABLE IF EXISTS bills;
CREATE TABLE bills
(
    bill_id        INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    checkin_id     INT            NOT NULL,
    payment_time   DATETIME       NOT NULL,
    payment_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (checkin_id) REFERENCES checkins (checkin_id)
);

-- 10账号表
DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    employee_id INT           NOT NULL PRIMARY KEY,
    account     VARCHAR(20)   NOT NULL,
    password    VARCHAR(20)   NOT NULL,
    level       INT DEFAULT 2 NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (employee_id) ON DELETE CASCADE
);

-- 11同居表
DROP TABLE IF EXISTS cohabit;
CREATE TABLE cohabit
(
    occupancy_id INT NOT NULL,
    customer_id  INT NOT NULL,
    FOREIGN KEY (occupancy_id) REFERENCES occupancies (occupancy_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE,
    PRIMARY KEY (occupancy_id, customer_id)
);

-- 12账号表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    customer_id INT           NOT NULL PRIMARY KEY,
    account     VARCHAR(20)   NOT NULL,
    password    VARCHAR(20)   NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE
);