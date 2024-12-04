import java.util.HashMap;
import java.util.Map;

public class SmartHome {

    public static void main(String[] args) {
        DeviceFactory factory = new DeviceFactory();

        Device fridge = factory.createDevice("Refrigerator", "Samsung", "CoolMaster 3000");
        Device washingMachine = factory.createDevice("WashingMachine", "LG", "WashWarrior 5000");
        Device microwave = factory.createDevice("Microwave", "Panasonic", "HeatWave 2000");

        // Использование устройств (пример)
        if (fridge instanceof Refrigerator) {
            ((Refrigerator) fridge).setTemperature(4);
        }
        if (washingMachine instanceof WashingMachine) {
            ((WashingMachine) washingMachine).selectProgram("Cotton");
        }
        if (microwave instanceof Microwave) {
            ((Microwave) microwave).setTime(2, 30);
            ((Microwave) microwave).start();
        }
    }
}
// Абстрактный класс для устройств
abstract class Device {
    String type;
    String brand;
    String model;
    boolean isOn;

    public Device(String type, String brand, String model) {
        this.type = type;
        this.brand = brand;
        this.model = model;
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public void displayInfo() {
        System.out.println("Тип: " + type);
        System.out.println("Бренд: " + brand);
        System.out.println("Модель: " + model);
        System.out.println("Состояние: " + (isOn ? "Вкл." : "Выкл."));
    }
}
// Абстрактная фабрика для устройств
abstract class DeviceAbstractFactory {
    public abstract Device createDevice(String brand, String model);
}

// Конкретные фабрики для каждого типа устройств
class RefrigeratorFactory extends DeviceAbstractFactory {
    @Override
    public Device createDevice(String brand, String model) {
        return new Refrigerator(brand, model);
    }
}

class WashingMachineFactory extends DeviceAbstractFactory {
    @Override
    public Device createDevice(String brand, String model) {
        return new WashingMachine(brand, model);
    }
}

class MicrowaveFactory extends DeviceAbstractFactory {
    @Override
    public Device createDevice(String brand, String model) {
        return new Microwave(brand, model);
    }
}

// Фабрика устройств
class DeviceFactory {
    private Map<String, DeviceAbstractFactory> factories = new HashMap<>();

    public DeviceFactory() {
        factories.put("Refrigerator", new RefrigeratorFactory());
        factories.put("WashingMachine", new WashingMachineFactory());
        factories.put("Microwave", new MicrowaveFactory());
    }

    public Device createDevice(String type, String brand, String model) {
        DeviceAbstractFactory factory = factories.get(type);
        if (factory != null) {
            Device device = factory.createDevice(brand, model);
            device.displayInfo(); // Вывод информации о созданном устройстве
            return device;
        } else {
            throw new IllegalArgumentException("Неизвестный тип устройства: " + type);
        }
    }
}

// Холодильник
class Refrigerator extends Device {
    int temperature;

    public Refrigerator(String brand, String model) {
        super("Refrigerator", brand, model);
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Холодильник включен");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Холодильник выключен");
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Температура установлена на " + temperature + "°C");
    }
}

// Стиральная машина
class WashingMachine extends Device {
    String program;

    public WashingMachine(String brand, String model) {
        super("WashingMachine", brand, model);
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Стиральная машина включена");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Стиральная машина выключена");
    }

    public void selectProgram(String program) {
        this.program = program;
        System.out.println("Выбрана программа стирки: " + program);
    }
}

// Микроволновая печь
class Microwave extends Device {
    int timeMinutes;
    int timeSeconds;

    public Microwave(String brand, String model) {
        super("Microwave", brand, model);
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Микроволновая печь включена");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Микроволновая печь выключена");
    }

    public void setTime(int minutes, int seconds) {
        this.timeMinutes = minutes;
        this.timeSeconds = seconds;
        System.out.println("Время установлено на " + minutes
                + " мин. " + seconds + " сек.");
    }

    public void start() {

        System.out.println("Нагрев начался");

    }
}