import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskNotificationSystem {

    public static void main(String[] args) {
        // Создаем систему управления проектами
        ProjectManagementSystem system = new ProjectManagementSystem();

        // Создаем пользователей
        User user1 = new User("Иван");
        User user2 = new User("Мария");

        // Пользователи подписываются на уведомления о новых задачах
        system.subscribe("Новая задача", user1);
        system.subscribe("Новая задача", user2);

        // Создаем новую задачу
        Task task = new Task("Разработать модуль А", "Описание задачи");

        // Назначаем задачу пользователю
        system.assignTask(task, user1);
    }
}

// Класс пользователя
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void notify(String message) {
        System.out.println("Уведомление для " + name + ": " + message);
    }
}

// Класс задачи
class Task {
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

// Класс системы управления проектами
class ProjectManagementSystem {
    // Хранилище подписок на уведомления
    private Map<String, List<User>> subscribers = new HashMap<>();

    public void subscribe(String eventType, User user) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(user);
    }

    public void unsubscribe(String eventType, User user) {
        List<User> users = subscribers.get(eventType);
        if (users != null) {
            users.remove(user);
        }
    }

    // Метод назначения задачи
    public void assignTask(Task task, User user) {
        // Логика назначения задачи

        // Отправка уведомлений
        notifySubscribers("Новая задача", "Назначена новая задача: " + task.getTitle() + " - " + user.getName());
    }

    // Метод отправки уведомлений
    private void notifySubscribers(String eventType, String message) {
        List<User> users = subscribers.get(eventType);
        if (users != null) {
            for (User user : users) {
                user.notify(message);
            }
        }
    }
}