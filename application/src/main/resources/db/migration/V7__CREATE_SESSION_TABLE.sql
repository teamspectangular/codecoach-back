create table codecoach.sessions
(
    session_id int generated by default as identity constraint sessions_pk primary key,
    subject varchar(255),
    date date not null,
    start_time time not null,
    location varchar(255) not null ,
    remarks varchar(255),
    status varchar(255) not null,

    coach_id int not null
        constraint app_user_coach_id_fk
            references codecoach.app_user (user_id)
            on delete set null,

    coachee_id int not null
        constraint app_user_coachee_id__fk
            references codecoach.app_user (user_id)
            on delete set null
);
