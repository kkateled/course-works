package mycats;

import java.sql.*;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class MyCats {
    private static final String[] types = new String[]{"Абиссинская кошка", "Австралийский мист", "Американская жесткошерстная",
            "Американская короткошерстная", "Американский бобтейл", "Американский кёрл", "Балинезийская кошка",
            "Бенгальская кошка", "Бирманская кошка", "Бомбейская кошка", "Бразильская короткошёрстная",
            "Британская длинношерстная", "Британская короткошерстная", "Бурманская кошка", "Бурмилла кошка",
            "Гавана", "Гималайская кошка", "Девон-рекс", "Донской сфинкс", "Европейская короткошерстная",
            "Египетская мау", "Канадский сфинкс", "Кимрик", "Корат", "Корниш-рекс", "Курильский бобтейл",
            "Лаперм", "Манчкин", "Мейн-ку́н", "Меконгский бобтейл", "Мэнкс кошка", "Наполеон", "Немецкий рекс",
            "Нибелунг", "Норвежская лесная кошка", "Ориентальная кошка", "Оцикет", "Персидская кошка",
            "Петерболд", "Пиксибоб", "Рагамаффин", "Русская голубая кошка", "Рэгдолл", "Саванна", "Селкирк-рекс",
            "Сиамская кошка", "Сибирская кошка", "Сингапурская кошка", "Скоттиш-фолд", "Сноу-шу", "Сомалийская кошка",
            "Тайская кошка", "Тойгер", "Тонкинская кошка", "Турецкая ангорская кошка", "Турецкий ван",
            "Украинский левкой", "Чаузи", "Шартрез", "Экзотическая короткошерстная", "Японский бобтейл"
    };
    private static final String[] names = {"Гарфилд","Том","Гудвин","Рокки","Ленивец","Пушок","Спорти","Бегемот","Пират","Гудини",
            "Зорро","Саймон","Альбус","Базилио","Леопольд","Нарцисс","Атос","Каспер","Валлито","Оксфорд","Бисквит",
            "Соня","Клеопатра","Цунами","Забияка","Матильда","Кнопка","Масяня","Царапка","Серсея","Ворсинка",
            "Амели","Наоми","Маркиза","Изольда","Вальс","Несквик","Златан","Баскет","Изюм","Цукат","Мокко","Месси",
            "Кокос","Адидас","Бейлиз","Тайгер","Зефир","Мохи","Валенсия","Баунти","Свити","Текила","Ириска",
            "Карамель","Виски","Кукуруза","Гренка","Фасолька","Льдинка","Китана","Офелия","Дайкири","Брусника",
            "Аватар","Космос","Призрак","Изумруд","Плинтус","Яндекс","Крисмас","Метеор","Оптимус","Смайлик",
            "Цельсий","Краска","Дейзи","Пенка","Веста","Астра","Эйприл","Среда","Бусинка","Гайка","Елка","Золушка",
            "Мята","Радость","Сиам","Оскар","Феликс","Гарри","Байрон","Чарли","Симба","Тао","Абу","Ватсон","Енисей",
            "Измир","Кайзер","Васаби","Байкал","Багира","Айрис","Диана","Мими","Сакура","Индия","Тиффани",
            "Скарлетт","Пикси","Лиззи","Алиса","Лило","Ямайка","Пэрис","Мальта","Аляска"};
    private static final Random random = new Random();

    public static void add_all_types() {
             for (String type : types) {
                 DatabaseManagerMyCats.insert_type(type);
             }
    }

    public static void add_more_cats(int n) throws SQLException{
        for (int i = 0; i < n; i += 1) {
            String name =  names[random.nextInt(names.length)];
            String type = types[random.nextInt(types.length)];
            int age = 1 + (int) (Math.random() * 20);
            double weight = 2 + (double) (Math.random() * 20);
            BigDecimal bd = new BigDecimal(weight);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double rounded = bd.doubleValue();
            DatabaseManagerMyCats.insert_cat(name, type, age, rounded);
        }
    }

    public static void main(String[] args) {
        try{
            DatabaseManagerMyCats.connect();
//            DatabaseManagerMyCats.create_tables();
//            DatabaseManagerMyCats.insert_type("Абиссинская кошка");
//            DatabaseManagerMyCats.insert_type("Австралийский мист");
//            DatabaseManagerMyCats.insert_type("Американская жесткошерстная");
//            add_all_types();
//            DatabaseManagerMyCats.delete_type(65);
//            DatabaseManagerMyCats.update_type(66, "Новая порода");
//            DatabaseManagerMyCats.get_type(66);
//            DatabaseManagerMyCats.get_type_where("id > 80");
//            DatabaseManagerMyCats.get_type_where("type LIKE '%а'");
//            DatabaseManagerMyCats.get_all_types();
//            DatabaseManagerMyCats.insert_cat("клепа", "самый новый вид", 3, 15.3);
//            DatabaseManagerMyCats.insert_cat("клепа", "новейший вид", 3, 15.3);
//            DatabaseManagerMyCats.insert_cat("дако", "Египетская мау", 1, 9.6);
//            DatabaseManagerMyCats.insert_cat("маркиз", "Корат", 2, 4.6);
            add_more_cats(5000);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error SQL !");
        }
    }
}