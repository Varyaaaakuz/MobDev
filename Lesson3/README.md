Для начала был создан новый проект Lesson 3, а также новый модуль IntentApp. Далее создали вторую активность SecondActivity.
![image](https://github.com/user-attachments/assets/a2371b15-3ec1-43fe-999e-ef5f1a61e5ff)
Была осуществлена проверка записи о новой активности в manifest-файле.
![image](https://github.com/user-attachments/assets/2472a36d-c7a8-42d8-9478-d119651e6577)
Необходимо было получить в первой активности системное время, а далее требуется передать время из одной активности в другую и отобразить во второй activity в «textView» квадрат номера в группе и текущее время. Результаты работы представлены ниже:
![image](https://github.com/user-attachments/assets/73012ae1-5281-4713-b8ae-b5811f6f0776)
![image](https://github.com/user-attachments/assets/128c7ee0-1728-451e-a23d-f766dd843479)
Далее был создан новый модуль Sharer.
![image](https://github.com/user-attachments/assets/72b41449-0789-435d-9b60-8cb08f4a38e1)
Для того, чтобы разрабатываемая активность имела возможность обрабатывать подобные намерения, требовалось указать дополнительные значения в манифест-файле:
Тэг «action» сообщает ОС «Android» о том, что активность может обрабатывать действие «ACTION_SEND». Фильтр «category» должен включать категорию «DEFAULT», в противном случае он не сможет получать неявные намерения. В поле «mimeType» указываются типы данных, которые могут обрабатываться активностью.
![image](https://github.com/user-attachments/assets/8059142f-92a2-4c3a-83a5-b4690e02e93c)
Результатом работы является вызов диалогового окна выбора приложения для обработки намерения после запуска самого приложения.
![image](https://github.com/user-attachments/assets/eb5a8a07-4aeb-4c52-ac11-ca2bf9fd420b)
В новом модуле FavoriteBook было создано приложение с двумя экранами. Основное предназначение приложения заключается в отображении на экране названия любимой книги разработчика и пользователя приложением с использованием двух активностей. 
На первом экране поле отображения «TextView» имеет несколько состояний. Первое значение: «Тут появится название вашей любимой книги и любимая цитата из нее!». Второе значение: «Название Вашей любимой книги: КНИГА. Цитата: ЦИТАТА».
Для открытия второй активности предназначена кнопка  «Открыть экран ввода данных».
![image](https://github.com/user-attachments/assets/f7923893-70be-4007-802a-9f6c86d6f322)
На втором экране: 
- поле отображения «TextView» – «Любимая книга разработчика»;
- поле отображения «TextView» – «Цитата из книги»;
- поле ввода «EditText», со значением свойства «Hint»: «Введите название
Вашей любимой книги»;
- поле ввода «EditText», со значением свойства «Hint»: «Введите цитату из
Вашей любимой книги»;
- кнопка «Button» предназначена для отправки введенных данных
пользователя на первый экран.
![image](https://github.com/user-attachments/assets/3c88e880-2ddd-4383-855f-6ad52ee5c68e)
Конечный результат первого экрана:
![image](https://github.com/user-attachments/assets/989a04d3-1356-4cac-b66a-9a79101cff75)
Следующим заданием было создание модуля SystemIntentsApp, в котором отображается страница веб-ресурса, координаты на карте, окно набора номера.
Был оформлен экран activity_main.xml, на экране размещены три кнопки (обработка нажатий реализована через setOnClickListener). В классе MainActivity реализованы 3 метода, реагирующие на нажатие кнопок.
![image](https://github.com/user-attachments/assets/3a386b06-41c7-43d6-aaf2-3f30eacf7e39)
![image](https://github.com/user-attachments/assets/e2d564dd-6a93-4ae9-a7e9-ced419493f7a)
![image](https://github.com/user-attachments/assets/5c7967d4-c8d0-4e5b-ab49-113b06b06277)
![image](https://github.com/user-attachments/assets/405d56af-b00f-491d-810b-0f335cce525a)
![image](https://github.com/user-attachments/assets/2fdac63b-d46c-4957-a102-ada9341813ce)
![image](https://github.com/user-attachments/assets/90c83157-68d0-4720-a07f-d7545a35e863)
![image](https://github.com/user-attachments/assets/0bdca6b9-907f-4a61-a78a-d1efa886ceee)
![image](https://github.com/user-attachments/assets/eeaf7e96-bb27-4e4d-b132-59be9bb41343)
![image](https://github.com/user-attachments/assets/cedd7b03-05e9-4c28-8494-dff99f0910c8)
![image](https://github.com/user-attachments/assets/09a97197-ce68-49e7-815a-533b0ffb2b31)
![image](https://github.com/user-attachments/assets/34b11a66-17d6-4a55-890b-53016b820251)
![image](https://github.com/user-attachments/assets/89d5cae9-21eb-48ac-8042-4a5567b241c6)



