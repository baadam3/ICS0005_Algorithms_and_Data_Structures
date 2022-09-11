
public class Sheep {

    enum Animal {sheep, goat}

    public static void main(String[] param) {
        // for debugging

    }

    public static void reorder(Animal[] animals) {
        // TODO!!! Your program here

        int fromFront = 0;
        int fromBack = animals.length - 1;
        Animal helper = Animal.goat;
        while (fromFront < fromBack) {
            if (animals[fromFront] == Animal.sheep) {
                helper = animals[fromFront];
                if (animals[fromBack] == Animal.goat) {
                    animals[fromFront] = animals[fromBack];
                    animals[fromBack] = helper;
                }
            }
            while (animals[fromBack] != Animal.goat && fromBack != 0) {
                fromBack--;
            }

            while (animals[fromFront] != Animal.sheep && fromFront != animals.length - 1) {
                fromFront++;
            }
        }
    }
}