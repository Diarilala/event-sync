create table admin
(
    id            uuid primary key default gen_random_uuid() not null,
    first_name    varchar(255)                               not null,
    last_name     varchar(255),
    email         varchar(255),
    password_hash varchar(255)                               not null
);

create table event
(
    id          uuid primary key default gen_random_uuid() not null,
    title       varchar(50)                                not null,
    description text                                       not null,
    start_date  date                                       not null,
    end_date    date                                       not null,
    location    varchar(255)                               not null
);

create table room
(
    id   uuid primary key default gen_random_uuid() not null,
    name varchar(255)
);

create table session
(
    id          uuid primary key default gen_random_uuid() not null,
    title       varchar(50)                                not null,
    description text                                       not null,
    start_time  time                                       not null,
    end_time    time                                       not null,
    room_id     uuid references room(id),
    capacity    integer,
    event_id    uuid references event(id)
);