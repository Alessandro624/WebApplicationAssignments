--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-12-02 21:18:10

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
-- TOC entry 4870 (class 1262 OID 16388)
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
-- TOC entry 4871 (class 0 OID 0)
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
-- TOC entry 220 (class 1259 OID 16424)
-- Name: utente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente (
    username character varying NOT NULL,
    password character varying,
    role character varying
);


ALTER TABLE public.utente OWNER TO postgres;

--
-- TOC entry 4862 (class 0 OID 16397)
-- Dependencies: 218
-- Data for Name: piatto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.piatto (nome, ingredienti) FROM stdin;
Piatto12	Ing12
piatto123	ciao
pizza margherita	pomodoro, mozzarella, basilico
\.


--
-- TOC entry 4861 (class 0 OID 16390)
-- Dependencies: 217
-- Data for Name: ristorante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ristorante (nome, descrizione, ubicazione) FROM stdin;
Il Nero Corsaro	Tutto nero	Cosenza
Ristorante5	Desc5	Cosenza
La Cascina di Zio Tobia	Tutto buono qui	Castrolibero
Nuovo Ristorante	Un bel ristorante	Rende
RIST10	Desc10	Ub10
RIST11	Desc11	Ub11
\.


--
-- TOC entry 4863 (class 0 OID 16409)
-- Dependencies: 219
-- Data for Name: ristorante_piatto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ristorante_piatto (ristorante_nome, piatto_nome) FROM stdin;
Il Nero Corsaro	pizza margherita
La Cascina di Zio Tobia	pizza margherita
RIST10	Piatto12
RIST11	Piatto12
\.


--
-- TOC entry 4864 (class 0 OID 16424)
-- Dependencies: 220
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente (username, password, role) FROM stdin;
user@	$2a$10$1Lg7DFizXq0Hw./Ttoau3uFClNEVqeJ0fsNdOPghC9uCe4KGejIii	USER
\.


--
-- TOC entry 4709 (class 2606 OID 16403)
-- Name: piatto piatto_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.piatto
    ADD CONSTRAINT piatto_pk PRIMARY KEY (nome);


--
-- TOC entry 4711 (class 2606 OID 16413)
-- Name: ristorante_piatto ristorante_piatto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante_piatto
    ADD CONSTRAINT ristorante_piatto_pkey PRIMARY KEY (ristorante_nome, piatto_nome);


--
-- TOC entry 4707 (class 2606 OID 16396)
-- Name: ristorante ristorante_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante
    ADD CONSTRAINT ristorante_pk PRIMARY KEY (nome);


--
-- TOC entry 4713 (class 2606 OID 16430)
-- Name: utente utente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pk PRIMARY KEY (username);


--
-- TOC entry 4714 (class 2606 OID 16419)
-- Name: ristorante_piatto ristorante_piatto_piatto_nome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante_piatto
    ADD CONSTRAINT ristorante_piatto_piatto_nome_fkey FOREIGN KEY (piatto_nome) REFERENCES public.piatto(nome) ON DELETE CASCADE;


--
-- TOC entry 4715 (class 2606 OID 16414)
-- Name: ristorante_piatto ristorante_piatto_ristorante_nome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ristorante_piatto
    ADD CONSTRAINT ristorante_piatto_ristorante_nome_fkey FOREIGN KEY (ristorante_nome) REFERENCES public.ristorante(nome) ON DELETE CASCADE;


-- Completed on 2024-12-02 21:18:10

--
-- PostgreSQL database dump complete
--

