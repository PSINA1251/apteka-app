CREATE TABLE Лекарства (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Название NVARCHAR(255) COLLATE Cyrillic_General_CI_AS NOT NULL,
    Цена DECIMAL(10,2) NOT NULL,
    В_наличии BIT NOT NULL
);

CREATE TABLE Заказы (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ФИО NVARCHAR(255) COLLATE Cyrillic_General_CI_AS NOT NULL,
    Адрес NVARCHAR(255) COLLATE Cyrillic_General_CI_AS NOT NULL,
    Телефон NVARCHAR(50) NOT NULL,
    Дата DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE Состав_заказа (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ID_заказа INT NOT NULL,
    ID_лекарства INT NOT NULL,
    Количество INT NOT NULL,
    FOREIGN KEY (ID_заказа) REFERENCES Заказы(Id),
    FOREIGN KEY (ID_лекарства) REFERENCES Лекарства(Id)
);