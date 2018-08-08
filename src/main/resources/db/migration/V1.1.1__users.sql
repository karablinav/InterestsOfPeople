INSERT INTO users (id, name)
 VALUES (1, 'User'),
        (2,'Admin')
ON CONFLICT (id)
  DO UPDATE SET
    id   = EXCLUDED.id,
    name = EXCLUDED.name;
