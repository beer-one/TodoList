create table TODO_LIST (
       todo_list_no INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(50),
       content TEXT,
       start_ymdt DATETIME,
       end_ymdt DATETIME,
       finish_yn CHAR(1)
);