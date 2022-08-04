-- public.usuarios definition

-- Drop table

-- DROP TABLE public.usuarios;

CREATE TABLE public.usuarios (
	id serial4 NOT NULL,
	nm_completo varchar(50) NOT NULL,
	login varchar(20) NOT NULL,
	senha varchar(10) NOT NULL,
	perfil varchar(10) NOT NULL,
	CONSTRAINT usuarios_pk PRIMARY KEY (id)
);


-- public.entregadores definition

-- Drop table

-- DROP TABLE public.entregadores;

CREATE TABLE public.entregadores (
	id serial4 NOT NULL,
	nm_completo varchar(50) NOT NULL,
	cpf varchar(14) NOT NULL,
	dt_nascimento date NOT NULL,
	rg varchar(10) NOT NULL,
	id_usuario int4 NOT NULL,
	CONSTRAINT entregadores_pk PRIMARY KEY (id),
	CONSTRAINT entregadores_fk FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id)
);


-- public.entregas definition

-- Drop table

-- DROP TABLE public.entregas;

CREATE TABLE public.entregas (
	id serial4 NOT NULL,
	endereco varchar(500) NOT NULL,
	dt_entrega date NOT NULL,
	entregue varchar(3) NOT NULL,
	descricao varchar(1500) NOT NULL,
	id_entregador int4 NOT NULL,
	CONSTRAINT entregas_pk PRIMARY KEY (id),
	CONSTRAINT entregas_fk FOREIGN KEY (id_entregador) REFERENCES public.entregadores(id)
);