CREATE TABLE film
(
    id       SERIAL PRIMARY KEY,
    name     TEXT NOT NULL,
    duration INT  NOT NULL
);
CREATE UNIQUE INDEX ui_film_name ON film (name);

CREATE TABLE session
(
    id         SERIAL PRIMARY KEY,
    film_id    INT       NOT NULL,
    start_time TIMESTAMP NOT NULL,
    price      DECIMAL   NOT NULL,

    CONSTRAINT fk_session_movie FOREIGN KEY (film_id) REFERENCES film (id)
);
CREATE INDEX idx_session_film_id ON session (film_id);
CREATE INDEX idx_session_start_time ON session (start_time);

CREATE TABLE ticket
(
    id         SERIAL PRIMARY KEY,
    session_id INT NOT NULL,
    place      INT NOT NULL,

    CONSTRAINT fk_ticket_session_id FOREIGN KEY (session_id) REFERENCES session (id)
);
CREATE UNIQUE INDEX ui_ticket_session_id_place ON ticket (session_id, place);

INSERT INTO film (name, duration)
VALUES ('Movie_1', 120),
       ('Movie_2', 60),
       ('Movie_3', 90),
       ('Movie_4', 30),
       ('Movie_5', 60),
       ('Movie_6', 120);

INSERT INTO session (film_id, start_time, price)
VALUES ((SELECT id FROM film where name = 'Movie_1'), '2023-02-27 09:00:00', 10),
       ((SELECT id FROM film where name = 'Movie_2'), '2023-02-27 10:00:00', 10),
       ((SELECT id FROM film where name = 'Movie_3'), '2023-02-27 11:00:00', 15),
       ((SELECT id FROM film where name = 'Movie_4'), '2023-02-27 12:00:00', 20),
       ((SELECT id FROM film where name = 'Movie_5'), '2023-02-27 13:00:00', 20),
       ((SELECT id FROM film where name = 'Movie_1'), '2023-02-27 15:00:00', 20),
       ((SELECT id FROM film where name = 'Movie_2'), '2023-02-27 16:30:00', 20),
       ((SELECT id FROM film where name = 'Movie_3'), '2023-02-27 18:00:00', 25),
       ((SELECT id FROM film where name = 'Movie_4'), '2023-02-27 19:30:00', 50),
       ((SELECT id FROM film where name = 'Movie_5'), '2023-02-27 21:00:00', 50),
       ((SELECT id FROM film where name = 'Movie_6'), '2023-02-27 23:00:00', 50),
       ((SELECT id FROM film where name = 'Movie_1'), '2023-02-28 09:00:00', 20),
       ((SELECT id FROM film where name = 'Movie_2'), '2023-02-28 10:00:00', 20),
       ((SELECT id FROM film where name = 'Movie_3'), '2023-02-28 11:00:00', 30),
       ((SELECT id FROM film where name = 'Movie_4'), '2023-02-28 12:00:00', 40),
       ((SELECT id FROM film where name = 'Movie_5'), '2023-02-28 13:00:00', 40),
       ((SELECT id FROM film where name = 'Movie_6'), '2023-02-28 15:00:00', 40),
       ((SELECT id FROM film where name = 'Movie_1'), '2023-02-28 16:30:00', 40),
       ((SELECT id FROM film where name = 'Movie_2'), '2023-02-28 18:00:00', 50),
       ((SELECT id FROM film where name = 'Movie_3'), '2023-02-28 19:30:00', 100),
       ((SELECT id FROM film where name = 'Movie_4'), '2023-02-28 21:00:00', 100),
       ((SELECT id FROM film where name = 'Movie_5'), '2023-02-28 23:00:00', 100);

INSERT INTO ticket (session_id, place)
VALUES ((SELECT id FROM session where start_time = '2023-02-27 11:00:00'), 1),
       ((SELECT id FROM session where start_time = '2023-02-27 11:00:00'), 2),
       ((SELECT id FROM session where start_time = '2023-02-27 16:30:00'), 1),
       ((SELECT id FROM session where start_time = '2023-02-27 16:30:00'), 2),
       ((SELECT id FROM session where start_time = '2023-02-27 16:30:00'), 3),
       ((SELECT id FROM session where start_time = '2023-02-28 10:00:00'), 1),
       ((SELECT id FROM session where start_time = '2023-02-28 10:00:00'), 2),
       ((SELECT id FROM session where start_time = '2023-02-28 10:00:00'), 3),
       ((SELECT id FROM session where start_time = '2023-02-28 10:00:00'), 4),
       ((SELECT id FROM session where start_time = '2023-02-28 18:00:00'), 1),
       ((SELECT id FROM session where start_time = '2023-02-28 18:00:00'), 2),
       ((SELECT id FROM session where start_time = '2023-02-28 18:00:00'), 3),
       ((SELECT id FROM session where start_time = '2023-02-28 21:00:00'), 1),
       ((SELECT id FROM session where start_time = '2023-02-28 21:00:00'), 2),
       ((SELECT id FROM session where start_time = '2023-02-28 21:00:00'), 3),
       ((SELECT id FROM session where start_time = '2023-02-28 21:00:00'), 4);

-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

select f1.name, s1.start_time, f1.duration, f2.name, s2.start_time, f2.duration
from film f1
         join session s1 on f1.id = s1.film_id
         join session s2 on s2.start_time >= s1.start_time and
                            s1.start_time + (f1.duration * interval '1 minute') > s2.start_time
         join film f2 on s2.film_id = f2.id
where s1.id != s2.id;

-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

select *
from (select f1.name,
             s1.start_time,
             f1.duration,
             s2.start_time,
             s2.start_time - (s1.start_time + (f1.duration * interval '1 minute')) as break
      from film f1
               join session s1 on f1.id = s1.film_id
               join session s2
                    on s2.id =
                       (select s.id from session s where s1.start_time < s.start_time order by s.start_time limit 1)
      where extract(day from s1.start_time) = extract(day from s2.start_time)) s3
where break > (30 * interval '1 minute')
order by break desc;

-- список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс
-- и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

with result as (select f.name,
                       count(t.id)                                    as ticket_count,
                       cast(count(t.id) as double precision) /
                       cast(count(distinct s.id) as double precision) as avg_clients,
                       sum(s.price)                                   as price
                from film f
                         join session s on f.id = s.film_id
                         join ticket t on s.id = t.session_id
                group by f.name
                order by price desc)
select *
from result
union all
select 'Итого', sum(result.ticket_count), avg(result.avg_clients), sum(result.price)
from result;

-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

select '9 - 15' as time, count(t.id), sum(s.price)
from session s
         join ticket t on s.id = t.session_id
where extract(hour from start_time) >= 9
  and extract(hour from start_time) < 15
union all
select '15 - 18' as time, count(t.id), sum(s.price)
from session s
         join ticket t on s.id = t.session_id
where extract(hour from start_time) >= 15
  and extract(hour from start_time) < 18
union all
select '18 - 21' as time, count(t.id), sum(s.price)
from session s
         join ticket t on s.id = t.session_id
where extract(hour from start_time) >= 18
  and extract(hour from start_time) < 21
union all
select '21 - 00' as time, count(t.id), sum(s.price)
from session s
         join ticket t on s.id = t.session_id
where extract(hour from start_time) >= 21
  and extract(hour from start_time) < 00;