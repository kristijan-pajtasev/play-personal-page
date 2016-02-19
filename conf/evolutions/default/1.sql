# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table posts (
  id                        bigserial not null,
  title                     varchar(255),
  url_title                 varchar(255),
  post                      varchar(255),
  tags                      varchar(255),
  description               varchar(255),
  created                   timestamp,
  published                 timestamp,
  constraint uq_posts_url_title unique (url_title),
  constraint pk_posts primary key (id))
;

create table sesions (
  id                        bigserial not null,
  uuid                      varchar(255),
  username                  varchar(255),
  constraint pk_sesions primary key (id))
;

create table users (
  id                        bigserial not null,
  email                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_users primary key (id))
;




# --- !Downs

drop table if exists posts cascade;

drop table if exists sesions cascade;

drop table if exists users cascade;

