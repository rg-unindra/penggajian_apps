-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Jul 2022 pada 04.38
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penggajian`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `cuti`
--

CREATE TABLE `cuti` (
  `id_cuti` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `tanggal` bigint(20) NOT NULL,
  `jumlah_hari` int(2) NOT NULL,
  `keterangan` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `cuti`
--

INSERT INTO `cuti` (`id_cuti`, `id_karyawan`, `tanggal`, `jumlah_hari`, `keterangan`) VALUES
(6, 'KR4', 1656641231007, 1, ''),
(7, 'KR4', 1656641231007, 2, 'Edit 2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `id_jabatan` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `gaji_pokok` double NOT NULL,
  `tunjangan` double NOT NULL,
  `gaji_perjam` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`id_jabatan`, `nama`, `gaji_pokok`, `tunjangan`, `gaji_perjam`) VALUES
('JB1', 'Kepala Sekolah', 8000000, 1500000, 29629.62),
('JB2', 'Wakil Kepala Sekolah', 6000000, 1300000, 22222.22),
('JB3', 'Guru', 4600000, 1000000, 17037.04),
('JB4', 'Staff IT', 5000000, 1500000, 18518.51),
('JB5', 'Petugas Perpustakaan', 2000000, 500000, 7407.4),
('JB6', 'Tata Usaha', 3500000, 1000000, 12962.96),
('JB7', 'Office Boy', 5200000, 1000000, 19259.26);

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `agama` varchar(10) NOT NULL,
  `id_jabatan` varchar(50) NOT NULL,
  `tanggal_lahir` bigint(11) NOT NULL,
  `tanggal_masuk` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama`, `alamat`, `agama`, `id_jabatan`, `tanggal_lahir`, `tanggal_masuk`) VALUES
('KR1', 'Farhan Fadila', 'Limo Kota Kota Depok', 'Islam', 'JB4', 991396538762, 1655034976767),
('KR2', 'Risky Setiawan', 'Lenteng Agung Kota Jakarta Selatan', 'Islam', 'JB2', 959832752000, 1591935152000),
('KR3', 'Dian Anggara', 'Bekasi', 'Islam', 'JB1', 936271517000, 1559434454472),
('KR4', 'Muhammad Zaenudin', 'Kota Depok', 'Islam', 'JB3', 928429200000, 1624504358315);

-- --------------------------------------------------------

--
-- Struktur dari tabel `keterlambatan`
--

CREATE TABLE `keterlambatan` (
  `id_keterlambatan` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `jam` int(11) NOT NULL,
  `tanggal` bigint(20) NOT NULL,
  `keterangan` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `keterlambatan`
--

INSERT INTO `keterlambatan` (`id_keterlambatan`, `id_karyawan`, `jam`, `tanggal`, `keterangan`) VALUES
(3, 'KR1', 2, 1655086113997, 'Kendaraan bermasalah'),
(5, 'KR2', 1, 1654049313997, ''),
(6, 'KR1', 1, 1651543713997, 'Kendaraan bermasalah'),
(7, 'KR1', 1, 1657029356755, ''),
(8, 'KR4', 1, 1656608400000, 'Hujan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `laporan_penggajian`
--

CREATE TABLE `laporan_penggajian` (
  `id_laporan_penggajian` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `nama_karyawan` varchar(50) NOT NULL,
  `nama_jabatan` varchar(50) NOT NULL,
  `gaji_pokok` double NOT NULL,
  `tunjangan` double NOT NULL,
  `total_lembur` double NOT NULL,
  `total_potongan` double NOT NULL,
  `gaji_bersih` double NOT NULL,
  `tanggal` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `laporan_penggajian`
--

INSERT INTO `laporan_penggajian` (`id_laporan_penggajian`, `id_karyawan`, `nama_karyawan`, `nama_jabatan`, `gaji_pokok`, `tunjangan`, `total_lembur`, `total_potongan`, `gaji_bersih`, `tanggal`) VALUES
(49, 'KR3', 'Dian Anggara', 'Kepala Sekolah', 8000000, 1500000, 29629.63, 0, 9529629.63, 1657247549368),
(50, 'KR1', 'Farhan Fadila', 'Staff IT', 5000000, 1500000, 37037.04, 18518.52, 6518518.5200000005, 1657247549375),
(51, 'KR4', 'Muhammad Zaenudin', 'Guru', 4600000, 1000000, 34074.07, 17037.04, 5617037.03, 1657247549378),
(52, 'KR2', 'Risky Setiawan', 'Wakil Kepala Sekolah', 6000000, 1300000, 22222.22, 0, 7322222.22, 1657247549380);

-- --------------------------------------------------------

--
-- Struktur dari tabel `lembur`
--

CREATE TABLE `lembur` (
  `id_lembur` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `jam` int(11) NOT NULL,
  `tanggal` bigint(20) NOT NULL,
  `keterangan` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `lembur`
--

INSERT INTO `lembur` (`id_lembur`, `id_karyawan`, `jam`, `tanggal`, `keterangan`) VALUES
(1, 'KR1', 1, 1656867600000, ''),
(2, 'KR2', 1, 1656867600000, ''),
(3, 'KR1', 1, 1657029304157, 'Benerin PC'),
(4, 'KR3', 1, 1657040400000, ''),
(5, 'KR4', 2, 1656694800000, '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penggajian`
--

CREATE TABLE `penggajian` (
  `id_penggajian` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `id_user` int(11) NOT NULL,
  `total_lembur` double NOT NULL,
  `total_potongan` double NOT NULL,
  `gaji_bersih` double NOT NULL,
  `tanggal` bigint(20) NOT NULL,
  `tanggal_dibuat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penggajian`
--

INSERT INTO `penggajian` (`id_penggajian`, `id_karyawan`, `id_user`, `total_lembur`, `total_potongan`, `gaji_bersih`, `tanggal`, `tanggal_dibuat`) VALUES
(61, 'KR3', 1, 29629.63, 0, 9529629.63, 1656608400000, 1657247549368),
(62, 'KR1', 1, 37037.04, 18518.52, 6518518.5200000005, 1656608400000, 1657247549375),
(63, 'KR4', 1, 34074.07, 17037.04, 5617037.03, 1656608400000, 1657247549378),
(64, 'KR2', 1, 22222.22, 0, 7322222.22, 1656608400000, 1657247549380);

-- --------------------------------------------------------

--
-- Struktur dari tabel `session`
--

CREATE TABLE `session` (
  `id_session` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `login_time` bigint(20) NOT NULL COMMENT 'login time in epoch milliseconds time '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`) VALUES
(1, 'admin', 'admin'),
(2, 'admin2', 'admin'),
(3, 'admin4', 'admin3');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `cuti`
--
ALTER TABLE `cuti`
  ADD PRIMARY KEY (`id_cuti`);

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id_jabatan`);

--
-- Indeks untuk tabel `keterlambatan`
--
ALTER TABLE `keterlambatan`
  ADD PRIMARY KEY (`id_keterlambatan`);

--
-- Indeks untuk tabel `laporan_penggajian`
--
ALTER TABLE `laporan_penggajian`
  ADD PRIMARY KEY (`id_laporan_penggajian`);

--
-- Indeks untuk tabel `lembur`
--
ALTER TABLE `lembur`
  ADD PRIMARY KEY (`id_lembur`);

--
-- Indeks untuk tabel `penggajian`
--
ALTER TABLE `penggajian`
  ADD PRIMARY KEY (`id_penggajian`);

--
-- Indeks untuk tabel `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id_session`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `cuti`
--
ALTER TABLE `cuti`
  MODIFY `id_cuti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `keterlambatan`
--
ALTER TABLE `keterlambatan`
  MODIFY `id_keterlambatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `laporan_penggajian`
--
ALTER TABLE `laporan_penggajian`
  MODIFY `id_laporan_penggajian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT untuk tabel `lembur`
--
ALTER TABLE `lembur`
  MODIFY `id_lembur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `penggajian`
--
ALTER TABLE `penggajian`
  MODIFY `id_penggajian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT untuk tabel `session`
--
ALTER TABLE `session`
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
