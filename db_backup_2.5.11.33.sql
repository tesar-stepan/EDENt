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
    pass character varying(255),
    type character varying(30)
);


ALTER TABLE public.userx OWNER TO postgres;

--
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO appointment VALUES (319, 1367442000000, 'Overeni poctu zubu', NULL, 59);
INSERT INTO appointment VALUES (580, 1367445000000, 'Test razeni zubu', NULL, 450);


--
-- Data for Name: diagnosis; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: diagnosis_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: gum; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO gum VALUES (63, 64, 59, NULL);
INSERT INTO gum VALUES (67, 68, 59, NULL);
INSERT INTO gum VALUES (193, 194, 189, NULL);
INSERT INTO gum VALUES (71, 72, 59, NULL);
INSERT INTO gum VALUES (75, 76, 59, NULL);
INSERT INTO gum VALUES (436, 437, 320, NULL);
INSERT INTO gum VALUES (79, 80, 59, NULL);
INSERT INTO gum VALUES (197, 198, 189, NULL);
INSERT INTO gum VALUES (83, 84, 59, NULL);
INSERT INTO gum VALUES (87, 88, 59, NULL);
INSERT INTO gum VALUES (324, 325, 320, NULL);
INSERT INTO gum VALUES (91, 92, 59, NULL);
INSERT INTO gum VALUES (201, 202, 189, NULL);
INSERT INTO gum VALUES (95, 96, 59, NULL);
INSERT INTO gum VALUES (99, 100, 59, NULL);
INSERT INTO gum VALUES (103, 104, 59, NULL);
INSERT INTO gum VALUES (205, 206, 189, NULL);
INSERT INTO gum VALUES (107, 108, 59, NULL);
INSERT INTO gum VALUES (111, 112, 59, NULL);
INSERT INTO gum VALUES (115, 116, 59, NULL);
INSERT INTO gum VALUES (209, 210, 189, NULL);
INSERT INTO gum VALUES (119, 120, 59, NULL);
INSERT INTO gum VALUES (123, 124, 59, NULL);
INSERT INTO gum VALUES (328, 329, 320, NULL);
INSERT INTO gum VALUES (127, 128, 59, NULL);
INSERT INTO gum VALUES (213, 214, 189, NULL);
INSERT INTO gum VALUES (131, 132, 59, NULL);
INSERT INTO gum VALUES (135, 136, 59, NULL);
INSERT INTO gum VALUES (139, 140, 59, NULL);
INSERT INTO gum VALUES (217, 218, 189, NULL);
INSERT INTO gum VALUES (143, 144, 59, NULL);
INSERT INTO gum VALUES (147, 148, 59, NULL);
INSERT INTO gum VALUES (542, 543, 450, NULL);
INSERT INTO gum VALUES (151, 152, 59, NULL);
INSERT INTO gum VALUES (221, 222, 189, NULL);
INSERT INTO gum VALUES (155, 156, 59, NULL);
INSERT INTO gum VALUES (159, 160, 59, NULL);
INSERT INTO gum VALUES (332, 333, 320, NULL);
INSERT INTO gum VALUES (163, 164, 59, NULL);
INSERT INTO gum VALUES (225, 226, 189, NULL);
INSERT INTO gum VALUES (167, 168, 59, NULL);
INSERT INTO gum VALUES (171, 172, 59, NULL);
INSERT INTO gum VALUES (175, 176, 59, NULL);
INSERT INTO gum VALUES (229, 230, 189, NULL);
INSERT INTO gum VALUES (179, 180, 59, NULL);
INSERT INTO gum VALUES (183, 184, 59, NULL);
INSERT INTO gum VALUES (440, 441, 320, NULL);
INSERT INTO gum VALUES (187, 188, 59, NULL);
INSERT INTO gum VALUES (233, 234, 189, NULL);
INSERT INTO gum VALUES (336, 337, 320, NULL);
INSERT INTO gum VALUES (237, 238, 189, NULL);
INSERT INTO gum VALUES (241, 242, 189, NULL);
INSERT INTO gum VALUES (245, 246, 189, NULL);
INSERT INTO gum VALUES (340, 341, 320, NULL);
INSERT INTO gum VALUES (249, 250, 189, NULL);
INSERT INTO gum VALUES (253, 254, 189, NULL);
INSERT INTO gum VALUES (506, 507, 450, NULL);
INSERT INTO gum VALUES (257, 258, 189, NULL);
INSERT INTO gum VALUES (344, 345, 320, NULL);
INSERT INTO gum VALUES (261, 262, 189, NULL);
INSERT INTO gum VALUES (265, 266, 189, NULL);
INSERT INTO gum VALUES (444, 445, 320, NULL);
INSERT INTO gum VALUES (269, 270, 189, NULL);
INSERT INTO gum VALUES (348, 349, 320, NULL);
INSERT INTO gum VALUES (273, 274, 189, NULL);
INSERT INTO gum VALUES (277, 278, 189, NULL);
INSERT INTO gum VALUES (281, 282, 189, NULL);
INSERT INTO gum VALUES (352, 353, 320, NULL);
INSERT INTO gum VALUES (285, 286, 189, NULL);
INSERT INTO gum VALUES (289, 290, 189, NULL);
INSERT INTO gum VALUES (293, 294, 189, NULL);
INSERT INTO gum VALUES (356, 357, 320, NULL);
INSERT INTO gum VALUES (297, 298, 189, NULL);
INSERT INTO gum VALUES (301, 302, 189, NULL);
INSERT INTO gum VALUES (448, 449, 320, NULL);
INSERT INTO gum VALUES (305, 306, 189, NULL);
INSERT INTO gum VALUES (360, 361, 320, NULL);
INSERT INTO gum VALUES (309, 310, 189, NULL);
INSERT INTO gum VALUES (313, 314, 189, NULL);
INSERT INTO gum VALUES (317, 318, 189, NULL);
INSERT INTO gum VALUES (364, 365, 320, NULL);
INSERT INTO gum VALUES (368, 369, 320, NULL);
INSERT INTO gum VALUES (454, 455, 450, NULL);
INSERT INTO gum VALUES (372, 373, 320, NULL);
INSERT INTO gum VALUES (376, 377, 320, NULL);
INSERT INTO gum VALUES (510, 511, 450, NULL);
INSERT INTO gum VALUES (380, 381, 320, NULL);
INSERT INTO gum VALUES (458, 459, 450, NULL);
INSERT INTO gum VALUES (384, 385, 320, NULL);
INSERT INTO gum VALUES (388, 389, 320, NULL);
INSERT INTO gum VALUES (392, 393, 320, NULL);
INSERT INTO gum VALUES (462, 463, 450, NULL);
INSERT INTO gum VALUES (396, 397, 320, NULL);
INSERT INTO gum VALUES (400, 401, 320, NULL);
INSERT INTO gum VALUES (404, 405, 320, NULL);
INSERT INTO gum VALUES (466, 467, 450, NULL);
INSERT INTO gum VALUES (408, 409, 320, NULL);
INSERT INTO gum VALUES (412, 413, 320, NULL);
INSERT INTO gum VALUES (514, 515, 450, NULL);
INSERT INTO gum VALUES (416, 417, 320, NULL);
INSERT INTO gum VALUES (470, 471, 450, NULL);
INSERT INTO gum VALUES (420, 421, 320, NULL);
INSERT INTO gum VALUES (424, 425, 320, NULL);
INSERT INTO gum VALUES (428, 429, 320, NULL);
INSERT INTO gum VALUES (474, 475, 450, NULL);
INSERT INTO gum VALUES (432, 433, 320, NULL);
INSERT INTO gum VALUES (546, 547, 450, NULL);
INSERT INTO gum VALUES (518, 519, 450, NULL);
INSERT INTO gum VALUES (478, 479, 450, NULL);
INSERT INTO gum VALUES (482, 483, 450, NULL);
INSERT INTO gum VALUES (486, 487, 450, NULL);
INSERT INTO gum VALUES (522, 523, 450, NULL);
INSERT INTO gum VALUES (490, 491, 450, NULL);
INSERT INTO gum VALUES (494, 495, 450, NULL);
INSERT INTO gum VALUES (566, 567, 450, NULL);
INSERT INTO gum VALUES (498, 499, 450, NULL);
INSERT INTO gum VALUES (526, 527, 450, NULL);
INSERT INTO gum VALUES (502, 503, 450, NULL);
INSERT INTO gum VALUES (550, 551, 450, NULL);
INSERT INTO gum VALUES (530, 531, 450, NULL);
INSERT INTO gum VALUES (534, 535, 450, NULL);
INSERT INTO gum VALUES (538, 539, 450, NULL);
INSERT INTO gum VALUES (578, 579, 450, NULL);
INSERT INTO gum VALUES (554, 555, 450, NULL);
INSERT INTO gum VALUES (570, 571, 450, NULL);
INSERT INTO gum VALUES (558, 559, 450, NULL);
INSERT INTO gum VALUES (562, 563, 450, NULL);
INSERT INTO gum VALUES (574, 575, 450, NULL);


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

SELECT pg_catalog.setval('hibernate_sequence', 581, true);


--
-- Data for Name: history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO history VALUES (60, NULL, NULL, 59);
INSERT INTO history VALUES (62, NULL, 61, NULL);
INSERT INTO history VALUES (64, 63, NULL, NULL);
INSERT INTO history VALUES (66, NULL, 65, NULL);
INSERT INTO history VALUES (68, 67, NULL, NULL);
INSERT INTO history VALUES (70, NULL, 69, NULL);
INSERT INTO history VALUES (72, 71, NULL, NULL);
INSERT INTO history VALUES (74, NULL, 73, NULL);
INSERT INTO history VALUES (76, 75, NULL, NULL);
INSERT INTO history VALUES (78, NULL, 77, NULL);
INSERT INTO history VALUES (80, 79, NULL, NULL);
INSERT INTO history VALUES (82, NULL, 81, NULL);
INSERT INTO history VALUES (84, 83, NULL, NULL);
INSERT INTO history VALUES (86, NULL, 85, NULL);
INSERT INTO history VALUES (88, 87, NULL, NULL);
INSERT INTO history VALUES (90, NULL, 89, NULL);
INSERT INTO history VALUES (92, 91, NULL, NULL);
INSERT INTO history VALUES (94, NULL, 93, NULL);
INSERT INTO history VALUES (96, 95, NULL, NULL);
INSERT INTO history VALUES (98, NULL, 97, NULL);
INSERT INTO history VALUES (100, 99, NULL, NULL);
INSERT INTO history VALUES (102, NULL, 101, NULL);
INSERT INTO history VALUES (104, 103, NULL, NULL);
INSERT INTO history VALUES (106, NULL, 105, NULL);
INSERT INTO history VALUES (108, 107, NULL, NULL);
INSERT INTO history VALUES (110, NULL, 109, NULL);
INSERT INTO history VALUES (112, 111, NULL, NULL);
INSERT INTO history VALUES (114, NULL, 113, NULL);
INSERT INTO history VALUES (116, 115, NULL, NULL);
INSERT INTO history VALUES (118, NULL, 117, NULL);
INSERT INTO history VALUES (120, 119, NULL, NULL);
INSERT INTO history VALUES (122, NULL, 121, NULL);
INSERT INTO history VALUES (124, 123, NULL, NULL);
INSERT INTO history VALUES (126, NULL, 125, NULL);
INSERT INTO history VALUES (128, 127, NULL, NULL);
INSERT INTO history VALUES (130, NULL, 129, NULL);
INSERT INTO history VALUES (132, 131, NULL, NULL);
INSERT INTO history VALUES (134, NULL, 133, NULL);
INSERT INTO history VALUES (136, 135, NULL, NULL);
INSERT INTO history VALUES (138, NULL, 137, NULL);
INSERT INTO history VALUES (140, 139, NULL, NULL);
INSERT INTO history VALUES (142, NULL, 141, NULL);
INSERT INTO history VALUES (144, 143, NULL, NULL);
INSERT INTO history VALUES (146, NULL, 145, NULL);
INSERT INTO history VALUES (148, 147, NULL, NULL);
INSERT INTO history VALUES (150, NULL, 149, NULL);
INSERT INTO history VALUES (152, 151, NULL, NULL);
INSERT INTO history VALUES (154, NULL, 153, NULL);
INSERT INTO history VALUES (156, 155, NULL, NULL);
INSERT INTO history VALUES (158, NULL, 157, NULL);
INSERT INTO history VALUES (160, 159, NULL, NULL);
INSERT INTO history VALUES (162, NULL, 161, NULL);
INSERT INTO history VALUES (164, 163, NULL, NULL);
INSERT INTO history VALUES (166, NULL, 165, NULL);
INSERT INTO history VALUES (168, 167, NULL, NULL);
INSERT INTO history VALUES (170, NULL, 169, NULL);
INSERT INTO history VALUES (172, 171, NULL, NULL);
INSERT INTO history VALUES (174, NULL, 173, NULL);
INSERT INTO history VALUES (176, 175, NULL, NULL);
INSERT INTO history VALUES (178, NULL, 177, NULL);
INSERT INTO history VALUES (180, 179, NULL, NULL);
INSERT INTO history VALUES (182, NULL, 181, NULL);
INSERT INTO history VALUES (184, 183, NULL, NULL);
INSERT INTO history VALUES (186, NULL, 185, NULL);
INSERT INTO history VALUES (188, 187, NULL, NULL);
INSERT INTO history VALUES (190, NULL, NULL, 189);
INSERT INTO history VALUES (192, NULL, 191, NULL);
INSERT INTO history VALUES (194, 193, NULL, NULL);
INSERT INTO history VALUES (196, NULL, 195, NULL);
INSERT INTO history VALUES (198, 197, NULL, NULL);
INSERT INTO history VALUES (200, NULL, 199, NULL);
INSERT INTO history VALUES (202, 201, NULL, NULL);
INSERT INTO history VALUES (204, NULL, 203, NULL);
INSERT INTO history VALUES (206, 205, NULL, NULL);
INSERT INTO history VALUES (208, NULL, 207, NULL);
INSERT INTO history VALUES (210, 209, NULL, NULL);
INSERT INTO history VALUES (212, NULL, 211, NULL);
INSERT INTO history VALUES (214, 213, NULL, NULL);
INSERT INTO history VALUES (216, NULL, 215, NULL);
INSERT INTO history VALUES (218, 217, NULL, NULL);
INSERT INTO history VALUES (220, NULL, 219, NULL);
INSERT INTO history VALUES (222, 221, NULL, NULL);
INSERT INTO history VALUES (224, NULL, 223, NULL);
INSERT INTO history VALUES (226, 225, NULL, NULL);
INSERT INTO history VALUES (228, NULL, 227, NULL);
INSERT INTO history VALUES (230, 229, NULL, NULL);
INSERT INTO history VALUES (232, NULL, 231, NULL);
INSERT INTO history VALUES (234, 233, NULL, NULL);
INSERT INTO history VALUES (236, NULL, 235, NULL);
INSERT INTO history VALUES (238, 237, NULL, NULL);
INSERT INTO history VALUES (240, NULL, 239, NULL);
INSERT INTO history VALUES (242, 241, NULL, NULL);
INSERT INTO history VALUES (244, NULL, 243, NULL);
INSERT INTO history VALUES (246, 245, NULL, NULL);
INSERT INTO history VALUES (248, NULL, 247, NULL);
INSERT INTO history VALUES (250, 249, NULL, NULL);
INSERT INTO history VALUES (252, NULL, 251, NULL);
INSERT INTO history VALUES (254, 253, NULL, NULL);
INSERT INTO history VALUES (256, NULL, 255, NULL);
INSERT INTO history VALUES (258, 257, NULL, NULL);
INSERT INTO history VALUES (260, NULL, 259, NULL);
INSERT INTO history VALUES (262, 261, NULL, NULL);
INSERT INTO history VALUES (264, NULL, 263, NULL);
INSERT INTO history VALUES (266, 265, NULL, NULL);
INSERT INTO history VALUES (268, NULL, 267, NULL);
INSERT INTO history VALUES (270, 269, NULL, NULL);
INSERT INTO history VALUES (272, NULL, 271, NULL);
INSERT INTO history VALUES (274, 273, NULL, NULL);
INSERT INTO history VALUES (276, NULL, 275, NULL);
INSERT INTO history VALUES (278, 277, NULL, NULL);
INSERT INTO history VALUES (280, NULL, 279, NULL);
INSERT INTO history VALUES (282, 281, NULL, NULL);
INSERT INTO history VALUES (284, NULL, 283, NULL);
INSERT INTO history VALUES (286, 285, NULL, NULL);
INSERT INTO history VALUES (288, NULL, 287, NULL);
INSERT INTO history VALUES (290, 289, NULL, NULL);
INSERT INTO history VALUES (292, NULL, 291, NULL);
INSERT INTO history VALUES (294, 293, NULL, NULL);
INSERT INTO history VALUES (296, NULL, 295, NULL);
INSERT INTO history VALUES (298, 297, NULL, NULL);
INSERT INTO history VALUES (300, NULL, 299, NULL);
INSERT INTO history VALUES (302, 301, NULL, NULL);
INSERT INTO history VALUES (304, NULL, 303, NULL);
INSERT INTO history VALUES (306, 305, NULL, NULL);
INSERT INTO history VALUES (308, NULL, 307, NULL);
INSERT INTO history VALUES (310, 309, NULL, NULL);
INSERT INTO history VALUES (312, NULL, 311, NULL);
INSERT INTO history VALUES (314, 313, NULL, NULL);
INSERT INTO history VALUES (316, NULL, 315, NULL);
INSERT INTO history VALUES (318, 317, NULL, NULL);
INSERT INTO history VALUES (321, NULL, NULL, 320);
INSERT INTO history VALUES (323, NULL, 322, NULL);
INSERT INTO history VALUES (325, 324, NULL, NULL);
INSERT INTO history VALUES (327, NULL, 326, NULL);
INSERT INTO history VALUES (329, 328, NULL, NULL);
INSERT INTO history VALUES (331, NULL, 330, NULL);
INSERT INTO history VALUES (333, 332, NULL, NULL);
INSERT INTO history VALUES (335, NULL, 334, NULL);
INSERT INTO history VALUES (337, 336, NULL, NULL);
INSERT INTO history VALUES (339, NULL, 338, NULL);
INSERT INTO history VALUES (341, 340, NULL, NULL);
INSERT INTO history VALUES (343, NULL, 342, NULL);
INSERT INTO history VALUES (345, 344, NULL, NULL);
INSERT INTO history VALUES (347, NULL, 346, NULL);
INSERT INTO history VALUES (349, 348, NULL, NULL);
INSERT INTO history VALUES (351, NULL, 350, NULL);
INSERT INTO history VALUES (353, 352, NULL, NULL);
INSERT INTO history VALUES (355, NULL, 354, NULL);
INSERT INTO history VALUES (357, 356, NULL, NULL);
INSERT INTO history VALUES (359, NULL, 358, NULL);
INSERT INTO history VALUES (361, 360, NULL, NULL);
INSERT INTO history VALUES (363, NULL, 362, NULL);
INSERT INTO history VALUES (365, 364, NULL, NULL);
INSERT INTO history VALUES (367, NULL, 366, NULL);
INSERT INTO history VALUES (369, 368, NULL, NULL);
INSERT INTO history VALUES (371, NULL, 370, NULL);
INSERT INTO history VALUES (373, 372, NULL, NULL);
INSERT INTO history VALUES (375, NULL, 374, NULL);
INSERT INTO history VALUES (377, 376, NULL, NULL);
INSERT INTO history VALUES (379, NULL, 378, NULL);
INSERT INTO history VALUES (381, 380, NULL, NULL);
INSERT INTO history VALUES (383, NULL, 382, NULL);
INSERT INTO history VALUES (385, 384, NULL, NULL);
INSERT INTO history VALUES (387, NULL, 386, NULL);
INSERT INTO history VALUES (389, 388, NULL, NULL);
INSERT INTO history VALUES (391, NULL, 390, NULL);
INSERT INTO history VALUES (393, 392, NULL, NULL);
INSERT INTO history VALUES (395, NULL, 394, NULL);
INSERT INTO history VALUES (397, 396, NULL, NULL);
INSERT INTO history VALUES (399, NULL, 398, NULL);
INSERT INTO history VALUES (401, 400, NULL, NULL);
INSERT INTO history VALUES (403, NULL, 402, NULL);
INSERT INTO history VALUES (405, 404, NULL, NULL);
INSERT INTO history VALUES (407, NULL, 406, NULL);
INSERT INTO history VALUES (409, 408, NULL, NULL);
INSERT INTO history VALUES (411, NULL, 410, NULL);
INSERT INTO history VALUES (413, 412, NULL, NULL);
INSERT INTO history VALUES (415, NULL, 414, NULL);
INSERT INTO history VALUES (417, 416, NULL, NULL);
INSERT INTO history VALUES (419, NULL, 418, NULL);
INSERT INTO history VALUES (421, 420, NULL, NULL);
INSERT INTO history VALUES (423, NULL, 422, NULL);
INSERT INTO history VALUES (425, 424, NULL, NULL);
INSERT INTO history VALUES (427, NULL, 426, NULL);
INSERT INTO history VALUES (429, 428, NULL, NULL);
INSERT INTO history VALUES (431, NULL, 430, NULL);
INSERT INTO history VALUES (433, 432, NULL, NULL);
INSERT INTO history VALUES (435, NULL, 434, NULL);
INSERT INTO history VALUES (437, 436, NULL, NULL);
INSERT INTO history VALUES (439, NULL, 438, NULL);
INSERT INTO history VALUES (441, 440, NULL, NULL);
INSERT INTO history VALUES (443, NULL, 442, NULL);
INSERT INTO history VALUES (445, 444, NULL, NULL);
INSERT INTO history VALUES (447, NULL, 446, NULL);
INSERT INTO history VALUES (449, 448, NULL, NULL);
INSERT INTO history VALUES (451, NULL, NULL, 450);
INSERT INTO history VALUES (453, NULL, 452, NULL);
INSERT INTO history VALUES (455, 454, NULL, NULL);
INSERT INTO history VALUES (457, NULL, 456, NULL);
INSERT INTO history VALUES (459, 458, NULL, NULL);
INSERT INTO history VALUES (461, NULL, 460, NULL);
INSERT INTO history VALUES (463, 462, NULL, NULL);
INSERT INTO history VALUES (465, NULL, 464, NULL);
INSERT INTO history VALUES (467, 466, NULL, NULL);
INSERT INTO history VALUES (469, NULL, 468, NULL);
INSERT INTO history VALUES (471, 470, NULL, NULL);
INSERT INTO history VALUES (473, NULL, 472, NULL);
INSERT INTO history VALUES (475, 474, NULL, NULL);
INSERT INTO history VALUES (477, NULL, 476, NULL);
INSERT INTO history VALUES (479, 478, NULL, NULL);
INSERT INTO history VALUES (481, NULL, 480, NULL);
INSERT INTO history VALUES (483, 482, NULL, NULL);
INSERT INTO history VALUES (485, NULL, 484, NULL);
INSERT INTO history VALUES (487, 486, NULL, NULL);
INSERT INTO history VALUES (489, NULL, 488, NULL);
INSERT INTO history VALUES (491, 490, NULL, NULL);
INSERT INTO history VALUES (493, NULL, 492, NULL);
INSERT INTO history VALUES (495, 494, NULL, NULL);
INSERT INTO history VALUES (497, NULL, 496, NULL);
INSERT INTO history VALUES (499, 498, NULL, NULL);
INSERT INTO history VALUES (501, NULL, 500, NULL);
INSERT INTO history VALUES (503, 502, NULL, NULL);
INSERT INTO history VALUES (505, NULL, 504, NULL);
INSERT INTO history VALUES (507, 506, NULL, NULL);
INSERT INTO history VALUES (509, NULL, 508, NULL);
INSERT INTO history VALUES (511, 510, NULL, NULL);
INSERT INTO history VALUES (513, NULL, 512, NULL);
INSERT INTO history VALUES (515, 514, NULL, NULL);
INSERT INTO history VALUES (517, NULL, 516, NULL);
INSERT INTO history VALUES (519, 518, NULL, NULL);
INSERT INTO history VALUES (521, NULL, 520, NULL);
INSERT INTO history VALUES (523, 522, NULL, NULL);
INSERT INTO history VALUES (525, NULL, 524, NULL);
INSERT INTO history VALUES (527, 526, NULL, NULL);
INSERT INTO history VALUES (529, NULL, 528, NULL);
INSERT INTO history VALUES (531, 530, NULL, NULL);
INSERT INTO history VALUES (533, NULL, 532, NULL);
INSERT INTO history VALUES (535, 534, NULL, NULL);
INSERT INTO history VALUES (537, NULL, 536, NULL);
INSERT INTO history VALUES (539, 538, NULL, NULL);
INSERT INTO history VALUES (541, NULL, 540, NULL);
INSERT INTO history VALUES (543, 542, NULL, NULL);
INSERT INTO history VALUES (545, NULL, 544, NULL);
INSERT INTO history VALUES (547, 546, NULL, NULL);
INSERT INTO history VALUES (549, NULL, 548, NULL);
INSERT INTO history VALUES (551, 550, NULL, NULL);
INSERT INTO history VALUES (553, NULL, 552, NULL);
INSERT INTO history VALUES (555, 554, NULL, NULL);
INSERT INTO history VALUES (557, NULL, 556, NULL);
INSERT INTO history VALUES (559, 558, NULL, NULL);
INSERT INTO history VALUES (561, NULL, 560, NULL);
INSERT INTO history VALUES (563, 562, NULL, NULL);
INSERT INTO history VALUES (565, NULL, 564, NULL);
INSERT INTO history VALUES (567, 566, NULL, NULL);
INSERT INTO history VALUES (569, NULL, 568, NULL);
INSERT INTO history VALUES (571, 570, NULL, NULL);
INSERT INTO history VALUES (573, NULL, 572, NULL);
INSERT INTO history VALUES (575, 574, NULL, NULL);
INSERT INTO history VALUES (577, NULL, 576, NULL);
INSERT INTO history VALUES (579, 578, NULL, NULL);


--
-- Data for Name: mouth; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO mouth VALUES (59, 60);
INSERT INTO mouth VALUES (189, 190);
INSERT INTO mouth VALUES (320, 321);
INSERT INTO mouth VALUES (450, 451);


--
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO patient VALUES (59, 'Martin', 'Vlaštovka', '900801/8230', 649461600000, 1367441864863);
INSERT INTO patient VALUES (189, 'Ondrej', 'Jasmin', '750901/3245', 158454000000, 1367442028670);
INSERT INTO patient VALUES (320, 'Tereza', 'Malá', '870324/4345', 543538800000, 1367442168903);
INSERT INTO patient VALUES (450, 'Ondra', 'Kulička', '740916/3455', -3007155600000, 1367445022724);


--
-- Data for Name: tooth; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tooth VALUES (137, 138, 59, NULL, false, false, true, 4, false, false, 139);
INSERT INTO tooth VALUES (65, 66, 59, NULL, false, true, true, 2, false, true, 67);
INSERT INTO tooth VALUES (366, 367, 320, NULL, false, true, false, 4, false, false, 368);
INSERT INTO tooth VALUES (275, 276, 189, NULL, false, false, true, 6, false, false, 277);
INSERT INTO tooth VALUES (141, 142, 59, NULL, false, false, true, 5, false, false, 143);
INSERT INTO tooth VALUES (239, 240, 189, NULL, false, true, false, 5, false, false, 241);
INSERT INTO tooth VALUES (191, 192, 189, NULL, false, true, true, 1, false, false, 193);
INSERT INTO tooth VALUES (69, 70, 59, NULL, false, true, true, 3, false, false, 71);
INSERT INTO tooth VALUES (145, 146, 59, NULL, false, false, true, 6, false, false, 147);
INSERT INTO tooth VALUES (73, 74, 59, NULL, false, true, true, 4, false, false, 75);
INSERT INTO tooth VALUES (303, 304, 189, NULL, false, false, false, 5, false, false, 305);
INSERT INTO tooth VALUES (77, 78, 59, NULL, false, true, true, 5, false, false, 79);
INSERT INTO tooth VALUES (149, 150, 59, NULL, false, false, true, 7, false, false, 151);
INSERT INTO tooth VALUES (195, 196, 189, NULL, false, true, true, 2, false, false, 197);
INSERT INTO tooth VALUES (81, 82, 59, NULL, false, true, true, 6, false, false, 83);
INSERT INTO tooth VALUES (153, 154, 59, NULL, false, false, true, 8, false, false, 155);
INSERT INTO tooth VALUES (85, 86, 59, NULL, false, true, true, 7, false, false, 87);
INSERT INTO tooth VALUES (243, 244, 189, NULL, false, true, false, 6, false, false, 245);
INSERT INTO tooth VALUES (89, 90, 59, NULL, false, true, true, 8, false, false, 91);
INSERT INTO tooth VALUES (199, 200, 189, NULL, false, true, true, 3, false, false, 201);
INSERT INTO tooth VALUES (125, 126, 59, NULL, false, false, true, 1, false, true, 127);
INSERT INTO tooth VALUES (61, 62, 59, NULL, false, true, true, 1, false, true, 63);
INSERT INTO tooth VALUES (279, 280, 189, NULL, false, false, true, 7, false, false, 281);
INSERT INTO tooth VALUES (93, 94, 59, NULL, false, true, false, 1, false, true, 95);
INSERT INTO tooth VALUES (157, 158, 59, NULL, false, false, false, 1, false, true, 159);
INSERT INTO tooth VALUES (97, 98, 59, NULL, false, true, false, 2, false, true, 99);
INSERT INTO tooth VALUES (203, 204, 189, NULL, false, true, true, 4, false, false, 205);
INSERT INTO tooth VALUES (101, 102, 59, NULL, false, true, false, 3, false, true, 103);
INSERT INTO tooth VALUES (105, 106, 59, NULL, false, true, false, 4, false, false, 107);
INSERT INTO tooth VALUES (247, 248, 189, NULL, false, true, false, 7, false, false, 249);
INSERT INTO tooth VALUES (109, 110, 59, NULL, false, true, false, 5, false, false, 111);
INSERT INTO tooth VALUES (358, 359, 320, NULL, false, true, false, 2, false, false, 360);
INSERT INTO tooth VALUES (169, 170, 59, NULL, false, false, false, 4, false, false, 171);
INSERT INTO tooth VALUES (113, 114, 59, NULL, false, true, false, 6, false, false, 115);
INSERT INTO tooth VALUES (207, 208, 189, NULL, false, true, true, 5, false, false, 209);
INSERT INTO tooth VALUES (330, 331, 320, NULL, false, true, true, 3, false, false, 332);
INSERT INTO tooth VALUES (117, 118, 59, NULL, false, true, false, 7, false, false, 119);
INSERT INTO tooth VALUES (173, 174, 59, NULL, false, false, false, 5, false, false, 175);
INSERT INTO tooth VALUES (307, 308, 189, NULL, false, false, false, 6, false, false, 309);
INSERT INTO tooth VALUES (121, 122, 59, NULL, false, true, false, 8, false, false, 123);
INSERT INTO tooth VALUES (251, 252, 189, NULL, false, true, false, 8, false, false, 253);
INSERT INTO tooth VALUES (211, 212, 189, NULL, false, true, true, 6, false, false, 213);
INSERT INTO tooth VALUES (177, 178, 59, NULL, false, false, false, 6, false, false, 179);
INSERT INTO tooth VALUES (129, 130, 59, NULL, false, false, true, 2, false, true, 131);
INSERT INTO tooth VALUES (165, 166, 59, NULL, false, false, false, 3, false, true, 167);
INSERT INTO tooth VALUES (283, 284, 189, NULL, false, false, true, 8, false, false, 285);
INSERT INTO tooth VALUES (181, 182, 59, NULL, false, false, false, 7, false, false, 183);
INSERT INTO tooth VALUES (133, 134, 59, NULL, false, false, true, 3, false, false, 135);
INSERT INTO tooth VALUES (215, 216, 189, NULL, false, true, true, 7, false, false, 217);
INSERT INTO tooth VALUES (185, 186, 59, NULL, false, false, false, 8, false, false, 187);
INSERT INTO tooth VALUES (255, 256, 189, NULL, false, false, true, 1, false, false, 257);
INSERT INTO tooth VALUES (219, 220, 189, NULL, false, true, true, 8, false, false, 221);
INSERT INTO tooth VALUES (346, 347, 320, NULL, false, true, true, 7, false, false, 348);
INSERT INTO tooth VALUES (287, 288, 189, NULL, false, false, false, 1, false, false, 289);
INSERT INTO tooth VALUES (223, 224, 189, NULL, false, true, false, 1, false, false, 225);
INSERT INTO tooth VALUES (259, 260, 189, NULL, false, false, true, 2, false, false, 261);
INSERT INTO tooth VALUES (227, 228, 189, NULL, false, true, false, 2, false, false, 229);
INSERT INTO tooth VALUES (311, 312, 189, NULL, false, false, false, 7, false, false, 313);
INSERT INTO tooth VALUES (263, 264, 189, NULL, false, false, true, 3, false, false, 265);
INSERT INTO tooth VALUES (231, 232, 189, NULL, false, true, false, 3, false, false, 233);
INSERT INTO tooth VALUES (291, 292, 189, NULL, false, false, false, 2, false, false, 293);
INSERT INTO tooth VALUES (235, 236, 189, NULL, false, true, false, 4, false, false, 237);
INSERT INTO tooth VALUES (267, 268, 189, NULL, false, false, true, 4, false, false, 269);
INSERT INTO tooth VALUES (334, 335, 320, NULL, false, true, true, 4, false, false, 336);
INSERT INTO tooth VALUES (295, 296, 189, NULL, false, false, false, 3, false, false, 297);
INSERT INTO tooth VALUES (271, 272, 189, NULL, false, false, true, 5, false, false, 273);
INSERT INTO tooth VALUES (315, 316, 189, NULL, false, false, false, 8, false, false, 317);
INSERT INTO tooth VALUES (390, 391, 320, NULL, false, false, true, 2, false, false, 392);
INSERT INTO tooth VALUES (374, 375, 320, NULL, false, true, false, 6, false, false, 376);
INSERT INTO tooth VALUES (299, 300, 189, NULL, false, false, false, 4, false, false, 301);
INSERT INTO tooth VALUES (350, 351, 320, NULL, false, true, true, 8, false, false, 352);
INSERT INTO tooth VALUES (338, 339, 320, NULL, false, true, true, 5, false, false, 340);
INSERT INTO tooth VALUES (322, 323, 320, NULL, false, true, true, 1, false, false, 324);
INSERT INTO tooth VALUES (326, 327, 320, NULL, false, true, true, 2, false, false, 328);
INSERT INTO tooth VALUES (362, 363, 320, NULL, false, true, false, 3, false, false, 364);
INSERT INTO tooth VALUES (342, 343, 320, NULL, false, true, true, 6, false, false, 344);
INSERT INTO tooth VALUES (354, 355, 320, NULL, false, true, false, 1, false, false, 356);
INSERT INTO tooth VALUES (370, 371, 320, NULL, false, true, false, 5, false, false, 372);
INSERT INTO tooth VALUES (161, 162, 59, NULL, false, false, false, 2, false, true, 163);
INSERT INTO tooth VALUES (382, 383, 320, NULL, false, true, false, 8, false, false, 384);
INSERT INTO tooth VALUES (378, 379, 320, NULL, false, true, false, 7, false, false, 380);
INSERT INTO tooth VALUES (386, 387, 320, NULL, false, false, true, 1, false, false, 388);
INSERT INTO tooth VALUES (394, 395, 320, NULL, false, false, true, 3, false, false, 396);
INSERT INTO tooth VALUES (398, 399, 320, NULL, false, false, true, 4, false, false, 400);
INSERT INTO tooth VALUES (402, 403, 320, NULL, false, false, true, 5, false, false, 404);
INSERT INTO tooth VALUES (406, 407, 320, NULL, false, false, true, 6, false, false, 408);
INSERT INTO tooth VALUES (410, 411, 320, NULL, false, false, true, 7, false, false, 412);
INSERT INTO tooth VALUES (414, 415, 320, NULL, false, false, true, 8, false, false, 416);
INSERT INTO tooth VALUES (418, 419, 320, NULL, false, false, false, 1, false, false, 420);
INSERT INTO tooth VALUES (422, 423, 320, NULL, false, false, false, 2, false, false, 424);
INSERT INTO tooth VALUES (426, 427, 320, NULL, false, false, false, 3, false, false, 428);
INSERT INTO tooth VALUES (430, 431, 320, NULL, false, false, false, 4, false, false, 432);
INSERT INTO tooth VALUES (434, 435, 320, NULL, false, false, false, 5, false, false, 436);
INSERT INTO tooth VALUES (438, 439, 320, NULL, false, false, false, 6, false, false, 440);
INSERT INTO tooth VALUES (442, 443, 320, NULL, false, false, false, 7, false, false, 444);
INSERT INTO tooth VALUES (446, 447, 320, NULL, false, false, false, 8, false, false, 448);
INSERT INTO tooth VALUES (452, 453, 450, NULL, false, true, false, 8, false, false, 454);
INSERT INTO tooth VALUES (532, 533, 450, NULL, true, false, false, 4, false, false, 534);
INSERT INTO tooth VALUES (536, 537, 450, NULL, true, false, false, 3, false, false, 538);
INSERT INTO tooth VALUES (456, 457, 450, NULL, false, true, false, 7, false, false, 458);
INSERT INTO tooth VALUES (540, 541, 450, NULL, true, false, false, 2, false, false, 542);
INSERT INTO tooth VALUES (544, 545, 450, NULL, true, false, false, 1, false, false, 546);
INSERT INTO tooth VALUES (460, 461, 450, NULL, false, true, false, 6, false, false, 462);
INSERT INTO tooth VALUES (464, 465, 450, NULL, false, true, false, 5, false, false, 466);
INSERT INTO tooth VALUES (552, 553, 450, NULL, true, false, true, 2, false, false, 554);
INSERT INTO tooth VALUES (556, 557, 450, NULL, true, false, true, 3, false, false, 558);
INSERT INTO tooth VALUES (468, 469, 450, NULL, true, true, false, 4, false, false, 470);
INSERT INTO tooth VALUES (472, 473, 450, NULL, true, true, false, 3, false, false, 474);
INSERT INTO tooth VALUES (476, 477, 450, NULL, true, true, false, 2, false, false, 478);
INSERT INTO tooth VALUES (480, 481, 450, NULL, true, true, false, 1, false, false, 482);
INSERT INTO tooth VALUES (484, 485, 450, NULL, true, true, true, 1, false, false, 486);
INSERT INTO tooth VALUES (492, 493, 450, NULL, true, true, true, 3, false, false, 494);
INSERT INTO tooth VALUES (488, 489, 450, NULL, true, true, true, 2, false, false, 490);
INSERT INTO tooth VALUES (548, 549, 450, NULL, true, false, true, 1, false, false, 550);
INSERT INTO tooth VALUES (560, 561, 450, NULL, true, false, true, 4, false, false, 562);
INSERT INTO tooth VALUES (496, 497, 450, NULL, true, true, true, 4, false, false, 498);
INSERT INTO tooth VALUES (500, 501, 450, NULL, false, true, true, 5, false, false, 502);
INSERT INTO tooth VALUES (504, 505, 450, NULL, false, true, true, 6, false, false, 506);
INSERT INTO tooth VALUES (508, 509, 450, NULL, false, true, true, 7, false, false, 510);
INSERT INTO tooth VALUES (564, 565, 450, NULL, false, false, true, 5, false, false, 566);
INSERT INTO tooth VALUES (512, 513, 450, NULL, false, true, true, 8, false, false, 514);
INSERT INTO tooth VALUES (568, 569, 450, NULL, false, false, true, 6, false, false, 570);
INSERT INTO tooth VALUES (516, 517, 450, NULL, false, false, false, 8, false, false, 518);
INSERT INTO tooth VALUES (520, 521, 450, NULL, false, false, false, 7, false, false, 522);
INSERT INTO tooth VALUES (572, 573, 450, NULL, false, false, true, 7, false, false, 574);
INSERT INTO tooth VALUES (524, 525, 450, NULL, false, false, false, 6, false, false, 526);
INSERT INTO tooth VALUES (528, 529, 450, NULL, false, false, false, 5, false, false, 530);
INSERT INTO tooth VALUES (576, 577, 450, NULL, false, false, true, 8, false, false, 578);


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
INSERT INTO toothstate VALUES (581, 'NEPŘÍTOMEN', '=X=', '', NULL);


--
-- Data for Name: user_appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_appointment VALUES (319, 45);
INSERT INTO user_appointment VALUES (319, 48);
INSERT INTO user_appointment VALUES (319, 46);


--
-- Data for Name: user_patient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_patient VALUES (59, 48);
INSERT INTO user_patient VALUES (59, 46);
INSERT INTO user_patient VALUES (59, 45);


--
-- Data for Name: userx; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO userx VALUES (45, 'Pavel', 'Hřídlička', 'phridel', 'ing.', '', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea', 'doctor');
INSERT INTO userx VALUES (47, 'Pavel', 'Majáček', 'majacek', 'ing.', '', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea', 'doctor');
INSERT INTO userx VALUES (49, 'Woldemort', 'Riddle', 'riddie<3', 'lord', 'the prince of darkness', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea', 'nurse');
INSERT INTO userx VALUES (46, 'Franta', 'Jejduková', 'fagi', 'Mgr. Ing. PhDr.', 'phd. dis. cis.', 'da39a3ee5e6b4b0d3255bfef95601890afd80709', 'nurse');
INSERT INTO userx VALUES (48, 'Kikikiki', 'Dinng', 'kikiki', '', 'dis.', '6e017b5464f820a6c1bb5e9f6d711a667a80d8ea', 'doctor');


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

