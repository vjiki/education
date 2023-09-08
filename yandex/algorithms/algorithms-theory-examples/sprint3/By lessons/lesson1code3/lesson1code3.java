public class Matryoshka {
    public static void buildMatryoshka(int size, int n) {
        if (n >= 1) {
            System.out.println("Создаём низ матрёшки размера " + size + ".");
            buildMatryoshka(size - 1, n - 1);
            System.out.println("Создаём верх матрешки размера " + size + ".");
        } else {
            return;
        }
    }

    public static void main(String[] args) {
        buildMatryoshka(4, 3);
    }
}

# Получим вывод:
Создаём низ матрёшки размера 4.
Создаём низ матрёшки размера 3.
Создаём низ матрёшки размера 2.
Создаём верх матрёшки размера 2.
Создаём верх матрёшки размера 3.
Создаём верх матрёшки размера 4.