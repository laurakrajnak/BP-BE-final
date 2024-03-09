-- Create tables

CREATE TABLE IF NOT EXISTS address (
                                       id SERIAL PRIMARY KEY,
                                       country VARCHAR(255),
    city VARCHAR(255),
    postal_code VARCHAR(10),
    street VARCHAR(255),
    house_number INT,
    is_deleted BOOLEAN
    );

CREATE TABLE IF NOT EXISTS app_user (
                                        id SERIAL PRIMARY KEY,
                                        email VARCHAR(255),
    login VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS account (
                                       id SERIAL PRIMARY KEY,
                                       user_id INT,
                                       name VARCHAR(255),
    is_deleted BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
    );

CREATE TABLE IF NOT EXISTS contact (
                                       id SERIAL PRIMARY KEY,
                                       account_id INT,
                                       name VARCHAR(255),
    address_id INT,
    registrational_id VARCHAR(50),
    tax_id VARCHAR(50),
    vat_id VARCHAR(50),
    account_type INT,
    is_deleted BOOLEAN,
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
    );

CREATE TABLE IF NOT EXISTS invoice (
                                       id SERIAL PRIMARY KEY,
                                       serial_number INT,
                                       account_id INT,
                                       issuer_id INT,
                                       recipient_id INT,
                                       date TIMESTAMP,
                                       vat NUMERIC(5,2),
    price NUMERIC(10,2),
    is_deleted BOOLEAN,
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (issuer_id) REFERENCES contact(id),
    FOREIGN KEY (recipient_id) REFERENCES contact(id)
    );

CREATE TABLE IF NOT EXISTS invoice_item (
                                            id SERIAL PRIMARY KEY,
                                            invoice_id INT,
                                            name VARCHAR(255),
    price NUMERIC(10,2),
    quantity INT,
    FOREIGN KEY (invoice_id) REFERENCES invoice(id)
    );

-- Insert initial data

INSERT INTO address (id, country, city, postal_code, street, house_number, is_deleted)
VALUES
    (11, 'Country1', 'City1', '12345', 'Street1', 1, false),
    (12, 'Country2', 'City2', '67890', 'Street2', 2, false),
    (13, 'Country3', 'City3', '54321', 'Street3', 3, false);

INSERT INTO app_user (id, email, login)
VALUES
    (11, 'email1@example.com', 'user1'),
    (12, 'email2@example.com', 'user2'),
    (13, 'email3@example.com', 'user3');

INSERT INTO account (id, user_id, name, is_deleted)
VALUES
    (11, 11, 'Account1', false),
    (12, 12, 'Account2', false),
    (13, 13, 'Account3', false);

INSERT INTO contact (id, account_id, name, address_id, registrational_id, tax_id, vat_id, account_type, is_deleted)
VALUES
    (11, 11, 'Contact1', 11, 'RegId1', 'TaxId1', 'VatId1', 0, false),
    (12, 12, 'Contact2', 12, 'RegId2', 'TaxId2', 'VatId2', 1, false),
    (13, 13, 'Contact3', 13, 'RegId3', 'TaxId3', 'VatId3', 2, false);

INSERT INTO invoice (id, serial_number, account_id, issuer_id, recipient_id, date, vat, price, is_deleted)
VALUES
    (11, 1011, 11, 11, 12, '2024-02-26T12:00:00Z', 20.00, 100.00, false),
    (12, 1012, 12, 12, 13, '2024-02-27T12:00:00Z', 30.00, 150.00, false),
    (13, 1013, 13, 13, 11, '2024-02-28T12:00:00Z', 40.00, 200.00, false);

INSERT INTO invoice_item (id, invoice_id, name, price, quantity)
VALUES
    (11, 11, 'Item1', 50.00, 2),
    (12, 11, 'Item2', 30.00, 1),
    (13, 12, 'Item3', 75.00, 1),
    (14, 13, 'Item4', 100.00, 1);
