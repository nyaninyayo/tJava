--liquibase formatted sql

--changeset zhamwan:table-chat

create table chat(
    id bigserial primary key,
    name text
)

--rollback DROP TABLE "link";