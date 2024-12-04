import java.util.ArrayList;
import java.util.List;

public class MusicComposer {

    public static void main(String[] args) {
        // Создаем фабрику инструментов
        InstrumentFactory instrumentFactory = new InstrumentFactory();

        // Создаем композицию
        Composition composition = new Composition();

        // Добавляем гитару
        Instrument guitar = instrumentFactory.createInstrument("Guitar");
        guitar.setNotes("E4-B3-G3-D4-A4-E4");
        composition.addInstrument(guitar);

        // Добавляем фортепиано
        Instrument piano = instrumentFactory.createInstrument("Piano");
        piano.setNotes("C4-E4-G4-C5-G4-E4-C4");
        composition.addInstrument(piano);

        // Добавляем скрипку
        Instrument violin = instrumentFactory.createInstrument("Violin");
        violin.setNotes("A4-E5-A5-E5-A5");
        composition.addInstrument(violin);

        // Выводим композицию
        System.out.println(composition);
    }
}

// Абстрактный класс инструмента
abstract class Instrument {
    String type;
    String notes;

    public Instrument(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return type + ": " + notes;
    }
}

// Класс гитары
class Guitar extends Instrument {
    public Guitar() {
        super("Guitar");
    }
}

// Класс фортепиано
class Piano extends Instrument {
    public Piano() {
        super("Piano");
    }
}

// Класс скрипки
class Violin extends Instrument {
    public Violin() {
        super("Violin");
    }
}

// Фабрика инструментов
class InstrumentFactory {
    public Instrument createInstrument(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        switch (type) {
            case "Guitar":
                return new Guitar();
            case "Piano":
                return new Piano();
            case "Violin":
                return new Violin();
            default:
                throw new IllegalArgumentException("Unknown instrument type: " + type);
        }
    }
}

// Класс композиции
class Composition {
    List<Instrument> instruments = new ArrayList<>();

    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Instrument instrument : instruments) {
            sb.append(instrument).append("\n");
        }
        return sb.toString();
    }
}