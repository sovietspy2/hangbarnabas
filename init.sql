CREATE TABLE klub (
    klub_id SERIAL,
    klub_nev text NOT NULL,
    ev integer not null,
    orszag text not null,
    megye text not null,
    varos text not null,
    CONSTRAINT klub_pk PRIMARY KEY (klub_id)
);

CREATE TABLE jatekos (
	klub_id integer REFERENCES klub (klub_id),
	jatekos_id SERIAL,
   jatekos_nev text NOT NULL,
   szkezdete text not null,
   szvege text not null,
   jatekengedelyszam numeric,
   fizetes numeric not null,
   poszt text,
    CONSTRAINT jatekos_pk PRIMARY KEY (jatekos_id)
);