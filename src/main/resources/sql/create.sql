CREATE TABLE tags
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  tagName STRING,
  tagEnName STRING
);
CREATE UNIQUE INDEX tag_tagEnName_uindex ON tags (tagEnName)CREATE TABLE posts
(
id INTEGER PRIMARY KEY AUTOINCREMENT,
  title STRING,
content TEXT,
time DATETIME,
  author STRING
);
CREATE UNIQUE INDEX posts_title_uindex ON posts (title)