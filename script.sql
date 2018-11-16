INSERT INTO a VALUES (1, NULL), (2, 1);
CREATE TABLE object_link (
        object_link_id        INTEGER PRIMARY KEY,
        object_name   TEXT
);
INSERT INTO object_link VALUES (1, 'A'), (2, 'B');
CREATE TABLE IF NOT EXISTS field (
        field_id      INTEGER PRIMARY KEY,
        num_value     INTEGER,
        string_value  TEXT,
        field_definition_id   INTEGER,
        object_id     INTEGER
);
INSERT INTO field VALUES(1,1,NULL,1,1), (2,NULL,'name',2,1);
CREATE TABLE IF NOT EXISTS "field_definition" (
        field_definition_id   INTEGER PRIMARY KEY,
        object_link_id        INTEGER,
        field_type    INTEGER,
        field_name    TEXT
);
INSERT INTO field_definition VALUES (1,1,1,'key'), (2,2,1,'name');
CREATE VIEW field_view as
select
     ol.object_name
         , fd.field_name
         , f.num_value
         , f.string_value
         , fd.field_type
         , f.object_id
         , f.field_id
from object_link ol join field_definition fd using (object_link_id) join field f on (f.field_definition_id = fd.field_definition_id);

CREATE TRIGGER insert_field_view INSTEAD OF INSERT
    ON field_view
    FOR EACH ROW
        INSERT INTO field (num_value, string_value) VALUES (NEW.num_value, NEW.string_value)
        INSERT INTO field_definition (field_type, field_name, object_link_id)
        SELECT (NEW.field_type, NEW.field_name, SELECT object_link_id FROM object_link WHERE object_name = NEW.object_name)
;
