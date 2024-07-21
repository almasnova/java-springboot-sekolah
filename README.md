# java-springboot-sekolah
Backend Java Spring Boot Tema Sekolah
SCHEMA DB MYSQL : test-sekolah
SWAGGER : http://localhost:7907/swagger-ui.html
LOGIN : 
{
  "nip": "10001",
  "password": "123"
}
RESPONSE LOGIN : 
{
  "message": "Sucess Login",
  "content": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODEyMzQ1IiwibmlwIjoiMTAwMDEiLCJwaG9uZSI6IjA4MTIzNDUiLCJpZCI6MSwiZXhwIjoxNzIyMDYwMTI3LCJpYXQiOjE3MjE0NTUzMjd9.dPdLa-EwD67SJQXO-EsUKiwiZfRpsrC5iJq0aMvn9Lh5y-LTKjkGHKmLbdqYtDCl-0_LMXdHRDFxnFhzf7Z1wg"
  },
  "success": true
}
INPUT PADA JWT : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODEyMzQ1IiwibmlwIjoiMTAwMDEiLCJwaG9uZSI6IjA4MTIzNDUiLCJpZCI6MSwiZXhwIjoxNzIyMDYwMTI3LCJpYXQiOjE3MjE0NTUzMjd9.dPdLa-EwD67SJQXO-EsUKiwiZfRpsrC5iJq0aMvn9Lh5y-LTKjkGHKmLbdqYtDCl-0_LMXdHRDFxnFhzf7Z1wg

PENJELASAN SISTEM SEKOLAH
1.	DATA GURU : Hanya guru dengan status master yang dapat mengupdate dan create guru lainnya
	CREATE
	UPDATE
	DELETE
	VIEW SATUAN
	VIEW PAGING
	CONTOH CREATE 
{
  "alamat": "Jakarta",
  "master": true,
  "name": "Lumi",
  "nip": "10002",
  "phone": "8123456",
  "password": "123"
}
2.	DATA SISWA : Data siswa di input oleh guru, terdiri dari histori kelas saat kelas 7, 8 dan 9 serta nilai raport pada tingkatan kelas dan semester
	CREATE
	UPDATE
	DELETE
	VIEW SATUAN
	VIEW PAGING 
	CONTOH CREATE
{
  "alamat": "Cempaka Baru",
  "kelasId": 1,
  "name": "Reyhans",
  "nis": "1230001",
  "phone": "87775765717"
}
3.	KELAS : Sebagai landasan untuk siswa dan mata pelajaran
	CREATE
	UPDATE
	DELETE
	VIEW SATUAN
	LIST KELAS
4.	PELAJARAN : Sebagai landasan untuk mata pelajaran
	CREATE
	UPDATE
	DELETE
	VIEW SATUAN
	LIST PELAJARAN
5.	MATA PELAJARAN : Menghubungkan kelas, guru dan pelajaran untuk nantinya sebagai acuan rapor siswa
	CREATE
	UPDATE
	DELETE
	VIEW SATUAN
	VIEW PAGING
6.	RAPOR SISWA : Rapor siswa di input oleh guru, saat penginputan nilai memilih tingkatan kelas dan semester 
	VIEW
	CREATE
