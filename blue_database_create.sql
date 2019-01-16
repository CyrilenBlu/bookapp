create table admin (id bigint not null auto_increment, check_logged bit not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB
create table author (id bigint not null auto_increment, age integer not null, name varchar(255), primary key (id)) engine=InnoDB
create table author_books (author_id bigint not null, books_id bigint not null, primary key (author_id, books_id)) engine=InnoDB
create table book (id bigint not null auto_increment, ean bigint, description longtext, genre varchar(255), image longblob, title varchar(255), year integer not null, author_id bigint, publisher_id bigint, primary key (id)) engine=InnoDB
create table book_pages (book_id bigint not null, pages_id bigint not null, primary key (book_id, pages_id)) engine=InnoDB
create table pages (id bigint not null auto_increment, content longtext, page integer not null, title varchar(255), book_id bigint, primary key (id)) engine=InnoDB
create table publisher (id bigint not null auto_increment, city varchar(255), country varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table publisher_books (publisher_id bigint not null, books_id bigint not null, primary key (publisher_id, books_id)) engine=InnoDB
alter table author_books add constraint UK_fxksjqa1a5dnqf0egcdxlrcna unique (books_id)
alter table book_pages add constraint UK_8e5844eboi67sof02j0m9ntn0 unique (pages_id)
alter table publisher_books add constraint UK_gyr8g912ixa9bpjlafq3qkqa7 unique (books_id)
alter table author_books add constraint FKr514ej8rhei197wx3nrvp0qie foreign key (books_id) references book (id)
alter table author_books add constraint FKfvabqdr9njwv4khjqkf1pbmma foreign key (author_id) references author (id)
alter table book add constraint FKklnrv3weler2ftkweewlky958 foreign key (author_id) references author (id)
alter table book add constraint FKgtvt7p649s4x80y6f4842pnfq foreign key (publisher_id) references publisher (id)
alter table book_pages add constraint FK4j64lokjl86w4j8luawfp9k8u foreign key (pages_id) references pages (id)
alter table book_pages add constraint FKk20veuy4usvstmjguwvmotd36 foreign key (book_id) references book (id)
alter table pages add constraint FK95mtflxfml8f9r0s27qn6kxgx foreign key (book_id) references book (id)
alter table publisher_books add constraint FKmiu76amnv8pwaob4qxyk1gvpg foreign key (books_id) references book (id)
alter table publisher_books add constraint FK9b9tqc2fhqbipdrbatnraxmcr foreign key (publisher_id) references publisher (id)
