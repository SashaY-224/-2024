public class Main {
    public static void main(String[] args) {
        // Создаем комнату с помощью билдера
        Room livingRoom = new Room.RoomBuilder()
                .area(25.5)
                .wallColor("White")
                .furniture("Sofa, TV, Coffee table")
                .build();

        // Создаем другую комнату с другим набором параметров
        Room bedroom = new Room.RoomBuilder()
                .area(18.0)
                .wallColor("Beige")
                .furniture("Bed, Wardrobe, Nightstand")
                .build();

        // Вывод информации о комнатах
        System.out.println("Living room: Area - " + livingRoom.getArea() +
                ", Wall color - " + livingRoom.getWallColor() +
                ", Furniture - " + livingRoom.getFurniture());

        System.out.println("Bedroom: Area - " + bedroom.getArea() +
                ", Wall color - " + bedroom.getWallColor() +
                ", Furniture - " + bedroom.getFurniture());
    }
}