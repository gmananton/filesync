
### Утилита для синхронизации файлов фото между директориями

#### Запуск:
java -jar app.jar<br>
-SD, --source-dir <source_dir> (or -SF, --source-file)<br> 
-TD, --target-dir <target_dir> <br>
-SE, --source-extension <source_extension> <br> 
-TE, --target-extension <target_extension> <br>

Пример:
`java -jar filesync-1.0.jar -SD my_src -TD my_target -SE jpg -TE cr2`


1 вариант
- В source_dir должны лежать файлы с расширением source_extension

2 вариант 
- Через source-file надо указать файл с перечнем файлов как будто из source-папки. Составить такой файл из исходной папки можно командой 

`ls -l dir_name > file.txt` в Linux 

`dir dir_name > file.txt` в Windows

- В target_dir должны лежать файлы с расширением target_extension
- Если в source были отсортированы и удалены фото, то соотвествующие файлы будут также удалены из target-папки
