public class Main {
    int boxes = scanner.nextInt();
    int newConteiner = 1;
    int box;
    int b = 1;
    int truck = 1;
    int containers = boxes / MAX_NUMBER_OF_BOXES + (boxes % MAX_NUMBER_OF_BOXES == 0 ? 0:1);
    truck = containers / MAX_CONTAINERS + (containers % MAX_CONTAINERS == 0 ? 0:1);

        if(boxes == 0) {
        System.out.println("Необходимо:" + System.lineSeparator() + "грузовиков - " + 0 + " шт."
                + System.lineSeparator() + "контейнеров - " + 0 + " шт.");
    }


        System.out.println("Грузовик: " + truck);
        for (int container = 1; container <= containers; container++) {
        System.out.println("\tКонтейнер: " + container);
        for (box = newConteiner; box <= boxes; box++) {
            System.out.println("\t\tЯщик: " + box);
            if (box / MAX_NUMBER_OF_BOXES == b) {
                b++;
                break;
            }
        }
        newConteiner = ++box;

    }

            System.out.println("Необходимо:" + System.lineSeparator() + "грузовиков - " + truck + " шт."
            + System.lineSeparator() + "контейнеров - " + containers + " шт.");
}
