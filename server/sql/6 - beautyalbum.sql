create table beautyalbuminventory   (
    id bigint not null auto_increment,
    styleid int,
    slotid int,
    charid int,
    primary key (id),
    foreign key (charid) references characters(id)

);