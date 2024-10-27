package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineSesudah {

    // Refactoring 2: Mengganti magic number dengan konstanta
    public static final int HARGA_COKLAT = 10000;
    public static final int HARGA_AIR_MINERAL = 5000;
    public static final int HARGA_SODA = 15000;
    public static final int HARGA_CHIPS = 12000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Produk> produk = inisialisasiProduk();

        // Refactoring 1: Memanggil metode untuk menampilkan daftar produk
        tampilkanProduk(produk);

        // Pilih operasi produk menggunakan switch-case (Refactoring 4)
        pilihOperasi(produk, scanner);

        scanner.close();
    }

    // Refactoring 1: Memisahkan inisialisasi produk ke metode terpisah
    public static Map<String, Produk> inisialisasiProduk() {
        Map<String, Produk> produk = new HashMap<>();
        produk.put("Coklat", new Produk("Coklat", HARGA_COKLAT));
        produk.put("Air Mineral", new Produk("Air Mineral", HARGA_AIR_MINERAL));
        produk.put("Soda", new Produk("Soda", HARGA_SODA));
        produk.put("Chips", new Produk("Chips", HARGA_CHIPS));
        return produk;
    }

    // Refactoring 1: Memisahkan tampilan produk ke metode terpisah
    public static void tampilkanProduk(Map<String, Produk> produk) {
        System.out.println("=== Daftar Produk Vending Machine ===");
        for (Map.Entry<String, Produk> entry : produk.entrySet()) {
            System.out.println(entry.getKey() + ": Rp" + entry.getValue().getHarga());
        }
    }

    // Refactoring 4: Mengganti if-else dengan switch-case
    public static void pilihOperasi(Map<String, Produk> produk, Scanner scanner) {
        System.out.println("Pilih operasi pada produk: ");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Hapus Produk");
        System.out.println("3. Perbarui Harga Produk");
        System.out.print("Masukkan pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();  // Bersihkan buffer input

        switch (pilihan) {
            case 1:
                tambahProduk(produk, scanner);
                break;
            case 2:
                hapusProduk(produk, scanner);
                break;
            case 3:
                perbaruiHargaProduk(produk, scanner);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    // Refactoring 1: Memisahkan penambahan produk ke metode terpisah
    public static void tambahProduk(Map<String, Produk> produk, Scanner scanner) {
        System.out.print("Masukkan nama produk baru: ");
        String namaBaru = scanner.nextLine();
        System.out.print("Masukkan harga produk baru: ");
        int hargaBaru = scanner.nextInt();
        scanner.nextLine(); // Bersihkan buffer
        produk.put(namaBaru, new Produk(namaBaru, hargaBaru));
        System.out.println("Produk " + namaBaru + " dengan harga Rp" + hargaBaru + " telah ditambahkan.");
    }

    // Refactoring 1: Memisahkan penghapusan produk ke metode terpisah
    public static void hapusProduk(Map<String, Produk> produk, Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String namaHapus = scanner.nextLine();
        if (produk.containsKey(namaHapus)) {
            produk.remove(namaHapus);
            System.out.println("Produk " + namaHapus + " telah dihapus.");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    // Refactoring 1: Memisahkan pembaruan harga produk ke metode terpisah
    public static void perbaruiHargaProduk(Map<String, Produk> produk, Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin diperbarui: ");
        String namaUpdate = scanner.nextLine();
        if (produk.containsKey(namaUpdate)) {
            System.out.print("Masukkan harga baru: ");
            int hargaUpdate = scanner.nextInt();
            scanner.nextLine();
            produk.get(namaUpdate).setHarga(hargaUpdate);
            System.out.println("Harga produk " + namaUpdate + " telah diperbarui menjadi Rp" + hargaUpdate);
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }
}
