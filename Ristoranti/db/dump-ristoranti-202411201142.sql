--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-20 11:42:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4863 (class 1262 OID 16388)
-- Name: ristoranti; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ristoranti WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Italian_Italy.1252';


ALTER DATABASE ristoranti OWNER TO postgres;

\connect ristoranti

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16397)
-- Name: piatto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.piatto (
    nome character varying NOT NULL,
    ingredienti character varying
);


ALTER TABLE public.piatto OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16390)
-- Name: ristorante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ristorante (
    nome character varying NOT NULL,
    descrizione character varying,
    ubicazione character varying
);


ALTER TABLE public.ristorante OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16409)
-- Name: ristorante_piatto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ristorante_piatto (
    ristorante_nome character varying(100) NOT NULL,
    piatto_nome character varying(100) NOT NULL
);


ALTER TABLE public.ristorante_piatto OWNER TO postgres;

--
-- TOC entry 4856 (class 0 OID 16397)
-- Dependencies: 218
-- Data for Name: piatto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.piatto (nome, ingredienti) FROM stdin;
pizza margherita	pomodoro, mozzarella
pizza marinara	pomodoro
Piatto12	Ing12
PIATTO10	ing10
PIATTO11	ing11
Piatto14	Ing14-1
\.


--
-- TOC entry 4855 (class 0 OID 16390)
-- Dependencies: 217
-- Data for Name: ristorante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ristorante (nome, descrizione, ubicazione) FROM stdin;
La Cascina di Zio Tobia	Tutto buono qui	Rende
Il Nero Corsaro	Tutto nero	Cosenza
RIST10	Desc10	Ub10
Ristorante5	Desc5	Ub5
a	a	Cosenza
dsjiifhj	dkksn	Castrolibero
sdf	sdf	Cosenza
RIST11	DESC11-1	UB11-1
\.


--
-- TOC entry 4857 (class 0 OID 16409)
-- Dependencies: 219
-- Data for Name: ristorante_piatto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ristorante_piatto (ristorante_nome, piatto_nome) FROM stdin;
La Cascina di Zio Tobia	pizza margherita
La Cascina di Zio Tobia	pizza marinara
Il Nero Corsaro	pizza margherita
Il Nero Corsaro	pizza marinara
RIST10	Piatto12
RIST11	Piatto12
Ristorante5	PIATTO10
Ristorante5	PIATTO11
\.


--
-- TOC entry 4705 (class 2606 OID 16403)
-- Name: piatto piatto_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.piatto
    ADD CONSTRAINT piatto_pk PRIMARY KEY (nome);


--
-- TOC entry 4707 (class 2606 OID 16413)
-- Name: ristorante_piatto ristorante_piatto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante_piatto
    ADD CONSTRAINT ristorante_piatto_pkey PRIMARY KEY (ristorante_nome, piatto_nome);


--
-- TOC entry 4703 (class 2606 OID 16396)
-- Name: ristorante ristorante_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante
    ADD CONSTRAINT ristorante_pk PRIMARY KEY (nome);


--
-- TOC entry 4708 (class 2606 OID 16419)
-- Name: ristorante_piatto ristorante_piatto_piatto_nome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante_piatto
    ADD CONSTRAINT ristorante_piatto_piatto_nome_fkey FOREIGN KEY (piatto_nome) REFERENCES public.piatto(nome) ON DELETE CASCADE;


--
-- TOC entry 4709 (class 2606 OID 16414)
-- Name: ristorante_piatto ristorante_piatto_ristorante_nome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante_piatto
    ADD CONSTRAINT ristorante_piatto_ristorante_nome_fkey FOREIGN KEY (ristorante_nome) REFERENCES public.ristorante(nome) ON DELETE CASCADE;


-- Completed on 2024-11-20 11:42:58

--
-- PostgreSQL database dump complete
--

