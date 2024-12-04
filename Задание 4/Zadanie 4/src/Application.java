// Интерфейс графической библиотеки
interface GraphicsLibrary {
    void init();
    void clearScreen(float r, float g, float b);
    void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3);
    void swapBuffers();
}

// Конкретная реализация OpenGL
class OpenGLibrary implements GraphicsLibrary {

    @Override
    public void init() {
        System.out.println("Инициализация OpenGL");
        // Реализация инициализации OpenGL
    }

    @Override
    public void clearScreen(float r, float g, float b) {
        System.out.println("OpenGL: Очистка экрана цветом (" + r + ", " + g + ", " + b + ")");
        // Реализация очистки экрана в OpenGL
    }

    @Override
    public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        System.out.println("OpenGL: Рисование треугольника");
        // Реализация рисования треугольника в OpenGL
    }

    @Override
    public void swapBuffers() {
        System.out.println("OpenGL: Смена буферов");
        // Реализация смены буферов в OpenGL
    }
}

// Конкретная реализация DirectX (фиктивная для примера)
class DirectXLibrary implements GraphicsLibrary {

    @Override
    public void init() {
        System.out.println("Инициализация DirectX");
        // Реализация инициализации DirectX
    }

    @Override
    public void clearScreen(float r, float g, float b) {
        System.out.println("DirectX: Очистка экрана цветом (" + r + ", " + g + ", " + b + ")");
        // Реализация очистки экрана в DirectX
    }

    @Override
    public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        System.out.println("DirectX: Рисование треугольника");
        // Реализация рисования треугольника в DirectX
    }

    @Override
    public void swapBuffers() {
        System.out.println("DirectX: Смена буферов");
        // Реализация смены буферов в DirectX
    }
}

// Конкретная реализация Vulkan (фиктивная для примера)
class VulkanLibrary implements GraphicsLibrary {

    @Override
    public void init() {
        System.out.println("Инициализация Vulkan");
        // Реализация инициализации Vulkan
    }

    @Override
    public void clearScreen(float r, float g, float b) {
        System.out.println("Vulkan: Очистка экрана  цветом (" + r + ", " + g + ", " + b + ")");
        // Реализация очистки экрана в Vulkan
    }

    @Override
    public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        System.out.println("Vulkan: Рисование треугольника");
        // Реализация рисования треугольника в Vulkan
    }

    @Override
    public void swapBuffers() {
        System.out.println("Vulkan: Смена буферов");
        // Реализация смены буферов в Vulkan
    }
}

// Адаптер
class GraphicsAdapter implements GraphicsLibrary {
    private GraphicsLibrary library;

    public GraphicsAdapter(String libraryName) {
        if ("OpenGL".equalsIgnoreCase(libraryName)) {
            library = new OpenGLibrary();
        } else if ("DirectX".equalsIgnoreCase(libraryName)) {
            library = new DirectXLibrary();
        } else if ("Vulkan".equalsIgnoreCase(libraryName)) {
            library = new VulkanLibrary();
        } else {
            throw new IllegalArgumentException("Неподдерживаемая графическая библиотека: " + libraryName);
        }
    }

    // Делегирование методов к выбранной библиотеке
    @Override
    public void init() {
        library.init();
    }

    @Override
    public void clearScreen(float r, float g, float b) {
        library.clearScreen(r, g, b);
    }

    @Override
    public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        library.drawTriangle(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public void swapBuffers() {
        library.swapBuffers();
    }
}

// Пример использования
public class Application {
    public static void main(String[] args) {
        // Выбор графической библиотеки (можно сделать через конфигурацию)
        String chosenLibrary = "OpenGL"; // Или "DirectX", "Vulkan"

        GraphicsAdapter adapter = new GraphicsAdapter(chosenLibrary);

        adapter.init();
        adapter.clearScreen(1.0f, 0.0f, 0.0f); // Очистка красным цветом
        adapter.drawTriangle(0, 0, 1, 0, 0.5f, 1);
        adapter.swapBuffers();
    }
}