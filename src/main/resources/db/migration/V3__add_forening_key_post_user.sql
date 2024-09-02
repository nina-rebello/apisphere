ALTER TABLE posts
    ADD COLUMN user_id BIGINT;

ALTER TABLE posts
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES users(id);

INSERT INTO users (name, bio, email, password, created_at, updated_at) VALUES
('João Carlos Lima', 'Desenvolvedor Java', 'joao@fiap.com.br', '$2a$13$/a8Q8gtgqqMNpdTiAucAgu0eoyz0aHJ1eK6EJ8XtWXpY6yzdhISgK', '2024-08-25 10:00:00', '2024-08-25 10:00:00'),
('Maria Silva', 'Engenheira de Software', 'maria.silva@example.com', '$2a$13$/a8Q8gtgqqMNpdTiAucAgu0eoyz0aHJ1eK6EJ8XtWXpY6yzdhISgK', '2024-08-25 11:00:00', '2024-08-25 11:00:00'),
('Carlos Souza', 'Analista de Sistemas', 'carlos.souza@example.com', '$2a$13$/a8Q8gtgqqMNpdTiAucAgu0eoyz0aHJ1eK6EJ8XtWXpY6yzdhISgK', '2024-08-25 12:00:00', '2024-08-25 12:00:00'),
('Ana Paula', 'Desenvolvedora Frontend', 'ana.paula@example.com', '$2a$13$/a8Q8gtgqqMNpdTiAucAgu0eoyz0aHJ1eK6EJ8XtWXpY6yzdhISgK', '2024-08-25 13:00:00', '2024-08-25 13:00:00'),
('Pedro Santos', 'Administrador de Banco de Dados', 'pedro.santos@example.com', '$2a$13$/a8Q8gtgqqMNpdTiAucAgu0eoyz0aHJ1eK6EJ8XtWXpY6yzdhISgK', '2024-08-25 14:00:00', '2024-08-25 14:00:00');

-- Update posts to relate with users
UPDATE posts SET user_id = 1 WHERE id = 1;
UPDATE posts SET user_id = 2 WHERE id = 2;
UPDATE posts SET user_id = 3 WHERE id = 3;
UPDATE posts SET user_id = 4 WHERE id = 4;
UPDATE posts SET user_id = 5 WHERE id = 5;

