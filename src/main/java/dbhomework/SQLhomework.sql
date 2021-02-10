--1
SELECT count(*)
FROM city.person;
--2
SELECT ROUND(AVG(city.person.age), 2) AS middle_age
FROM city.person;
--3
SELECT DISTINCT city.person."lastName"
FROM city.person
ORDER BY ("lastName");
--4
SELECT city.person."lastName", count(city.person."lastName") as count_lname
FROM city.person
GROUP BY(city.person."lastName")
ORDER BY (count_lname);
--5
SELECT person."lastName"
FROM city.person
WHERE city.person."lastName" LIKE '%b%';
--6
SELECT *
FROM city.person
WHERE person.id_street IS NULL;
--7
SELECT "firstName", "lastName", age, S.name AS strit_name
FROM city.person p
         INNER JOIN city.street S ON P.id_street = S.id
WHERE age < 18
  AND S.name = 'Pr. Pravdu';
--8
SELECT city.street.name, count(p.id_street = city.street.id) AS tenants
FROM city.street
         INNER JOIN city.person p ON p.id_street = city.street.id
GROUP BY (city.street.name)
ORDER BY (city.street.name);
--9
SELECT city.street.name
FROM city.street
WHERE name LIKE '______'
--10
SELECT city.street.name
FROM city.street
         INNER JOIN city.person ON street.id = city.person.id_street
GROUP BY city.street.name
HAVING COUNT(city.person.id) < 3;
