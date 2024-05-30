package com.example.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String selectedTopic = "";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        final LinearLayout football = findViewById(R.id.footballLayout);
        final LinearLayout countries = findViewById(R.id.countriesLayout);
        final LinearLayout maths = findViewById(R.id.mathsLayout);
        final LinearLayout facts = findViewById(R.id.factsLayout);
        final Button startQuizBtn = findViewById(R.id.startQuizBtn);

        football.setOnClickListener(v -> {
            selectedTopic = "football";
            football.setBackgroundResource(R.drawable.round_back_white_stroke10);
            countries.setBackgroundResource(R.drawable.round_back_white10);
            maths.setBackgroundResource(R.drawable.round_back_white10);
            facts.setBackgroundResource(R.drawable.round_back_white10);
        });

        countries.setOnClickListener(v -> {
            selectedTopic = "countries";
            countries.setBackgroundResource(R.drawable.round_back_white_stroke10);
            football.setBackgroundResource(R.drawable.round_back_white10);
            maths.setBackgroundResource(R.drawable.round_back_white10);
            facts.setBackgroundResource(R.drawable.round_back_white10);
        });

        maths.setOnClickListener(v -> {
            selectedTopic = "maths";
            maths.setBackgroundResource(R.drawable.round_back_white_stroke10);
            football.setBackgroundResource(R.drawable.round_back_white10);
            countries.setBackgroundResource(R.drawable.round_back_white10);
            facts.setBackgroundResource(R.drawable.round_back_white10);
        });

        facts.setOnClickListener(v -> {
            selectedTopic = "facts";
            facts.setBackgroundResource(R.drawable.round_back_white_stroke10);
            football.setBackgroundResource(R.drawable.round_back_white10);
            countries.setBackgroundResource(R.drawable.round_back_white10);
            maths.setBackgroundResource(R.drawable.round_back_white10);
        });

        startQuizBtn.setOnClickListener(v -> {
            if (selectedTopic.isEmpty()) {
                Toast.makeText(MainActivity.this, "Выберите Викторину", Toast.LENGTH_SHORT).show();
            } else {
                loadQuestionAndAnswers(selectedTopic);

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("selectedTopic", selectedTopic);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestionAndAnswers(String selectedTopic) {
        switch (selectedTopic) {
            case "countries":
                addCountriesQuestionsToFirestore();
                break;
            case "football":
                addFootballQuestionsToFirestore();
                break;
            case "math":
                addMathQuestionsToFirestore();
                break;
            case "facts":
                addFactsQuestionsToFirestore();
                break;
            default:
        }
    }

    private void addCountriesQuestionsToFirestore() {
        final QuestionsList question1 = new QuestionsList("Какая страна является самой маленькой по площади среди независимых государств мира?",
                "Науру", "Мальта", "США", "Ватикан",
                "Ватикан", "");

        final QuestionsList question2 = new QuestionsList("Какая страна является крупнейшим экспортером нефти в мире?",
                "Россия", "Саудовская Аравия", "США", "Иран",
                "Саудовская Аравия", "");

        final QuestionsList question3 = new QuestionsList("Какая страна является крупнейшим производителем кофе в мире?",
                "Бразилия", "Колумбия", "Эфиопия", "Вьетнам",
                "Бразилия", "");

        final QuestionsList question4 = new QuestionsList("Какая страна имеет самую длинную границу с водой в мире?",
                "Канада", "Россия", "Индонезия", "Австралия",
                "Россия", "");

        final QuestionsList question5 = new QuestionsList("Какая страна является родиной самого высокого горного массива в мире, Гималаев?",
                "Китай", "Непал", "Индия", "Бутан",
                "Непал", "");
        final QuestionsList question6 = new QuestionsList("В какой стране находится самый большой по площади национальный парк в мире, Восточно-Сибирский?",
                "Канада", "Россия", "Австралия", " США",
                "Россия", "");

        final QuestionsList question7 = new QuestionsList("Какая страна является самой населенной в мире?",
                " Китай", "Индия", "США", " Индонезия",
                " Китай", "");

        final QuestionsList question8 = new QuestionsList("В какой стране находится самое глубокое озеро в мире, Байкал?",
                "Россия", "Канада", "США", " Казахстан",
                "Россия", "");

        final QuestionsList question9 = new QuestionsList("Какая страна является крупнейшим производителем бананов в мире?\n",
                "Индия ", "Бразилия", "Эквадор", "Китай",
                "Эквадор", "");

        final QuestionsList question10 = new QuestionsList("Какая страна является самой большой по площади в мире?",
                "Китай ", "Канада", "Россия", "США",
                "Китай", "");

        final QuestionsList question11 = new QuestionsList("Которая страна является лидером по производству рыбы в мире?",
                "Индия", "Китай", "Индонезия", "Россия",
                "Китай", "");

        final QuestionsList question12 = new QuestionsList("Какая страна является крупнейшим экспортером технических услуг в мире?",
                "Германия", "Китай", "Япония", "США",
                "США", "");

        final QuestionsList question13 = new QuestionsList("Которая из перечисленных стран является крупнейшим производителем электроники в мире?",
                "США", "Китай", "Япония", "Ватикан",
                "Китай", "");

        final QuestionsList question14 = new QuestionsList("Какая страна является крупнейшим производителем биотоплива в мире?",
                "США", "Бразилия", "Индия", "Китай",
                "США", "");

        final QuestionsList question15 = new QuestionsList("Которая страна является лидером по производству крупнейших круизных лайнеров в мире?",
                "Япония", "США", "Германия", "Италия",
                "Германия", "");

        final QuestionsList question16 = new QuestionsList("Какая страна является крупнейшим экспортером шерсти в мире?",
                "Австралия", "Китай", "Индия", "Новая Зеландия",
                "Австралия", "");

        final QuestionsList question17 = new QuestionsList("Которая из перечисленных стран является крупнейшим производителем чая в мире?",
                "Индия", "Китай", "Мальта", "Кения",
                "Китай", "");

        final QuestionsList question18 = new QuestionsList("Какая страна является лидером по производству медицинского оборудования в мире?",
                "США", "Япония", "Китай", "Германия",
                "Германия", "");

        final QuestionsList question19 = new QuestionsList("Какая страна является крупнейшим экспортером морепродуктов в мире?",
                "Китай", "Норвегия", "Исландия", "США",
                "Китай", "");

        final QuestionsList question20 = new QuestionsList("Которая из перечисленных стран имеет самое большое количество активных вулканов?",
                "Япония", "Индонезия", "Италия", "Россия",
                "Индонезия", "");

        addQuestionToFirestore("countries", question1);
        addQuestionToFirestore("countries", question2);
        addQuestionToFirestore("countries", question3);
        addQuestionToFirestore("countries", question4);
        addQuestionToFirestore("countries", question5);
        addQuestionToFirestore("countries", question6);
        addQuestionToFirestore("countries", question7);
        addQuestionToFirestore("countries", question8);
        addQuestionToFirestore("countries", question9);
        addQuestionToFirestore("countries", question10);
        addQuestionToFirestore("countries", question11);
        addQuestionToFirestore("countries", question12);
        addQuestionToFirestore("countries", question13);
        addQuestionToFirestore("countries", question14);
        addQuestionToFirestore("countries", question15);
        addQuestionToFirestore("countries", question16);
        addQuestionToFirestore("countries", question17);
        addQuestionToFirestore("countries", question18);
        addQuestionToFirestore("countries", question19);
        addQuestionToFirestore("countries", question20);
    }

    private void addFootballQuestionsToFirestore() {
        final QuestionsList question1 = new QuestionsList("Какой футбольный клуб выиграл Лигу Чемпионов в сезоне 2018/2019?",
                "Real Madrid", "FC Barcelona", "Liverpool", " Manchester City",
                "Liverpool", "");

        final QuestionsList question2 = new QuestionsList("Кто является рекордсменом по количеству забитых голов на Чемпионате Мира по футболу?",
                "Lionel Messi", "Cristiano Ronaldo", "Miroslav Klose", " Pelé",
                "Miroslav Klose", "");

        final QuestionsList question3 = new QuestionsList("Какая страна выиграла Чемпионат Европы по футболу 2016 года?",
                "Германия", "Италия", "Португалия", " Франция",
                "Португалия", "");

        final QuestionsList question4 = new QuestionsList("Кто является капитаном сборной Аргентины на Чемпионате Мира по футболу 2022 года?",
                "Lionel Messi", "Sergio Agüero", "Paulo Dybala", "Angel Di Maria",
                "Lionel Messi", "");

        final QuestionsList question5 = new QuestionsList("Какой футболист завоевал Золотой мяч в 2019 году?",
                " Luka Modrić ", "Cristiano Ronaldo", "Lionel Messi", " Neymar",
                "Lionel Messi", "");

        final QuestionsList question6 = new QuestionsList("Кто является текущим тренером сборной Франции по футболу",
                "Didier Deschamps", "Zinedine Zidane", "Arsène Wenger", " Laurent Blanc",
                "Didier Deschamps", "");

        final QuestionsList question7 = new QuestionsList("Какой клуб является рекордсменом по количеству побед в чемпионатах Англии?",
                " Manchester United", "Chelsea", "Arsenal", " Liverpool",
                " Manchester United", "");

        final QuestionsList question8 = new QuestionsList("Какой год был последним, когда сборная Германии выиграла Чемпионат Мира по футболу?",
                "2006", "2010", "2014", "2018",
                "2014", "");

        final QuestionsList question9 = new QuestionsList("Кто является лучшим бомбардиром в истории Лиги Чемпионов?",
                "Lionel Messi", "Cristiano Ronaldo", "Raúl", " Karim Benzema",
                "Cristiano Ronaldo", "");

        final QuestionsList question10 = new QuestionsList("Какой клуб победил в последнем финале Кубка Англии (FA Cup)?",
                " Manchester City", "Arsenal", "Chelsea", " Manchester United",
                "Chelsea", "");

        final QuestionsList question11 = new QuestionsList("Кто является рекордсменом по количеству выигранных Кубков Голландии (KNVB-Beker)?",
                "Ajax", "PSV Eindhoven", "Feyenoord", "AZ Alkmaar",
                "Ajax", "");

        final QuestionsList question12 = new QuestionsList("Какая страна выиграла первый Чемпионат мира по футболу среди юношей (U-20)?",
                "Бразилия", "Аргентина", "Германия", "СССР",
                "Аргентина", "");

        final QuestionsList question13 = new QuestionsList("Какой игрок получил награду за лучшего вратаря на Чемпионате мира по футболу в 2018 году?",
                "Thibaut Courtois", "Keylor Navas", "Jordan Pickford", "Hugo Lloris",
                "Hugo Lloris", "");

        final QuestionsList question14 = new QuestionsList("Какой клуб выиграл Лигу чемпионов УЕФА в сезоне 2017/2018?",
                "Real Madrid", "Barcelona", "Bayern Munich", "Liverpool FC",
                "Real Madrid", "");

        final QuestionsList question15 = new QuestionsList("Кто является рекордсменом по количеству забитых голов в одном сезоне Лиги чемпионов УЕФА?",
                "Cristiano Ronaldo", "Lionel Messi", "Robert Lewandowski", "Ruud van Nistelrooy",
                "Robert Lewandowski", "");

        final QuestionsList question16 = new QuestionsList("Какой клуб является обладателем наибольшего количества Кубков Франции (Кубок Лиги)?",
                "Paris Saint-Germain", "Lyon", "AS Monaco", "Marseille",
                "Marseille", "");

        final QuestionsList question17 = new QuestionsList("Какой клуб выиграл Кубок Нидерландов (KNVB-Beker) в сезоне 2020/2021?",
                "Ajax", "PSV Eindhoven", "Feyenoord", "AZ Alkmaar",
                "Ajax", "");

        final QuestionsList question18 = new QuestionsList("Кто является текущим обладателем Кубка Конфедераций по футболу?",
                "Германия", "Чили", "Бразилия", "Португалия",
                "Бразилия", "");

        final QuestionsList question19 = new QuestionsList("Какой клуб выиграл Кубок Англии (FA Cup) наибольшее количество раз?",
                "Chelsea", "Manchester United", "Liverpool FC", "Arsenal",
                "Arsenal", "");

        final QuestionsList question20 = new QuestionsList("Какая страна выиграла первый Чемпионат мира по футболу среди девушек (U-17)?",
                "США", "Германия", "Япония", "Франция",
                "Германия", "");

        addQuestionToFirestore("football", question1);
        addQuestionToFirestore("football", question2);
        addQuestionToFirestore("football", question3);
        addQuestionToFirestore("football", question4);
        addQuestionToFirestore("football", question5);
        addQuestionToFirestore("football", question6);
        addQuestionToFirestore("football", question7);
        addQuestionToFirestore("football", question8);
        addQuestionToFirestore("football", question9);
        addQuestionToFirestore("football", question10);
        addQuestionToFirestore("football", question11);
        addQuestionToFirestore("football", question12);
        addQuestionToFirestore("football", question13);
        addQuestionToFirestore("football", question14);
        addQuestionToFirestore("football", question15);
        addQuestionToFirestore("football", question16);
        addQuestionToFirestore("football", question17);
        addQuestionToFirestore("football", question18);
        addQuestionToFirestore("football", question19);
        addQuestionToFirestore("football", question20);

    }

    private void addMathQuestionsToFirestore() {
        final QuestionsList question1 = new QuestionsList("Чему равно значение числа пи (π) с точностью до двух десятичных знаков?",
                "3.18", "3.16", "3.12", "3.14",
                "3.14", "");

        final QuestionsList question2 = new QuestionsList("Как называется операция, обратная умножению?",
                "Деление", "Сложение", "Вычитание", " Возведение в степень",
                "Деление", "");

        final QuestionsList question3 = new QuestionsList("Какое из следующих чисел является простым числом?",
                "24", "50", "37", " 64",
                "37", "");
        final QuestionsList question4 = new QuestionsList("Чему равна сумма углов треугольника?",
                "90°", "180°", "270°", "360°",
                "180°", "");

        final QuestionsList question5 = new QuestionsList("Как называется число, которое умножается само на себя?",
                " Произведение", "Сумма", "Квадрат", "Степень",
                "Квадрат", "");

        final QuestionsList question6 = new QuestionsList("Сколько сторон имеет правильный семиугольник?",
                "5", "7", "6", "8",
                "7", "");

        final QuestionsList question7 = new QuestionsList("Чему равен логарифм по основанию 10 числа 1000?",
                "1", "2", "4", "3",
                "3", "");

        final QuestionsList question8 = new QuestionsList("Как называется теорема, утверждающая, что квадрат гипотенузы прямоугольного ",
                "Теорема Ферма", "Теорема Пифагора", "Теорема Архимеда", " Теорема Эйлера",
                "Теорема Пифагора", "");

        final QuestionsList question9 = new QuestionsList("Как называется число, которое делится нацело на другое число?",
                "Простое", "Кратное", "Составное", "Целое",
                "Кратное", "");

        final QuestionsList question10 = new QuestionsList("Как называется операция, обратная извлечению квадратного корня?",
                "Возведение в степень", "Деление", "Умножение", "Возведение в квадрат",
                "Возведение в квадрат", "");

        final QuestionsList question11 = new QuestionsList("Сколько граней у куба?",
                "4", "6", "8", "12",
                "6", "");

        final QuestionsList question12 = new QuestionsList("Чему равна сумма углов треугольника?",
                "90 градусов", "270 градусов", "180 градусов", "360 градусов",
                "180 градусов", "");

        final QuestionsList question13 = new QuestionsList("Как называется число, которое делится только на 1 и на само себя?",
                " Чётное число", "Нечётное число", "Простое число", "Составное число",
                "Простое число", "");

        final QuestionsList question14 = new QuestionsList("Что такое пи в математике?",
                "Площадь квадрата", "Отношение длины окружности к её диаметру", "Периметр прямоугольника", "Объем куба",
                "Отношение длины окружности к её диаметру", "");

        final QuestionsList question15 = new QuestionsList("Какой математический символ использует букву 'i'?",
                "Натуральное число", "Комплексное число", "Рациональное число", "Дробное число",
                "Комплексное число", "");

        final QuestionsList question16 = new QuestionsList("Сколько сантиметров в одном метре?",
                "100", "10", "1000", "10000",
                "100", "");

        final QuestionsList question17 = new QuestionsList("Кто считается основателем математической логики?",
                "Архимед", "Эвклид", "Аристотель", "Джордж Буль",
                "Джордж Буль", "");

        final QuestionsList question18 = new QuestionsList("Какой из этих числовых рядов является арифметической прогрессией?",
                "2, 4, 8, 16", "3, 6, 12, 24", "5, 10, 15, 20", "1, 2, 4, 8",
                "5, 10, 15, 20", "");

        final QuestionsList question19 = new QuestionsList("Что называется гипотенузой в прямоугольном треугольнике?",
                "Сторона, противоположная прямому углу", "Сторона, прилегающая к прямому углу", "Сторона, наименьшая по длине", "Любая из двух сторон, не являющихся гипотенузой",
                "Сторона, противоположная прямому углу", "");

        final QuestionsList question20 = new QuestionsList("Чему равна площадь круга с радиусом 3 см (используйте π ≈ 3.14)?",
                "18.84 см²", "37.68 см²", "31.42 см²", "28.26 см²",
                "28.26 см²", "");



        addQuestionToFirestore("math", question1);
        addQuestionToFirestore("math", question2);
        addQuestionToFirestore("math", question3);
        addQuestionToFirestore("math", question4);
        addQuestionToFirestore("math", question5);
        addQuestionToFirestore("math", question6);
        addQuestionToFirestore("math", question7);
        addQuestionToFirestore("math", question8);
        addQuestionToFirestore("math", question9);
        addQuestionToFirestore("math", question10);
        addQuestionToFirestore("math", question11);
        addQuestionToFirestore("math", question12);
        addQuestionToFirestore("math", question13);
        addQuestionToFirestore("math", question14);
        addQuestionToFirestore("math", question15);
        addQuestionToFirestore("math", question16);
        addQuestionToFirestore("math", question17);
        addQuestionToFirestore("math", question18);
        addQuestionToFirestore("math", question19);
        addQuestionToFirestore("math", question20);
    }

    private void addFactsQuestionsToFirestore() {
        final QuestionsList question1 = new QuestionsList("Какая планета солнечной системы является самой горячей?",
                "Венера", "Марс", "Меркурий", "Юпитер",
                "Венера", "");

        final QuestionsList question2 = new QuestionsList("Какой материк является самым большим по площади?",
                "Африка", "Северная Америка", "Евразия", "Южная Америка",
                "Евразия", "");

        final QuestionsList question3 = new QuestionsList("Сколько костей взрослого человека содержит скелет?",
                "Около 150", "Около 200", "Около 250", "Около 300",
                "Около 200", "");
        final QuestionsList question4 = new QuestionsList("Какой океан является самым глубоким?",
                "Тихий", "Атлантический", "Индийский", "Южный",
                "Тихий", "");

        final QuestionsList question5 = new QuestionsList("Сколько континентов начинаются на букву 'A'?",
                "Четыре", "Два", "Три", "Один",
                "Один", "");

        final QuestionsList question6 = new QuestionsList("Какой самый крупный вид кошек?",
                "Тигр", "Ягуар", "Лев", "Пума",
                "Лев", "");

        final QuestionsList question7 = new QuestionsList("Какая самая высокая гора в Солнечной системе?",
                "Эверест", "Мауна-Кеа", "Олимп", "Вулкан Тау",
                "Олимп", "");

        final QuestionsList question8 = new QuestionsList("Сколько зубов в среднем у взрослого человека?",
                "24", "28", "36", "32",
                "32", "");

        final QuestionsList question9 = new QuestionsList("Какая планета солнечной системы имеет самую длинную орбиту?",
                "Марс", "Уран", "Сатурн", "Нептун",
                "Уран", "");

        final QuestionsList question10 = new QuestionsList("Какой металл является самым легким?",
                "Железо", "Титан", "Алюминий", "Медь",
                "Алюминий", "");

        final QuestionsList question11 = new QuestionsList("Какой континент является самым большим по площади?",
                "Европа", "Африка", "Южная Америка", "Азия",
                "Азия", "");

        final QuestionsList question12 = new QuestionsList("Какое озеро считается самым глубоким в мире?",
                "Озеро Байкал", "Озеро Виктория", "Озеро Танганьика", "Озеро Каспийское",
                "Озеро Байкал", "");

        final QuestionsList question13 = new QuestionsList("Что является наиболее распространённым газом в атмосфере Земли?",
                "Кислород", "Азот", "Углекислый газ", "Аргон",
                "Азот", "");

        final QuestionsList question14 = new QuestionsList("Какая страна имеет самое большое население?",
                "Индия", "США", "Россия", "Китай",
                "Китай", "");

        final QuestionsList question15 = new QuestionsList("Что является наиболее многочисленным животным на Земле?",
                "Люди", "Куры", "Муравьи", "Киты",
                "Муравьи", "");

        final QuestionsList question16 = new QuestionsList("Какое дерево считается самым высоким в мире?",
                "Секвойя", "Ель", "Эвкалипт", "Пальма",
                "Секвойя", "");

        final QuestionsList question17 = new QuestionsList("Какая река является самой длинной на планете?",
                "Амазонка", "Нил", "Янцзы", "Миссисипи",
                "Нил","");
        final QuestionsList question18 = new QuestionsList("Что является наиболее посещаемым городом в мире по количеству туристов?",
                "Париж", "Нью-Йорк", "Токио", "Бангкок",
                "Бангкок", "");

        final QuestionsList question19 = new QuestionsList("Какой океан самый большой по площади?",
                "Атлантический", "Тихий", "Индийский", "Северный Ледовитый",
                "Тихий", "");

        final QuestionsList question20 = new QuestionsList("Какой город считается самым старым, непрерывно населенным городом в мире?",
                "Афины", "Рим", "Каир", "Дамаск",
                "Дамаск", "");

        addQuestionToFirestore("facts", question1);
        addQuestionToFirestore("facts", question2);
        addQuestionToFirestore("facts", question3);
        addQuestionToFirestore("facts", question4);
        addQuestionToFirestore("facts", question5);
        addQuestionToFirestore("facts", question6);
        addQuestionToFirestore("facts", question7);
        addQuestionToFirestore("facts", question8);
        addQuestionToFirestore("facts", question9);
        addQuestionToFirestore("facts", question10);
        addQuestionToFirestore("facts", question11);
        addQuestionToFirestore("facts", question12);
        addQuestionToFirestore("facts", question13);
        addQuestionToFirestore("facts", question14);
        addQuestionToFirestore("facts", question15);
        addQuestionToFirestore("facts", question16);
        addQuestionToFirestore("facts", question17);
        addQuestionToFirestore("facts", question18);
        addQuestionToFirestore("facts", question19);
        addQuestionToFirestore("facts", question20);
    }

    private void addQuestionToFirestore(String topic, QuestionsList question) {
        Map<String, Object> questionData = new HashMap<>();
        questionData.put("question", question.getQuestion());
        questionData.put("option1", question.getOption1());
        questionData.put("option2", question.getOption2());
        questionData.put("option3", question.getOption3());
        questionData.put("option4", question.getOption4());
        questionData.put("correctAnswer", question.getAnswer());
        questionData.put("userSelectedAnswer", question.getUserSelectedAnswer());

        db.collection("quiz_questions").document(topic)
                .collection("questions")
                .add(questionData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Question added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding document", e);
                        //Toast.makeText(MainActivity.this, "Error adding document", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
