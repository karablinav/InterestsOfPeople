INSERT INTO users_info (id, user_id, checkbox)
 VALUES (1,  1, true),
        (2, 2, false)
ON CONFLICT (id)
  DO UPDATE SET
    id   = EXCLUDED.id,
    checkbox = EXCLUDED.checkbox,
    user_id =  EXCLUDED.user_id
    ;
