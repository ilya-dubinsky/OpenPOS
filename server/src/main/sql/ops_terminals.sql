-- Table: public.ops_terminals

-- DROP TABLE public.ops_terminals;

CREATE TABLE public.ops_terminals
(
    op_id bigint NOT NULL,
    terminal_id uuid
)

TABLESPACE pg_default;

ALTER TABLE public.ops_terminals
    OWNER to openpos;