
### Утилита для синхронизации файлов фото между директориями

#### Запуск:
java -jar  filesync-1.0.jar <source_dir> <target_dir>

- В source_dir лежат jpg-файлы
- В target_dir - CR2-файлы
- Если в source были отсортированы и удалены фото, то соотвествующие CR2-файлы будут также удалены из target-папки
