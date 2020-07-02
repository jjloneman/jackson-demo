drop all objects;

create table post (
    id bigint(20),
    title varchar(255),
    primary key (id)
);

create table tag (
    id bigint(20),
    name varchar(255),
    primary key (id)
);

create table post_tag (
    post_id bigint(20),
    tag_id bigint(20),
    primary key (post_id, tag_id)
);

-- Insert a sample post and tags

insert into post(id, title)
values (1, 'Post 1');

insert into tag(id, name)
values (1, 'foo');

insert into tag(id, name)
values (2, 'bar');

-- Add tags 'foo' and 'bar' to 'Post 1'

insert into post_tag(post_id, tag_id)
values (1, 1);

insert into post_tag(post_id, tag_id)
values (1, 2);
