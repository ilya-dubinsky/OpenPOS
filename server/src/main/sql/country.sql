-- Table: public.country

-- DROP TABLE public.country;

CREATE TABLE public.country
(
    alpha2 character varying(2) COLLATE pg_catalog."default" NOT NULL,
    alpha3 character varying(3) COLLATE pg_catalog."default" NOT NULL,
    "numeric" character varying(3) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Country_pkey" PRIMARY KEY (alpha2, alpha3, "numeric")
)

TABLESPACE pg_default;

ALTER TABLE public.country
    OWNER to openpos;