package lesson_5;

import java.util.List;

/**
 * 1. Создать базу данных Student с полями id, name, mark.
 * 2. Создать сущность Student и добавить в нее аннотации. Поле id должно заполняться
 * автоматически.
 * 3. Создать конфигурационный файл hibernate.cfg.xml, в котором указать свойства для
 * подключения к БД и список классов с аннотациями Entity.
 * 4. Создать класс со статическим методом, который возвращает объект SessionFactory.
 * 5. Создать DAO-слой с операциями добавления, удаления, изменения сущности, выборки записи
 * по ID и всех записей.
 * 6. Добавить 1000 записей в таблицу Student.
 * 7. Проверить работоспособность приложения, выполнить методы по удалению, изменению,
 * добавлению записей, а также выборки одной и всех записей.
 */

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        for (int i = 0; i < 100; i++) {
            String name = "name" + i;
            String mark = "mark" + i;
            Student student = new Student(name, mark);

            service.persist(student);
        }

        Student student = service.findById(538L);
        student.setName("updated name");
        service.update(student);

        Student updatedStudent = service.findById(538L);
        System.out.println(updatedStudent);

        service.delete(538L);
        Student deletedStudent = service.findById(538L);
        System.out.println(deletedStudent);

        List<Student> all = service.findAll();
        System.out.println(all.size());
    }
}
