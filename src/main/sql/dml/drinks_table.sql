-- Table: public.drinks

-- DROP TABLE IF EXISTS public.drinks;

CREATE TABLE IF NOT EXISTS public.drinks
(
    order_id integer NOT NULL,
    drinks_id integer NOT NULL DEFAULT nextval('drinks_drink_id_seq'::regclass),
    drink character varying(50) COLLATE pg_catalog."default" NOT NULL,
    size character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT drinks_pkey PRIMARY KEY (drinks_id),
    CONSTRAINT order_id FOREIGN KEY (order_id)
        REFERENCES public.orders (order_id) MATCH FULL
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.drinks
    OWNER to postgres;