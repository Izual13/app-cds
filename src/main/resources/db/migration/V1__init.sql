create table stub
(
    ID          varchar(100) not null,
    json_string varchar(100) not null
);

INSERT INTO stub
VALUES ('1', '{}');
INSERT INTO stub
VALUES ('2', '{"1":"2"}');