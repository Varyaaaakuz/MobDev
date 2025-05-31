package ru.mirea.kuzmina.employeedb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;



import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private SuperheroDao superheroDao;
    private TextView heroesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heroesTextView = findViewById(R.id.heroesTextView);
        db = SuperheroApp.getInstance().getDatabase();
        superheroDao = db.superheroDao();
        addRussianSuperheroes();
        showAllHeroes();
    }

    private void showAllHeroes() {
        List<Superhero> allHeroes = superheroDao.getAll();
        showHeroesList(allHeroes);
    }

    private void showHeroesList(List<Superhero> heroes) {
        if (heroes.isEmpty()) {
            heroesTextView.setText("В базе данных нет супергероев");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("База данных супергероев:\n\n");

        for (Superhero hero : heroes) {
            sb.append("Имя: ").append(hero.name).append("\n")
                    .append("Псевдоним: ").append(hero.alias).append("\n")
                    .append("Команда: ").append(hero.affiliation).append("\n")
                    .append("Способности: ").append(hero.powers).append("\n")
                    .append("Уровень силы: ").append(hero.strengthLevel).append("\n\n");
        }

        heroesTextView.setText(sb.toString());
    }

    private void addRussianSuperheroes() {
        db.clearAllTables();
        Superhero superman = new Superhero();
        superman.name = "Кларк Кент";
        superman.alias = "Супермен";
        superman.powers = "Суперсила, непробиваемость";
        superman.strengthLevel = 100;
        superman.affiliation = "Лига Справедливости";

        Superhero batman = new Superhero();
        batman.name = "Брюс Уэйн";
        batman.alias = "Бэтмен";
        batman.powers = "Гениальный интеллект, различные гаджеты";
        batman.strengthLevel = 85;
        batman.affiliation = "Лига Справедливости";

        Superhero spiderman = new Superhero();
        spiderman.name = "Питер Паркер";
        spiderman.alias = "Человек-паук";
        spiderman.powers = "Сверхчеловеческая ловкость, способность прилипать к стенам";
        spiderman.strengthLevel = 75;
        spiderman.affiliation = "Мстители";

        Superhero ironman = new Superhero();
        ironman.name = "Тони Старк";
        ironman.alias = "Железный человек";
        ironman.powers = "Гениальный изобретатель, высокотехнологичные костюмы";
        ironman.strengthLevel = 90;
        ironman.affiliation = "Мстители";

        superheroDao.insert(superman);
        superheroDao.insert(batman);
        superheroDao.insert(spiderman);
        superheroDao.insert(ironman);
    }
}