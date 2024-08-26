-- Criação da tabela 'posts'
CREATE TABLE posts (
   id BIGINT AUTO_INCREMENT ,
   text VARCHAR(255),
   created_at datetime,
   PRIMARY KEY (id)
);

-- Inserção de registros de exemplo na tabela 'posts'
INSERT INTO posts (text, created_at) VALUES ('Hello, world!', '2024-08-26 10:00:00');
INSERT INTO posts (text, created_at) VALUES ('Learning SQL with H2 Database.', '2024-08-26 10:30:00');
INSERT INTO posts (text, created_at) VALUES ('Spring Boot makes it easy to create standalone applications.', '2024-08-26 11:00:00');
INSERT INTO posts (text, created_at) VALUES ('H2 Database is a great tool for development and testing.', '2024-08-26 11:30:00');
INSERT INTO posts (text, created_at) VALUES ('This is the final example post for today.', '2024-08-26 12:00:00');