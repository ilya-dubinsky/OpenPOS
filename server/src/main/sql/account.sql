-- Table: public.account

-- DROP TABLE public.account;

CREATE TABLE public.account
(
    id bigint NOT NULL,
    contract_number character varying(128) COLLATE pg_catalog."default" NOT NULL,
    display_name character varying(128) COLLATE pg_catalog."default",
    CONSTRAINT account_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.account
    OWNER to openpos;