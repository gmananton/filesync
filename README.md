
### Утилита для синхронизации файлов фото между директориями

#### Запуск:
java -jar  filesync-1.0.jar <source_dir> <target_dir> <source_extension> <target_extension>

- В source_dir должны лежать файлы с расширением source_extension
- В target_dir должны лежать файлы с расширением target_extension
- Если в source были отсортированы и удалены фото, то соотвествующие CR2-файлы будут также удалены из target-папки
