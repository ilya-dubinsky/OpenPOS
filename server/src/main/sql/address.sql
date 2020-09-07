-- Table: public.address

-- DROP TABLE public.address;

CREATE TABLE public.address
(
    city character varying(48) COLLATE pg_catalog."default",
    id bigint NOT NULL,
    line1 character varying(128) COLLATE pg_catalog."default",
    zip character varying(32) COLLATE pg_catalog."default",
    line2 character varying(128) COLLATE pg_catalog."default",
    country_code character varying(2) COLLATE pg_catalog."default",
    CONSTRAINT "Address_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.address
    OWNER to openpos;