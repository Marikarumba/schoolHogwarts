
SELECT s.name, s.age FROM student AS s
                              LEFT JOIN faculty f ON s.faculty_id = f.id;

SELECT s.* FROM student AS s
                    INNER JOIN avatar AS a on s.id = a.student_id;