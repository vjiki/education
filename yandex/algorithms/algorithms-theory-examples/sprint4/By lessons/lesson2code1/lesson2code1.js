// Функция bucket вычисляет номер корзины для указанного фрукта
function bucket(fruit) {
    switch (fruit) {
        case "яблоко":
            return 0;
        case "слива":
            return 1;
        case "груша":
            return 2;
        default:
            throw new Error("Неизвестный фрукт");
    }
}
