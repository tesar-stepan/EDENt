--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

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
-- Name: appointment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE appointment (
    id bigint NOT NULL,
    date bigint NOT NULL,
    note character varying(255),
    creator bigint,
    patient bigint NOT NULL
);


ALTER TABLE public.appointment OWNER TO postgres;

--
-- Name: diagnosis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE diagnosis (
    id bigint NOT NULL,
    date bigint NOT NULL,
    creator bigint NOT NULL,
    doctor bigint,
    text character varying(255),
    app_id bigint,
    app_index integer
);


ALTER TABLE public.diagnosis OWNER TO postgres;

--
-- Name: diagnosis_history; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE diagnosis_history (
    diag_id bigint NOT NULL,
    elt bigint NOT NULL,
    hist_id bigint NOT NULL,
    hist_index integer NOT NULL
);


ALTER TABLE public.diagnosis_history OWNER TO postgres;

--
-- Name: gum; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gum (
    id bigint NOT NULL,
    history bigint,
    mouth bigint,
    state bigint
);


ALTER TABLE public.gum OWNER TO postgres;

--
-- Name: gumstate; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gumstate (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    mark character varying(255),
    icon character varying(255)
);


ALTER TABLE public.gumstate OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: history; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE history (
    id bigint NOT NULL,
    gum bigint,
    tooth bigint,
    mouth bigint
);


ALTER TABLE public.history OWNER TO postgres;

--
-- Name: mouth; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mouth (
    id bigint NOT NULL,
    history bigint
);


ALTER TABLE public.mouth OWNER TO postgres;

--
-- Name: patient; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE patient (
    id bigint NOT NULL,
    fname character varying(255),
    sname character varying(255),
    birthnum character varying(255) NOT NULL,
    birthdate bigint,
    created bigint
);


ALTER TABLE public.patient OWNER TO postgres;

--
-- Name: tooth; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tooth (
    id bigint NOT NULL,
    history bigint,
    mouth bigint,
    state bigint,
    imilk boolean NOT NULL,
    topfloor boolean NOT NULL,
    leftside boolean NOT NULL,
    "position" integer NOT NULL,
    denture boolean NOT NULL,
    braced boolean NOT NULL,
    gum bigint
);


ALTER TABLE public.tooth OWNER TO postgres;

--
-- Name: toothstate; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE toothstate (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    mark character varying(255),
    icon character varying(255),
    overx character varying(255)
);


ALTER TABLE public.toothstate OWNER TO postgres;

--
-- Name: user_appointment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_appointment (
    app_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_appointment OWNER TO postgres;

--
-- Name: user_patient; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_patient (
    patient_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.user_patient OWNER TO postgres;

--
-- Name: userx; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userx (
    id bigint NOT NULL,
    fname character varying(255),
    sname character varying(255),
    uname character varying(255) NOT NULL,
    titlepre character varying(255),
    titlepos character varying(255),
    type character varying(30),
    pass character varying(255)
);


ALTER TABLE public.userx OWNER TO postgres;

--
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO appointment VALUES (94, 1367423040000, 'Oprava rovnátek', NULL, 52);
INSERT INTO appointment VALUES (76, 1367425980000, 'Něco dalšího', NULL, 56);
INSERT INTO appointment VALUES (73, 1367470800000, 'Plombování', NULL, 62);
INSERT INTO appointment VALUES (74, 1367474400000, 'Ke kontrole 2', NULL, 50);
INSERT INTO appointment VALUES (99, 1367423520000, '', NULL, 60);
INSERT INTO appointment VALUES (100, 1367426160000, '', NULL, 60);


--
-- Data for Name: diagnosis; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: diagnosis_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: gum; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: gumstate; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO gumstate VALUES (34, 'Bez krvácení', '0', '');
INSERT INTO gumstate VALUES (35, 'Bodové/kratší krvácení', '1', '');
INSERT INTO gumstate VALUES (36, 'Vícebodové/delší krvácení', '2', '');
INSERT INTO gumstate VALUES (37, 'Naplnění mezizub. prostoru krví', '3', '');
INSERT INTO gumstate VALUES (38, 'Silné krvácení', '4', '');
INSERT INTO gumstate VALUES (39, 'Gingvitida', 'Gin', '');
INSERT INTO gumstate VALUES (43, 'Parodontitida', 'Par', '');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 100, true);


--
-- Data for Name: history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO history VALUES (51, NULL, NULL, 50);
INSERT INTO history VALUES (53, NULL, NULL, 52);
INSERT INTO history VALUES (55, NULL, NULL, 54);
INSERT INTO history VALUES (57, NULL, NULL, 56);
INSERT INTO history VALUES (59, NULL, NULL, 58);
INSERT INTO history VALUES (61, NULL, NULL, 60);
INSERT INTO history VALUES (63, NULL, NULL, 62);
INSERT INTO history VALUES (65, NULL, NULL, 64);
INSERT INTO history VALUES (67, NULL, NULL, 66);
INSERT INTO history VALUES (69, NULL, NULL, 68);


--
-- Data for Name: mouth; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO mouth VALUES (50, 51);
INSERT INTO mouth VALUES (52, 53);
INSERT INTO mouth VALUES (54, 55);
INSERT INTO mouth VALUES (56, 57);
INSERT INTO mouth VALUES (58, 59);
INSERT INTO mouth VALUES (60, 61);
INSERT INTO mouth VALUES (62, 63);
INSERT INTO mouth VALUES (64, 65);
INSERT INTO mouth VALUES (66, 67);
INSERT INTO mouth VALUES (68, 69);


--
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO patient VALUES (1, 'Ferdidand', 'Nováček', '130429/4103', -5542131600000, 1);
INSERT INTO patient VALUES (50, 'Pepa', 'Rosomák', '111111/2222', 419378400000, 1367306208372);
INSERT INTO patient VALUES (54, 'Super', 'Man', 'XOXO/<3', 46896274800000, 1367320926355);
INSERT INTO patient VALUES (52, 'Bylsemtu', 'Fantomas', 'hahaha/haha', -2524525200000, 1367310244145);
INSERT INTO patient VALUES (58, 'Martin', 'Luther', '550808/1234', -454467600000, 1367411309878);
INSERT INTO patient VALUES (62, 'Franta', 'Novák', '670601/4657', -81651600000, 1367411417853);
INSERT INTO patient VALUES (64, 'Martin', 'Pahejl', '731108/8741', 121561200000, 1367411453252);
INSERT INTO patient VALUES (66, 'Marta', 'Martová', '871209/9435', 566002800000, 1367411488334);
INSERT INTO patient VALUES (68, 'Eleonodora', 'Jarnicovovovová', '820625', 393804000000, 1367411551218);
INSERT INTO patient VALUES (56, 'Martin', 'Vlasatý', '11111/2222', 631148400000, 1367321617808);
INSERT INTO patient VALUES (60, 'Krystian', 'Kristovský', '940409/3456', 765842400000, 1367411370534);


--
-- Data for Name: tooth; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: toothstate; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO toothstate VALUES (2, 'Plomba', 'P', '', '');
INSERT INTO toothstate VALUES (3, 'Kaz', '/', '', '');
INSERT INTO toothstate VALUES (4, 'Inlay', '^', '', '');
INSERT INTO toothstate VALUES (11, 'Korunka plášťová', 'Π', NULL, NULL);
INSERT INTO toothstate VALUES (13, 'Mezičlen', ')', NULL, NULL);
INSERT INTO toothstate VALUES (14, 'Mezičlen s fazetou', ')|', NULL, NULL);
INSERT INTO toothstate VALUES (15, 'Dočasný zub', 'M', NULL, NULL);
INSERT INTO toothstate VALUES (16, 'Pultipis', 'P', NULL, NULL);
INSERT INTO toothstate VALUES (17, 'Nekróza', '×', NULL, NULL);
INSERT INTO toothstate VALUES (18, 'Plněný zub', '×', NULL, NULL);
INSERT INTO toothstate VALUES (19, 'Gangréna', '═', NULL, NULL);
INSERT INTO toothstate VALUES (20, 'Period. acuta', '═', NULL, NULL);
INSERT INTO toothstate VALUES (21, 'Period. chronica', '═', NULL, NULL);
INSERT INTO toothstate VALUES (22, 'Radix', 'R', NULL, NULL);
INSERT INTO toothstate VALUES (23, 'Resekce', 'Res', NULL, NULL);
INSERT INTO toothstate VALUES (24, 'K extrakci', 'X', NULL, NULL);
INSERT INTO toothstate VALUES (12, 'Korunka fazetová', 'Ħ', NULL, NULL);
INSERT INTO toothstate VALUES (25, 'Extrakce', '☼', NULL, NULL);
INSERT INTO toothstate VALUES (26, 'Neprořezaný', '( )', NULL, NULL);
INSERT INTO toothstate VALUES (27, 'Nezaložen', '( )', NULL, NULL);
INSERT INTO toothstate VALUES (28, 'Prořezává', '↑', NULL, NULL);
INSERT INTO toothstate VALUES (29, 'Dent. diffic', 'D', NULL, NULL);
INSERT INTO toothstate VALUES (30, 'Inlay kořenová', 'Y', NULL, NULL);
INSERT INTO toothstate VALUES (31, 'Náhrada částečná', '○', NULL, NULL);
INSERT INTO toothstate VALUES (32, 'Náhrada totální', '○', NULL, NULL);
INSERT INTO toothstate VALUES (33, 'Implantát', 'Ұp', NULL, NULL);
INSERT INTO toothstate VALUES (1, 'V pořádku', '', '', '');


--
-- Data for Name: user_appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: user_patient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_patient VALUES (1, 47);
INSERT INTO user_patient VALUES (50, 45);
INSERT INTO user_patient VALUES (54, 46);
INSERT INTO user_patient VALUES (52, 45);
INSERT INTO user_patient VALUES (62, 48);
INSERT INTO user_patient VALUES (62, 45);
INSERT INTO user_patient VALUES (52, 49);
INSERT INTO user_patient VALUES (60, 45);
INSERT INTO user_patient VALUES (60, 46);


--
-- Data for Name: userx; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO userx VALUES (45, 'Pavel', 'Hřídlička', 'phridel', 'ing.', '', 'doctor', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea');
INSERT INTO userx VALUES (47, 'Pavel', 'Majáček', 'majacek', 'ing.', '', 'doctor', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea');
INSERT INTO userx VALUES (49, 'Woldemort', 'Riddle', 'riddie<3', 'lord', 'the prince of darkness', 'nurse', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea');
INSERT INTO userx VALUES (46, 'Franta', 'Jejduková', 'fagi', 'Mgr. Ing. PhDr.', 'phd. dis. cis.', 'doctor', 'da39a3ee5e6b4b0d3255bfef95601890afd80709');
INSERT INTO userx VALUES (48, 'Kikikiki', 'Dinng', 'kikiki', '', 'dis.', 'nurse', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea');


--
-- Name: appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);


--
-- Name: diagnosis_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY diagnosis_history
    ADD CONSTRAINT diagnosis_history_pkey PRIMARY KEY (hist_id, hist_index);


--
-- Name: diagnosis_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY diagnosis
    ADD CONSTRAINT diagnosis_pkey PRIMARY KEY (id);


--
-- Name: gum_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gum
    ADD CONSTRAINT gum_pkey PRIMARY KEY (id);


--
-- Name: gumstate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gumstate
    ADD CONSTRAINT gumstate_pkey PRIMARY KEY (id);


--
-- Name: history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY history
    ADD CONSTRAINT history_pkey PRIMARY KEY (id);


--
-- Name: mouth_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mouth
    ADD CONSTRAINT mouth_pkey PRIMARY KEY (id);


--
-- Name: patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- Name: tooth_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tooth
    ADD CONSTRAINT tooth_pkey PRIMARY KEY (id);


--
-- Name: toothstate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY toothstate
    ADD CONSTRAINT toothstate_pkey PRIMARY KEY (id);


--
-- Name: user_appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_appointment
    ADD CONSTRAINT user_appointment_pkey PRIMARY KEY (user_id, app_id);


--
-- Name: user_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_patient
    ADD CONSTRAINT user_patient_pkey PRIMARY KEY (user_id, patient_id);


--
-- Name: userx_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userx
    ADD CONSTRAINT userx_pkey PRIMARY KEY (id);


--
-- Name: fk1191f169dceaa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gum
    ADD CONSTRAINT fk1191f169dceaa FOREIGN KEY (state) REFERENCES gumstate(id);


--
-- Name: fk1191f715a64c1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gum
    ADD CONSTRAINT fk1191f715a64c1 FOREIGN KEY (history) REFERENCES history(id);


--
-- Name: fk1191fc8372767; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gum
    ADD CONSTRAINT fk1191fc8372767 FOREIGN KEY (mouth) REFERENCES mouth(id);


--
-- Name: fk2bc3829158d27333; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_patient
    ADD CONSTRAINT fk2bc3829158d27333 FOREIGN KEY (patient_id) REFERENCES patient(id);


--
-- Name: fk2bc3829160998ce1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_patient
    ADD CONSTRAINT fk2bc3829160998ce1 FOREIGN KEY (user_id) REFERENCES userx(id);


--
-- Name: fk394ab5463a1c08da; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis_history
    ADD CONSTRAINT fk394ab5463a1c08da FOREIGN KEY (elt) REFERENCES history(id);


--
-- Name: fk394ab546609deeb9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis_history
    ADD CONSTRAINT fk394ab546609deeb9 FOREIGN KEY (diag_id) REFERENCES diagnosis(id);


--
-- Name: fk394ab546715a2785; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis_history
    ADD CONSTRAINT fk394ab546715a2785 FOREIGN KEY (hist_id) REFERENCES history(id);


--
-- Name: fk394ab546fdfc4cf7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis_history
    ADD CONSTRAINT fk394ab546fdfc4cf7 FOREIGN KEY (elt) REFERENCES diagnosis(id);


--
-- Name: fk4714ca7715a64c1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mouth
    ADD CONSTRAINT fk4714ca7715a64c1 FOREIGN KEY (history) REFERENCES history(id);


--
-- Name: fk4714ca7d3ae93b9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mouth
    ADD CONSTRAINT fk4714ca7d3ae93b9 FOREIGN KEY (id) REFERENCES patient(id);


--
-- Name: fk4b164d2b60998ce1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_appointment
    ADD CONSTRAINT fk4b164d2b60998ce1 FOREIGN KEY (user_id) REFERENCES userx(id);


--
-- Name: fk4b164d2bea7b3ab1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_appointment
    ADD CONSTRAINT fk4b164d2bea7b3ab1 FOREIGN KEY (app_id) REFERENCES appointment(id);


--
-- Name: fk4d3daa8715a64c1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tooth
    ADD CONSTRAINT fk4d3daa8715a64c1 FOREIGN KEY (history) REFERENCES history(id);


--
-- Name: fk4d3daa8c8372767; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tooth
    ADD CONSTRAINT fk4d3daa8c8372767 FOREIGN KEY (mouth) REFERENCES mouth(id);


--
-- Name: fk4d3daa8d8545d01; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tooth
    ADD CONSTRAINT fk4d3daa8d8545d01 FOREIGN KEY (state) REFERENCES toothstate(id);


--
-- Name: fk4d3daa8fb257297; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tooth
    ADD CONSTRAINT fk4d3daa8fb257297 FOREIGN KEY (gum) REFERENCES gum(id);


--
-- Name: fk9a787c74c8372767; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY history
    ADD CONSTRAINT fk9a787c74c8372767 FOREIGN KEY (mouth) REFERENCES mouth(id);


--
-- Name: fk9a787c74c8fc4369; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY history
    ADD CONSTRAINT fk9a787c74c8fc4369 FOREIGN KEY (tooth) REFERENCES tooth(id);


--
-- Name: fk9a787c74fb257297; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY history
    ADD CONSTRAINT fk9a787c74fb257297 FOREIGN KEY (gum) REFERENCES gum(id);


--
-- Name: fkb7f037fa48271a3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appointment
    ADD CONSTRAINT fkb7f037fa48271a3 FOREIGN KEY (patient) REFERENCES patient(id);


--
-- Name: fkb7f037fa6ad1efe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appointment
    ADD CONSTRAINT fkb7f037fa6ad1efe FOREIGN KEY (creator) REFERENCES userx(id);


--
-- Name: fkbecacad11a4e2ff1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis
    ADD CONSTRAINT fkbecacad11a4e2ff1 FOREIGN KEY (doctor) REFERENCES userx(id);


--
-- Name: fkbecacad1a6ad1efe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis
    ADD CONSTRAINT fkbecacad1a6ad1efe FOREIGN KEY (creator) REFERENCES userx(id);


--
-- Name: fkbecacad1ea7b3ab1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diagnosis
    ADD CONSTRAINT fkbecacad1ea7b3ab1 FOREIGN KEY (app_id) REFERENCES appointment(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

