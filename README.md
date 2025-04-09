Для начала был установлен Android Studio, все настройки были выбраны по умолчанию.

![image](https://github.com/user-attachments/assets/4a7d6a21-f02e-46c7-aa5a-6578479bdf82)

Согласно инструкции необходимо было выбрать и установить нужные версии Android SDK.

![image](https://github.com/user-attachments/assets/0f12860a-fcb0-4ddc-a01d-d25e8a6e7c89)

Далее во вкладке SDK Tools был установлен различный инструментарий обеспечения процесса разработки мобильных приложений.

![image](https://github.com/user-attachments/assets/e206aee6-256b-4d47-897f-7abcd4b8f78c)

Был создан новый проект требуемого шаблона и определены параметры данного проекта.

![image](https://github.com/user-attachments/assets/bb87772b-5c32-4773-852e-8c8e9c598c1d)

Был создан в проекте новый модуль. Процедура
частично похожа на создание проекта. Для создания модуля необходимо было в меню выбрать «File -> New -> New module».

![image](https://github.com/user-attachments/assets/b8c4edbb-5bee-4ae1-874a-4647c96cbef1)

Для тестирования приложение был использован эмулятор Android устройства, встроенного в Android Studio. На представленном экране производится создание устройства Pixel 9. Далее
был скачан образ операционной системы «VanillaIceCream».

![image](https://github.com/user-attachments/assets/b0fc5583-8803-4a41-aa9a-b7bafb1ad644) ![image](https://github.com/user-attachments/assets/b86128a2-5d77-4d8e-9528-0f787be7c3e5)

Далее было запущено соданное устройство, которое отобразилось в менеджере виртуальных устройств после создания эмулятора.

![image](https://github.com/user-attachments/assets/2c24d2b5-aa99-446a-a563-3f712deab277)

Был создан новый «Layout resource file» с именем «linear_layout.xml». В один «LinearLayout» были вложены два других, внутри них были  размещены элементы «button». Значение ориентации корневого «LinearLayout» устанавливается вертикальной, а двух вложенных макетов – горизонтальной.

![image](https://github.com/user-attachments/assets/3d0d06ff-6c48-4448-becf-03d704d78de7)

Далее был создан файл разметки «table_layout.xml» с корневым элементом «TableLayout» и добавлены в корневой
элемент три «TableRow»-строки. У каждого вложенного элемента имеется атрибут «layout_weight», назначающий весовой коэффициент представления.

![image](https://github.com/user-attachments/assets/8a7ef95a-ec47-4cdb-90a8-d3b66126737c)

Следующим этапом необходимо было добавить на экран несколько элементов (textviev, button) и привязать их между собой.

![image](https://github.com/user-attachments/assets/457b46c2-2329-4aee-85b4-18639ca90f2f)

Был создан новый модуль «control_lesson1».Была открыта разметка «activity_main.xml» (res>layout), из меню palette были перенесены
следующие элементы и изучены их свойства (область «attributes»):
- Text>textView, plainText (editText);
- Buttons>button, imageButton, checkBox;
- Widgets>imageview.
Были добавлены на экран несколько элементов и привязаны между собой.

![image](https://github.com/user-attachments/assets/013c16f9-64b9-4e0e-aa5d-d46b27ac206c)

Поскольку существует два режима отображения приложения на мобильном устройстве (портретный и альбомный), для обеспечения возможности поворота экрана на эмуляторе
необходимо было убедиться, что в меню установлено значение «Auto-rotate».

![image](https://github.com/user-attachments/assets/4978d5e9-08ae-4a46-b313-6f6c238e1571)

Для того чтобы при повороте все кнопки были в пределах видимости, необходимо было сформировать разметку для
другой ориентации. В качестве
родительского контейнера был использован «TableLayout». С его помощью кнопки
были разбиты на две колонки и помещены в три ряда.
Было вызвано контекстное меню у директории «res» и выбрана команда «New>>Layout Resource File». В списке «Available
qualifiers» расположен элемент «Orientation», который перенесли в правую часть
«Chosen qualifiers». По умолчанию значение
поля «Directory name» изменилось на «layout-port». Т.к. основной целью является
поддержка горизонтальной ориентации в выпадающем списке «Screen orientation»
был выбран «Landscape».

![image](https://github.com/user-attachments/assets/fb8078a5-6606-47a8-a7fd-57744001da3d) ![image](https://github.com/user-attachments/assets/73868d80-b1cd-4269-a2f0-f484d3fd2a7e)

Заключительным шагом необходимо было создать новый модуль, а именно в меню «File>New>New Module>Phone & Tablet
Module>Empty Activity» проект «ButtonClicker». По нажатию кнопки меняется содержимое «TextView».
По нажатию кнопки «WhoAmI» – выводится текст: «Мой номер по списку № 16 », по нажатию « ItIsNotMe» – «Это не я».

![image](https://github.com/user-attachments/assets/36fcd87d-b8db-42f0-a991-89a90d13cdee) ![image](https://github.com/user-attachments/assets/3fe05662-0ba5-499a-a140-47e73e844d37) ![image](https://github.com/user-attachments/assets/8bf1ded6-4adc-4f21-9702-fc402462f0db)

MainActivity инициализирует UI-элементы через findViewById() и назначает обработчики: клик по btnWhoAmI изменяет текст, а onMyButtonClick(), вызываемый из XML, переключает CheckBox и текст. ViewCompat.setOnApplyWindowInsetsListener() корректирует отступы под системные бары, а EdgeToEdge.enable(this) включает полноэкранный режим. Код демонстрирует два подхода к обработке событий: программный и декларативный, управляя состоянием виджетов методами setText() и setChecked().

```
package com.mirea.kuzminavv.buttonclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.mirea.kuzminavv.buttonclicker.R;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textViewStudent = findViewById(R.id.textViewStudent);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        checkBox = findViewById(R.id.checkBox);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvOut), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку № 16");
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }

    public void onMyButtonClick(View view) {
        boolean isChecked = !checkBox.isChecked();
        checkBox.setChecked(isChecked);

        if (isChecked) {
            textViewStudent.setText("Это не я");
        } else {
            textViewStudent.setText("Мой номер по списку № 16");
        }

    }
}
```
**Выводы** 

В ходе данной работы было изучено создание простых Android-приложений: верстка экранов в XML, привязка кнопок и других элементов к коду через `findViewById()`, а также обработка нажатия разными способами (через `setOnClickListener`, через атрибут `onClick` в XML). Была изучена базовая логика Android-разработки: от создания проекта до запуска рабочего приложения с интерактивными элементами.











