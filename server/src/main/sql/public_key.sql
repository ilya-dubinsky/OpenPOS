-- Table: public.public_key

-- DROP TABLE public.public_key;

CREATE TABLE public.public_key
(
    id bigint NOT NULL,
    fingerprint character varying(128) COLLATE pg_catalog."default" NOT NULL,
    key oid NOT NULL,
    CONSTRAINT public_key_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.public_key
    OWNER to openpos;