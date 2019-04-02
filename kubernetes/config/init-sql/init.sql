-- 这行不能少，否则会乱码：https://www.iszhouhua.com/docker-deploy-mysql-messy-code.html
SET NAMES utf8;
drop table if exists f_article;
create table f_article
(
  id         bigint primary key auto_increment comment 'id',
  title      varchar(56)  not null default '' comment '标题',
  summary    varchar(256) not null default '' comment '概述',
  issue_date datetime     not null comment '发布时间',
  content    varchar(1024)         default '' comment '内容'
) comment '文章表';

INSERT INTO f_article (id, title, summary, issue_date, content)
VALUES (1, '标题', '概要', '2019-03-15 12:06:42', '内容');