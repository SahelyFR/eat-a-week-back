INSERT INTO users (username, password, email, enabled) values
    ('springuser', '$2y$10$ge1LjcSG.HQKcxxbPjGBpeiONoEQCRN9Fpbqyhc5WofuIJR5R3hGG', 'toto@gmail.com', TRUE),
    ('admin', '$2y$10$wUzSkzQXhhaYNOnbr/dw.uOOqHHy/SaSa2TwK0jGER96qfsERSzIe', 'tata@gmail.com', TRUE);

INSERT INTO authorities (username, authority) values
    ('springuser', 'USER'),
    ('admin', 'ADMIN');
