// Абстрактный класс Транспорт
abstract class Транспорт {
    private boolean движется;

    // Шаблонный метод движения
    public final void движение() {
        if (!движется) {
            запуск();
            переключениеПередачи();
            набратьСкорость();
            движется = true;
            System.out.println(this.getType() + " начал движение.");
        } else {
            System.out.println(this.getType() + " уже движется.");
        }
    }

    // Шаблонный метод остановки
    public final void остановка() {
        if (движется) {
            снизитьСкорость();
            переключениеПередачи();
            затормозить();
            движется = false;
            System.out.println(this.getType() + " остановился.");
        } else {
            System.out.println(this.getType() + " уже остановлен.");
        }
    }

    // Абстрактные методы, которые нужно реализовать в подклассах
    protected abstract void запуск();
    protected abstract void переключениеПередачи();
    protected abstract void набратьСкорость();
    protected abstract void снизитьСкорость();
    protected abstract void затормозить();
    public abstract String getType();
}

// Класс Автомобиль
class Автомобиль extends Транспорт {
    @Override
    protected void запуск() {
        System.out.println("Заводим автомобиль.");
    }

    @Override
    protected void переключениеПередачи() {
        System.out.println("Переключаем передачу автомобиля.");
    }

    @Override
    protected void набратьСкорость() {
        System.out.println("Автомобиль набирает скорость.");
    }

    @Override
    protected void снизитьСкорость() {
        System.out.println("Автомобиль снижает скорость.");
    }

    @Override
    protected void затормозить() {
        System.out.println("Автомобиль тормозит.");
    }

    @Override
    public String getType() {
        return "Автомобиль";
    }
}

// Класс Велосипед
class Велосипед extends Транспорт {
    @Override
    protected void запуск() {
        System.out.println("Садимся на велосипед.");
    }

    @Override
    protected void переключениеПередачи() {
        System.out.println("Переключаем передачу велосипеда.");
    }

    @Override
    protected void набратьСкорость() {
        System.out.println("Велосипед набирает скорость.");
    }

    @Override
    protected void снизитьСкорость() {
        System.out.println("Велосипед снижает скорость.");
    }

    @Override
    protected void затормозить() {
        System.out.println("Велосипед тормозит.");
    }

    @Override
    public String getType() {
        return "Велосипед";
    }
}

// Класс Поезд
class Поезд extends Транспорт {
    @Override
    protected void запуск() {
        System.out.println("Поезд трогается.");
    }

    @Override
    protected void переключениеПередачи() {
        System.out.println("Переключение не требуется.");
    }

    @Override
    protected void набратьСкорость() {
        System.out.println("Поезд набирает скорость.");
    }

    @Override
    protected void снизитьСкорость() {
        System.out.println("Поезд снижает скорость.");
    }

    @Override
    protected void затормозить() {
        System.out.println("Поезд тормозит.");
    }

    @Override
    public String getType() {
        return "Поезд";
    }
}

public class Main {
    public static void main(String[] args) {
        Автомобиль автомобиль = new Автомобиль();
        Велосипед велосипед = new Велосипед();
        Поезд поезд = new Поезд();

        автомобиль.движение();
        автомобиль.движение();
        автомобиль.остановка();
        автомобиль.остановка();
        System.out.println("--------------------");

        велосипед.движение();
        велосипед.остановка();
        System.out.println("--------------------");

        поезд.движение();
        поезд.остановка();
    }
}