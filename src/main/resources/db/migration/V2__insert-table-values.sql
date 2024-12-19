-- Inserir dados na tabela event_manager
INSERT INTO event_manager (is_email_verified, person_role, cpf_number, email, name, password, phone)
VALUES
    (true, 0, '12345677901', 'admin', 'admin', '$2a$10$T5NvB0eRgaLY8iI/jMNO6urUVZWk1V8M1VsfBhX3lkhTDAuxQfFOe', '555-1234'), -- Senha: password123
    (true, 1, '12345678901', 'manager1@example.com', 'Manager One', '$2a$10$eImiTXuWVxfM37uY4JANjQe6.6nP3HtT4YRWHL8e60PHjlk7p8nZe', '555-1234'), -- Senha: password123
    (true, 1, '98765432100', 'manager2@example.com', 'Manager Two', '$2a$10$7QyzU5tG3.SBtS2W0Cm.dOV6/38CFA.I0PiD1.w3gAPEN8v7AsRxC', '555-5678'), -- Senha: admin2024
    (true, 1, '11223344556', 'manager3@example.com', 'Manager Three', '$2a$10$8fF8j6VkOeA7fWbPL3Oixu9WB.Z0OuqEPjdgF5ZOWdBodShI6aKyK', '555-9999'); -- Senha: securepass

-- Inserir dados na tabela event_participant
INSERT INTO event_participant (is_email_verified, person_role, cpf_number, email, name, password, phone)
VALUES
    (true, 2, '12345098765', 'participant1@example.com', 'Participant One', '$2a$10$aXq7zDg6QC.5CzVfs/OIaeG6QGghJCRBz9Om/jz6lR8zg.L3yFBmi', '555-1111'), -- Senha: welcome123
    (true, 2, '56789012345', 'participant2@example.com', 'Participant Two', '$2a$10$gA4yKbF9QglBWWUNF.jMUuMb23yzKiCew/AV/uLPc8qHmg8WJPGMa', '555-2222'), -- Senha: mypassword
    (true, 2, '67890123456', 'participant3@example.com', 'Participant Three', '$2a$10$9KmvWNU8yA7ZnXeQBVvBeuR1PZHcvdcDJWoc89qQGG1XaGlZ9U1lK', '555-3333'), -- Senha: secret321
    (true, 2, '78901234567', 'participant4@example.com', 'Participant Four', '$2a$10$FZOn8P2nTfC5nhBhjNQQVeHDt1N1yO.FNxYyXkbJcUNf2XZTQWmBW', '555-4444'); -- Senha: hello2024

-- Inserir dados na tabela main_event_type
INSERT INTO main_event_type (name)
VALUES
    ('Conference'),
    ('Workshop'),
    ('Webinar'),
    ('Seminar'),
    ('Networking Event');

-- Inserir dados na tabela main_event
INSERT INTO main_event (registration_price, end_date_time, event_manager_id, main_event_type_id, start_date_time, address, title)
VALUES
    (100.00, '2024-12-31 18:00:00', 1, 1, '2024-12-30 09:00:00', '123 Main St', 'Tech Conference 2024'),
    (50.00, '2024-12-25 17:00:00', 2, 2, '2024-12-25 10:00:00', '456 Elm St', 'AI Workshop'),
    (30.00, '2024-12-15 12:00:00', 3, 3, '2024-12-15 10:00:00', '789 Oak St', 'Virtual Leadership Webinar'),
    (75.00, '2024-11-20 16:00:00', 1, 4, '2024-11-20 13:00:00', '321 Pine St', 'Business Strategy Seminar'),
    (20.00, '2024-10-10 20:00:00', 2, 5, '2024-10-10 18:00:00', '654 Maple St', 'Entrepreneur Networking Night'),
    (150.00, '2025-01-10 18:00:00', 1, 1, '2025-01-09 09:00:00', '987 Cedar St', 'Future of Tech 2025'),
    (60.00, '2024-12-20 17:00:00', 2, 2, '2024-12-20 10:00:00', '159 Birch St', 'AI in Practice'),
    (35.00, '2025-01-15 12:00:00', 3, 3, '2025-01-15 10:00:00', '753 Spruce St', 'Innovation Webinar'),
    (80.00, '2025-02-10 16:00:00', 1, 4, '2025-02-10 13:00:00', '369 Willow St', 'Advanced Business Seminar'),
    (25.00, '2025-03-01 20:00:00', 2, 5, '2025-03-01 18:00:00', '852 Palm St', 'Startups Meetup');

-- Inserir dados na tabela main_event_action
INSERT INTO main_event_action (quantity_vacancies, registration_price, end_date_time, event_manager_id, main_event_id, start_date_time, address, title)
VALUES
    (100, 20.00, '2024-12-30 17:00:00', 1, 1, '2024-12-30 14:00:00', '123 Main St', 'Keynote Speech'),
    (50, 10.00, '2024-12-25 16:00:00', 2, 2, '2024-12-25 13:00:00', '456 Elm St', 'Hands-on AI Session'),
    (200, 5.00, '2024-12-15 11:30:00', 3, 3, '2024-12-15 11:00:00', '789 Oak St', 'Q&A with Experts'),
    (80, 15.00, '2024-11-20 15:30:00', 1, 4, '2024-11-20 14:30:00', '321 Pine St', 'Breakout Group Discussions'),
    (30, 5.00, '2024-10-10 19:30:00', 2, 5, '2024-10-10 19:00:00', '654 Maple St', 'Startup Pitches'),
    (150, 25.00, '2025-01-10 17:00:00', 1, 6, '2025-01-10 14:00:00', '987 Cedar St', 'Tech Trends'),
    (100, 20.00, '2024-12-20 16:00:00', 2, 7, '2024-12-20 13:00:00', '159 Birch St', 'AI Workshops'),
    (250, 10.00, '2025-01-15 11:30:00', 3, 8, '2025-01-15 11:00:00', '753 Spruce St', 'Innovators Panel'),
    (120, 15.00, '2025-02-10 15:30:00', 1, 9, '2025-02-10 14:30:00', '369 Willow St', 'Leadership Discussions'),
    (60, 8.00, '2025-03-01 19:30:00', 2, 10, '2025-03-01 19:00:00', '852 Palm St', 'Entrepreneur Keynotes'),
    (110, 30.00, '2024-12-30 18:00:00', 1, 1, '2024-12-30 15:00:00', '123 Main St', 'Panel Discussion'),
    (75, 12.00, '2024-12-25 17:00:00', 2, 2, '2024-12-25 14:00:00', '456 Elm St', 'AI Demo Session'),
    (300, 6.00, '2024-12-15 12:00:00', 3, 3, '2024-12-15 11:45:00', '789 Oak St', 'Networking Break'),
    (90, 18.00, '2024-11-20 16:00:00', 1, 4, '2024-11-20 15:00:00', '321 Pine St', 'Case Studies'),
    (45, 7.00, '2024-10-10 20:00:00', 2, 5, '2024-10-10 19:45:00', '654 Maple St', 'Investor Q&A'),
    (140, 28.00, '2025-01-10 18:00:00', 1, 6, '2025-01-10 15:00:00', '987 Cedar St', 'Future Insights'),
    (90, 18.00, '2024-12-20 17:00:00', 2, 7, '2024-12-20 14:00:00', '159 Birch St', 'AI Keynotes'),
    (220, 9.00, '2025-01-15 12:00:00', 3, 8, '2025-01-15 11:30:00', '753 Spruce St', 'Innovators Networking'),
    (130, 19.00, '2025-02-10 16:00:00', 1, 9, '2025-02-10 15:00:00', '369 Willow St', 'Strategy Session'),
    (70, 10.00, '2025-03-01 20:00:00', 2, 10, '2025-03-01 19:30:00', '852 Palm St', 'Closing Keynote');
