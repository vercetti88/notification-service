create table notification.code_verification
(
    id      bigserial    not null
    constraint code_verification_pkey
    primary key,
    email varchar(255) not null,
    verification_code varchar(255) not null
);

create table notification.notification_history
(
    id                  bigserial    not null
        constraint notification_history_pkey
            primary key,
    created_at          timestamp(6),
    email               varchar(255) not null,
    message             varchar(255) not null,
    verification_status varchar(255) not null,
    notification_type   varchar(255) not null,
    verification_code   varchar(255)
);

