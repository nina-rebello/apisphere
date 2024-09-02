ALTER TABLE users
ADD COLUMN avatar VARCHAR(255);

UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Joao+Carlos' WHERE id = 1;
UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Maria+Silva' WHERE id = 2;
UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Carlos+Souza' WHERE id = 3;
UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Ana+Paula' WHERE id = 4;
UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Pedro+Santos' WHERE id = 5;