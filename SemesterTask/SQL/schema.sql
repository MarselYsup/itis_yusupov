create table users(
    user_id serial primary key ,
    username varchar(100),
    first_name varchar(100),
    last_name varchar(100),
    password varchar(30),
    avatar_id int,
    foreign key (avatar_id) references avatars(avatar_id)
);
create table avatars(
    avatar_id serial primary key ,
    original_name varchar(255),
    storage_name varchar(255),
    size int,
    mimeType varchar(100)
);
create table studio(
    studio_id serial primary key ,
    name varchar(100),
    avatar_id int,
    foreign key (avatar_id) references avatars(avatar_id)
);
create table anime(
    anime_id serial primary key ,
    anime_title varchar(255),
    year int,
    episodes int,
    description varchar(10000),
    rating int,
    studio_id int,
    avatar_id int,
    foreign key (studio_id) references studio(studio_id),
    foreign key (avatar_id) references avatars(avatar_id)
);
create table genres(
    genre_id serial primary key ,
    genre_title varchar(255)
);
create table anime_genre(
    genre_id int,
    anime_id int,
    primary key (genre_id,anime_id)

);
create table user_anime(
   user_id int,
   anime_id int,
   status int check(status=1 OR status=2 OR status=3),
   user_rating int check ( user_rating>=0 AND user_rating<=10 ),
   primary key (user_id,anime_id)
);
create table admin(
    admin_id serial primary key ,
    user_id int,
    foreign key(user_id) references users(user_id)
);