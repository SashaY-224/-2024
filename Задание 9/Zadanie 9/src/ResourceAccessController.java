import java.util.concurrent.Semaphore;

// Синхронизатор доступа к ресурсам (паттерн Одиночка)
public class ResourceAccessController {

    // Единственный экземпляр класса
    private static ResourceAccessController instance;

    // Семафор для управления доступом к ресурсу
    private final Semaphore semaphore = new Semaphore(1);

    // Приватный конструктор (паттерн Одиночка)
    private ResourceAccessController() {}

    // Метод для получения единственного экземпляра
    public static synchronized ResourceAccessController getInstance() {
        if (instance == null) {
            instance = new ResourceAccessController();
        }
        return instance;
    }

    // Метод для безопасного доступа к ресурсу
    public void accessResource(Runnable action) {
        try {
            // Попытка получить разрешение на доступ
            semaphore.acquire();

            System.out.println(Thread.currentThread().getName() + " получил доступ к ресурсу.");

            // Выполнение действия с общим ресурсом
            action.run();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Поток прерван: " + e.getMessage());
        } finally {
            // Освобождение разрешения
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " освободил ресурс.");
        }
    }
}
