/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     29/11/2023 10:37:10                          */
/*==============================================================*/


/*==============================================================*/
/* Table: AKUN                                                  */
/*==============================================================*/
create table AKUN (
   USERNAME             VARCHAR(20)          not null,
   PASSWORD             VARCHAR(20)          null,
   constraint PK_AKUN primary key (USERNAME)
);

/*==============================================================*/
/* Index: AKUN_PK                                               */
/*==============================================================*/
create unique index AKUN_PK on AKUN (
USERNAME
);

/*==============================================================*/
/* Table: ANGGOTA                                               */
/*==============================================================*/
create table ANGGOTA (
   ID_ANGGOTA           VARCHAR(5)           not null,
   NAMA_ANGGOTA         VARCHAR(30)          null,
   ANGKATAN             VARCHAR(4)           null,
   ALAMAT               VARCHAR(30)          null,
   constraint PK_ANGGOTA primary key (ID_ANGGOTA)
);

/*==============================================================*/
/* Index: ANGGOTA_PK                                            */
/*==============================================================*/
create unique index ANGGOTA_PK on ANGGOTA (
ID_ANGGOTA
);

/*==============================================================*/
/* Table: BUKU                                                  */
/*==============================================================*/
create table BUKU (
   ISBN                 VARCHAR(5)           not null,
   JUDUL_BUKU           VARCHAR(30)          null,
   TAHUN_TERBIT         VARCHAR(4)           null,
   PENERBIT             VARCHAR(30)          null,
   KATEGORI		VARCHAR(30)	     null,
   constraint PK_BUKU primary key (ISBN)
);

/*==============================================================*/
/* Index: BUKU_PK                                               */
/*==============================================================*/
create unique index BUKU_PK on BUKU (
ISBN
);


/*==============================================================*/
/* Table: SKRIPSI                                               */
/*==============================================================*/
create table SKRIPSI (
   NIM                  VARCHAR(5)           not null,
   JUDUL_SKRIPSI        VARCHAR(30)          null,
   TAHUN_SKRIPSI        VARCHAR(4)           null,
   constraint PK_SKRIPSI primary key (NIM)
);

/*==============================================================*/
/* Index: SKRIPSI_PK                                            */
/*==============================================================*/
create unique index SKRIPSI_PK on SKRIPSI (
NIM
);

/*==============================================================*/
/* Table: PEMINJAMAN                                            */
/*==============================================================*/
CREATE TABLE public.peminjaman(
    id_peminjaman character varying(5) NOT NULL,
    id_peminjam character varying(5),
    nama_peminjam character varying(30),
    angkatan character varying(4),
    id_buku character varying(5),
    id_skripsi character varying(5),
    tanggal_pinjam date,
    tanggal_kembali date,
    jumlah int,
    kategori character varying(30),
    PRIMARY KEY (id_peminjaman),
    CONSTRAINT id_peminjam FOREIGN KEY (id_peminjam)
        REFERENCES public.anggota (id_anggota) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT id_buku FOREIGN KEY (id_buku)
        REFERENCES public.buku (isbn) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT id_skripsi FOREIGN KEY (id_skripsi)
        REFERENCES public.skripsi (nim) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.peminjaman
    OWNER to postgres;
