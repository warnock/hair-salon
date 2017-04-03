--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: meganwarnock
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    address character varying,
    phone character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO meganwarnock;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: meganwarnock
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO meganwarnock;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: meganwarnock
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: meganwarnock
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stylists OWNER TO meganwarnock;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: meganwarnock
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO meganwarnock;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: meganwarnock
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: meganwarnock
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: meganwarnock
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: meganwarnock
--

COPY clients (id, name, address, phone, stylist_id) FROM stdin;
3	MEgan	sef	adf	1
5	Test	Test	Test	1
2	Hl	kjsdlfkj	kasjd	2
10	client 3	asdf	asdfas	8
8	Molley	6th st	333-333-3333	7
13	Megan	4th st	333-333-3333	11
14	someone	like y	you	16
4	Helop	Yep	Yep	1
1	hello	skjlaksdj	askdjf	1
6	Please	its	me	2
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: meganwarnock
--

SELECT pg_catalog.setval('clients_id_seq', 14, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: meganwarnock
--

COPY stylists (id, name) FROM stdin;
11	jim
16	someone
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: meganwarnock
--

SELECT pg_catalog.setval('stylists_id_seq', 16, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: meganwarnock
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: meganwarnock
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

