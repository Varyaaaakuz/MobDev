В проекте «MireaProject» был создан отдельный фрагмент BackgroundTaskFragment для выполнения фоновой
задачи. Выполнение задачи было реализовано с помощью применения механизма «Worker». Был создан дополнительный макет, а также добавлен фрагмент в навигацию. 
Выполнена проверка подключения к сети (пока устройство не подключено к wifi фоновая задача находится в ожидании), а также была имитирована долгая операция, то есть задача завершается спустя 10 секунд. В logcat также выведены все статусы фоновой задачи.
Результаты выполнения работы:
![image](https://github.com/user-attachments/assets/527ec710-95e3-41c8-bfef-45eb0a91ec8a) ![image](https://github.com/user-attachments/assets/0e703693-c5f1-4dde-9332-1a5da8e14598)
![image](https://github.com/user-attachments/assets/3d28c8e0-5f67-4b15-b153-afd05e51d632) ![image](https://github.com/user-attachments/assets/16a99cf6-d7b7-4531-887b-b6dcbb3928a6)
![image](https://github.com/user-attachments/assets/1209a284-687e-4a69-8963-68c75409b450)
![image](https://github.com/user-attachments/assets/6645e27c-e70a-409b-bac3-c7d52b12b44a)

