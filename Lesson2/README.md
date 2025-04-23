Для начала был создан новый проект "Lesson2". Далее создан первый модуль «ActivityLifecycle». 
![image](https://github.com/user-attachments/assets/639faba7-54c1-4beb-86a4-1e4b28bbbf9f)
Были переопределены основные методы жизненного цикла родительского класса, а именно onStart(), onRestoreInstanceState(Bundle savedInstanceState), onRestart(), onPause(), onStop(), onSaveInstanceState(Bundle savedInstanceState), onResume(), onDestroy().
Метод onCreate() был переопределен изначально. Было реализовано отображение состояния «activity» используя класс «Log». 
![image](https://github.com/user-attachments/assets/c92acce2-c98e-4275-bf26-e1f6d0a8e7e0)
Далее было добавлено в разметку «activity_main.xml» поле текстового ввода
«EditText».
![image](https://github.com/user-attachments/assets/aa2c9853-5a76-43a0-9b7a-26cecb59fb4a)
Далее был осуществлен запуск проекта и изучены сообщения в окне «logcat». Сначала были внесены изменения в поле ввода текста, а затем был сделан выход на «Главный экран» (кнопка «Home»).
![3](https://github.com/user-attachments/assets/3b2e7f73-1151-4188-92d8-206024c51193)
Вопросы:
1. Будет ли вызван метод «onCreate» после нажатия на кнопку «Home» и возврата
в приложение?
2. Изменится ли значение поля «EditText» после нажатия на кнопку «Home» и
возврата в приложение?
3. Изменится ли значение поля «EditText» после нажатия на кнопку «Back» и
возврата в приложение?

Ответы:
1. Нет, onCreate вызывается только при первом создании Activity или после её полного уничтожения (например, системой для освобождения памяти). При нажатии Home активность переходит в фоновый режим, и при возврате срабатывают onRestart(), onStart() и onResume().
2. Нет, введённый текст останется без изменений. Поскольку Activity не уничтожается, её состояние (включая содержимое EditText) сохраняется в памяти.
3. Да, при нажатии кнопки Back значение в EditText не сохранится, так как Activity полностью завершается (onDestroy()), и все UI-состояния, включая введённый текст, сбрасываются. При повторном запуске приложения создаётся новая Activity с пустым EditText.

Следующим шагом был создан новый модуль «MultiActivity». В разметке activity_main.xml была добавлена кнопка и реализован обработчик нажатия на кнопку.
Была создана активность «SecondActivity», была добавлена запись в манифестфайл о новом компоненте приложения.
В файл разметки «activity_second.xml», расположенный в ресурсах, был добавлен «TextView» и инициализирован вызов данного «activity» из «MainActivity». 
В первую «activity» добавлено поле ввода и кнопка «Отправить». На второй «activity» отображено значение поля первой активности в «TextView».
![image](https://github.com/user-attachments/assets/d4490df6-3bd3-438f-8044-906e7559d40a)
![image](https://github.com/user-attachments/assets/d9a71a46-38c8-4856-b933-f1c7a645e645)
Были переопределены основные методы жизненного цикла у обеих «Activity». Изучен жизненный цикл каждой из активностей.
![image](https://github.com/user-attachments/assets/1e5bbed6-2560-4bf4-ac20-22c5470dc789)
![image](https://github.com/user-attachments/assets/5049b935-04d1-4b75-8eaa-54633653ffcf)
Был создан новый модуль «IntentFilter». В разметку «activity_main.xml» была добавлена одна кнопка, а также обработчик нажатия для вызова веб-браузера.
Далее была добавлена дополнительная кнопка для передачи ФИО студента и университета в
другое приложение.
![image](https://github.com/user-attachments/assets/5b03828a-fad7-4787-bf4e-a03ffa87938d)
![image](https://github.com/user-attachments/assets/7edfac81-5599-4dae-a450-c567b1897232)
![image](https://github.com/user-attachments/assets/19b757ca-3e28-49f9-a4ca-f4186d5b476b)
Был создан новый модуль «ToastApp». Добавлено поле ввода и кнопка.
Был реализован подсчет количества символов в поле ввода, а также было реализовано отображение сообщения «Toast». 
![image](https://github.com/user-attachments/assets/1b385475-95f4-40b4-8ef3-babc5e2dc98e)
Для создания всплывающего уведомления необходимо было инициализировать
экземпляр класса «Toast» при помощи метода «makeText», а затем вызвать метод
«show» для отображения сообщения на экране.
![image](https://github.com/user-attachments/assets/12c695b4-d265-4218-89e9-d3a474b6c6db)
Был создан новый модуль «NotificationApp». В файле разметки activity_main.xml была добавлена button, которой присвоено значение android:onClick="onClickNewMessageNotification"
![image](https://github.com/user-attachments/assets/aef790dd-8cb2-4704-830d-81d57922f800)
![image](https://github.com/user-attachments/assets/bbc659b5-1b42-439c-83b4-73abd7063f63)
![image](https://github.com/user-attachments/assets/e3325563-701f-4a03-8875-fdc9c739e1e2)
![image](https://github.com/user-attachments/assets/1246c06c-40b4-41a6-a592-811145b465ee)
![image](https://github.com/user-attachments/assets/2b017a61-f778-419f-b73b-fb6d9d3cc5a5)
![image](https://github.com/user-attachments/assets/248a9bde-e80a-49c1-9711-d5db2d4446f7)
![image](https://github.com/user-attachments/assets/4862f4f6-952a-4ef8-a94e-984aa6b74e5b)


