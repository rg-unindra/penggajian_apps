-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Jun 2022 pada 08.27
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
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `id_jabatan` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `gaji_pokok` bigint(20) NOT NULL,
  `tunjangan` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`id_jabatan`, `nama`, `gaji_pokok`, `tunjangan`) VALUES
('JB1', 'Kepala Sekolah', 8000000, 1500000),
('JB2', 'Wakil Kepala Sekolah', 6000000, 1300000),
('JB3', 'Guru', 3600000, 1000000),
('JB4', 'Staff IT', 5000000, 1500000),
('JB5', 'Petugas Perpustakaan', 2000000, 500000),
('JB6', 'Tata Usaha', 3500000, 1000000);

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
('KR2', 'Risky Setiawan', 'Lenteng Agung Kota Jakarta Selatan', 'Islam', 'JB2', 960783152000, 1591935152000),
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
(5, 'KR2', 1, 1654049313997, '');

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
  `total_potongan` double NOT NULL,
  `gaji_bersih` double NOT NULL,
  `tanggal` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penggajian`
--

CREATE TABLE `penggajian` (
  `id_penggajian` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `id_user` int(11) NOT NULL,
  `total_potongan` double NOT NULL,
  `gaji_bersih` double NOT NULL,
  `tanggal` bigint(20) NOT NULL,
  `tanggal_dibuat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `session`
--

CREATE TABLE `session` (
  `id_session` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `login_time` bigint(20) NOT NULL COMMENT 'login time in epoch milliseconds time '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `session`
--

INSERT INTO `session` (`id_session`, `id_user`, `login_time`) VALUES
(13, 1, 1655092695506);

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
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

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
-- AUTO_INCREMENT untuk tabel `keterlambatan`
--
ALTER TABLE `keterlambatan`
  MODIFY `id_keterlambatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `laporan_penggajian`
--
ALTER TABLE `laporan_penggajian`
  MODIFY `id_laporan_penggajian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `penggajian`
--
ALTER TABLE `penggajian`
  MODIFY `id_penggajian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT untuk tabel `session`
--
ALTER TABLE `session`
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
