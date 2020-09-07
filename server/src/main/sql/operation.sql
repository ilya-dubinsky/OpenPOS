-- Table: public.operation

-- DROP TABLE public.operation;

CREATE TABLE public.operation
(
    id bigint NOT NULL,
    type character varying(16) COLLATE pg_catalog."default" NOT NULL,
    description character varying(256) COLLATE pg_catalog."default",
    CONSTRAINT "Operation_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.operation
    OWNER to openpos;