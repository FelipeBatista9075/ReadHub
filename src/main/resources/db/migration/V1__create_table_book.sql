CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    editora VARCHAR(255),
    isbn VARCHAR(100),
    number_pages INT,
    descricao TEXT,
    formato VARCHAR(50),
    publicacao DATE,
    img TEXT
);