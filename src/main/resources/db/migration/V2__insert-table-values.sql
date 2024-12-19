-- Inserir dados na tabela event_manager
INSERT INTO event_manager (is_email_verified, person_role, cpf_number, email, name, password, phone)
VALUES
    (true, 1, '12345678901', 'manager1@example.com', 'Manager One', '$2a$10$eImiTXuWVxfM37uY4JANjQe6.6nP3HtT4YRWHL8e60PHjlk7p8nZe', '555-1234'),
    (true, 1, '98765432100', 'manager2@example.com', 'Manager Two', '$2a$10$7QyzU5tG3.SBtS2W0Cm.dOV6/38CFA.I0PiD1.w3gAPEN8v7AsRxC', '555-5678');

-- Inserir dados na tabela event_participant
INSERT INTO event_participant (is_email_verified, person_role, cpf_number, email, name, password, phone)
VALUES
    (true, 2, '12345098765', 'participant1@example.com', 'Participant One', '$2a$10$aXq7zDg6QC.5CzVfs/OIaeG6QGghJCRBz9Om/jz6lR8zg.L3yFBmi', '555-1111'),
    (true, 2, '56789012345', 'participant2@example.com', 'Participant Two', '$2a$10$gA4yKbF9QglBWWUNF.jMUuMb23yzKiCew/AV/uLPc8qHmg8WJPGMa', '555-2222');

-- Inserir dados na tabela main_event_type
INSERT INTO main_event_type (name)
VALUES
    ('Conference'),
    ('Workshop');

-- Inserir dados na tabela main_event
INSERT INTO main_event (registration_price, end_date_time, event_manager_id, main_event_type_id, start_date_time, address, title)
VALUES
    (100.00, '2024-12-31 18:00:00', 1, 1, '2024-12-30 09:00:00', '123 Main St', 'Tech Conference 2024'),
    (50.00, '2024-12-25 17:00:00', 2, 2, '2024-12-25 10:00:00', '456 Elm St', 'AI Workshop');

-- Inserir dados na tabela main_event_action
INSERT INTO main_event_action (quantity_vacancies, registration_price, end_date_time, event_manager_id, main_event_id, start_date_time, address, title)
VALUES
    (100, 20.00, '2024-12-30 17:00:00', 1, 1, '2024-12-30 14:00:00', '123 Main St', 'Keynote Speech'),
    (50, 10.00, '2024-12-25 16:00:00', 2, 2, '2024-12-25 13:00:00', '456 Elm St', 'Hands-on AI Session');
