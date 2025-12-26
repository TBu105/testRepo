-- INSERT DATA INTO DEPARTMENTS TABLE
INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, LOCATION) VALUES
('Information Technology', 'Nha Trang'),
('Human Resources', 'Nha Trang'),
('Sales and Marketing', 'Nha Trang');

SELECT * FROM DEPARTMENTS;

-- INSERT MANAGERS INTO EMPLOYEES TABLE
INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, JOB_TITLE, SALARY, DEPARTMENT_ID, MANAGER_ID)
VALUES
('Bird', 'Girl', 'birdgirl@gmail.com', '0988556677', 'IT Manager', '950000', 2, NULL),
('Snow', 'Man', 'snowman@gmail.com', '0985267743', 'HR Manager', '880000', 3, NULL),
('Jax', 'Jax', 'jaxjax@gmail.com', '0946219425', 'Sales Manager', '920000', 4, NULL);

SELECT * FROM EMPLOYEES;

-- INSERT EMPLOYEES INTO EMPLOYEES TABLE GROUP 1 IT Department
INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, JOB_TITLE, SALARY, DEPARTMENT_ID, MANAGER_ID)
VALUES
('Vu', 'Vu', 'vuvu@gmail.com', '0962576421', 'Software Engineer', '750000', 2, 1),
('Lover', 'Man', 'loverman@gmail.com', '0963576423', 'System Administrator', '700000', 2, 1),
('Brownie', 'Maxi', 'browniemaxi@gmail.com', '0964576422', 'QA Engineer', '680000', 2, 1),
('Mickey', 'Mouse', 'mickeymouse@gmail.com', '0965576428', 'DevOps Engineer', '780000', 2, 1);

-- INSERT EMPLOYEES INTO EMPLOYEES TABLE GROUP 2 HR Department
INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, JOB_TITLE, SALARY, DEPARTMENT_ID, MANAGER_ID)
VALUES
('Phep', 'Mau', 'phepmau@gmail.com', '0917988256', 'HR Specialist', '600000', 3, 2),
('Ca', 'Go', 'cago@gmail.com', '0917788236', 'Recruiter', '620000', 3, 2),
('Co', 'Don', 'codon@gmail.com', '0937928456', 'Payroll Clerk', '550000', 3, 2),
('Ruc', 'Ro', 'rucro@gmail.com', '0957908156', 'Training Coordinator', '580000', 3, 2);

-- INSERT EMPLOYEES INTO EMPLOYEES TABLE GROUP 3 Sales Department
INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, JOB_TITLE, SALARY, DEPARTMENT_ID, MANAGER_ID)
VALUES
('Thuc', 'Giac', 'thucgiac@gmail.com', '0942801598', 'Sales Representative', '650000', 4, 3),
('The', 'Gioi', 'thegioi@gmail.com', '0943821598', 'Account Executive', '670000', 4, 3),
('Lady', 'Gaga', 'ladygaga@gmail.com', '0946807598', 'Sales Support', '500000', 4, 3),
('Bruno', 'Mars', 'brunomars@gmail.com', '0942001510', 'Marketing Specialist', '630000', 4, 3);

SELECT * FROM EMPLOYEES;