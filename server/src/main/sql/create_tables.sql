-- public.account definition

-- Drop table

-- DROP TABLE public.account;

CREATE TABLE public.account (
	id int8 NOT NULL,
	contract_number varchar(128) NOT NULL,
	display_name varchar(128) NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id)
);


-- public.accounts_acquirers definition

-- Drop table

-- DROP TABLE public.accounts_acquirers;

CREATE TABLE public.accounts_acquirers (
	account_id int8 NOT NULL,
	acquirer_id int8 NOT NULL,
	CONSTRAINT accounts_acquirers_pkey PRIMARY KEY (account_id, acquirer_id)
);


-- public.acquirer definition

-- Drop table

-- DROP TABLE public.acquirer;

CREATE TABLE public.acquirer (
	id int8 NOT NULL,
	"name" varchar(128) NULL,
	protocol_id int8 NULL,
	CONSTRAINT acquirer_pkey PRIMARY KEY (id)
);


-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
	city varchar(48) NULL,
	id int8 NOT NULL,
	line1 varchar(128) NULL,
	zip varchar(32) NULL,
	line2 varchar(128) NULL,
	country_code varchar(2) NULL,
	CONSTRAINT "Address_pkey" PRIMARY KEY (id)
);


-- public.country definition

-- Drop table

-- DROP TABLE public.country;

CREATE TABLE public.country (
	alpha2 varchar(2) NOT NULL,
	alpha3 varchar(3) NOT NULL,
	"numeric" varchar(3) NOT NULL,
	CONSTRAINT "Country_pkey" PRIMARY KEY (alpha2, alpha3, "numeric")
);


-- public.operation definition

-- Drop table

-- DROP TABLE public.operation;

CREATE TABLE public.operation (
	id int8 NOT NULL,
	"type" varchar(16) NOT NULL,
	description varchar(256) NULL,
	CONSTRAINT "Operation_pkey" PRIMARY KEY (id)
);


-- public.ops_terminals definition

-- Drop table

-- DROP TABLE public.ops_terminals;

CREATE TABLE public.ops_terminals (
	op_id int8 NOT NULL,
	terminal_id int8 NULL
);


-- public.protocol definition

-- Drop table

-- DROP TABLE public.protocol;

CREATE TABLE public.protocol (
	id int8 NOT NULL,
	"name" varchar(64) NOT NULL,
	"handler" varchar(32) NULL,
	CONSTRAINT protocol_pkey PRIMARY KEY (id)
);


-- public.public_key definition

-- Drop table

-- DROP TABLE public.public_key;

CREATE TABLE public.public_key (
	id int8 NOT NULL,
	fingerprint varchar(128) NOT NULL,
	"key" oid NOT NULL,
	expiry_date timestamptz NULL,
	CONSTRAINT public_key_pkey PRIMARY KEY (id)
);


-- public.terminal definition

-- Drop table

-- DROP TABLE public.terminal;

CREATE TABLE public.terminal (
	active_from timestamptz NOT NULL,
	active_to timestamptz NOT NULL,
	address_id int8 NULL,
	active_key_id int8 NULL,
	id int8 NULL,
	account_id int8 NULL
);


-- public."transaction" definition

-- Drop table

-- DROP TABLE public."transaction";

CREATE TABLE public."transaction" (
	id int8 NOT NULL,
	terminal_id int8 NOT NULL,
	amount numeric(20,2) NULL,
	currency varchar(3) NULL,
	"type" varchar(16) NULL,
	CONSTRAINT transaction_pkey PRIMARY KEY (id)
);


-- public.currency definition

-- Drop table

-- DROP TABLE public.currency;

CREATE TABLE public.currency (
	alpha3 varchar(3) NOT NULL,
	"numeric" varchar(3) NOT NULL,
	"name" varchar(64) NULL
);


-- public.message definition

-- Drop table

-- DROP TABLE public.message;

CREATE TABLE public.message (
	id int8 NOT NULL,
	terminal_id int8 NOT NULL,
	protocol_id int8 NOT NULL,
	transaction_id int8 NOT NULL,
	"data" oid NULL
);