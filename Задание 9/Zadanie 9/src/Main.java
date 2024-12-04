public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            // Действие, выполняемое над общим ресурсом
            System.out.println(Thread.currentThread().getName() +
                    " работает с ресурсом...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Получение единственного экземпляра контроллера
        ResourceAccessController controller = ResourceAccessController.getInstance();

        // Запуск потоков, использующих один и тот же контроллер
        new Thread(() -> controller.accessResource(task)).start();
        new Thread(() -> controller.accessResource(task)).start();
        new Thread(() -> controller.accessResource(task)).start();
    }
}
