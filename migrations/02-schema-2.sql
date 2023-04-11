--liquibase formatted sql

--changeset nyaninyayo:table-chat

create table chat(
    id bigserial primary key,
    name text
)

--rollback DROP TABLE "link";