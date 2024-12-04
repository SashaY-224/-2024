public class Room {
    private final double area;
    private final String wallColor;
    private final String furniture;

    // Приватный конструктор, доступен только через Builder
    private Room(RoomBuilder builder) {
        this.area = builder.area;
        this.wallColor = builder.wallColor;
        this.furniture = builder.furniture;
    }

    // Геттеры
    public double getArea() {
        return area;
    }

    public String getWallColor() {
        return wallColor;
    }

    public String getFurniture() {
        return furniture;
    }

    // Внутренний класс Builder
    public static class RoomBuilder {
        private double area;
        private String wallColor;
        private String furniture;

        // Сеттеры билдера (возвращают сам билдер для цепочечного вызова)
        public RoomBuilder area(double area) {
            this.area = area;
            return this;
        }

        public RoomBuilder wallColor(String wallColor) {
            this.wallColor = wallColor;
            return this;
        }

        public RoomBuilder furniture(String furniture) {
            this.furniture = furniture;
            return this;
        }

        // Метод build() создает объект Room
        public Room build() {
            return new Room(this);
        }
    }
}